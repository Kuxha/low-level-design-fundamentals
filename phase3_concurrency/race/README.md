# Race Conditions & The Java Memory Model

## 1. The Core Concepts

To understand why bugs happen in concurrency, you must understand the underlying machinery.

### Process vs. Thread
* **Process (The House):** An instance of a running program (e.g., the JVM). It has its own isolated memory (RAM).
* **Thread (The Person):** A worker inside the process. Threads share the same heap memory (Kitchen). If two threads grab the same variable (Knife) at the same time, accidents happen.

### The OS Scheduler & Hardware
* **Java Threads = Native Threads:** In modern Java, every `new Thread()` maps 1:1 to a Kernel Operating System thread (Lightweight Process).
* **True Parallelism:** On a multi-core CPU (like an AMD Ryzen 7), the OS can run Thread A on **Core 0** and Thread B on **Core 1** at the exact same nanosecond.
* **Time Slicing:** On a single core, the OS simulates parallelism by rapidly pausing (Context Switching) threads every few milliseconds.

---

## 2. The Bug: The Race Condition

**Scenario:** Two threads try to increment a shared counter (`balance++`) 10,000 times each.
**Expected:** 20,000.
**Actual:** Random values (e.g., 16,318).

### The "Atomicity" Lie
We look at `balance++` and see **one line of code**.
The CPU sees **three distinct instructions** (Read-Modify-Write):
1.  **LOAD:** Read value from RAM to CPU Register.
2.  **ADD:** Add 1 to the Register.
3.  **STORE:** Write Register value back to RAM.

### The Crash (The Nanosecond Timeline)
This is exactly what happens inside the hardware when the bug occurs:

1.  **Nanosecond 0:** **Core 0** asks RAM: "What is `balance`?" -> RAM says "10".
2.  **Nanosecond 0:** **Core 1** asks RAM: "What is `balance`?" -> RAM says "10". *(Both now hold the stale value).*
3.  **Nanosecond 1:** **Core 0** calculates `10 + 1 = 11`.
4.  **Nanosecond 1:** **Core 1** calculates `10 + 1 = 11`.
5.  **Nanosecond 2:** **Core 0** writes `11` to RAM.
6.  **Nanosecond 3:** **Core 1** writes `11` to RAM.

**Result:** Two additions occurred, but the memory only incremented by 1. The work of Core 0 was "overwritten" or lost.

---

## 3. The Fix: Synchronization

To fix this, we must make the operation **Atomic**. We need a way to lock the door so only one thread can enter the logic at a time.

### The Keyword: `synchronized`
    public synchronized void addMoney() {
        balance++;
    }

### How it Works (The Monitor Lock)
1.  **The Lock (Mutex):** Every Java object has an internal "Monitor" or lock.
2.  **Acquire:** When Thread A calls `addMoney()`, it grabs the lock.
3.  **Block:** If Thread B tries to call it, it sees the lock is taken. The OS moves Thread B to a **Blocked State** (Waiting Queue). Thread B consumes 0% CPU while waiting.
4.  **Release:** When Thread A finishes, it releases the lock. The OS wakes up Thread B.

---

## 4. Deep Dive: Glossary & Internals

### `Thread` vs `Runnable`
* **Resource (The Vault):** The object holding the data (e.g., `BankAccount`). It should **not** implement Runnable.
* **Runnable (The Job):** A set of instructions (`void run()`) defining *what* needs to be done.
* **Thread (The Worker):** The engine that executes the Job.

### `start()` vs `run()`
* **`t.run()`:** This is a trap. It just calls the method on the **current thread** (Main). No multitasking happens.
* **`t.start()`:** This is the magic.
    1.  The JVM calls a **JNI (Java Native Interface)** function.
    2.  This calls the OS Kernel (System Call, e.g., `clone()` on Linux).
    3.  The Kernel creates a new Stack in memory and registers a new LWP (Lightweight Process).
    4.  The OS Scheduler eventually picks this thread to run on a CPU Core.

### Why C++ is involved?
The JVM is written in C++. The Operating System (Linux/Windows) APIs are written in C. Java uses **JNI** as a bridge to "speak" to the Kernel to request these hardware resources.

---

## 5. Code Example

### The Vulnerable Class (Non-Atomic)
    class BankAccount {
        int balance = 0;
        // NOT SAFE!
        public void addMoney() {
            balance++; 
        }
    }

### The Thread-Safe Class (Atomic)
    class BankAccount {
        int balance = 0;
        // SAFE: Only one thread can enter at a time.
        public synchronized void addMoney() {
            balance++;
        }
    }
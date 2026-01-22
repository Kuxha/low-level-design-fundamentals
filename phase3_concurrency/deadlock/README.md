# Deadlocks & Synchronization Syntax

## 1. Definition
A **Deadlock** occurs when two or more threads are blocked forever, waiting for each other.

**The Classic Scenario (The Deadly Embrace):**
* **Thread A** holds Lock 1 and wants Lock 2.
* **Thread B** holds Lock 2 and wants Lock 1.
* Neither can proceed. Neither can release. The app freezes.

---

## 2. Syntax Breakdown (The "Weird" Stuff)

### A. The Lambda: `() -> { ... }`
This is a shortcut introduced in Java 8.

    // "Create a thread, and run this block of code."
    Thread t = new Thread(() -> {
        System.out.println("I am running!");
    });

* **Before Java 8:** You had to write `new Runnable() { public void run() { ... } }`.
* **Meaning:** It defines the "Job" (Runnable) inline without creating a separate class file.

### B. The Lock Object: `new Object()`
    public static final Object lock1 = new Object();

* **What is it?** A plain, empty Java object.
* **Why?** In synchronization, we don't care about the object's data. We only care about its **Monitor (Key)**. We use `new Object()` as a cheap "Token" or "Flag" to lock.
* **Why `static`?** It ensures there is only **one** instance of this lock in the entire app. Both threads must look at the exact same object for locking to work.

### C. The Synchronized Block: `synchronized(obj)`
    synchronized (lock1) {
        // Critical Section
    }

* **The Action:**
    1.  **Check:** Does anyone else have the key for `lock1`?
    2.  **Block:** If yes, **SLEEP**. Do not execute a single line more. Wait until the key is returned.
    3.  **Acquire:** If no, take the key.
    4.  **Execute:** Run the code inside the block.
    5.  **Release:** Return the key when the block ends `}`.

---

## 3. The Diagnosis (Why it Froze)

In the broken code:
1.  **T1** entered `synchronized(lock1)`. It held Key 1.
2.  **T2** entered `synchronized(lock2)`. It held Key 2.
3.  **T1** tried to enter `synchronized(lock2)`.
    * JVM: "Key 2 is missing (held by T2). Sleep." -> **T1 Blocked.**
4.  **T2** tried to enter `synchronized(lock1)`.
    * JVM: "Key 1 is missing (held by T1). Sleep." -> **T2 Blocked.**
5.  **Result:** Circular Dependency.

---

## 4. The Fix: Lock Ordering

**The Rule:** All threads must acquire locks in the **exact same global order**.

* **Broken:**
    * T1: A -> B
    * T2: B -> A
* **Fixed:**
    * T1: A -> B
    * T2: A -> B

**Why it works:**
If T1 has Lock A, and T2 wants Lock A, T2 is blocked **immediately at the gate**. It never gets the chance to grab Lock B and cause a problem. It simply waits for T1 to finish everything.

## 5. Summary Glossary

| Concept | Explanation |
| :--- | :--- |
| **Race Condition** | Two threads write to memory at the same time. Data is lost. Fix: `synchronized`. |
| **Deadlock** | Two threads hold resources the other wants. App freezes. Fix: **Lock Ordering**. |
| **Mutex** | "Mutual Exclusion." The technical name for the lock created by `synchronized`. |
| **Context Switch** | The OS pausing one thread to run another. This pause is what causes Race Conditions if not handled safely. |
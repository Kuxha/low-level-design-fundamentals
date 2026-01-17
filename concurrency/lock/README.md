# ReentrantLock: The "Smart" Mutex

## 1. The Concept
The `synchronized` keyword is a "blunt instrument"—if a thread cannot get the lock, it waits forever. This causes application freezes.

**ReentrantLock** is a class that gives us control. It allows us to:
1.  **Time Out:** "Try to get the lock for 2 seconds, then give up."
2.  **Poll:** Check if a lock is free without waiting.
3.  **Unlock Manually:** Unlock in a `finally` block to ensure safety.

## 2. The Execution Flow (The "Shopper" Scenario)

### Step 1: The Setup
* We create a `static ReentrantLock inventoryLock`.
* This object acts as the single "Key" to the store.

### Step 2: The Attempt (`tryLock`)
* **Shopper 1** calls `inventoryLock.tryLock(2, SECONDS)`.
* The lock is free. Shopper 1 takes it and returns `true`.
* Shopper 1 enters the `if (acquired)` block and starts a 3-second sleep (simulating work).

### Step 3: The Conflict
* **Shopper 2** calls `inventoryLock.tryLock(2, SECONDS)`.
* The lock is TAKEN.
* Shopper 2 enters a **Timed Wait**. It does not block forever; it watches the clock.

### Step 4: The Timeout (The Bailout)
* 2 Seconds pass. Shopper 1 is still working (3s total).
* Shopper 2's timer expires.
* `tryLock` returns `false`.
* Shopper 2 executes the `else` block: "COULD NOT acquire lock."
* **Result:** The thread is freed to do other work instead of freezing.

## 3. Critical Syntax: The `finally` Block
Unlike `synchronized`, `ReentrantLock` does not unlock automatically if an error occurs. You **MUST** use this pattern:

    boolean acquired = lock.tryLock(2, TimeUnit.SECONDS);
    if (acquired) {
        try {
            // Critical Section
        } finally {
            // ALWAYS unlock here.
            // If you forget this, the lock stays taken forever (Deadlock).
            lock.unlock();
        }
    }
# Thread Pools: The "Manager" Pattern

## 1. The Concept
Creating a thread (`new Thread()`) is expensive. It costs ~1MB of RAM and takes time for the OS to register.
If 10,000 requests hit your server and you create 10,000 threads, the server runs out of memory (`OutOfMemoryError`) and crashes.

**ExecutorService** solves this by reusing a fixed number of threads (The Pool) to handle an infinite number of tasks (The Queue).

## 2. The Execution Flow (The "Wave" Effect)

### Step 1: Initialization
* `Executors.newFixedThreadPool(2)`
* Java creates exactly **2 Worker Threads** (`pool-1-thread-1`, `pool-1-thread-2`).
* These threads enter an infinite loop: "Is there work in the queue?"

### Step 2: Submission
* We submit **5 Tasks** using `executor.submit()`.

### Step 3: Processing (Wave 1)
* **Worker 1** grabs Task 1.
* **Worker 2** grabs Task 2.
* **The Queue:** Tasks 3, 4, and 5 are placed in a `LinkedBlockingQueue`. They are **Blocked**.

### Step 4: Recycling (Wave 2)
* Worker 1 finishes Task 1.
* It does **not** die. It immediately asks the Queue for the next job.
* Worker 1 grabs Task 3.
* **Result:** The Thread ID remains the same, but the task changes.

## 3. Why L4 Engineers Use This
1.  **Protection:** It caps your CPU/RAM usage. If the pool size is 100, you will never have more than 100 active threads, even if 1,000,000 users are online.
2.  **Efficiency:** It skips the overhead of creating/destroying OS threads.
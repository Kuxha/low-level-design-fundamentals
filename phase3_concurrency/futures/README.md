# CompletableFuture: The "Async Pipeline"

## 1. The Concept
Standard threads (`Runnable`) return `void`. You can't get data back easily.
**CompletableFuture** allows you to:
1.  Run a task in the background and return a value (`supplyAsync`).
2.  Chain tasks together ("Do A, then B").
3.  Run tasks in parallel and combine results ("Do A and B, then merge").

## 2. The Execution Flow (The Dashboard)

### Step 1: The Fork (Non-Blocking)
* `CompletableFuture.supplyAsync(() -> fetchUser())`
* The Main Thread throws this task into the global `ForkJoinPool`.
* The Main Thread **does not stop**. It immediately moves to the next line.
* `CompletableFuture.supplyAsync(() -> fetchOrders())`
* The Main Thread throws the second task into the pool.

### Step 2: The Parallel Run
* **Worker 1** is running `fetchUser()` (1 second).
* **Worker 2** is running `fetchOrders()` (2 seconds).
* These happen **at the same time**.

### Step 3: The Recipe (`thenCombine`)
* `userFuture.thenCombine(ordersFuture, (u, o) -> u + o)`
* This line defines *what to do* when both are finished. It registers a callback. It still does not block.

### Step 4: The Join (Blocking)
* `dashboardFuture.join()`
* **NOW** the Main Thread waits.
* It waits for the *slowest* task to finish (2 seconds).
* Once both are done, the lambda merges the strings, and the result is returned.

## 3. The Math
* **Sequential Code:** 1s + 2s = 3s.
* **CompletableFuture:** MAX(1s, 2s) = 2s.
* **Impact:** On a dashboard with 10 widgets, this is the difference between loading in 1 second vs 10 seconds.
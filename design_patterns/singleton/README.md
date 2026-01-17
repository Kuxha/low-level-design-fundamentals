# Singleton Pattern

## 1. Definition
> "Ensure a class has only one instance and provide a global point of access to it."

## 2. The Use Case: "The Highlander Rule"
There can be only one.
* **Database Connections:** You don't want 500 connections opening and closing. You want one shared connection pool.
* **Logger:** You want all logs to go to the same file, managed by the same object.
* **Config Manager:** You only want to read `application.properties` once.

## 3. The Implementation Rules
1.  **Private Constructor:** `private DatabaseConnection() {}`. Prevents anyone from typing `new DatabaseConnection()`.
2.  **Private Static Instance:** Holds the single copy.
3.  **Public Static Method:** `getInstance()`. The only way to get the object.

## 4. Thread Safety (The Senior Interview Question)
* **The Problem:** In a multi-threaded app, if two threads call `getInstance()` at the exact same millisecond, they might both create a new object.
* **The Fix (Basic):** Add `synchronized` to the method.
    `public static synchronized DatabaseConnection getInstance()`
* **The Fix (Advanced):** "Double-Checked Locking" (We will cover this in the Concurrency Phase).

## 5. Why Singletons are Controversial
* **Global State:** They act like global variables, which can make testing difficult (hard to mock).
* **Hidden Dependencies:** If a class uses a Singleton, it's not obvious in the constructor.
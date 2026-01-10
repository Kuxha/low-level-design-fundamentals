# Single Responsibility Principle (SRP)

## 📌 Definition
> "A class should have one, and only one, reason to change."

## 🆚 Bad vs. Good Code
### ❌ Bad Code (The "God Object")
A single `Employee` class that:
1. Stores data (`id`, `name`).
2. Calculates salary (Business Logic).
3. Saves to the database (Persistence).
* **Problem:** If you change the database from MySQL to Mongo, you risk breaking the salary calculation code because they live in the same file.

### ✅ Good Code (The "Toolbox")
We split the responsibilities:
1. **`Employee`:** Just the data.
2. **`SalaryCalculator`:** Just the math.
3. **`EmployeeRepository`:** Just the database connection.
* **Benefit:** You can change the database without ever touching the salary logic.

## 🌍 Real-World Use Case
**Backend API Design (Controller vs. Service):**
* **Controller:** Handles HTTP requests and parsing JSON.
* **Service:** Handles the actual business logic (e.g., "create order").
* **Repository:** SQL queries.
* **Why:** If you put SQL queries in your API Controller, you can't reuse that logic for a background cron job.
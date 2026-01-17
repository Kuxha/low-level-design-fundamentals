# Single Responsibility Principle (SRP)

## Definition
> "A class should have one, and only one, reason to change."

##  Bad vs. Good Code
### Bad Code (The "God Object")
Here in the bad code section we have a single `Employee` class that:
1. Stores data (`id`, `name`).
2. Calculates salary (Business Logic).
3. Saves to the database (Persistence).

* **Problem:** Now what happens if you change the database from MySQL to Mongo, you risk breaking the salary calculation code because they live in the same file. Sure you can be careful , but this is just an example code where the risks are obvious. For complex systems there is a very real chance of mistakes and code breaking.

###  Good Code (The "Toolbox")
Here we will split the responsibilities:
1. **`Employee`:** Just the data.
2. **`SalaryCalculator`:** Just the math.
3. **`EmployeeRepository`:** Just the database connection.

* **Benefit:** You can change the database without ever touching the salary logic.
You can change math without touching db. You can change any one of them without touching the others basically.

## Real-World Use Case

**Backend API Design (Controller vs. Service):**
* **Controller:** Handles HTTP requests and parsing JSON.
* **Service:** Handles the actual business logic (e.g., "create order").
* **Repository:** SQL queries.
* **Why:** If you put SQL queries in your API Controller, you can't reuse that logic for a background cron job.
package srp.bad;

public class Employee {
    private String id;
    private String name;

    public Employee(String id, String name) {
        this.id = id;
        this.name = name;
    }

    // VIOLATION: Business Logic (Math) mixed with Data
    public double calculateSalary() {
        return 10000 * 0.8; // Logic hardcoded here
    }

    // VIOLATION: Persistence Logic (DB) mixed with Data
    public void save() {
        System.out.println("Saving " + this.name + " to database...");
    }
}
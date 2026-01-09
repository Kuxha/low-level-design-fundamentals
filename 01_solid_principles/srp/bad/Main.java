package srp.bad;

public class Main {
    public static void main(String[] args) {
        System.out.println("--- RUNNING BAD CODE ---");
        Employee emp = new Employee("1", "Laba");
        
        // The Employee object does too much!
        System.out.println("Salary: " + emp.calculateSalary());
        emp.save();
    }
}
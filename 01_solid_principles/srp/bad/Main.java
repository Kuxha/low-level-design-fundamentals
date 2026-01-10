package srp.bad;

public class Main {
    public static void main(String[] args) {
        System.out.println("--- RUNNING BAD CODE ---");
        Employee emp = new Employee("1", "Laba");

        // The Employee object does too much. why does the employee object have to
        // calcualte slaayr
        System.out.println("Salary: " + emp.calculateSalary());
        emp.save();
    }
}
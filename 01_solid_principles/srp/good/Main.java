package srp.good;

public class Main {
    public static void main(String[] args) {
        System.out.println("--- RUNNING GOOD CODE ---");
        Employee emp = new Employee("1", "Laba");
        
        // 1. Calculate Salary
        SalaryCalculator calculator = new SalaryCalculator();
        double salary = calculator.calculate(emp);
        System.out.println("Salary: " + salary);

        // 2. Save to DB
        EmployeeRepository repo = new EmployeeRepository();
        repo.save(emp);
    }
}
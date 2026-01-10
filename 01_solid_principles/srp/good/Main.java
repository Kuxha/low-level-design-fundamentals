package srp.good;

public class Main {
    public static void main(String[] args) {
        System.out.println("--- RUNNING GOOD CODE ---");
        Employee emp = new Employee("1", "Laba");

        // 1. Calculate Salary. notice that we are using calculator here and then giving
        // employee to that
        // not having emp.calcualte
        SalaryCalculator calculator = new SalaryCalculator();
        double salary = calculator.calculate(emp);
        System.out.println("Salary: " + salary);

        // 2. Save to DB
        // same as above. we have decoupled the calcualtor and db save to their own
        // classes
        EmployeeRepository repo = new EmployeeRepository();
        repo.save(emp);
    }
}
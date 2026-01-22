package srp.bad;

public class Employee {

    private String id;
    private String name;

    public Employee(String id, String name) {
        this.id = id;
        this.name = name;
    }

    // why do we have business data in this cla
    public double calculateSalary() {
        return 10000;
    }

    public void save() {
        System.out.println("saving to database");
    }

}
package singleton.bad;

public class Main {
    public static void main(String[] args) {
        // creating multiple instances of the same database
        DatabaseConnection db1 = new DatabaseConnection();
        DatabaseConnection db2 = new DatabaseConnection();

        System.out.println(db1);
        System.out.println(db2);

    }
}
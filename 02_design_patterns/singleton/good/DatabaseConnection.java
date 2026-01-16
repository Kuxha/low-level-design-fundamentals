package singleton.good;

public class DatabaseConnection {
    private static DatabaseConnection dbInstance;

    private DatabaseConnection() {
        System.out.println("new db connection");
    }

    public static DatabaseConnection getInstance() {
        if (dbInstance == null) {
            dbInstance = new DatabaseConnection();
        }
        return dbInstance;
    }
}

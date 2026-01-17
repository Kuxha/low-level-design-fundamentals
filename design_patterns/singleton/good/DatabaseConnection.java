package singleton.good;

public class DatabaseConnection {
    private static DatabaseConnection dbInstance;

    private DatabaseConnection() {
        System.out.println("new db connection");
    }

    // adding 'synchronized' to the method signature.
    // forces a thread B to wait for thread A to finish
    public static synchronized DatabaseConnection getInstance() {
        if (dbInstance == null) {
            dbInstance = new DatabaseConnection();
        }
        return dbInstance;
    }
}

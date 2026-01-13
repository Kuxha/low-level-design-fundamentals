public class NavigationApp {
    public void buildRoute(String transportType) {
        if (transportType.equals("car")) {
            System.out.println("Building route for Car: 15 mins");
        } else if (transportType.equals("bike")) {
            System.out.println("Building route for Bike: 25 mins");
        } else if (transportType.equals("walk")) {
            System.out.println("Building route for Walk: 45 mins");
        } else {
            System.out.println("Unknown transport type");
        }
    }
}
public class NavigationContext {

    RouteStrategy strategy;

    public void setStrategy(RouteStrategy strategy) {
        this.strategy = strategy;

    }

    public void executeRoute() {
        this.strategy.buildRoute();
    }
}

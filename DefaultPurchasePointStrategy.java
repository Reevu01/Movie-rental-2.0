public class DefaultPurchasePointStrategy implements PurchasePointStrategy {
    private int points;

    public DefaultPurchasePointStrategy(int points) {
        this.points = points;
    }

    @Override
    public int getPoints() {
        return points; // Example: fixed points per purchase
    }
}
public class FixedPurchasePriceStrategy implements PurchasePriceStrategy {
    private double price;
    private int points;

    public FixedPurchasePriceStrategy(double price, int points) {
        this.price = price;
        this.points = points;
    }

    @Override
    public double getCharge() {
        return price;
    }

    @Override
    public int getFrequentPurchaserPoints() {
        return points;
    }
}
public class DefaultPurchasePrice implements Price {
    private final double price;

    public DefaultPurchasePrice(double price) {
        this.price = price;
    }

    @Override
    public double getCharge(int daysRented) {
        return price;
    }

    public double getPrice() {
        return price;
    }
}
public class NewReleasePurchasePrice implements Price {
    private final double premium;

    public NewReleasePurchasePrice(double premium) {
        this.premium = premium;
    }

    @Override
    public double getCharge(int daysRented) {
        return premium;
    }

    public double getPrice() {
        return premium;
    }
}
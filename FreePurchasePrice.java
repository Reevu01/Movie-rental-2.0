public class FreePurchasePrice implements Price {
    @Override
    public double getCharge(int daysRented) {
        return 0;
    }

    public double getPrice() {
        return 0;
    }
}
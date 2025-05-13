public class NewReleaseChargeStrategy implements RentalPriceStrategy {
    public double getCharge(int daysRented) {
        return daysRented * 3;
    }
}
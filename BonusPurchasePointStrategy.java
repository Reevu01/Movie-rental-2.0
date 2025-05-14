public class BonusPurchasePointStrategy implements RenterPointStrategy {
    @Override
    public int getPoints(int daysRented) {
        return 2;
    }
}
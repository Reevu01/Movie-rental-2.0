public class DefaultPurchasePointStrategy implements RenterPointStrategy {
    @Override
    public int getPoints(int daysRented) {
        return 1;
    }
}
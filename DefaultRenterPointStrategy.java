public class DefaultRenterPointStrategy implements RenterPointStrategy {
    public int getPoints(int daysRented) {
        return 1;
    }
}
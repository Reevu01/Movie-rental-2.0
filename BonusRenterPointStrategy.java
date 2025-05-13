public class BonusRenterPointStrategy implements RenterPointStrategy {
    public int getPoints(int daysRented) {
        return (daysRented > 1) ? 2 : 1;
    }
}
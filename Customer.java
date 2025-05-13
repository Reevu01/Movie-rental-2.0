import java.util.ArrayList;
import java.util.List;

public class Customer {
    private String name;
    private List<Rental> rentals = new ArrayList<>();
    private List<Coupon> coupons = new ArrayList<>();
    private int redeemedPoints = 0;

    public Customer(String name) {
        this.name = name;
    }

    public void addRental(Rental rental) {
        rentals.add(rental);
    }

    public void addCoupon(Coupon coupon) {
        coupons.add(coupon);
    }

    public boolean redeemFreeRental(Movie movie, int daysRented) {
        if (getFrequentRenterPointsBalance() >= 10) {
            redeemedPoints += 10;
            rentals.add(new Rental(new Movie(movie.getTitle(), new FreePrice()), daysRented));
            return true;
        }
        return false;
    }

    public int getFrequentRenterPointsBalance() {
        int totalEarned = 0;
        for (Rental r : rentals) {
            totalEarned += r.getFrequentRenterPoints();
        }
        return totalEarned - redeemedPoints;
    }

    public String getName() {
        return name;
    }

    public List<Rental> getRentals() {
        return rentals;
    }

    public List<Coupon> getCoupons() {
        return coupons;
    }

    public String getTextStatement() {
        return StatementFormatter.formatText(this);
    }

    public String getXmlStatement() {
        return StatementFormatter.formatXML(this);
    }
}

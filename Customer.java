import java.util.ArrayList;
import java.util.List;

public class Customer {
    private String name;
    private List<TransactionItem> transactionItems = new ArrayList<>(); // Holds both Rentals and Purchases
    private List<Coupon> coupons = new ArrayList<>();
    private int redeemedPoints = 0;

    public Customer(String name) {
        this.name = name;
    }

    public void addRental(Rental rental) {
        transactionItems.add(rental);
    }

    public void addPurchase(Purchase purchase) {
        transactionItems.add(purchase);
    }

    public void addCoupon(Coupon coupon) {
        coupons.add(coupon);
    }

    public boolean redeemFreeRental(Movie movie, int daysRented) {
        if (getFrequentPointsBalance() >= 10) { // Assuming 10 points for a free rental
            redeemedPoints += 10;
            // Create a movie instance specifically for this free rental.
            // It has a FreePrice for rental and no specified purchase price (null).
            Movie freeRentalMovie = new Movie(movie.getTitle(), new FreePrice(), null);
            transactionItems.add(new Rental(freeRentalMovie, daysRented));
            return true;
        }
        return false;
    }

    public int getFrequentPointsBalance() {
        int totalEarned = 0;
        for (TransactionItem item : transactionItems) {
            totalEarned += item.getFrequentPoints();
        }
        return totalEarned - redeemedPoints;
    }

    public String getName() {
        return name;
    }

    public List<TransactionItem> getTransactionItems() {
        return transactionItems;
    }

    // Specific getters if needed, though iterating transactionItems is more general
    public List<Rental> getRentals() {
        List<Rental> rentals = new ArrayList<>();
        for (TransactionItem item : transactionItems) {
            if (item instanceof Rental) {
                rentals.add((Rental) item);
            }
        }
        return rentals;
    }

    public List<Purchase> getPurchases() {
        List<Purchase> purchases = new ArrayList<>();
        for (TransactionItem item : transactionItems) {
            if (item instanceof Purchase) {
                purchases.add((Purchase) item);
            }
        }
        return purchases;
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
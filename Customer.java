import java.util.ArrayList;
import java.util.List;

public class Customer {
    private String name;
    private List<TransactionItem> transactionItems = new ArrayList<>(); // Changed from List<Rental>
    private List<Coupon> coupons = new ArrayList<>();
    private int redeemedPoints = 0; // Represents points used for redemptions

    public Customer(String name) {
        this.name = name;
    }

    public void addRental(Rental rental) {
        transactionItems.add(rental);
    }

    public void addPurchase(Purchase purchase) { // New method
        transactionItems.add(purchase);
    }

    public void addCoupon(Coupon coupon) {
        coupons.add(coupon);
    }

    public boolean redeemFreeRental(Movie movieToRedeemForFree, int daysRented) {
        if (getFrequentPointsBalance() >= 10) { // Assuming 10 points for a free rental
            redeemedPoints += 10;
            // Create a new Movie instance specifically for this free rental, using
            // FreePrice strategy
            // The original purchase price of movieToRedeemForFree isn't relevant for this
            // free rental movie instance.
            Movie freeRentalMovieInstance = new Movie(movieToRedeemForFree.getTitle(), new FreePrice(), 0.0);
            transactionItems.add(new Rental(freeRentalMovieInstance, daysRented));
            return true;
        }
        return false;
    }

    public int getFrequentPointsBalance() {
        int totalEarned = 0;
        for (TransactionItem item : transactionItems) {
            // Ensure we don't double-count points from a free rental that used FreePrice
            if (!(item.getMovie().getRentalPriceStrategy() instanceof FreePrice)) {
                totalEarned += item.getFrequentPoints();
            }
        }
        return totalEarned - redeemedPoints;
    }

    public int getTotalFrequentPointsEarnedInTransaction() {
        int totalEarned = 0;
        for (TransactionItem item : transactionItems) {
            // Do not count points from items that were obtained via redemption (like
            // FreePrice)
            if (!(item.getMovie().getRentalPriceStrategy() instanceof FreePrice)) {
                totalEarned += item.getFrequentPoints();
            }
        }
        return totalEarned;
    }

    public String getName() {
        return name;
    }

    public List<TransactionItem> getTransactionItems() { // Changed from getRentals()
        return transactionItems;
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
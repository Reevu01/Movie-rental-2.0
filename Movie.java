public class Movie {
    private String title;
    private Price rentalPriceStrategy; // Renamed for clarity
    private double purchaseSalePrice; // New field for purchase price

    public Movie(String title, Price rentalPriceStrategy, double purchaseSalePrice) {
        this.title = title;
        this.rentalPriceStrategy = rentalPriceStrategy;
        this.purchaseSalePrice = purchaseSalePrice;
    }

    public String getTitle() {
        return title;
    }

    // --- Rental-specific methods ---
    public double getRentalCharge(int daysRented) {
        return rentalPriceStrategy.getCharge(daysRented);
    }

    public int getFrequentRentalPoints(int daysRented) {
        return rentalPriceStrategy.getFrequentRenterPoints(daysRented);
    }

    // Getter for the rental strategy if needed by other classes (e.g. Customer for
    // redemption)
    public Price getRentalPriceStrategy() {
        return rentalPriceStrategy;
    }

    // --- Purchase-specific methods ---
    public double getPurchaseSalePrice() {
        return this.purchaseSalePrice;
    }

    public int getFrequentPurchasePoints() {
        // Example: 1 point for any purchase. This can be made more complex
        // with a PurchasePointStrategy if needed in the future.
        return 1;
    }
}
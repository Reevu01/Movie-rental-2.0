public class Movie {
    private String title;
    private Price rentalPrice; // Existing rental price structure
    private PurchasePrice purchasePriceInfo; // New: for purchase details

    public Movie(String title, Price rentalPrice, PurchasePrice purchasePriceInfo) {
        this.title = title;
        this.rentalPrice = rentalPrice;
        this.purchasePriceInfo = purchasePriceInfo;
    }

    // Constructor for movies that might only be rentable (or purchasable) initially
    public Movie(String title, Price rentalPrice) {
        this(title, rentalPrice, null); // Or a default non-purchasable PurchasePrice
    }

    // public Movie(String title, PurchasePrice purchasePriceInfo) {
    // this(title, null, purchasePriceInfo); // Or a default non-rentable Price
    // }

    public String getTitle() {
        return title;
    }

    // --- Rental Methods ---
    public double getRentalCharge(int daysRented) {
        if (rentalPrice != null) {
            return rentalPrice.getCharge(daysRented);
        }
        // Consider throwing an exception or returning a specific value if not rentable
        throw new UnsupportedOperationException("Movie " + title + " is not available for rent.");
    }

    public int getFrequentRenterPoints(int daysRented) {
        if (rentalPrice != null) {
            return rentalPrice.getFrequentRenterPoints(daysRented);
        }
        return 0; // Or throw exception
    }

    // --- Purchase Methods ---
    public double getPurchaseCharge() {
        if (purchasePriceInfo != null) {
            return purchasePriceInfo.getCharge();
        }
        // Consider throwing an exception or returning a specific value if not
        // purchasable
        throw new UnsupportedOperationException("Movie " + title + " is not available for purchase.");
    }

    public int getFrequentPurchaserPoints() {
        if (purchasePriceInfo != null) {
            return purchasePriceInfo.getFrequentPurchaserPoints();
        }
        return 0; // Or throw exception
    }

    // --- Setter for Purchase Price if a movie can be made purchasable later ---
    public void setPurchasePriceInfo(PurchasePrice purchasePriceInfo) {
        this.purchasePriceInfo = purchasePriceInfo;
    }

    public void setRentalPrice(Price rentalPrice) {
        this.rentalPrice = rentalPrice;
    }

    public Price getRentalPriceObject() {
        return rentalPrice;
    }

    public PurchasePrice getPurchasePriceObject() {
        return purchasePriceInfo;
    }
}
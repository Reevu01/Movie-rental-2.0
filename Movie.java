// File: reevu01/movie-rental-2.0/Movie-rental-2.0-1/Movie.java
public class Movie {
    private String title;
    private Price rentalPriceStrategy;
    private Price purchasePriceStrategy;
    private RenterPointStrategy rentalPointStrategy;
    private RenterPointStrategy purchasePointStrategy;

    public Movie(String title,
            Price rentalPriceStrategy,
            Price purchasePriceStrategy,
            RenterPointStrategy rentalPointStrategy,
            RenterPointStrategy purchasePointStrategy) {
        this.title = title;
        this.rentalPriceStrategy = rentalPriceStrategy;
        this.purchasePriceStrategy = purchasePriceStrategy;
        this.rentalPointStrategy = rentalPointStrategy;
        this.purchasePointStrategy = purchasePointStrategy;
    }

    public String getTitle() {
        return title;
    }

    public Price getRentalPriceStrategy() {
        return rentalPriceStrategy;
    }

    public Price getPurchasePriceStrategy() {
        return purchasePriceStrategy;
    }

    public RenterPointStrategy getRentalPointStrategy() {
        return rentalPointStrategy;
    }

    public RenterPointStrategy getPurchasePointStrategy() {
        return purchasePointStrategy;
    }

    // Added methods to get charge and points by delegating to strategies
    public double getRentalCharge(int daysRented) {
        return rentalPriceStrategy.getCharge(daysRented);
    }

    public int getFrequentRentalPoints(int daysRented) {
        return rentalPointStrategy.getPoints(daysRented);
    }

    public double getPurchaseSalePrice() {
        // For purchase, daysRented is irrelevant, so pass 0 as it's not used by
        // purchase price strategies
        return purchasePriceStrategy.getCharge(0);
    }

    public int getFrequentPurchasePoints() {
        // For purchase, daysRented is irrelevant, so pass 0 as it's not used by
        // purchase point strategies
        return purchasePointStrategy.getPoints(0);
    }
}
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
}
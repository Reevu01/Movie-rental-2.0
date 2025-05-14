public class Movie {
    private String title;
    private Price rentalPrice;
    private PurchasePrice purchasePriceInfo;

    public Movie(String title, Price rentalPrice, PurchasePrice purchasePriceInfo) {
        this.title = title;
        this.rentalPrice = rentalPrice;
        this.purchasePriceInfo = purchasePriceInfo;
    }

    public Movie(String title, Price rentalPrice) {
        this(title, rentalPrice, null);
    }

    public String getTitle() {
        return title;
    }

    public double getRentalCharge(int daysRented) {
        if (rentalPrice != null) {
            return rentalPrice.getCharge(daysRented);
        }
        throw new UnsupportedOperationException("Movie " + title + " is not available for rent.");
    }

    public int getFrequentRenterPoints(int daysRented) {
        if (rentalPrice != null) {
            return rentalPrice.getFrequentRenterPoints(daysRented);
        }
        return 0;
    }

    public double getPurchaseCharge() {
        if (purchasePriceInfo != null) {
            return purchasePriceInfo.getCharge();
        }

        throw new UnsupportedOperationException("Movie " + title + " is not available for purchase.");
    }

    public int getFrequentPurchaserPoints() {
        if (purchasePriceInfo != null) {
            return purchasePriceInfo.getFrequentPurchaserPoints();
        }
        return 0;
    }

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
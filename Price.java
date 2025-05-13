public abstract class Price {
    protected RentalPriceStrategy priceStrategy;
    protected RenterPointStrategy pointStrategy;

    public double getCharge(int daysRented) {
        return priceStrategy.getCharge(daysRented);
    }

    public int getFrequentRenterPoints(int daysRented) {
        return pointStrategy.getPoints(daysRented);
    }
}

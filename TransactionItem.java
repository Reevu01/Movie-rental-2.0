public class TransactionItem {
    private Movie movie;
    private TransactionType type;
    private int daysRented;  // meaningful only if type == RENTAL

    public TransactionItem(Movie movie, TransactionType type, int daysRented) {
        this.movie = movie;
        this.type = type;
        this.daysRented = daysRented;
    }

    public double getCharge() {
        if (type == TransactionType.RENTAL) {
            return movie.getRentalPriceStrategy().getCharge(daysRented);
        } else {
            return movie.getPurchasePriceStrategy().getCharge(0);
        }
    }

    public int getFrequentPoints() {
        if (type == TransactionType.RENTAL) {
            return movie.getRentalPointStrategy().getPoints(daysRented);
        } else {
            return movie.getPurchasePointStrategy().getPoints(0);
        }
    }

    public Movie getMovie() {
        return movie;
    }

    public TransactionType getType() {
        return type;
    }

    public int getDaysRented() {
        return daysRented;
    }
}
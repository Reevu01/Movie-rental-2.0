public class Purchase implements TransactionItem {
    private Movie movie;
    // No daysRented for a purchase

    public Purchase(Movie movie) {
        this.movie = movie;
    }

    public Movie getMovie() {
        return movie;
    }

    @Override
    public String getTitle() {
        return movie.getTitle();
    }

    @Override
    public double getCharge() {
        return movie.getPurchaseCharge();
    }

    @Override
    public int getFrequentPoints() {
        return movie.getFrequentPurchaserPoints();
    }

    // If purchases can have different "types" affecting price/points later
    // public Purchase(Movie movie, PurchaseType type) { ... }
}
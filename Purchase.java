public class Purchase implements TransactionItem {
    private Movie movie;

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

}
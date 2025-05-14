public class Rental implements TransactionItem { // Added implements TransactionItem
    private Movie movie;
    private int daysRented;

    public Rental(Movie movie, int daysRented) {
        this.movie = movie;
        this.daysRented = daysRented;
    }

    public int getDaysRented() {
        return daysRented;
    }

    // getTitle() is already in TransactionItem
    @Override
    public String getTitle() {
        return movie.getTitle();
    }

    // getCharge() is already in TransactionItem
    @Override
    public double getCharge() {
        return movie.getRentalCharge(daysRented);
    }

    // Renamed from getFrequentRenterPoints to getFrequentPoints
    @Override
    public int getFrequentPoints() {
        return movie.getFrequentRenterPoints(daysRented);
    }

    // Keep the original specific method if used elsewhere, or refactor usage
    public int getFrequentRenterPoints() {
        return movie.getFrequentRenterPoints(daysRented);
    }

    public Movie getMovie() { // Added for completeness
        return movie;
    }
}
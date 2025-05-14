public class Rental implements TransactionItem {
    private Movie movie;
    private int daysRented;

    public Rental(Movie movie, int daysRented) {
        this.movie = movie;
        this.daysRented = daysRented;
    }

    public int getDaysRented() {
        return daysRented;
    }

    @Override
    public String getTitle() {
        return movie.getTitle();
    }

    @Override
    public double getCharge() {
        return movie.getRentalCharge(daysRented);
    }

    @Override
    public int getFrequentPoints() {
        return movie.getFrequentRenterPoints(daysRented);
    }

    public int getFrequentRenterPoints() {
        return movie.getFrequentRenterPoints(daysRented);
    }

    public Movie getMovie() {
        return movie;
    }
}
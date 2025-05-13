public class Purchase implements TransactionItem {
    private Movie movie;
    // private int quantity; // If you want to support purchasing multiple copies of
    // the same movie as one item

    public Purchase(Movie movie) {
        this.movie = movie;
        // this.quantity = 1; // Default to 1 if quantity is implemented
    }

    @Override
    public Movie getMovie() {
        return movie;
    }

    @Override
    public String getTitle() {
        return movie.getTitle();
    }

    @Override
    public double getCharge() {
        return movie.getPurchaseSalePrice(); // Assumes quantity is 1
    }

    @Override
    public int getFrequentPoints() {
        return movie.getFrequentPurchasePoints(); // Assumes quantity is 1
    }

    @Override
    public String getType() {
        return "Purchase";
    }

    // public int getQuantity() { // If quantity is implemented
    // return quantity;
    // }
}
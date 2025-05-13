public interface TransactionItem {
    String getTitle();

    double getCharge();

    int getFrequentPoints();

    String getType(); // To distinguish "Rental" vs. "Purchase" in statements
}
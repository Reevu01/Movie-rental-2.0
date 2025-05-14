public interface PurchasePriceStrategy {
    double getCharge(); // Purchase price is typically fixed, not dependent on days

    int getFrequentPurchaserPoints();
}
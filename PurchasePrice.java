public class PurchasePrice {
    private double price;
    private int frequentPurchaserPoints;

    public PurchasePrice(double price, int frequentPurchaserPoints) {
        this.price = price;
        this.frequentPurchaserPoints = frequentPurchaserPoints;
    }

    public double getCharge() {
        return price;
    }

    public int getFrequentPurchaserPoints() {
        return frequentPurchaserPoints;
    }
}
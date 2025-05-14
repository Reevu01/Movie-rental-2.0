public class FreePrice extends Price {
    public FreePrice() {
        this.priceStrategy = daysRented -> 0.0;
        this.pointStrategy = daysRented -> 0;
    }
}
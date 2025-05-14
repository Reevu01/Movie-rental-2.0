public class RegularPrice extends Price {
    public RegularPrice() {
        this.priceStrategy = new RegularChargeStrategy();
        this.pointStrategy = new DefaultRenterPointStrategy();
    }
}
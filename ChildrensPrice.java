public class ChildrensPrice extends Price {
    public ChildrensPrice() {
        this.priceStrategy = new ChildrensChargeStrategy();
        this.pointStrategy = new DefaultRenterPointStrategy();
    }
}
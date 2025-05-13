public class NewReleasePrice extends Price {
    public NewReleasePrice() {
        this.priceStrategy = new NewReleaseChargeStrategy();
        this.pointStrategy = new BonusRenterPointStrategy();
    }
}

public class TenDollarsOffCoupon implements Coupon {
    @Override
    public double applyDiscount(double amount) {
        if (amount > 50) {
            return amount - 10;
        }
        return amount;
    }
}
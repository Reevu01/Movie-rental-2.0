public class HalfOffCoupon implements Coupon {
    @Override
    public double applyDiscount(double amount) {
        return amount * 0.5;
    }
}

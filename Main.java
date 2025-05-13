public class Main {
    public static void main(String[] args) {
        Customer customer = new Customer("John Smith");

        // --- add some regular rentals ---
        Movie m1 = new Movie("Independence Day", new NewReleasePrice());
        Movie m2 = new Movie("The Lion King", new ChildrensPrice());
        Movie m3 = new Movie("The Godfather", new RegularPrice());

        customer.addRental(new Rental(m1, 2));
        customer.addRental(new Rental(m2, 4));
        customer.addRental(new Rental(m3, 3));

        // --- give the customer two coupons ---
        customer.addCoupon(new HalfOffCoupon()); // 50% off entire bill
        customer.addCoupon(new TenDollarsOffCoupon()); // $10 off if > $50

        // --- try redeeming a free movie (costs 10 points) ---
        Movie freePick = new Movie("The Matrix", new NewReleasePrice());
        boolean redeemed = customer.redeemFreeRental(freePick, 1);
        if (redeemed) {
            System.out.println("🎉 Redeemed a free rental for \"" + freePick.getTitle() + "\"\n");
        } else {
            System.out.println("Not enough points to redeem a free movie.\n");
        }

        // --- print out statements ---
        System.out.println(customer.getTextStatement());
        System.out.println("\nXML Output:");
        System.out.println(customer.getXmlStatement());
    }
}

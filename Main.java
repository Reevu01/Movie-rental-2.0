public class Main {
    public static void main(String[] args) {
        Customer customer1 = new Customer("Alice Wonderland");

        Movie m1 = new Movie("(Quantum Leap) ", new NewReleasePrice(), new PurchasePrice(25.00, 5));

        Movie m2 = new Movie("(The Lion King)", new ChildrensPrice(), new PurchasePrice(15.00, 3));

        Movie m4 = new Movie("(The Matrix) ", null, new PurchasePrice(20.00, 4));

        customer1.addRental(new Rental(m1, 3));
        customer1.addRental(new Rental(m2, 5));

        if (m4.getPurchasePriceObject() != null) {
            customer1.addPurchase(new Purchase(m4));
        } else {
            System.out.println(m4.getTitle() + " is not available for purchase.");
        }

        if (m1.getPurchasePriceObject() != null) {
            customer1.addPurchase(new Purchase(m1));
        } else {
            System.out.println(m1.getTitle() + " is not available for purchase.");
        }

        customer1.addCoupon(new HalfOffCoupon());

        System.out.println("--- Alice's Statement ---");
        System.out.println(customer1.getTextStatement());
        System.out.println("\n--- Alice's XML Statement ---");
        System.out.println(customer1.getXmlStatement());
        System.out.println("\n\n");

        Customer customer2 = new Customer("Bob The Builder");
        Movie m5_regular_rent_purchase = new Movie("(Interstellar)", new RegularPrice(),
                new PurchasePrice(18.00, 3));
        Movie m6_childrens_rent_only = new Movie("(Toy Story)", new ChildrensPrice(), null);

        customer2.addRental(new Rental(m5_regular_rent_purchase, 2));
        customer2.addPurchase(new Purchase(m5_regular_rent_purchase));
        customer2.addRental(new Rental(m6_childrens_rent_only, 4));
        customer2.addCoupon(new TenDollarsOffCoupon());

        System.out.println("Bob's points before trying to redeem: " + customer2.getFrequentPointsBalance());
        Movie freePick = new Movie("(Blade Runner)", new RegularPrice(), null);
        boolean redeemed = customer2.redeemFreeRental(freePick, 2);
        if (redeemed) {
            System.out.println("🎉 Bob redeemed a free rental for \"" + freePick.getTitle() + "\"\n");
        } else {
            System.out.println("Bob: Not enough points to redeem a free movie or redemption failed.\n");
        }

        System.out.println("--- Bob's Statement ---");
        System.out.println(customer2.getTextStatement());
        System.out.println("\n--- Bob's XML Statement ---");
        System.out.println(customer2.getXmlStatement());
    }
}
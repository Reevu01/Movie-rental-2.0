// File: reevu01/movie-rental-2.0/Movie-rental-2.0-1/Main.java
public class Main {
    public static void main(String[] args) {
        // --- Create Movies with Rental and Purchase Prices ---
        // Updated Movie constructors to use appropriate Price and RenterPoint
        // strategies for both rental and purchase
        Movie m1 = new Movie("Independence Day", new NewReleasePrice(), new DefaultPurchasePrice(19.99),
                new BonusRenterPointStrategy(), new DefaultPurchasePointStrategy());
        Movie m2 = new Movie("The Lion King", new ChildrensPrice(), new DefaultPurchasePrice(14.99),
                new DefaultRenterPointStrategy(), new DefaultPurchasePointStrategy());
        Movie m3 = new Movie("The Godfather", new RegularPrice(), new DefaultPurchasePrice(9.99),
                new DefaultRenterPointStrategy(), new DefaultPurchasePointStrategy());
        Movie m4 = new Movie("Oppenheimer", new NewReleasePrice(), new DefaultPurchasePrice(29.99),
                new BonusRenterPointStrategy(), new DefaultPurchasePointStrategy()); // New movie for purchase

        // --- Create a Customer ---
        Customer customer1 = new Customer("John Smith");

        // --- Transaction 1: Rentals and Purchases ---
        System.out.println("--- Transaction for John Smith ---");
        customer1.addRental(new Rental(m1, 2)); // Rent Independence Day (New Release)
        customer1.addPurchase(new Purchase(m2)); // Buy The Lion King (Childrens)
        customer1.addRental(new Rental(m3, 5)); // Rent The Godfather (Regular)

        // --- Give the customer coupons ---
        customer1.addCoupon(new HalfOffCoupon()); // 50% off entire bill
        customer1.addCoupon(new TenDollarsOffCoupon()); // $10 off if > $50 (applied after half-off)

        // --- Try redeeming a free movie (costs 10 points) ---
        // Let's say John wants to redeem "Oppenheimer"
        boolean redeemed = customer1.redeemFreeRental(m4, 1); // Attempt to redeem m4
        if (redeemed) {
            System.out.println("🎉 Redeemed a free rental for \"" + m4.getTitle() + "\"\n");
        } else {
            System.out.println("Not enough points to redeem \"" + m4.getTitle() + "\" for free, or other issue.\n");
        }

        // --- Print out statements for customer1 ---
        System.out.println(customer1.getTextStatement());
        System.out.println("\nXML Output for John Smith:");
        System.out.println(customer1.getXmlStatement());
        System.out.println("\n---------------------------------------\n");

        // --- Create another Customer for a different scenario ---
        Customer customer2 = new Customer("Alice Wonderland");
        System.out.println("--- Transaction for Alice Wonderland ---");
        // Alice only purchases
        customer2.addPurchase(new Purchase(m1)); // Buys Independence Day
        customer2.addPurchase(new Purchase(m4)); // Buys Oppenheimer

        // No coupons for Alice this time, let's see her points.
        // Try to redeem The Godfather
        boolean aliceRedeemed = customer2.redeemFreeRental(m3, 3);
        if (aliceRedeemed) {
            System.out.println("🎉 Alice redeemed a free rental for \"" + m3.getTitle() + "\"\n");
        } else {
            System.out.println(
                    "Alice: Not enough points to redeem \"" + m3.getTitle() + "\" for free, or other issue.\n");
        }

        System.out.println(customer2.getTextStatement());
        System.out.println("\nXML Output for Alice Wonderland:");
        System.out.println(customer2.getXmlStatement());
    }
}
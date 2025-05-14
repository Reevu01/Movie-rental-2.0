public class Main {
    public static void main(String[] args) {
        Customer customer1 = new Customer("Alice Wonderland");

        // --- Define Movies with Rental and Purchase Prices ---
        // Movie 1: New Release, can be rented or purchased
        Movie m1 = new Movie(" квантовый скачок (Quantum Leap) ", new NewReleasePrice(), new PurchasePrice(25.00, 5)); // title
                                                                                                                       // in
                                                                                                                       // Russian
        // Movie 2: Children's Movie, can be rented or purchased
        Movie m2 = new Movie("Король Лев (The Lion King)", new ChildrensPrice(), new PurchasePrice(15.00, 3)); // title
                                                                                                               // in
                                                                                                               // Russian
        // Movie 3: Regular Movie, can be rented (no purchase option for this one for
        // demo)
        Movie m3 = new Movie("Крестный отец (The Godfather)", new RegularPrice(), null); // title in Russian
        // Movie 4: Another Movie, can be purchased (no rental option for this one for
        // demo)
        Movie m4 = new Movie("Матрица (The Matrix) ", null, new PurchasePrice(20.00, 4)); // title in Russian

        // --- Alice's Transactions ---
        // Alice rents "Quantum Leap" and "The Lion King"
        customer1.addRental(new Rental(m1, 3)); // 3 days for New Release
        customer1.addRental(new Rental(m2, 5)); // 5 days for Children's

        // Alice purchases "The Matrix"
        if (m4.getPurchasePriceObject() != null) { // Check if purchasable
            customer1.addPurchase(new Purchase(m4));
        } else {
            System.out.println(m4.getTitle() + " is not available for purchase.");
        }

        // Alice also purchases "Quantum Leap"
        if (m1.getPurchasePriceObject() != null) {
            customer1.addPurchase(new Purchase(m1));
        } else {
            System.out.println(m1.getTitle() + " is not available for purchase.");
        }

        // --- Give Alice a coupon ---
        customer1.addCoupon(new HalfOffCoupon()); // 50% off entire bill

        System.out.println("--- Alice's Statement ---");
        System.out.println(customer1.getTextStatement());
        System.out.println("\n--- Alice's XML Statement ---");
        System.out.println(customer1.getXmlStatement());
        System.out.println("\n\n");

        // --- Second Customer: Bob The Builder ---
        Customer customer2 = new Customer("Bob The Builder");
        Movie m5_regular_rent_purchase = new Movie("Интерстеллар (Interstellar)", new RegularPrice(),
                new PurchasePrice(18.00, 3));
        Movie m6_childrens_rent_only = new Movie("История игрушек (Toy Story)", new ChildrensPrice(), null);

        customer2.addRental(new Rental(m5_regular_rent_purchase, 2)); // Rent Interstellar
        customer2.addPurchase(new Purchase(m5_regular_rent_purchase)); // Buy Interstellar as well
        customer2.addRental(new Rental(m6_childrens_rent_only, 4)); // Rent Toy Story

        // Bob has a $10 off coupon (if total > $50)
        customer2.addCoupon(new TenDollarsOffCoupon());

        // Try redeeming a free movie (costs 10 points as per current Customer.java)
        // Let's assume Bob has enough points from previous transactions (not shown
        // here)
        // For demo, let's temporarily adjust redeemedPoints to test redemption logic
        // In a real scenario, points would accumulate.

        // To test redemption, let's assume Bob earned points.
        // The logic in Customer.java for redeemFreeRental requires actual earned
        // points.
        // For now, the test will show if redemption fails if points are insufficient.
        System.out.println("Bob's points before trying to redeem: " + customer2.getFrequentPointsBalance());
        Movie freePick = new Movie("Бегущий по лезвию (Blade Runner)", new RegularPrice(), null); // Movie to redeem
        boolean redeemed = customer2.redeemFreeRental(freePick, 2); // Redeem for 2 days
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
public class StatementFormatter {
        public static String formatText(Customer customer) {
                StringBuilder result = new StringBuilder("Transaction Record for " + customer.getName() + "\n");
                double totalAmount = 0;
                // Points earned in this specific transaction (excluding already redeemed free
                // items)
                int frequentPointsEarnedThisTransaction = customer.getTotalFrequentPointsEarnedInTransaction();

                for (TransactionItem item : customer.getTransactionItems()) {
                        double thisAmount = item.getCharge();
                        // Points for this item were already summed in
                        // getTotalFrequentPointsEarnedInTransaction if applicable

                        result.append("\t")
                                        .append(item.getTitle())
                                        .append(" (").append(item.getType()).append(")");
                        if (item instanceof Rental) {
                                Rental rental = (Rental) item;
                                result.append(" for ").append(rental.getDaysRented()).append(" days");
                                // Clarify if it's a free rental
                                if (rental.getMovie().getRentalPriceStrategy() instanceof FreePrice) {
                                        result.append(" - FREE");
                                }
                        }
                        result.append("\t$")
                                        .append(String.format("%.2f", thisAmount))
                                        .append("\n");
                        totalAmount += thisAmount;
                }

                double amountBeforeCoupons = totalAmount;
                boolean couponsApplied = !customer.getCoupons().isEmpty();
                String couponDetails = "";

                for (Coupon coupon : customer.getCoupons()) {
                        double amountAfterDiscount = coupon.applyDiscount(totalAmount);
                        if (amountAfterDiscount < totalAmount) {
                                if (coupon instanceof HalfOffCoupon)
                                        couponDetails += "\tApplied 50% off coupon.\n";
                                if (coupon instanceof TenDollarsOffCoupon)
                                        couponDetails += "\tApplied $10 off coupon.\n";
                        }
                        totalAmount = amountAfterDiscount;
                }

                if (couponsApplied && !couponDetails.isEmpty()) {
                        result.append("Subtotal: $").append(String.format("%.2f", amountBeforeCoupons)).append("\n");
                        result.append(couponDetails);
                }

                result.append("Amount owed is $")
                                .append(String.format("%.2f", totalAmount))
                                .append("\n");
                result.append("You earned ")
                                .append(frequentPointsEarnedThisTransaction)
                                .append(" frequent points in this transaction.\n");
                result.append("Total points available for redemption: ")
                                .append(customer.getFrequentPointsBalance());

                return result.toString();
        }

        public static String formatXML(Customer customer) {
                StringBuilder xml = new StringBuilder("<customer>\n");
                xml.append("\t<name>")
                                .append(customer.getName())
                                .append("</name>\n");

                xml.append("\t<transactionItems>\n");
                double totalChargeFromItems = 0;

                for (TransactionItem item : customer.getTransactionItems()) {
                        totalChargeFromItems += item.getCharge();
                        xml.append("\t\t<item type=\"").append(item.getType()).append("\">\n")
                                        .append("\t\t\t<movie>").append(item.getTitle()).append("</movie>\n");
                        if (item instanceof Rental) {
                                Rental rental = (Rental) item;
                                xml.append("\t\t\t<daysRented>").append(rental.getDaysRented())
                                                .append("</daysRented>\n");
                                if (rental.getMovie().getRentalPriceStrategy() instanceof FreePrice) {
                                        xml.append("\t\t\t<status>FREE_REDEMPTION</status>\n");
                                }
                        }
                        xml.append("\t\t\t<charge>").append(String.format("%.2f", item.getCharge()))
                                        .append("</charge>\n")
                                        .append("\t\t\t<pointsEarnedForItem>")
                                        .append(item.getMovie().getRentalPriceStrategy() instanceof FreePrice ? 0
                                                        : item.getFrequentPoints())
                                        .append("</pointsEarnedForItem>\n")
                                        .append("\t\t</item>\n");
                }
                xml.append("\t</transactionItems>\n");

                xml.append("\t<totalBeforeCoupons>").append(String.format("%.2f", totalChargeFromItems))
                                .append("</totalBeforeCoupons>\n");

                double totalAmountAfterCoupons = totalChargeFromItems;
                if (!customer.getCoupons().isEmpty()) {
                        xml.append("\t<couponsApplied>\n");
                        for (Coupon coupon : customer.getCoupons()) {
                                double tempAmount = totalAmountAfterCoupons;
                                totalAmountAfterCoupons = coupon.applyDiscount(totalAmountAfterCoupons);
                                if (totalAmountAfterCoupons < tempAmount) {
                                        if (coupon instanceof HalfOffCoupon)
                                                xml.append("\t\t<coupon type=\"HalfOff\" />\n");
                                        if (coupon instanceof TenDollarsOffCoupon)
                                                xml.append("\t\t<coupon type=\"TenDollarsOff\" />\n");
                                }
                        }
                        xml.append("\t</couponsApplied>\n");
                }

                xml.append("\t<totalAfterCoupons>").append(String.format("%.2f", totalAmountAfterCoupons))
                                .append("</totalAfterCoupons>\n");

                xml.append("\t<frequentPointsEarnedThisTransaction>")
                                .append(customer.getTotalFrequentPointsEarnedInTransaction())
                                .append("</frequentPointsEarnedThisTransaction>\n");
                xml.append("\t<totalPointsAvailable>")
                                .append(customer.getFrequentPointsBalance())
                                .append("</totalPointsAvailable>\n");

                xml.append("</customer>");
                return xml.toString();
        }
}
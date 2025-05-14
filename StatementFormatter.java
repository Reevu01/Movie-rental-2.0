public class StatementFormatter {
        public static String formatText(Customer customer) {
                StringBuilder result = new StringBuilder("Transaction Record for " + customer.getName() + "\n");
                double totalAmount = 0;
                int totalFrequentPoints = 0;

                result.append("Items:\n");
                for (TransactionItem item : customer.getTransactionItems()) {
                        double thisAmount = item.getCharge();
                        totalFrequentPoints += item.getFrequentPoints();

                        String itemType = "";
                        String itemDetails = "";

                        if (item instanceof Rental) {
                                itemType = "Rental";
                                Rental rental = (Rental) item;
                                itemDetails = " (" + rental.getDaysRented() + " days)";
                        } else if (item instanceof Purchase) {
                                itemType = "Purchase";
                        }

                        result.append("\t")
                                        .append(item.getTitle())
                                        .append(" [").append(itemType).append("]")
                                        .append(itemDetails)
                                        .append("\t$")
                                        .append(String.format("%.2f", thisAmount))
                                        .append("\n");
                        totalAmount += thisAmount;
                }

                result.append("Subtotal: $").append(String.format("%.2f", totalAmount)).append("\n");

                if (!customer.getCoupons().isEmpty()) {
                        result.append("Applying Coupons:\n");
                }
                for (Coupon coupon : customer.getCoupons()) {
                        double amountBeforeCoupon = totalAmount;
                        totalAmount = coupon.applyDiscount(totalAmount);
                        double discountApplied = amountBeforeCoupon - totalAmount;
                        result.append("\tApplied a coupon. Discount: $")
                                        .append(String.format("%.2f", discountApplied))
                                        .append(". New Total: $").append(String.format("%.2f", totalAmount))
                                        .append("\n");
                }

                result.append("Total amount owed: $")
                                .append(String.format("%.2f", totalAmount))
                                .append("\n");
                result.append("You earned ")
                                .append(totalFrequentPoints)
                                .append(" frequent transaction points this period.\n");
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
                double subtotalAmount = 0;
                int totalFrequentPointsEarnedThisPeriod = 0;

                for (TransactionItem item : customer.getTransactionItems()) {
                        xml.append("\t\t<item>\n");
                        xml.append("\t\t\t<title>").append(item.getTitle()).append("</title>\n");
                        if (item instanceof Rental) {
                                Rental rental = (Rental) item;
                                xml.append("\t\t\t<type>Rental</type>\n");
                                xml.append("\t\t\t<daysRented>").append(rental.getDaysRented())
                                                .append("</daysRented>\n");
                        } else if (item instanceof Purchase) {
                                xml.append("\t\t\t<type>Purchase</type>\n");
                        }
                        xml.append("\t\t\t<charge>").append(String.format("%.2f", item.getCharge()))
                                        .append("</charge>\n");
                        xml.append("\t\t\t<frequentPointsEarned>").append(item.getFrequentPoints())
                                        .append("</frequentPointsEarned>\n");
                        xml.append("\t\t</item>\n");
                        subtotalAmount += item.getCharge();
                        totalFrequentPointsEarnedThisPeriod += item.getFrequentPoints();
                }
                xml.append("\t</transactionItems>\n");

                xml.append("\t<subtotalAmount>").append(String.format("%.2f", subtotalAmount))
                                .append("</subtotalAmount>\n");

                double finalAmount = subtotalAmount;
                if (!customer.getCoupons().isEmpty()) {
                        xml.append("\t<couponsApplied>\n");
                        for (Coupon coupon : customer.getCoupons()) {
                                double amountBeforeCoupon = finalAmount;
                                finalAmount = coupon.applyDiscount(finalAmount);
                                double discount = amountBeforeCoupon - finalAmount;
                                xml.append("\t\t<coupon>\n");
                                xml.append("\t\t\t<description>").append(coupon.getClass().getSimpleName())
                                                .append("</description>\n");
                                xml.append("\t\t\t<discountAmount>").append(String.format("%.2f", discount))
                                                .append("</discountAmount>\n");
                                xml.append("\t\t</coupon>\n");
                        }
                        xml.append("\t</couponsApplied>\n");
                }

                xml.append("\t<totalAmountOwed>").append(String.format("%.2f", finalAmount))
                                .append("</totalAmountOwed>\n");
                xml.append("\t<frequentPointsEarnedThisPeriod>")
                                .append(totalFrequentPointsEarnedThisPeriod)
                                .append("</frequentPointsEarnedThisPeriod>\n");
                xml.append("\t<totalFrequentPointsBalance>")
                                .append(customer.getFrequentPointsBalance())
                                .append("</totalFrequentPointsBalance>\n");

                xml.append("</customer>");
                return xml.toString();
        }
}
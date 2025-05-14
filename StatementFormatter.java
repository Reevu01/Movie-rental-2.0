
// File: reevu01/movie-rental-2.0/Movie-rental-2.0-1/StatementFormatter.java
import java.util.List;

public class StatementFormatter {

        public static String formatText(Customer customer) {
                StringBuilder result = new StringBuilder();
                result.append("Statement for ").append(customer.getName()).append("\n");

                double totalRental = 0;
                double totalPurchase = 0;
                int totalPoints = 0;

                for (TransactionItem item : customer.getTransactionItems()) {
                        double charge = item.getCharge();
                        int points = item.getFrequentPoints();
                        totalPoints += points;
                        if (item.getType() == TransactionType.RENTAL) {
                                totalRental += charge;
                                result.append("  Rent:   ")
                                                .append(item.getMovie().getTitle())
                                                .append(" (" + item.getDaysRented() + " days): ")
                                                .append(charge).append("\n");
                        } else { // Assuming TransactionType.PURCHASE
                                totalPurchase += charge;
                                result.append("  Purchase: ")
                                                .append(item.getMovie().getTitle())
                                                .append(": ")
                                                .append(charge).append("\n");
                        }
                }

                double grandTotal = totalRental + totalPurchase;

                // Apply coupons sequentially
                double totalDiscount = 0;
                for (Coupon coupon : customer.getCoupons()) {
                        // Ensure discount is applied to the remaining amount after previous discounts
                        totalDiscount += coupon.applyDiscount(grandTotal - totalDiscount);
                }
                grandTotal -= totalDiscount;

                result.append("Total rental: ").append(totalRental).append("\n");
                result.append("Total purchase: ").append(totalPurchase).append("\n");
                result.append("Coupon discount: ").append(totalDiscount).append("\n");
                result.append("Amount owed: ").append(grandTotal).append("\n");
                result.append("You earned ").append(totalPoints).append(" frequent points\n");

                return result.toString();
        }

        public static String formatXML(Customer customer) {
                StringBuilder result = new StringBuilder();
                result.append("<customer name=\"").append(customer.getName()).append("\">\n");

                double totalRental = 0;
                double totalPurchase = 0;
                int totalPoints = 0;

                for (TransactionItem item : customer.getTransactionItems()) {
                        double charge = item.getCharge();
                        int points = item.getFrequentPoints();
                        totalPoints += points;
                        if (item.getType() == TransactionType.RENTAL) {
                                totalRental += charge;
                                result.append("  <rental title=\"")
                                                .append(item.getMovie().getTitle())
                                                .append("\" daysRented=\"").append(item.getDaysRented())
                                                .append("\" charge=\"").append(charge)
                                                .append("\" points=\"").append(points).append("\"/>\n");
                        } else { // Assuming TransactionType.PURCHASE
                                totalPurchase += charge;
                                result.append("  <purchase title=\"")
                                                .append(item.getMovie().getTitle())
                                                .append("\" charge=\"").append(charge)
                                                .append("\" points=\"").append(points).append("\"/>\n");
                        }
                }

                double grandTotal = totalRental + totalPurchase;

                // Apply coupons sequentially
                double totalDiscount = 0;
                for (Coupon coupon : customer.getCoupons()) {
                        totalDiscount += coupon.applyDiscount(grandTotal - totalDiscount);
                }
                grandTotal -= totalDiscount;

                result.append("  <summary>\n");
                result.append("    <totalRental>").append(totalRental).append("</totalRental>\n");
                result.append("    <totalPurchase>").append(totalPurchase).append("</totalPurchase>\n");
                result.append("    <couponDiscount>").append(totalDiscount).append("</couponDiscount>\n");
                result.append("    <amountOwed>").append(grandTotal).append("</amountOwed>\n");
                result.append("    <frequentPoints>").append(totalPoints).append("</frequentPoints>\n");
                result.append("  </summary>\n");
                result.append("</customer>");

                return result.toString();
        }
}
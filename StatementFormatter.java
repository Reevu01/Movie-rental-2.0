import java.util.List;

public class StatementFormatter {
        public String format(String customerName,
                        List<TransactionItem> items,
                        Coupon coupon) {
                StringBuilder result = new StringBuilder();
                result.append("Statement for ").append(customerName).append("\n");

                double totalRental = 0;
                double totalPurchase = 0;
                int totalPoints = 0;

                for (TransactionItem item : items) {
                        double charge = item.getCharge();
                        int points = item.getFrequentPoints();
                        totalPoints += points;
                        if (item.getType() == TransactionType.RENTAL) {
                                totalRental += charge;
                                result.append("  Rent:   ")
                                                .append(item.getMovie().getTitle())
                                                .append(" (" + item.getDaysRented() + " days): ")
                                                .append(charge).append("\n");
                        } else {
                                totalPurchase += charge;
                                result.append("  Purchase: ")
                                                .append(item.getMovie().getTitle())
                                                .append(": ")
                                                .append(charge).append("\n");
                        }
                }

                double discount = (coupon != null)
                                ? coupon.apply(items)
                                : 0;

                double grandTotal = totalRental + totalPurchase - discount;

                result.append("Total rental: ").append(totalRental).append("\n");
                result.append("Total purchase: ").append(totalPurchase).append("\n");
                result.append("Coupon discount: ").append(discount).append("\n");
                result.append("Amount owed: ").append(grandTotal).append("\n");
                result.append("You earned ").append(totalPoints).append(" frequent points\n");

                return result.toString();
        }
}

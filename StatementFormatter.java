public class StatementFormatter {
    public static String formatText(Customer customer) {
        StringBuilder result = new StringBuilder("Rental Record for " + customer.getName() + "\n");
        double totalAmount = 0;
        int frequentRenterPoints = 0;

        for (Rental rental : customer.getRentals()) {
            double thisAmount = rental.getCharge();
            frequentRenterPoints += rental.getFrequentRenterPoints();

            result.append("\t")
                    .append(rental.getTitle())
                    .append("\t")
                    .append(thisAmount)
                    .append("\n");
            totalAmount += thisAmount;
        }

        for (Coupon coupon : customer.getCoupons()) {
            totalAmount = coupon.applyDiscount(totalAmount);
        }

        result.append("Amount owed is ")
                .append(totalAmount)
                .append("\n");
        result.append("You earned ")
                .append(frequentRenterPoints)
                .append(" frequent renter points\n");
        result.append("Points available for redemption: ")
                .append(customer.getFrequentRenterPointsBalance());

        return result.toString();
    }

    public static String formatXML(Customer customer) {
        StringBuilder xml = new StringBuilder("<customer>\n");
        xml.append("\t<name>")
                .append(customer.getName())
                .append("</name>\n");

        for (Rental rental : customer.getRentals()) {
            xml.append("\t<rental>\n")
                    .append("\t\t<movie>").append(rental.getTitle()).append("</movie>\n")
                    .append("\t\t<days>").append(rental.getDaysRented()).append("</days>\n")
                    .append("\t\t<charge>").append(rental.getCharge()).append("</charge>\n")
                    .append("\t</rental>\n");
        }

        double totalAmount = customer.getRentals().stream()
                .mapToDouble(Rental::getCharge)
                .sum();
        xml.append("\t<totalBeforeCoupons>").append(totalAmount).append("</totalBeforeCoupons>\n");

        for (Coupon coupon : customer.getCoupons()) {
            totalAmount = coupon.applyDiscount(totalAmount);
        }
        xml.append("\t<totalAfterCoupons>").append(totalAmount).append("</totalAfterCoupons>\n");

        xml.append("\t<frequentPointsEarned>")
                .append(customer.getRentals().stream().mapToInt(Rental::getFrequentRenterPoints).sum())
                .append("</frequentPointsEarned>\n");
        xml.append("\t<pointsAvailable>")
                .append(customer.getFrequentRenterPointsBalance())
                .append("</pointsAvailable>\n");

        xml.append("</customer>");
        return xml.toString();
    }
}

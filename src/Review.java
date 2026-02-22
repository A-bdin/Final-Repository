public class Review {

    private String productId;
    private String buyerUsername;
    private String comment;

    public Review(String productId, String buyerUsername, String comment) {
        this.productId = productId;
        this.buyerUsername = buyerUsername;
        this.comment = comment;
    }

    public String getProductId() {
        return productId;
    }

    public String getBuyerUsername() {
        return buyerUsername;
    }

    public String getComment() {
        return comment;
    }

    public String toFileString() {
        return productId + "|" + buyerUsername + "|" + comment;
    }

    public static Review fromFileString(String line) {

        if (line == null) return null;

        String[] parts = line.split("\\|");
        if (parts.length != 3) return null;

        return new Review(parts[0], parts[1], parts[2]);
    }
}
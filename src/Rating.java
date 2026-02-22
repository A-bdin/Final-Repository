public class Rating {

    private String productId;
    private String buyerUsername;
    private int rating; // 1-5

    public Rating(String productId, String buyerUsername, int rating) {
        this.productId = productId;
        this.buyerUsername = buyerUsername;
        this.rating = rating;
    }

    public String getProductId() {
        return productId;
    }

    public String getBuyerUsername() {
        return buyerUsername;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String toFileString() {
        return productId + "|" + buyerUsername + "|" + rating;
    }

    public static Rating fromFileString(String line) {

        if (line == null) return null;

        String[] parts = line.split("\\|");
        if (parts.length != 3) return null;

        int rating = Integer.parseInt(parts[2]);

        return new Rating(parts[0], parts[1], rating);
    }
}
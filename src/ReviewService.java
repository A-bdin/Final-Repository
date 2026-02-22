import java.util.ArrayList;
import java.io.*;

public class ReviewService {

    private ArrayList<Review> reviews;

    public ReviewService() {
        reviews = new ArrayList<Review>();
        loadFromFile();
    }

    public void addReview(String productId, String buyerUsername, String comment) {

        Review review = new Review(productId, buyerUsername, comment);
        reviews.add(review);
        saveToFile();
    }

    public ArrayList<Review> getReviewsForProduct(String productId) {

        ArrayList<Review> result = new ArrayList<Review>();

        for (int i = 0; i < reviews.size(); i++) {

            Review r = reviews.get(i);

            if (r.getProductId().equals(productId)) {
                result.add(r);
            }
        }

        return result;
    }

    private void saveToFile() {

        try {

            FileWriter writer = new FileWriter("reviews.txt");

            for (int i = 0; i < reviews.size(); i++) {
                writer.write(reviews.get(i).toFileString() + "\n");
            }

            writer.close();

        } catch (Exception e) {
        }
    }

    private void loadFromFile() {

        try {

            File file = new File("reviews.txt");
            if (!file.exists()) return;

            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;

            while ((line = reader.readLine()) != null) {

                Review review = Review.fromFileString(line);

                if (review != null) {
                    reviews.add(review);
                }
            }

            reader.close();

        } catch (Exception e) {
        }
    }
}
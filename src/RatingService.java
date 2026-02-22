import java.util.ArrayList;
import java.io.*;

public class RatingService {

    private ArrayList<Rating> ratings;

    public RatingService() {
        ratings = new ArrayList<Rating>();
        loadFromFile();
    }

    public boolean addOrUpdateRating(String productId, String buyerUsername, int rating) {

        if (rating < 1 || rating > 5) {
            return false;
        }

        for (int i = 0; i < ratings.size(); i++) {

            Rating r = ratings.get(i);

            if (r.getProductId().equals(productId)
                    && r.getBuyerUsername().equals(buyerUsername)) {

                r.setRating(rating);
                saveToFile();
                return true;
            }
        }

        Rating newRating = new Rating(productId, buyerUsername, rating);
        ratings.add(newRating);
        saveToFile();
        return true;
    }

    public ArrayList<Rating> getRatingsForProduct(String productId) {

        ArrayList<Rating> result = new ArrayList<Rating>();

        for (int i = 0; i < ratings.size(); i++) {

            Rating r = ratings.get(i);

            if (r.getProductId().equals(productId)) {
                result.add(r);
            }
        }

        return result;
    }

    public double getAverageRating(String productId) {

        int total = 0;
        int count = 0;

        for (int i = 0; i < ratings.size(); i++) {

            Rating r = ratings.get(i);

            if (r.getProductId().equals(productId)) {
                total += r.getRating();
                count++;
            }
        }

        if (count == 0) return 0.0;

        return (double) total / count;
    }

    public String convertToStars(int rating) {

        String stars = "";

        for (int i = 0; i < rating; i++) {
            stars = stars + "*";
        }

        return stars;
    }

    public String getAverageRatingStars(String productId) {

        double avg = getAverageRating(productId);
        int rounded = (int) Math.round(avg);

        return convertToStars(rounded);
    }

    private void saveToFile() {

        try {

            FileWriter writer = new FileWriter("ratings.txt");

            for (int i = 0; i < ratings.size(); i++) {
                writer.write(ratings.get(i).toFileString() + "\n");
            }

            writer.close();

        } catch (Exception e) {
        }
    }

    private void loadFromFile() {

        try {

            File file = new File("ratings.txt");
            if (!file.exists()) return;

            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;

            while ((line = reader.readLine()) != null) {

                Rating rating = Rating.fromFileString(line);

                if (rating != null) {
                    ratings.add(rating);
                }
            }

            reader.close();

        } catch (Exception e) {
        }
    }
}
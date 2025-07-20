import org.apache.mahout.cf.taste.eval.*;
import org.apache.mahout.cf.taste.impl.eval.*;
import org.apache.mahout.cf.taste.impl.model.file.*;
import org.apache.mahout.cf.taste.impl.neighborhood.*;
import org.apache.mahout.cf.taste.impl.recommender.*;
import org.apache.mahout.cf.taste.impl.similarity.*;
import org.apache.mahout.cf.taste.model.*;
import org.apache.mahout.cf.taste.neighborhood.*;
import org.apache.mahout.cf.taste.recommender.*;
import org.apache.mahout.cf.taste.similarity.*;

import java.io.*;
import java.util.*;

public class RecommenderApp {

    public static void main(String[] args) throws Exception {
        // Load the data from CSV
        DataModel model = new FileDataModel(new File("data/ratings.csv"));

        // Use Pearson Correlation similarity
        UserSimilarity similarity = new PearsonCorrelationSimilarity(model);

        // Define the neighborhood
        UserNeighborhood neighborhood = new NearestNUserNeighborhood(2, similarity, model);

        // Create the recommender
        Recommender recommender = new GenericUserBasedRecommender(model, neighborhood, similarity);

        // Recommend 3 items for user with ID 1
        List<RecommendedItem> recommendations = recommender.recommend(1, 3);

        for (RecommendedItem recommendation : recommendations) {
            System.out.println("Recommended Item: " + recommendation.getItemID() + " , Value: " + recommendation.getValue());
        }
    }
}

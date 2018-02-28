package lab1;
import lab1.movie.MovieReviews;
public class Main {
    public static void main(String[] args){
        MovieReviews movieReviews = new MovieReviews("PPLLab1Data.txt","ignored.txt");
        System.out.println(movieReviews.reviewScore("A weak script that ends with a quick and boring finale"));
    }
}

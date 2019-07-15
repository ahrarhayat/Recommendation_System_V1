
/**
 * Write a description of MovieRunnerAverage here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
public class MovieRunnerAverage {
    public void printAverageRatings ()
    {
    SecondRatings sr = new SecondRatings("data/ratedmoviesfull.csv","data/ratings.csv");
    System.out.println("Number of movies: "+sr.getMovieSize());
    System.out.println("Number of raters: "+sr.getRaterSize());
    ArrayList<Rating> ratings = sr.getAverageRatings(12);
    System.out.println("There are "+ratings.size()+" with 12 or more ratings");
    for(Rating r: ratings)
    {
        System.out.println(sr.getTitle(r.getItem())+" "+r.getValue());
    }
   }
    public void getAverageRatingOneMovie()
   {
    SecondRatings sr = new SecondRatings("data/ratedmoviesfull.csv","data/ratings.csv");
    String id = sr.getID("Vacation");
    System.out.println(id);
    ArrayList<Rating> ratings= sr.getAverageRatings(3);
    for(Rating r:ratings)
    {
        String MovieId=r.getItem();
        if(id.equals(MovieId))
        {
            System.out.print("Rating: "+r.getValue());
        }
    }
   }
}

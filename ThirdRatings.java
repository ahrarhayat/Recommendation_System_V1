
/**
 * Write a description of ThirdRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class ThirdRatings {
private ArrayList<Rater> myRaters;

public ThirdRatings() {
        // default constructor
        this("ratings.csv");
    }
    public ThirdRatings(String ratingsfile) {
        FirstRatings fr= new FirstRatings();
        myRaters= fr.loadRaters(ratingsfile);
    }
   
    public int getRaterSize()
    {
        return myRaters.size();
    }
    private double getAverageByID(String MovieId, int minimalRaters)
    {
        int n=0;
        int movieRatingCounter=0;
        double movieIdCounter=0;
        double totalRating=0;
        for(Rater r: myRaters)
        {
            ArrayList<String> list =r.getItemsRated();
            for(String item:list)
            {
            if(item.equals(MovieId))
            {
                movieRatingCounter=movieRatingCounter+1;
                
            }
            
        }
        }
        //System.out.println("item has " +" "+movieRatingCounter+" ratings");
        if (movieRatingCounter>=minimalRaters)
        {
         for(Rater r: myRaters)
         {
             Double rating= r.getRating(MovieId);
             if(rating>0)
             {
                totalRating=totalRating+ rating;
                movieIdCounter=movieIdCounter+1;
             }
         }
        }
        else {
         return 0.0;
        }
        //ArrayList<String> movies = 
        return totalRating/movieIdCounter;
    }
    public ArrayList<Rating> getAverageRatings(int minimalRaters)
    {
        ArrayList<Rating> avgRatings= new ArrayList<Rating>();
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        for(String m: movies)
        {
            double avgRating=getAverageByID(m,minimalRaters);
            Rating rating = new Rating(m,avgRating);
            if(avgRating!=0.0){
            avgRatings.add(rating);
        }
        }
        Collections.sort(avgRatings);
        return avgRatings;
    }
    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters ,Filter filterCriteria)
    {
        ArrayList<Rating> avgRatings= new ArrayList<Rating>();
        ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
        for(String m: movies)
        {
            double avgRating=getAverageByID(m,minimalRaters);
            Rating rating = new Rating(m,avgRating);
            if(avgRating!=0.0){
            avgRatings.add(rating);
        }
        }
        Collections.sort(avgRatings);
        return avgRatings;
    }

}

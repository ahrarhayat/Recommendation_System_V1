
/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    
    public SecondRatings() {
        // default constructor
        this("ratedmoviesfull.csv", "ratings.csv");
    }
    public SecondRatings(String moviefile, String ratingsfile) {
        FirstRatings fr= new FirstRatings();
        myMovies = fr.loadMovies(moviefile);
        myRaters= fr.loadRaters(ratingsfile);
    }
    public int getMovieSize()
    {
        return myMovies.size();
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
        for(Movie m: myMovies)
        {
            String moieId= m.getID();
            double avgRating=getAverageByID(moieId,minimalRaters);
            Rating rating = new Rating(moieId,avgRating);
            if(avgRating!=0.0){
            avgRatings.add(rating);
        }
        }
        Collections.sort(avgRatings);
        return avgRatings;
    }
    public String getTitle(String id)
    {
        String title="";
        for(Movie m: myMovies)
        {
            if(m.getID().equals(id))
            {
                title= m.getTitle();
            }
        }
        if(title.equals(""))
        {
            return "Movie not found";
        }
        return title;
    }
    public String getID(String title)
    {
        String id="";
        for(Movie m: myMovies)
        {
            if(m.getTitle().equals(title))
            {
                id=m.getID();
            }
        }
        if(id.equals(""))
        {
            return "NO SUCH TITLE";
        }
        return id;
    }
    
}


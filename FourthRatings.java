
/**
 * Write a description of FourthRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class FourthRatings {
    RaterDatabase rb = new RaterDatabase();
    MovieDatabase mv = new MovieDatabase();
    private double getAverageByID(String MovieId, int minimalRaters)
    {
        int n=0;
        int movieRatingCounter=0;
        double movieIdCounter=0;
        double totalRating=0;
        rb.addRatings("data/ratings.csv");
        ArrayList<Rater> ratings= rb.getRaters();
        for(Rater r: rb.getRaters())
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
            for(Rater r: rb.getRaters())
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

    private static double dotProduct(Rater me, Rater r)
    {
        ArrayList<String> allMovies= MovieDatabase.filterBy(new TrueFilter());
        double product=0;
        for(String s: allMovies)
        {
            if(r.hasRating(s) && me.hasRating(s))
            {
                double myRating= (me.getRating(s))-5;
                //System.out.println("my rating "+myRating);
                double rRating = (r.getRating(s))-5;
                //System.out.println("r rating "+rRating);
                product=product+(myRating*rRating);
            }
        }
        //System.out.println(product);
        return product;
    }

    private ArrayList<Rating> getSimilarities(String id)
    {
        ArrayList  <Rating> similarRatings= new ArrayList <Rating>();
        rb.addRatings("data/ratings.csv");
        ArrayList<Rater> allRaters = rb.getRaters();
        for(Rater r:allRaters)
        {
            String x = r.getID();
            if(!x.equals(id))
            {
                double product = dotProduct(rb.getRater(id),r);
                if(product>0)
                {
                    //System.out.println("product: "+product);
                    similarRatings.add(new Rating(x, product));
                }
            }
        }
        Collections.sort(similarRatings,Collections.reverseOrder());
        return similarRatings;
    }

    public ArrayList<Rating> getSimilarRatings(String id, int numSimilarRaters,int minimalRaters)
    {
        ArrayList<Rating> list = getSimilarities(id);
        ArrayList<Rating>MoviesList = new ArrayList<Rating>();
        ArrayList<String> allMovies = MovieDatabase.filterBy(new TrueFilter());
         
     for(int i=0;i<allMovies.size()-1;i++)
      {
      double Total =0;
      double NumOfRaters = 0;
      int n=0;
     for(int k=0;k<numSimilarRaters-1;k++)
      {
        String CurrentRaterID = list.get(k).getItem();
        Rater CurrentRater = RaterDatabase.getRater(CurrentRaterID);   
         if(CurrentRater.hasRating(allMovies.get(i))==true)
           { 
             NumOfRaters++;
             Total = Total + (list.get(k).getValue())* (CurrentRater.getRating(allMovies.get(i)));   
            }
            n=n+1;
            if(n==list.size())
            {
                break;
            }
         }
         if(NumOfRaters >= minimalRaters)
         {
          double Average = Total / NumOfRaters;  
          MoviesList.add(new Rating(allMovies.get(i),Average));
         }
       }
     Collections.sort(MoviesList,Collections.reverseOrder());
     return MoviesList;
    }
      public ArrayList<Rating> getSimilarRatingsWithFilter(String id, int numSimilarRaters,int minimalRaters,Filter filterCriteria)
    {
        ArrayList<Rating> list = getSimilarities(id);
        ArrayList<Rating>MoviesList = new ArrayList<Rating>();
        ArrayList<String> allMovies = MovieDatabase.filterBy(filterCriteria);
         
     for(int i=0;i<allMovies.size();i++)
      {
      double Total =0;
      double NumOfRaters = 0;
     for(int k=0;k<numSimilarRaters;k++)
      {
        String CurrentRaterID = list.get(k).getItem(); 
        Rater CurrentRater = RaterDatabase.getRater(CurrentRaterID);   
         if(CurrentRater.hasRating(allMovies.get(i))==true)
           { 
             NumOfRaters++;
             Total = Total + (list.get(k).getValue())* (CurrentRater.getRating(allMovies.get(i)));   
            }
         }
         if(NumOfRaters >= minimalRaters)
         {
          double Average = Total / NumOfRaters;  
          MoviesList.add(new Rating(allMovies.get(i),Average));
         }
       }
     Collections.sort(MoviesList,Collections.reverseOrder());
     return MoviesList;
    }
}

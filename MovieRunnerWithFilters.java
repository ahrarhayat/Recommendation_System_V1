
/**
 * Write a description of MovieRunnerWithFilters here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import org.apache.commons.csv.*;
import edu.duke.FileResource;

public class MovieRunnerWithFilters {
    public void printAverageRatings ()
    {
        ThirdRatings tr = new ThirdRatings("data/ratings.csv");
        MovieDatabase mv = new MovieDatabase();
        mv.initialize("ratedmoviesfull.csv");
        System.out.println("Number of raters: "+tr.getRaterSize());
        ArrayList<Rating> ratings = tr.getAverageRatings(35);
        System.out.println("There are "+ratings.size()+" with 35 or more ratings");
        for(Rating r: ratings)
        {
            System.out.println(mv.getTitle(r.getItem())+" "+r.getValue());
        }
    }
    
    public void printAverageRatingsByYear ()
    {
        ThirdRatings tr = new ThirdRatings("data/ratings.csv");
        MovieDatabase mv = new MovieDatabase();
        mv.initialize("ratedmoviesfull.csv");
        Filter yearAfterFilter = new YearAfterFilter(2000);
        ArrayList<Rating> ratings = tr.getAverageRatingsByFilter(20,yearAfterFilter);
        System.out.println("There are "+ratings.size()+" with 20 or more ratings");
        for(Rating r: ratings)
        {
            System.out.println(mv.getTitle(r.getItem())+" "+r.getValue());
        }
    }
    
    
    public void printAverageRatingsByGenre ()
    {
        ThirdRatings tr = new ThirdRatings("data/ratings.csv");
        MovieDatabase mv = new MovieDatabase();
        mv.initialize("ratedmoviesfull.csv");
        Filter genreFilter = new GenreFilter("Comedy");
        ArrayList<Rating> ratings = tr.getAverageRatingsByFilter(20,genreFilter);
        System.out.println("There are "+ratings.size()+" with 20 or more ratings");
        for(Rating r: ratings)
        {
            System.out.println(mv.getTitle(r.getItem())+" "+r.getValue());
            System.out.println(mv.getGenres(r.getItem()));
        }
    }
    public void printAverageRatingsByMinutes ()
    {
        ThirdRatings tr = new ThirdRatings("data/ratings.csv");
        MovieDatabase mv = new MovieDatabase();
        mv.initialize("ratedmoviesfull.csv");
        Filter minFilter = new MinutesFilter(105,135);
        ArrayList<Rating> ratings = tr.getAverageRatingsByFilter(5,minFilter);
        System.out.println("There are "+ratings.size()+" with 5 or more ratings");
        for(Rating r: ratings)
        {
            System.out.print(mv.getTitle(r.getItem())+" "+r.getValue());
            System.out.println(" Time: "+mv.getMinutes(r.getItem()));
        }
        
    }
    
    public void printAverageRatingsByDirectors()
    {
        ThirdRatings tr = new ThirdRatings("data/ratings.csv");
        MovieDatabase mv = new MovieDatabase();
        mv.initialize("ratedmoviesfull.csv");
        Filter directorFilter = new DirectorsFilter("Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack");
        ArrayList<Rating> ratings = tr.getAverageRatingsByFilter(4,directorFilter);
        System.out.println("There are "+ratings.size()+" with 4 or more ratings");
        for(Rating r: ratings)
        {
            System.out.print(mv.getTitle(r.getItem())+" "+r.getValue());
            System.out.println(" Directors: "+mv.getDirector(r.getItem()));
        }
    }
    public void printAverageRatingsByYearAfterAndGenre()
    {
        ThirdRatings tr = new ThirdRatings("data/ratings.csv");
        MovieDatabase mv = new MovieDatabase();
        mv.initialize("ratedmoviesfull.csv");
        Filter yearAfterFilter = new YearAfterFilter(1990);
        Filter genreFilter = new GenreFilter("Drama");
        ArrayList <Filter> f = new ArrayList <Filter>();
        f.add(yearAfterFilter);
        f.add(genreFilter);
        Filter allFilters = new AllFilters(f);
        ArrayList<Rating> ratings = tr.getAverageRatingsByFilter(8, allFilters);
        System.out.println("There are "+ratings.size()+" with 8 or more ratings");
        for(Rating r: ratings)
        { System.out.println(mv.getTitle(r.getItem())+" "+r.getValue()+" "+mv.getYear(r.getItem()));
          System.out.println(mv.getGenres(r.getItem()));
        }
    }
    public void printAverageRatingsByDirectorsAndMinutes()
    {
        ThirdRatings tr = new ThirdRatings("data/ratings.csv");
        MovieDatabase mv = new MovieDatabase();
        mv.initialize("ratedmoviesfull.csv");
        Filter min = new MinutesFilter(90,180);
        Filter dir= new DirectorsFilter("Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack");
        ArrayList <Filter> f = new ArrayList <Filter>();
        f.add(min);
        f.add(dir);
        Filter allFilters = new AllFilters(f);
        ArrayList<Rating> ratings = tr.getAverageRatingsByFilter(3, allFilters);
        System.out.println("There are "+ratings.size()+" with 3 or more ratings");
        for(Rating r: ratings)
        {
          System.out.println(mv.getTitle(r.getItem())+" Time: "+mv.getMinutes(r.getItem())+" "+r.getValue());
          System.out.println(mv.getDirector(r.getItem()));
        }
    }
    
    /* public void getAverageRatingOneMovie()
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
       }*/
}

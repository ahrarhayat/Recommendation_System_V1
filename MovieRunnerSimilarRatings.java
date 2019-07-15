
/**
 * Write a description of MovieRunnerSimilarRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import org.apache.commons.csv.*;
import edu.duke.FileResource;
public class MovieRunnerSimilarRatings {
 public void printAverageRatings ()
    {
        FourthRatings fr = new FourthRatings();
        MovieDatabase mv = new MovieDatabase();
        RaterDatabase rb = new RaterDatabase();
        rb.addRatings("data/ratings.csv");
        mv.initialize("ratedmoviesfull.csv");
        System.out.println("Number of raters: "+rb.size());
        ArrayList<Rating> ratings = fr.getAverageRatings(35);
        System.out.println("There are "+ratings.size()+" with 35 or more ratings"); 
        for(Rating r: ratings)
        {
            System.out.println(mv.getTitle(r.getItem())+" "+r.getValue());
        }
    }
 
 public void printAverageRatingsByYearAfterAndGenre()
    {
        FourthRatings fr = new FourthRatings();
        MovieDatabase mv = new MovieDatabase();
        mv.initialize("ratedmoviesfull.csv");
        RaterDatabase rb = new RaterDatabase();
        Filter yearAfterFilter = new YearAfterFilter(1990);
        Filter genreFilter = new GenreFilter("Drama");
        ArrayList <Filter> f = new ArrayList <Filter>();
        f.add(yearAfterFilter);
        f.add(genreFilter);
        Filter allFilters = new AllFilters(f);
        ArrayList<Rating> ratings = fr.getAverageRatingsByFilter(8, allFilters);
        System.out.println("There are "+ratings.size()+" with 8 or more ratings");
        for(Rating r: ratings)
        { System.out.println(mv.getTitle(r.getItem())+" "+r.getValue()+" "+mv.getYear(r.getItem()));
          System.out.println(mv.getGenres(r.getItem()));
        }
    }
 public void printSimilarRatings ()
 {
     FourthRatings fr = new FourthRatings();
     MovieDatabase mv = new MovieDatabase();
     mv.initialize("ratedmoviesfull.csv");
     RaterDatabase rb = new RaterDatabase();
     rb.addRatings("data/ratings.csv");
     ArrayList <Rating> results = fr.getSimilarRatings("71", 20, 5);
     for(Rating r:results)
     {
         System.out.println("Movie: "+mv.getTitle(r.getItem())+" "+ "Rating: "+ r.getValue());
     }
 }
 public void printSimilarRatingsByGenre()
 {
     FourthRatings fr = new FourthRatings();
     MovieDatabase mv = new MovieDatabase();
     mv.initialize("ratedmoviesfull.csv");
     RaterDatabase rb = new RaterDatabase();
     rb.addRatings("data/ratings.csv");
     Filter genreFilter = new GenreFilter("Mystery");
     ArrayList <Filter> f = new ArrayList <Filter>();
     Filter allFilters = new AllFilters(f);
     ArrayList <Rating> results = fr.getSimilarRatingsWithFilter("964", 20, 5, genreFilter);
     for(Rating r:results)
     {
         System.out.println("Movie: "+mv.getTitle(r.getItem())+" "+ "Similar Rating: "+ r.getValue());
         System.out.println("Genre "+mv.getGenres(r.getItem()));
     }
 }
 public void printSimilarRatingsByDirector ()
 {
     FourthRatings fr = new FourthRatings();
     MovieDatabase mv = new MovieDatabase();
     mv.initialize("ratedmoviesfull.csv");
     RaterDatabase rb = new RaterDatabase();
     rb.addRatings("data/ratings.csv");
     Filter directorsFilter = new DirectorsFilter("Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh");
     ArrayList <Filter> f = new ArrayList <Filter>();
     Filter allFilters = new AllFilters(f);
     ArrayList <Rating> results = fr.getSimilarRatingsWithFilter("120", 10, 2, directorsFilter);
      for(Rating r:results)
     {
         System.out.println("Movie: "+mv.getTitle(r.getItem())+" "+ "Similar Rating: "+ r.getValue());
         System.out.println("Directors "+mv.getDirector(r.getItem()));
     }
 }
 public void printSimilarRatingsByGenreAndMinutes ()
 {   FourthRatings fr = new FourthRatings();
     MovieDatabase mv = new MovieDatabase();
     mv.initialize("ratedmoviesfull.csv");
     RaterDatabase rb = new RaterDatabase();
     rb.addRatings("data/ratings.csv");
     Filter genreFilter = new GenreFilter("Drama");
     Filter minutesFilter = new MinutesFilter(80,160);
     ArrayList <Filter> f = new ArrayList <Filter>();
     f.add(genreFilter);
     f.add(minutesFilter);
     Filter allFilters = new AllFilters(f);
     ArrayList <Rating> results = fr.getSimilarRatingsWithFilter("168", 10, 3, allFilters);
      for(Rating r:results)
     {
         System.out.println("Movie: "+mv.getTitle(r.getItem())+" "+ " Similar Rating: "+ r.getValue()+" Minutes "+mv.getMinutes(r.getItem()));
         System.out.println("Genres "+mv.getGenres(r.getItem()));
     }
 }
 public void printSimilarRatingsByYearAfterAndMinutes ()
 {
     FourthRatings fr = new FourthRatings();
     MovieDatabase mv = new MovieDatabase();
     mv.initialize("ratedmoviesfull.csv");
     RaterDatabase rb = new RaterDatabase();
     rb.addRatings("data/ratings.csv");
     Filter yearAfterFilter = new YearAfterFilter(1975);
     Filter minutesFilter= new MinutesFilter(70,200);
     ArrayList <Filter> f = new ArrayList <Filter>();
     f.add(yearAfterFilter);
     f.add(minutesFilter);
     Filter allFilters = new AllFilters(f);
     ArrayList <Rating> results = fr.getSimilarRatingsWithFilter("314", 10, 5, allFilters);
     for(Rating r:results)
     {
         System.out.println("Movie: "+mv.getTitle(r.getItem())+" "+ " Similar Rating: "+ r.getValue()+" Minutes "+mv.getMinutes(r.getItem())+ " Year "+mv.getYear(r.getItem()));
         
     }
 }
}

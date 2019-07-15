
/**
 * Write a description of FirstRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;
public class FirstRatings {
public ArrayList<Movie> loadMovies(String filename)
{
    ArrayList<Movie> MovieList =new ArrayList<Movie>();
    FileResource fr = new FileResource(filename);
    
    for(CSVRecord rec: fr.getCSVParser())
    {
        String id= rec.get("id");
        String title=rec.get("title");
        String year= rec.get("year");
        String country= rec.get("country");
        String genre = rec.get("genre");
        String director = rec.get("director");
        int minutes= Integer.parseInt(rec.get("minutes"));
        String poster = rec.get("poster");
        Movie mov = new Movie(id,title,year,genre,director,country,poster,minutes);
        int index= genre.indexOf("Comedy");
        MovieList.add(mov);
    }
    return MovieList;
}
    public void testLoadMovies()
    {
        ArrayList<Movie> result = loadMovies("data/ratedmoviesfull.csv");
        int n=0;
        String director="";
        int maxDirectorCount=1;
        int r=0;
        for(Movie m: result)
        {
           // System.out.println(m.toString());
           String genre= m.getGenres();
           int index = genre.indexOf("Comedy");
           int minutes =m.getMinutes();
           String currDirector=m.getDirector();
           int currDirectorCount=0;
           for(Movie c: result)
        {
            if(c.getDirector().equals(currDirector))
            {
                currDirectorCount=currDirectorCount+1;
                if(currDirectorCount>maxDirectorCount)
                {
                    director=currDirector;
                    maxDirectorCount=maxDirectorCount+1;
                }
            }
            
        }
        if(index!=0)
        {
            r=r+1;
        }
            if(minutes> 150)
        {
            n=n+1;
        }
        }
        System.out.println("The number of movies with 150 "+n);
        System.out.println(director +" with "+maxDirectorCount);
        System.out.println("genre count "+r);
    }
    public ArrayList<Rater> loadRaters (String filename)
    {
        ArrayList<Rater> RaterList = new ArrayList<Rater>();
        ArrayList<Integer> RaterNum = new ArrayList<Integer>();
        ArrayList <String> ratedItems = new ArrayList<String>(); 
        FileResource fr = new FileResource(filename);
        for(CSVRecord rec: fr.getCSVParser())
       {
        String id = rec.get("rater_id");
        Rater rater = new EfficientRater(id);
        if(!RaterNum.contains(Integer.parseInt(id)))
        {
        RaterList.add(rater);
        RaterNum.add(Integer.parseInt(id));
        //System.out.println(id);
        for(CSVRecord rec1: fr.getCSVParser())
        {
            if(rec1.get("rater_id").equals(id))
            {
           //System.out.println(rec1.get("movie_id"));
          // System.out.println(rec1.get("rating"));
           rater.addRating( rec1.get("movie_id"),  Double.parseDouble(rec1.get("rating")));
           
        }
        }
        }
        
       }
       
        return RaterList;
    }
    public void testLoadRaters()
    {
      ArrayList <Rater> result = loadRaters("data/ratings.csv");
      System.out.println("Number of total raters: "+result.size());
      int n=0;
      int raterID=193;
      int r=0;
      int maxRating=0;
      int maxRatingID=0;
      String movieID="1798709";
      int movieCounter=0;
      ArrayList<String> diffMovies = new ArrayList<String>();
      for(Rater rater: result)
      {
          String id = rater.getID();
          
          System.out.println("ID: "+id+" ");
          ArrayList<String> list =rater.getItemsRated();
          for(String item:list)
          {
            if(item.equals(movieID))
            {
                movieCounter=movieCounter+1;
            }
            if(!diffMovies.contains(item))
            {
                diffMovies.add(item);
            }
             System.out.println(item+" "+rater.getRating(item));
             if(Integer.parseInt(id)==raterID)
             {
             n=n+1;
            }
            r=r+1;
          }
          if(maxRating==0)
          {
              maxRating=r;
              maxRatingID=Integer.parseInt(id);
          }
          if(r>maxRating)
          {
              maxRating=r;
              maxRatingID=Integer.parseInt(id);
          }
          r=0;
      }
      System.out.println("ID "+raterID+ " has "+ n +" ratings");
      System.out.println("ID "+maxRatingID+ " has "+ " max ratings "+" with "+maxRating+" ratings");
      System.out.println(movieID+ " has "+movieCounter+" raters ");
      System.out.println("There are "+diffMovies.size()+" different movies rated ");
    }
}

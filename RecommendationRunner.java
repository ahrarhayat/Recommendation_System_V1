
/**
 * Write a description of RecommendationRunner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class RecommendationRunner implements Recommender {

    public ArrayList<String> getItemsToRate (){
        ArrayList<String> MovieIDs = new ArrayList<String>();
        int n=0;
        Random random = new Random();
        ArrayList<String> allMovies= MovieDatabase.filterBy(new TrueFilter());
        int rand= random.nextInt(allMovies.size()-1);
        
        while(n<12)
        {
            MovieIDs.add(allMovies.get(rand));
            n=n+1;
            rand=random.nextInt(allMovies.size()-1);
            //System.out.println(allMovies.get(rand));
        }
        return MovieIDs;
    }
    
    
    public void printRecommendationsFor (String webRaterID){
    FourthRatings fr = new FourthRatings();
    MovieDatabase mv = new MovieDatabase();
    mv.initialize("ratedmoviesfull.csv");
    RaterDatabase rb = new RaterDatabase();
    rb.addRatings("data/ratings.csv");
    ArrayList<String> MoviesRatedByUser = getItemsToRate(); //Do not include movies the user rated here
    ArrayList <Rating> results = fr.getSimilarRatings(webRaterID, 10, 3);
    if(results.isEmpty()==true)
    {
        System.out.println("<html>  <head> <title> Movies </title> </head>  <style> body{ background-color: LightGray; } h1{ color: MidnightBlue; font: italic bold 60px Georgia, serif;} "
        +"p{font: italic bold 40px Georgia, serif;color: FireBrick;} </style>" +"<p> No movies are being recommended since you didn't rate enough movies, please go back and rate more movies to get results</p> </html>");
    }
    int TotalMoviesDisplayed =12;
    ArrayList <Rating> FinalListToDisplay= new ArrayList <Rating> ();
    for(Rating r:results)
    {
        if(MoviesRatedByUser.contains(r.getItem())==false ){
        FinalListToDisplay.add(r);
        TotalMoviesDisplayed=TotalMoviesDisplayed-1;
        if(TotalMoviesDisplayed<=0)
        {
            break;
        }
    }
    }
    
    System.out.println("<html>  <head> <title> Movies </title> </head>  <style> body{ background-color: LightGray; } h1{ color: MidnightBlue; font: italic bold 60px Georgia, serif;} "+
    "th{ font: italic bold 50px Georgia, serif;color: maroon;} td{font: italic bold 40px Georgia, serif;color: Black;} p{font: italic bold 40px Georgia, serif;color: FireBrick;} </style> <h1>Movie Recommendation</h1>"+
    "<table>  <tr><th>Recommended Movies:</th></tr>");
    for(int k=0;k<FinalListToDisplay.size();k++)
    {
        System.out.println( "<tr><td>"+MovieDatabase.getTitle(FinalListToDisplay.get(k).getItem())+"</td></tr>");
        System.out.println("<tr><td><p> "+" GENRES: "+MovieDatabase.getGenres(FinalListToDisplay.get(k).getItem())+ " YEAR: "+MovieDatabase.getYear(FinalListToDisplay.get(k).getItem())+"</p></td></tr> "+" ");
    }
    System.out.println( "</table> </html>");
    }
}

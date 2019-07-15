
/**
 * Write a description of DirectorsFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DirectorsFilter implements Filter {

     private String[] dir;

    public DirectorsFilter(String directors ){
        dir = directors.split("\\s*,");
    }
    @Override
    public boolean satisfies(String id) {
        String[] movieDirectors = MovieDatabase.getDirector(id).split("\\s*,");
        for (int i = 0; i < movieDirectors.length; i++){
            movieDirectors[i] = movieDirectors[i].trim();
        }

        for (int i = 0; i <movieDirectors.length ; i++) {
          for (String s: dir) {
            if (s.equals(movieDirectors[i])){
                return true;
            }
          }
        }
        return false;
    }
}


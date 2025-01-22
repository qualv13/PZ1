package kolos1;



import java.util.*;

public class Movie {
    String series_title;
    int release_year;
    // certyficate
    double runtime;
    List<String> genres;
    double IMDB_Rating;
    double Meta_score;
    String Director;
    Set<String> Stars = new HashSet<String>();;
    int No_of_Votes;
    double Gross;

    // C:\Users\jakub\IdeaProjects\PZ1\lab1\src\kolos1

    @Override
    public String toString(){
        String out = "";
        out += "Title: " + series_title + "\n";
        out += "Release Year: " + release_year + "\n";
        out += "Runtime: " + runtime + "\n";
        if(genres != null){
            out += "Genres: \n";
        }
        for (String genre : genres){

            if(genre != null){
                out += genre + "\n";
            }
        }
        out += "IMDB: " + IMDB_Rating + "\n";
        if(Meta_score != -1){
            out += "Meta: " + Meta_score + "\n";
        }
        out += "Director: " + Director + "\n";
        out += "\n";
        return out;
    }
}



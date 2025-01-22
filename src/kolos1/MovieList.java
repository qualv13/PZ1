package kolos1;

import lab6.CSVReader;



import java.io.IOException;
import java.io.PrintStream;
import java.util.*;

public class MovieList {
    List<Movie> movieList = new ArrayList<>();
    List<String> actors = new ArrayList<>();
    Map<String, Double> No_of_movies = new HashMap<>();

    void read(String filename) throws IOException {

        CSVReader reader = new CSVReader("C://Users//jakub//IdeaProjects//PZ1//lab1//src//kolos1//" + filename);
        while (reader.next()) {
            Movie movie = new Movie();

            try {
                movie.series_title = reader.get("Series_Title");
            } catch (RuntimeException e) {
                movie.series_title = null;
            }
            int release_year;
            try {
                movie.release_year = reader.getInt("Released_Year");
            } catch (RuntimeException e) {
                movie.release_year = -1;
            }
            double runtime;
            try {
                movie.runtime = reader.getDouble("Runtime");
            } catch (RuntimeException e) {
                movie.runtime = -1;
            }
            String genre;
            try {
                genre = reader.get("Genre");
            } catch (RuntimeException e) {
                genre = null;
            }
            String subgenre;
            try {
                subgenre = reader.get("Subgenre");
            } catch (RuntimeException e) {
                subgenre = null;
            }
            String subgenre1;
            try {
                subgenre1 = reader.get("Subgenre 1");
            } catch (RuntimeException e) {
                subgenre1 = null;
            }

            List<String> genres = new ArrayList<String>();
            genres.add(genre);
            genres.add(subgenre);
            genres.add(subgenre1);

            movie.genres = genres;
            //movie.genres.add(genre); movie.genres.add(subgenre); movie.genres.add(subgenre1);

            double IMDB_Rating;
            try {
                movie.IMDB_Rating = reader.getDouble("IMDB_Rating");
            } catch (RuntimeException e) {
                movie.IMDB_Rating = -1;
            }
            double Meta_score;
            try {
                movie.Meta_score = reader.getDouble("Meta_score");
            } catch (RuntimeException e) {
                movie.Meta_score = -1;
            }
            String Director;
            try {
                movie.Director = reader.get("Director");
            } catch (RuntimeException e) {
                movie.Director = null;
            }
            if(!No_of_movies.containsKey(movie.Director)) {
                No_of_movies.put(movie.Director, 0.0);
            }else{
                No_of_movies.put(movie.Director, No_of_movies.get(movie.Director) + 1);
            }
            String star1, star2, star3, star4;
            try {
                star1 = reader.get("Star1");
            } catch (RuntimeException e) {
                star1 = null;
            }
            try {
                star2 = reader.get("Star2");
            } catch (RuntimeException e) {
                star2 = null;
            }
            try {
                star3 = reader.get("Star3");
            } catch (RuntimeException e) {
                star3 = null;
            }
            try {
                star4 = reader.get("Star4");
            } catch (RuntimeException e) {
                star4 = null;
            }
            movie.Stars.add(star1);
            movie.Stars.add(star2);
            movie.Stars.add(star3);
            movie.Stars.add(star4);

            for(String star : movie.Stars) {
                if(!actors.contains(star)) {
                    actors.add(star);
                }
            }

            int No_of_Votes;
            try {
                movie.No_of_Votes = reader.getInt("No_of_Votes");
            } catch (RuntimeException e) {
                movie.No_of_Votes = -1;
            }
            double Gross;
            try {
                movie.Gross = reader.getDouble("Gross");
            } catch (RuntimeException e) {
                movie.Gross = -1;
            }
            movieList.add(movie);
        }
    }

    //    String series_title;
//    int release_year;
//    // certyficate
//    double runtime;
//    List<String> genres;
//    double IMDB_Rating;
//    double Meta_score;
//    String Director;
//    Set<String> Stars;
//    int No_of_Votes;
//    double Gross;



    MovieList selectByName(String pattern) {
        MovieList ret = new MovieList();
        for (Movie unit : movieList) {
            if (Objects.equals(unit.Director, pattern)) {
                ret.movieList.add(unit);
            }
        }
        return ret;
    }

    void list(PrintStream out){
        for(Movie unit : movieList) {
            out.append(unit.toString());
        }
    }

    MovieList sortInplaceByIMDB(){
        Comparator<Movie> comparator = new Comparator<Movie>() {
            @Override
            public int compare(Movie o1, Movie o2) {
                if(o1.IMDB_Rating>o2.IMDB_Rating) {
                    return 1;
                }
                if(o1.IMDB_Rating<o2.IMDB_Rating) {
                    return -1;
                }
                return 0;
            }
        };
        movieList.sort(comparator);
        return this;
    }
}


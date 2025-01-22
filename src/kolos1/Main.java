package kolos1;

// Movie main

import kolos1.*;

import java.io.IOException;
import java.io.PrintStream;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws IOException {
        MovieList movieList = new MovieList();
        movieList.read("movies_data.csv");

        System.out.println("Number of actors is equal " + movieList.actors.size());
        System.out.println("Top 10 Directors: " + movieList.No_of_movies.size());
        for (int i = 0; i < 10; i++){
            System.out.println(movieList.No_of_movies.get(i));
        }

        PrintStream out = new PrintStream("movie_list_director.txt");
        movieList.selectByName("Roman Polanski").list(out);

        MovieList movieList2 = new MovieList();
        movieList2.read("movies_data.csv");
        PrintStream out2 = new PrintStream("movie_list2.txt");
        MovieListQuery query = new MovieListQuery()
                .selectFrom(movieList2)
                .sort(Comparator.comparingDouble(a -> a.IMDB_Rating))
                .limit(20)
                .sort(Comparator.comparingDouble(a -> a.release_year))
                .sort(Comparator.comparingDouble(a -> a.IMDB_Rating));
        query.execute().list(out2);

        MovieList movieList3 = new MovieList();
        movieList3.read("movies_data.csv");
        PrintStream out3 = new PrintStream("movie_list3.txt");



    }


}

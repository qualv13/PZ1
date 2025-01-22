package kolos1;

import lab7.AdminUnit;
import lab7.AdminUnitList;
import lab7.AdminUnitQuery;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class MovieListQuery {
    MovieList src;
    Predicate<Movie> p = a->true;
    Comparator<Movie> cmp = (a, b) -> 0;
    int limit = Integer.MAX_VALUE;
    int offset = 0;

    MovieListQuery selectFrom(MovieList src){
        this.src = src;
        return this;
    }

    /**
     *
     * @param pred - ustawia predykat p
     * @return this
     */
    MovieListQuery where(Predicate<Movie> pred){
        this.p = pred;
        return this;
    }

    /**
     * Wykonuje operacj� p = p and pred
     * @param pred pred
     * @return this
     */
    MovieListQuery and(Predicate<Movie> pred){
        this.p = this.p.and(pred);
        return this;
    }
    /**
     * Wykonuje operacj� p = p or pred
     * @param pred pred
     * @return this
     */
    MovieListQuery or(Predicate<Movie> pred){
        this.p = this.p.or(pred);
        return this;
    }

    /**
     * Ustawia komparator cmp
     * @param cmp porownywator
     * @return this
     */
    MovieListQuery sort(Comparator<Movie> cmp){
        this.cmp = cmp;
        return this;
    }

    MovieListQuery limit(int limit){
        this.limit = limit;
        return this;
    }
    /**
     * Ustawia offset
     * @param offset offseter
     * @return this
     */
    MovieListQuery offset(int offset){
        this.offset = offset;
        return this;
    }

    /**
     * Wykonuje zapytanie i zwraca wynikow� list�
     * @return przefiltrowana i opcjonalnie posortowana lista (uwzgl�dniamy tak�e offset/limit)
     */
    MovieList execute(){
        List<Movie> result = new ArrayList<>();

        // Filter units
        for (Movie unit : src.movieList) {
            if (p.test(unit)) {
                result.add(unit);
            }
        }

        // Apply offset and limit
        int start = Math.min(offset, result.size());
        int end = Math.min(start + limit, result.size());

        MovieList filteredList = new MovieList();
        filteredList.movieList = result.subList(start, end);

        return filteredList;
    }
}

package lab7;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class AdminUnitQuery {
    AdminUnitList src;
    Predicate<AdminUnit> p = a->true;
    Comparator<AdminUnit> cmp = (a,b) -> 0;
    int limit = Integer.MAX_VALUE;
    int offset = 0;

    /**
     * Ustawia list� jako przetwarzane �r�d�o
     * @param src src
     * @return this
     */
    AdminUnitQuery selectFrom(AdminUnitList src){
        this.src = src;
        return this;
    }

    /**
     *
     * @param pred - ustawia predykat p
     * @return this
     */
    AdminUnitQuery where(Predicate<AdminUnit> pred){
        this.p = pred;
        return this;
    }

    /**
     * Wykonuje operacj� p = p and pred
     * @param pred pred
     * @return this
     */
    AdminUnitQuery and(Predicate<AdminUnit> pred){
        this.p = this.p.and(pred);
        return this;
    }
    /**
     * Wykonuje operacj� p = p or pred
     * @param pred pred
     * @return this
     */
    AdminUnitQuery or(Predicate<AdminUnit> pred){
        this.p = this.p.or(pred);
        return this;
    }

    /**
     * Ustawia komparator cmp
     * @param cmp porownywator
     * @return this
     */
    AdminUnitQuery sort(Comparator<AdminUnit> cmp){
        this.cmp = cmp;
        return this;
    }

    /**
     * Ustawia limit
     * @param limit limiter
     * @return this
     */
    AdminUnitQuery limit(int limit){
        this.limit = limit;
        return this;
    }
    /**
     * Ustawia offset
     * @param offset offseter
     * @return this
     */
    AdminUnitQuery offset(int offset){
        this.offset = offset;
        return this;
    }

    /**
     * Wykonuje zapytanie i zwraca wynikow� list�
     * @return przefiltrowana i opcjonalnie posortowana lista (uwzgl�dniamy tak�e offset/limit)
     */
    AdminUnitList execute(){
        List<AdminUnit> result = new ArrayList<>();

        // Filter units
        for (AdminUnit unit : src.units) {
            if (p.test(unit)) {
                result.add(unit);
            }
        }

        // Apply offset and limit
        int start = Math.min(offset, result.size());
        int end = Math.min(start + limit, result.size());

        AdminUnitList filteredList = new AdminUnitList();
        filteredList.units = result.subList(start, end);

        return filteredList;
    }
}
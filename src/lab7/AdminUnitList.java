package lab7;

import lab6.CSVReader;

import java.io.*;
import java.util.*;
import java.util.function.Predicate;

// 8 - miasta (1200); 11 - 192; od 8 poziomu administracyjnego są braki dzieci
public class AdminUnitList {
    List<AdminUnit> units = new ArrayList<>();
    List<AdminUnit> roots = new ArrayList<>();

    /**
     * Czyta rekordy pliku i dodaje do listy
     * @param filename nazwa pliku
     */
    void read(String filename) throws IOException {
        Map<Long, AdminUnit> idToUnitMap = new HashMap<>();
        Map<AdminUnit, Long> unitToIdMap = new HashMap<>();
        Map<Long, Long> idToParentId = new HashMap<>();
        CSVReader reader = new CSVReader("C://Users//jakub//IdeaProjects//PZ1//lab1//src//lab7//" + filename); //   "C://ProjektyStudia//PZ1//src//lab7//"
        while(reader.next()) {
            AdminUnit unit = new AdminUnit();
            long id, parentId;
            try{id = reader.getInt("id");}catch (RuntimeException e){id = -1;}

            idToUnitMap.put(id, unit);
            unitToIdMap.put(unit, id);

            try{parentId = reader.getInt("parent");}catch (RuntimeException e){parentId = -1;}
            idToParentId.put(id, parentId);

            try{unit.name = reader.get("name");}catch (RuntimeException e){unit.name = null;}
            try{unit.adminLevel = reader.getInt("admin_level");}catch (RuntimeException e){unit.adminLevel = -1;}
            try{unit.population = reader.getDouble("population");}catch (RuntimeException e){unit.population = -1;}
            try{unit.area = reader.getDouble("area");}catch (RuntimeException e){unit.area = -1;}
            try{unit.density = reader.getDouble("density");}catch (RuntimeException e){unit.density = -1;}
            double x1 = reader.getDouble("x1");
            double y1 = reader.getDouble("y1");
            double x2 = reader.getDouble("x2");
            double y2 = reader.getDouble("y2");
            double x3 = reader.getDouble("x3");
            double y3 = reader.getDouble("y3");
            double x4 = reader.getDouble("x4");
            double y4 = reader.getDouble("y4");
            double x5 = reader.getDouble("x5");
            double y5 = reader.getDouble("y5");
            unit.bbox.addPoint(x1, y1); unit.bbox.addPoint(x2, y2); unit.bbox.addPoint(x3, y3); unit.bbox.addPoint(x4, y4); unit.bbox.addPoint(x5, y5);
            units.add(unit);
        }

        for(AdminUnit unit : units) {
            long id = unitToIdMap.get(unit);
            long parentId = idToParentId.get(id);
            if(parentId != -1 && parentId != 0) {
                unit.parent = idToUnitMap.get(parentId);
                unit.parent.children.add(unit);
            }
            if(unit.adminLevel == 4){
                roots.add(unit);
            }
        }
        for(AdminUnit unit : units) {
            unit.fixMissingValues();
        }

    }
    /**
     * Wypisuje zawartość korzystając z AdminUnit.toString()
     * @param out
     */
    void list(PrintStream out){
        for(AdminUnit unit : units) {
            out.append(unit.toString());
        }
    }
    /**
     * Wypisuje co najwyżej limit elementów począwszy od elementu o indeksie offset
     * @param out - strumień wyjsciowy
     * @param offset - od którego elementu rozpocząć wypisywanie
     * @param limit - ile (maksymalnie) elementów wypisać
     */
    void list(PrintStream out, int offset, int limit ){
        for(int i = offset; i < offset + limit; i++) {
            out.append(units.get(i).toString());
        }
    }


    /**
     * Zwraca nową listę zawierającą te obiekty AdminUnit, których nazwa pasuje do wzorca
     * @param pattern - wzorzec dla nazwy
     * @param regex - jeśli regex=true, użyj finkcji String matches(); jeśli false użyj funkcji contains()
     * @return podzbiór elementów, których nazwy spełniają kryterium wyboru
     */
    AdminUnitList selectByName(String pattern, boolean regex){
        AdminUnitList ret = new AdminUnitList();
        for(AdminUnit unit : units) {
            if(regex && unit.name.matches(pattern)) {
                ret.units.add(unit);
            } else if (!regex && unit.name.contains(pattern)) {
                ret.units.add(unit);
            }
        }
        return ret;
    }

    private void fixMissingValues(){
        for(AdminUnit unit : units) {
            if(unit.parent.density == 0){
                fixMissingValues(unit.parent);
            }
            if(unit.density == 0){
                unit.density = unit.parent.density;
            }
            if(unit.population == 0){
                unit.population = unit.area * unit.density;
            }
        }
    }

    void fixMissingValues(AdminUnit au){
        if(au.density == 0){
            au.density = au.parent.density;
        }
        if(au.population == 0 && au.density > 0){
            au.population = au.area * au.density;
        }
    }

    /**
     * Zwraca listę jednostek sąsiadujących z jednostką unit na tym samym poziomie hierarchii admin_level.
     * Czyli sąsiadami wojweództw są województwa, powiatów - powiaty, gmin - gminy, miejscowości - inne miejscowości
     * @param unit - jednostka, której sąsiedzi mają być wyznaczeni
     * @param maxdistance - parametr stosowany wyłącznie dla miejscowości, maksymalny promień odległości od środka unit,
     *                    w którym mają sie znaleźć punkty środkowe BoundingBox sąsiadów
     * @return lista wypełniona sąsiadami
     */
    AdminUnitList getNeighbors(AdminUnit unit, double maxdistance){
        AdminUnitList ret = new AdminUnitList();
        for(AdminUnit child : units) {
            if(child.adminLevel == unit.adminLevel) {
                if (unit.bbox.intersects(child.bbox)) {
                    ret.units.add(child);
                }else if(unit.bbox.distanceTo(child.bbox) <= maxdistance){
                    ret.units.add(child);
                }
            }
        }
        return ret;
    }

    AdminUnitList getNeighborsLinear(AdminUnit unit, double maxdistance){
        AdminUnitList ret = new AdminUnitList();
        List<AdminUnit> hereRoots = roots;
        while(!hereRoots.isEmpty() && unit.adminLevel>hereRoots.getFirst().adminLevel) {
            AdminUnit root = hereRoots.removeFirst();

            if (unit.bbox.intersects(root.bbox)) {
                hereRoots.addAll(root.children);
            }else if(unit.bbox.distanceTo(root.bbox) <= maxdistance){
                hereRoots.addAll(root.children);
            }
        }

        for(AdminUnit units : hereRoots) {
            if (unit.bbox.intersects(units.bbox)) {
                ret.units.addAll(units.children);
            }else if(unit.bbox.distanceTo(units.bbox) <= maxdistance){
                ret.units.addAll(units.children);
            }
        }
        return ret;
    }

    AdminUnit getUnit(String nazwa){
        for(AdminUnit unit : units) {
            if(unit.name.equals(nazwa)) {
                return unit;
            }
        }
        return null;
    }

    /**
     * Sortuje daną listę jednostek (in place = w miejscu)
     * @return this
     */
    AdminUnitList sortInplaceByName()
    {
        class Compare implements Comparator<AdminUnit>{
            @Override
            public int compare(AdminUnit o1, AdminUnit o2) {
                return o1.name.compareTo(o2.name);
            }
        }
        units.sort(new Compare());
        return this;
    }

    /**
     * Sortuje daną listę jednostek (in place = w miejscu)
     * @return this
     */
    AdminUnitList sortInplaceByArea(){
        Comparator<AdminUnit> comparator = new Comparator<AdminUnit>() {
            @Override
            public int compare(AdminUnit o1, AdminUnit o2) {
                if(o1.area>o2.area) {
                    return 1;
                }
                if(o1.area<o2.area) {
                    return -1;
                }
                return 0;
            }
        };
        units.sort(comparator);
        return this;
    }

    /**
     * Sortuje daną listę jednostek (in place = w miejscu)
     * @return this
     */
    AdminUnitList sortInplaceByPopulation(){
        units.sort((p1, p2) -> Double.compare(p1.population, p2.population));
        return this;
    }

    AdminUnitList sortInplace(Comparator<AdminUnit> cmp){
        units.sort(cmp);
        return this;
    }

    AdminUnitList sort(Comparator<AdminUnit> cmp){
        AdminUnitList ret = new AdminUnitList();
        ret.units.addAll(units);
        ret.units.sort(cmp);
        return ret;
    }

    /**
     *
     * @param pred referencja do interfejsu Predicate
     * @return nową listę, na której pozostawiono tylko te jednostki,
     * dla których metoda test() zwraca true
     */
    AdminUnitList filter(Predicate<AdminUnit> pred){
        AdminUnitList res = new AdminUnitList();
        for(AdminUnit unit : units) {
            if(pred.test(unit)) {
                res.units.add(unit);
            }
        }
        return res;
    }

    /**
     * Zwraca co najwyżej limit elementów spełniających pred
     * @param pred - predykat
     * @param limit - maksymalna liczba elementów
     * @return nową listę
     */
    AdminUnitList filter(Predicate<AdminUnit> pred, int limit){
        AdminUnitList res = new AdminUnitList();
        this.filter(pred);
        while (res.units.size() < limit && this.units.size() <= limit) {
            res.units.add(this.units.get(res.units.size()-1));
        }
        return res;
    }

    /**
     * Zwraca co najwyżej limit elementów spełniających pred począwszy od offset
     * Offest jest obliczany po przefiltrowaniu
     * @param pred - predykat
     * @param - od którego elementu
     * @param limit - maksymalna liczba elementów
     * @return nową listę
     */
    AdminUnitList filter(Predicate<AdminUnit> pred, int offset, int limit){
        AdminUnitList res = new AdminUnitList();
        this.filter(pred);
        for (int i = offset; i < this.units.size(); i++) {
            if (limit-i>0){
                res.units.add(this.units.get(i));
            }
        }
        return res;
    }
}

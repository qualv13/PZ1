package lab7;

import lab6.CSVReader;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 8 - miasta (1200); 11 - 192; od 8 poziomu administracyjnego są braki dzieci
public class AdminUnitList {
    List<AdminUnit> units = new ArrayList<>();
    Map<Long,List<Long>> parentid2childid = new HashMap<>();

    /**
     * Czyta rekordy pliku i dodaje do listy
     * @param filename nazwa pliku
     */
    void read(String filename) throws IOException {
        Map<Long, AdminUnit> idToUnitMap = new HashMap<>();
        Map<AdminUnit, Long> unitToIdMap = new HashMap<>();
        Map<Long, Long> idToParentId = new HashMap<>();
        CSVReader reader = new CSVReader("C://Users//jakub//IdeaProjects//PZ1//lab1//src//lab7//" + filename);
        while(reader.next()) {
            AdminUnit unit = new AdminUnit();
            long id, parentId;
            try{id = reader.getInt("id");}catch (RuntimeException e){id = -1;}

            idToUnitMap.put(id, unit);
            unitToIdMap.put(unit, id);// ma być

            try{parentId = reader.getInt("parent");}catch (RuntimeException e){parentId = -1;}
            idToParentId.put(id, parentId);

            if(parentId == -1) {
                unit.parent = null;
            }else{
                idToParentId.put(id, parentId);
            }
            // dodajesz children jako to do klucza parenta tego, bo skoro on ma parenta, to ten wczytywany jest dzieckiem
            if(!parentid2childid.containsKey(parentId)) {
                parentid2childid.put(parentId, new ArrayList<>());
            }
            parentid2childid.get(parentId).add(id);



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
//        int id = reader.getInt("id");
//        int parent = reader.getInt("parent");
//        String name = reader.get("name");
//        int admin_level = reader.getInt("admin_level");
//        double population = reader.getDouble("population");
//        double area = reader.getDouble("area");
//        double density = reader.getDouble("density");
//        double x1 = reader.getDouble("x1");
//        double y1 = reader.getDouble("y1");
//        double x2 = reader.getDouble("x2");
//        double y2 = reader.getDouble("y2");
//        double x3 = reader.getDouble("x3");
//        double y3 = reader.getDouble("y3");
//        double x4 = reader.getDouble("x4");
//        double y4 = reader.getDouble("y4");
//        double x5 = reader.getDouble("x5");
//        double y5 = reader.getDouble("y5");
        }
//        for(AdminUnit unit : units) {
//            long id = unitToIdMap.get(unit);
//            long parentid = idToParentId.get(id);
//            if(parentid == -1) {
//                unit.parent = null;
//            }else{
//                idToParentId.put(id, parentid);
//            }
//        }
        // TODO: parentid2childid
        for(AdminUnit unit : units) {
            long id = unitToIdMap.get(unit);
            List<Long> childIds = parentid2childid.get(id);
            if(parentid2childid.containsKey(id)) {
                parentid2childid.put(id, childIds);
            }else{
                parentid2childid.put(id, childIds);
            }
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
        //System.out.println(out.toString());
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
        //System.out.println(out);
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
        // przeiteruj po zawartości units
        // jeżeli nazwa jednostki pasuje do wzorca dodaj do ret
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
                if (unit.bbox.contains(child.bbox)) {
                    ret.units.add(child);
                }else if(unit.bbox.distanceTo(child.bbox) <= maxdistance){
                    ret.units.add(child);
                }
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
}

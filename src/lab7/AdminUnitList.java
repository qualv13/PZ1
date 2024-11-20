package lab7;

import lab6.CSVReader;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class AdminUnitList {
    List<AdminUnit> units = new ArrayList<>();
    Map<Long,List<Long>> parentid2childid = new HashMap<>();

    /**
     * Czyta rekordy pliku i dodaje do listy
     * @param filename nazwa pliku
     */
    void read(String filename) throws IOException {
        Map<Long, AdminUnit> idMap = new HashMap<>();
        Map<AdminUnit, Long> parentIdMap = new HashMap<>();
        CSVReader reader = new CSVReader("C://Users//jakub//IdeaProjects//PZ1//lab1//src//lab7//" + filename);
        while(reader.next()) {
            AdminUnit unit = new AdminUnit();

            long id = reader.getInt("id");
            if(!idMap.containsKey(id)) {
                idMap.put(id, unit);
            }
            long parent = reader.getInt("parent");
            if(!parentIdMap.containsKey(unit)) {
                parentIdMap.put(unit, parent);
            }
            // TODO: poniższy kod zmienić tak, aby wykonał się po wczytaniu wszystkich elementów pliku
//            if(!parentid2childid.containsKey(parent)) {
//                List<Long> childids = new ArrayList<>();
//                parentid2childid.put(parent,childids);
//            }else{
//                parentid2childid.get(parent).add(id);
//            }

            unit.name = reader.get("name");
            unit.adminLevel = reader.getInt("admin_level");
            unit.population = reader.getDouble("population");
            unit.area = reader.getDouble("area");
            unit.density = reader.getDouble("density");
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
            unit.bbox.xmax = Math.max(Math.max(x1, x2), Math.max(Math.max(x3, x4), x5));
            unit.bbox.ymin = Math.min(Math.min(y1, y2), Math.min(Math.min(y3, y4), y5));
            unit.bbox.xmin = Math.min(Math.min(x1, x2), Math.min(Math.min(x3, x4), x5));
            unit.bbox.ymax = Math.max(Math.max(y1, y2), Math.max(Math.max(y3, y4), y5));
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
        for(AdminUnit unit : units) {

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
        System.out.println(out.toString());
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
        System.out.println(out);
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
        if(au.population == 0){
            au.population = au.area * au.density;
        }
    }
}

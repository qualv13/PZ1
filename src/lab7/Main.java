package lab7;


import java.io.IOException;
import java.io.PrintStream;
import java.util.Locale;

public class Main {
    public static void main(String[] args) throws IOException {
        AdminUnitList adminUnitList = new AdminUnitList();
        adminUnitList.read("admin-units.csv");
        PrintStream out = new PrintStream("admin-units.txt");
        //AdminUnitList toPrint = adminUnitList.selectByName(".*ębło.*", true);

        double t1 = System.nanoTime()/1e6;
        adminUnitList.getNeighbors(adminUnitList.getUnit("Kraków"), 15).list(out);
        double t2 = System.nanoTime()/1e6;
        System.out.printf(Locale.US,"t2-t1=%f\n",t2-t1);

        System.out.println(adminUnitList.units.get(1).bbox.contains(adminUnitList.units.get(2).bbox));
        //toPrint.list(out);
    }
}

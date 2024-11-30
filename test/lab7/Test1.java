package lab7;

import java.io.IOException;
import java.io.PrintStream;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

public class Test1 {
    @org.junit.jupiter.api.Test
    public void test1() throws IOException {
        AdminUnitList adminUnitList = new AdminUnitList();
        adminUnitList.read("admin-units.csv");
        PrintStream out = new PrintStream("admin-units2.txt");
        AdminUnitList toPrint = adminUnitList.selectByName(".*êb³o.*", true);

        double t1 = System.nanoTime()/1e6;
        adminUnitList.getNeighbors(adminUnitList.getUnit("Kraków"), 15).list(out);
        double t2 = System.nanoTime()/1e6;
        System.out.printf(Locale.US,"t2-t1=%f\n",t2-t1);


        //System.out.println(adminUnitList.units.get(1).bbox.intersects(adminUnitList.units.get(2).bbox));

    }
}

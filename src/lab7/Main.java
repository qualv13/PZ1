package lab7;


import java.io.IOException;
import java.io.PrintStream;

public class Main {
    public static void main(String[] args) throws IOException {
        AdminUnitList adminUnitList = new AdminUnitList();
        adminUnitList.read("admin-units.csv");
        PrintStream out = new PrintStream("admin-units.txt");
        AdminUnitList toPrint = adminUnitList.selectByName(".*ębło.*", true);
        toPrint.list(out);

    }
}

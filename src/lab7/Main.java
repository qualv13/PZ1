package lab7;


import java.io.IOException;
import java.io.PrintStream;
import java.util.Locale;
import java.util.Objects;

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

        AdminUnitList list = new AdminUnitList();
        list.read("admin-units.csv");
        PrintStream out2 = new PrintStream("admin-units2.txt");
        list.filter(a->a.name.startsWith("Ż")).sortInplaceByArea().list(out2);

        AdminUnitList list2 = new AdminUnitList();
        list2.read("admin-units.csv");
        PrintStream out3 = new PrintStream("admin-units3.txt");
        list2.filter(a->a.name.startsWith("K")).sortInplaceByArea().list(out3);

//        AdminUnitList list3 = new AdminUnitList();
//        list3.read("admin-units.csv");
//        PrintStream out4 = new PrintStream("admin-units4.txt");
//        list3.filter(a->a.adminLevel==6).filter(b-> Objects.equals(b.parent.name, "województwo małopolskie")).sortInplaceByArea().list(out4);

        AdminUnitList list4 = new AdminUnitList();
        list4.read("admin-units.csv");
        PrintStream out5 = new PrintStream("admin-units5.txt");
        list4.filter(a->a.density>300).sortInplaceByArea().list(out5);
        //toPrint.list(out);

        PrintStream out6 = new PrintStream("admin-units6.txt");
        AdminUnitQuery query = new AdminUnitQuery()
                .selectFrom(list)
                .where(a->a.area>1000)
                .or(a->a.name.startsWith("Sz"))
                .sort((a,b)->Double.compare(a.area,b.area))
                .limit(2);
        query.execute().list(out6);

        AdminUnitList list7 = new AdminUnitList();
        list7.read("admin-units.csv");
        PrintStream out7 = new PrintStream("admin-units7.txt");
        AdminUnitQuery query2 = new AdminUnitQuery()
                .selectFrom(list)
                .where(a->a.area>1000)
                .sort((a,b)->Double.compare(a.area,b.area))
                .limit(2);
        query2.execute().list(out7);
    }
}

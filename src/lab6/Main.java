package lab6;

import java.io.FileReader;
import java.io.IOException;
import java.util.Locale;

public class Main {
    public static void main(String[] args) throws IOException {
//        CSVReader reader = new CSVReader("C://Users//jakub//IdeaProjects//PZ1//lab1//src//lab7//admin-units.csv");
//        while (reader.next()){
//            int id = reader.getInt("id");
//            int parentid = reader.getInt("parent");
//            String name = reader.get("name");
//            int admin_level = reader.getInt("admin_level");
//            double population = reader.getDouble("population");
//            System.out.printf(Locale.US, "%d %d %s %d %f \n", id, parentid, name, admin_level, population);
//        }
//
//        CSVReader reader = new CSVReader("C://ProjektyStudia//PZ1//src//lab6//elec.csv",",",true);
//        while (reader.next()){
//            int date = reader.getInt("date");
//            int day = reader.getInt("day");
//            double period = reader.getDouble("period");
//            double nswprice  = reader.getDouble("nswprice");
//            double nswdemand = reader.getDouble("nswdemand");
//            double vicprice = reader.getDouble("vicprice");
//            double vicdemand = reader.getDouble("vicdemand");
//            double transfer = reader.getDouble("transfer");
//            String clas = reader.get("class");
//            System.out.printf(Locale.US, "%d %d %f %f %f %f %f %f %s \n", date, day, period, nswprice, nswdemand, vicprice, vicdemand, transfer, clas);
//        }

//        CSVReader reader = new CSVReader("C://ProjektyStudia//PZ1//src//lab6//missing-values.csv",";",true); // C://Users//jakub//IdeaProjects//PZ1//lab1//src//lab6//with-header.csv
//        while(reader.next()) {
//            int id = reader.getInt("id");
//            int parent = reader.getInt("parent");
//            String name = reader.get("name");
//            String admin_level = reader.get("admin_level");
//            int population = reader.getInt("population");
//            double area = reader.getDouble("area");
//            double density = reader.getDouble("density");
//            System.out.printf(Locale.US, "%d %d %s %s %d %f %f\n", id, parent, name, admin_level, population, area, density);
//        }

//        CSVReader reader = new CSVReader("C://ProjektyStudia//PZ1//src//lab6//no-header.csv",";",false); // C://Users//jakub//IdeaProjects//PZ1//lab1//src//lab6//with-header.csv
//        while(reader.next()) {
//            int id = reader.getInt(0); // with-header.csv
//            String name = reader.get(1);
//            String nazwisko = reader.get(2);
//            String ulica = reader.get(3);
//            int nrDomu = reader.getInt(4);
//            int nrMieszkania = reader.getInt(5);
//            System.out.printf(Locale.US, "%d %s %s %s %d %d \n", id, name, nazwisko, ulica, nrDomu, nrMieszkania);
//        }

//        CSVReader reader = new CSVReader("C://ProjektyStudia//PZ1//src//lab6//accelerator.csv",";",true); // C://Users//jakub//IdeaProjects//PZ1//lab1//src//lab6//with-header.csv
//        while(reader.next()) {
//            double X = reader.getDouble("X"); // with-header.csv
//            double Y = reader.getDouble("Y");
//            double Z = reader.getDouble("Z");
//            double langitude = reader.getDouble("longitude");
//            double latitude = reader.getDouble("latitude");
//            double speed = reader.getDouble("speed");
//            double time = reader.getDouble("time");
//            int label = reader.getInt("label");
//            System.out.printf(Locale.US, "%f %f %f %f %f %f %f %d \n",X,Y,Z,langitude,latitude,speed,time, label);
//        }

//        CSVReader reader = new CSVReader("C://ProjektyStudia//PZ1//src//lab6//with-header.csv",";",true); // C://Users//jakub//IdeaProjects//PZ1//lab1//src//lab6//with-header.csv
//        while(reader.next()) {
//            int id = reader.getInt("id"); // with-header.csv
//            String name = reader.get("imie");
//            String nazwisko = reader.get("nazwisko");
//            String ulica = reader.get("ulica");
//            int nrDomu = reader.getInt("nrdomu");
//            int nrMieszkania = reader.getInt("nrmieszkania");
//            System.out.printf(Locale.US, "%d %s %s %s %d %d \n", id, name, nazwisko, ulica, nrDomu, nrMieszkania);
//        }

//        CSVReader reader = new CSVReader("C://ProjektyStudia//PZ1//src//lab6//titanic-part.csv",",",true);
//        while(reader.next()) {
//            int id = reader.getInt("PassengerId"); // titanic-part.csv
//            String name = reader.get("Name");
//            int age = reader.getInt("Age");
//            String ticket = reader.get("Ticket");
//            double fare = reader.getDouble("Fare");
//            String embarked = reader.get("Embarked");
//            System.out.printf(Locale.US,"%d %s %d %s %f %s \n",id, name, age, ticket, fare, embarked);
//        }
        //test2();
    }

    public static void test2() throws IOException {
        CSVReader reader = new CSVReader("titanic-part.csv",",",true);
        while(reader.next()){
            int id = reader.getInt(0);
            String name = reader.get(3);
            double fare = reader.getDouble(9);
            System.out.printf(Locale.US,"%d %s %d \n",id, name, fare);


        }
    }
}

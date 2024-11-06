package lab6;

import java.io.FileReader;
import java.io.IOException;
import java.util.Locale;


public class Main {
    public static void main(String[] args) throws IOException {
        CSVReader reader = new CSVReader("C://Users//jakub//IdeaProjects//PZ1//lab1//src//lab6//with-header.csv",",",true);
        while(reader.next()){
            int id = reader.getInt("id"); // with-header.csv
            String name = reader.get("imię");
            String nazwisko = reader.get("nazwisko");
            String ulica = reader.get("ulica");
            int nrDomu = reader.getInt("nrdomu");
            int nrMieszkania = reader.getInt("nrmeszkania");
            System.out.printf(Locale.US, "%d %s %s %s %d %d", id, name, nazwisko, ulica, nrDomu, nrMieszkania);

//            int id = reader.getInt("PassengerId"); // titanic-part.csv
//            String name = reader.get("Name");
//            double fare = reader.getDouble("Fare");
//            System.out.printf(Locale.US,"%d %s %f",id, name, fare);
        }
        //test2();
    }

    public static void test2() throws IOException {
        CSVReader reader = new CSVReader("titanic-part.csv",",",true);
        while(reader.next()){
            int id = reader.getInt(0);
            String name = reader.get(3);
            double fare = reader.getDouble(9);
            System.out.printf(Locale.US,"%d %s %d",id, name, fare);

        }
    }
}

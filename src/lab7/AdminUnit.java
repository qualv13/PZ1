package lab7;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdminUnit {
    String name;
    int adminLevel;
    double population;
    double area;
    double density;
    AdminUnit parent;
    BoundingBox bbox = new BoundingBox();
    List<AdminUnit> children; // = new ArrayList<AdminUnit>();>

    @Override
    public String toString(){
        String out = "";
        out += "Name: " + name + "\n";
        out += "Admin Level: " + adminLevel + "\n";
        out += "Population: " + population + "\n";
        out += "Area: " + area + "\n";
        out += "Density: " + density + "\n";
        out += "Parent: " + parent + "\n";
        return out;
    }
}
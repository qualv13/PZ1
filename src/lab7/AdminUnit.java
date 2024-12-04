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
    AdminUnit parent = null;
    BoundingBox bbox = new BoundingBox();
    List<AdminUnit> children = new ArrayList<AdminUnit>();

    @Override
    public String toString(){
        String out = "";
        out += "Name: " + name + "\n";
        out += "Admin Level: " + adminLevel + "\n";
        out += "Population: " + population + "\n";
        out += "Area: " + area + " km^2\n";
        out += "Density: " + density + "\n";
        if(parent != null){
            out += "Parent: " + parent.name + "\n";
        }else{
            out += "Parent: null\n";
        }
        if(!children.isEmpty()) {
            out += "Children: ";
            for (AdminUnit child : children) {
                out += child.name + ", ";
            }
            out += "\n";
        }
        out += bbox.toString() + "\n";
        out += "\n";
        return out;
    }
    //p = a*d
    void fixMissingValues(){
        if(population != 0 && area != 0 && density != 0){
            return;
        } else if (population != 0 && area != 0 && density == 0) {
            density = population / area;
        } else if (population != 0 && area == 0 && density != 0) {
            area = population / density;
        } else if (population == 0 && area != 0 && density != 0) {
            population = area * density;
        }
        else if(population != 0 && area == 0 && density == 0){
            if(parent == null){
                return;
            }
            parent.fixMissingValues();
            density = parent.density;
            if(density != 0){
                area = population / density;
            }
        }else if(population == 0 && area != 0 && density == 0){
            if(parent == null){
                return;
            }
            parent.fixMissingValues();
            density = parent.density;
            if(density != 0){
                population = area * density;
            }
        }else if(population == 0 && area == 0 && density != 0){
            return;
        } else if (population == 0 && area == 0 && density == 0) {
            if(parent == null){
                return;
            }
            parent.fixMissingValues();
            density = parent.density;
        }
    }
}
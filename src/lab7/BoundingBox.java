package lab7;

import java.util.Objects;

public class BoundingBox {
    double xmin = 0;
    double ymin = 0;
    double xmax = 0;
    double ymax = 0;

    @Override
    public String toString() {
        String out = "";
        out += "xmin: " + xmin + "\n";
        out += "ymin: " + ymin + "\n";
        out += "xmax: " + xmax + "\n";
        out += "ymax: " + ymax + "\n";
        return out;
    }

    /**
     * Powiększa BB tak, aby zawierał punkt (x,y)
     * Jeżeli był wcześniej pusty - wówczas ma zawierać wyłącznie ten punkt
     * @param x - współrzędna x
     * @param y - współrzędna y
     */
    void addPoint(double x, double y){
        if(this.isEmpty()){
            this.xmin = x;
            this.ymin = y;
            this.xmax = x;
            this.ymax = y;
            return;
        }
        if(x>xmax){
            xmax=x;
        }else if(x<xmin){
            xmin=x;
        }
        if(y>ymax){
            ymax=y;
        }else if(y<ymin){
            ymin=y;
        }
    }

    /**
     * Sprawdza, czy BB zawiera punkt (x,y)
     * @param x
     * @param y
     * @return
     */
    boolean contains(double x, double y){
        return x >= xmin && x <= xmax && y >= ymin && y <= ymax;
    }

    /**
     * Sprawdza czy dany BB zawiera bb
     * @param bb
     * @return
     */
    boolean contains(BoundingBox bb){
        return this.xmax > bb.xmax && this.ymax > bb.ymax && this.xmin < bb.xmin && this.ymin < bb.ymin;
    }

    /**
     * Sprawdza, czy dany BB przecina się z bb
     * @param bb
     * @return
     */
    boolean intersects(BoundingBox bb){
        return !(bb.xmax < bb.xmin || bb.ymax < bb.ymin);
    }
    /**
     * Powiększa rozmiary tak, aby zawierał bb oraz poprzednią wersję this
     * Jeżeli był pusty - po wykonaniu operacji ma być równy bb
     * @param bb
     * @return
     */
    BoundingBox add(BoundingBox bb){
        if(this.isEmpty()){
            this.xmin=bb.xmin;
            this.ymin=bb.ymin;
            this.xmax=bb.xmax;
            this.ymax=bb.ymax;
            return this;
        }
        if(this.xmax < bb.xmax){
            this.xmax=bb.xmax;
        }
        if(this.ymax < bb.ymax){
            this.ymax=bb.ymax;
        }
        if(this.xmin > bb.xmin){
            this.xmin=bb.xmin;
        }
        if(this.ymin > bb.ymin){
            this.ymin=bb.ymin;
        }
        return this;
    }
    /**
     * Sprawdza czy BB jest pusty
     * @return
     */
    boolean isEmpty(){
        if( ((this.xmin == this.ymin) && (this.ymin == this.xmax)) && ((this.xmax == this.ymax) && (this.ymax == 0)) ){
            return true;
        }
        return false;
    }

    /**
     * Sprawdza czy
     * 1) typem o jest BoundingBox
     * 2) this jest równy bb
     * @return
     */
    public boolean equals(Object o){
        return o.getClass().equals(BoundingBox.class) && o == this;
    }

    /**
     * Oblicza i zwraca współrzędną x środka
     * @return if !isEmpty() współrzędna x środka else wyrzuca wyjątek
     * (sam dobierz typ)
     */
    double getCenterX(){
        return (this.xmax+this.xmin)/2;
    }
    /**
     * Oblicza i zwraca współrzędną y środka
     * @return if !isEmpty() współrzędna y środka else wyrzuca wyjątek
     * (sam dobierz typ)
     */
    double getCenterY(){
        return (this.ymax+this.ymin)/2;
    }

    /**
     * Oblicza odległość pomiędzy środkami this bounding box oraz bbx
     * @param bbx prostokąt, do którego liczona jest odległość
     * @return if !isEmpty odległość, else wyrzuca wyjątek lub zwraca maksymalną możliwą wartość double
     * Ze względu na to, że są to współrzędne geograficzne, zamiast odległości użyj wzoru haversine
     * (ang. haversine formula)
     *
     * Gotowy kod można znaleźć w Internecie...
     */
    double distanceTo(BoundingBox bbx){
        if(this.isEmpty() || bbx.isEmpty()){
            throw new RuntimeException("BBox is empty");
        }
        double yt = this.getCenterY(); double xt = this.getCenterX();
        double yb = bbx.getCenterY(); double xb = bbx.getCenterX();
        return haversine(yt, xt, yb, xb);
    }

    static double haversine(double lat1, double lon1,
                            double lat2, double lon2)
    {
        // distance between latitudes and longitudes
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);

        // convert to radians
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);

        // apply formulae
        double a = Math.pow(Math.sin(dLat / 2), 2) +
                Math.pow(Math.sin(dLon / 2), 2) *
                        Math.cos(lat1) *
                        Math.cos(lat2);
        double rad = 6371;
        double c = 2 * Math.asin(Math.sqrt(a));
        return rad * c;
    }
}
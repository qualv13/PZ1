package lab6;

import java.io.*;
import java.util.*;

public class CSVReader{
    private BufferedReader reader;
    private String delimiter;
    private boolean hasHeader;

    private List<String> columnLabels = new ArrayList<>();
    private Map<String, Integer> columnLabelsToInt = new HashMap<>();
    private ArrayList<String> current = new ArrayList<String>();//String[] current;

    /**
     * Constructor with full parameters.
     *
     * @param filename  - File path to the CSV file
     * @param delimiter - Field delimiter (e.g., comma or semicolon)
     * @param hasHeader - Whether the file contains a header row
     */
    public CSVReader(String filename, String delimiter, boolean hasHeader) throws IOException {
        try {
            this.reader = new BufferedReader(new FileReader(filename));
            this.delimiter = delimiter;
            this.hasHeader = hasHeader;
            if (hasHeader) parseHeader();
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + filename);
            throw e;
        }
    }

    // Overloaded constructors for flexibility
    public CSVReader(String filename, String delimiter) throws IOException {
        this(filename, delimiter, true);
    }

    public CSVReader(String filename) throws IOException {
        this(filename, ",");
    }

    public CSVReader(Reader reader, String delimiter, boolean hasHeader) throws IOException {
        this.reader = new BufferedReader(reader);
        this.delimiter = delimiter;
        this.hasHeader = hasHeader;
        if (hasHeader) parseHeader();
    }

    /**
     * Parses the header row to map column names to indices.
     */
    private void parseHeader() throws IOException {
        String line = reader.readLine();
        if (line != null) {
            String[] headers = line.split(delimiter);
            for (int i = 0; i < headers.length; i++) {
                headers[i] = headers[i].trim();
                columnLabels.add(headers[i]);
                columnLabelsToInt.put(headers[i], i);
                System.out.printf(headers[i] + " ");
            }
            System.out.println();
        }
    }

    /**
     * Reads the next line and splits it into fields.
     *
     * @return true if there’s a next line, false if end of file.
     */
    public boolean next() throws IOException {
        current.clear();
        String currentLine = reader.readLine();
        //System.out.println(currentLine);
        String tmpFullString = "";
        if (currentLine == null) {
            return false;
        }
        // TODO : https://www.baeldung.com/java-split-string-commas
        boolean isInQuotes = false;
        int count = 0;

        for (int i = 0; i < currentLine.length(); i++) {
            if (currentLine.charAt(i) == '\"') {
                isInQuotes = !isInQuotes;
                //System.out.println(i);
                continue;
            }
            if (!isInQuotes && currentLine.charAt(i) == delimiter.charAt(0)) {
                current.add(tmpFullString);
                //System.out.println(i);
                //System.out.println(tmpFullString);
                tmpFullString = "";
            }else{
                //System.out.println(i);
                tmpFullString += currentLine.charAt(i);
            }
            if(i + 1 == currentLine.length()){
                current.add(tmpFullString);
                //System.out.println(i);
                //System.out.println(tmpFullString);
            }

            //System.out.println(tmpFullString);


//            String tmp = "";
//            for (int j = startPos; j < currentLine.length(); j++) {
//                if (currentLine.charAt(j) == '\"') {isInQuotes = !isInQuotes;}
//                else if (currentLine.charAt(j) == delimiter.charAt(0) && !isInQuotes) {
//                    startPos = j+1;
//                    break;
//                }
//                tmp = tmp + currentLine.charAt(j);
//
//            }
//            tmpFullString += tmp;
            // tmpFullString.append('|'); //delimiter.charAt(0)
        }

//        String del = "|";
//        current = tmpFullString.toString().split(delimiter); //delimiter
        //System.out.println(Arrays.toString(current));
        //current = currentLine.split(delimiter);
        return true;
    }

    // Get column labels as a list
    public List<String> getColumnLabels() {
        return columnLabels;
    }

    // Get the number of columns in the current row
    public int getRecordLength() {
        return current.size();
    }

    // Check if a column index is missing
    public boolean isMissing(int columnIndex) {
        return columnIndex < 0 || columnIndex >= current.size() || current.get(columnIndex).isEmpty();
    }

    // Check if a column by name is missing
    public boolean isMissing(String columnLabel) {
        int index = columnLabelsToInt.getOrDefault(columnLabel, -1);
        return isMissing(index);
    }

    // Retrieve field by index, or a default if missing
    public String get(int columnIndex) {
        return isMissing(columnIndex) ? "" : current.get(columnIndex);
    }

    // Retrieve field by column name, or a default if missing
    public String get(String columnLabel) {
        return isMissing(columnLabel) ? "" : current.get(columnLabelsToInt.get(columnLabel));
    }

    // Parse integer by index with error handling
    public int getInt(int columnIndex) {
        try {
            return isMissing(columnIndex) ? 0 : Integer.parseInt(get(columnIndex));
        } catch (NumberFormatException e) {
            //System.err.println("Invalid integer at column " + columnIndex + ": " + get(columnIndex));
            return 0;
        }
    }

    // Parse integer by column label with error handling
    public int getInt(String columnLabel) {
        try {
            return isMissing(columnLabel) ? 0 : Integer.parseInt(get(columnLabel));
        } catch (NumberFormatException e) {
            //System.err.println("Invalid integer for column " + columnLabel + ": " + get(columnLabel));
            return 0;
        }
    }

    // Parse long by index with error handling
    public long getLong(int columnIndex) {
        try {
            return Long.parseLong(get(columnIndex));
        } catch (NumberFormatException e) {
            //System.err.println("Invalid long at column " + columnIndex + ": " + get(columnIndex));
            return 0L;
        }
    }

    // Parse long by column label with error handling
    public long getLong(String columnLabel) {
        try {
            return Long.parseLong(get(columnLabel));
        } catch (NumberFormatException e) {
            //System.err.println("Invalid long for column " + columnLabel + ": " + get(columnLabel));
            return 0L;
        }
    }

    // Parse double by index with error handling
    public double getDouble(int columnIndex) {
        try {
            return Double.parseDouble(get(columnIndex));
        } catch (NumberFormatException e) {
            //System.err.println("Invalid double at column " + columnIndex + ": " + get(columnIndex));
            return 0.0;
        }
    }

    // Parse double by column label with error handling
    public double getDouble(String columnLabel) {
        try {
            return Double.parseDouble(get(columnLabel));
        } catch (NumberFormatException e) {
            //System.err.println("Invalid double for column " + columnLabel + ": " + get(columnLabel));
            return 0.0;
        }
    }
}

//package lab6;
//
//import java.io.*;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//public class CSVReader {
//    BufferedReader reader;
//    String delimiter;
//    boolean hasHeader;
//
//    // nazwy kolumn w takiej kolejności, jak w pliku
//    List<String> columnLabels = new ArrayList<>();
//    // odwzorowanie: nazwa kolumny -> numer kolumny
//    Map<String,Integer> columnLabelsToInt = new HashMap<>();
//
//    /**
//     *
//     * @param filename - nazwa pliku
//     * @param delimiter - separator pól
//     * @param hasHeader - czy plik ma wiersz nagłówkowy
//     */
//
//    public CSVReader(String filename,String delimiter,boolean hasHeader) throws IOException {
//        try{
//            reader = new BufferedReader(new FileReader(filename));
//        }catch (FileNotFoundException e){
//            System.out.println("File not found");
//        }
//        this.delimiter = delimiter + "\"(?=([^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*$)\"";
//        this.hasHeader = hasHeader;
//        if(hasHeader)parseHeader();
//    }
//    public CSVReader(String filename,String delimiter) throws IOException {
//        this(filename,delimiter,true);
//    }
//    public CSVReader(String filename) throws IOException {
//        this(filename,",");
//    }
//    public CSVReader(Reader reader, String delimiter, boolean hasHeader) throws IOException {
//        this.reader = new BufferedReader(reader);
//        this.delimiter = delimiter;
//        this.hasHeader = hasHeader;
//        if(hasHeader)parseHeader();
//    }
//
//    void parseHeader() throws IOException {
//        // wczytaj wiersz
//        String line  = reader.readLine();
//        if(line==null){
//            return;
//        }
//        // podziel na pola
//        String[]header = line.split(delimiter);
//        // przetwarzaj dane w wierszu
//        for(int i=0;i<header.length;i++){
//            // dodaj nazwy kolumn do columnLabels i numery do columnLabelsToInt
//            header[i]=header[i].trim();
//            columnLabels.add(header[i]);
//            columnLabelsToInt.put(header[i],i);
//            System.out.print(header[i]);
//        }
//        System.out.println();
//
//    }
//    //...
//    String[]current;
//    boolean next() throws IOException {
//        // czyta następny wiersz, dzieli na elementy i przypisuje do current
//        String currentLine = reader.readLine();
//
//        if(currentLine==null){
//            //throw new RuntimeException("zbiór pusty");
//            return false;
//        }
//        current = currentLine.split(delimiter);
//
////        for(int i = 0; i<data.length; i++){
////            data[i]=data[i].trim();
////
////            columnLabels.add(data[i]);
////            columnLabelsToInt.put(data[i],i);
////        }
////        next();
//        return true;
//    }
//
//    List<String> getColumnLabels(){
//        return columnLabels;
//    }
//
//    int getRecordLength(){
//        return current.length;
//    }
//
//    boolean isMissing(int columnIndex){
//        if(current[columnIndex].isEmpty() || columnIndex>current.length-1 || columnIndex<0){
//            return true;
//        }
//        return false;
//    }
//
//    boolean isMissing(String columnLabel){
//        int index = columnLabelsToInt.getOrDefault(columnLabel,-1);
//        if(index==-1){
//            return true;
//        }
//        return isMissing(index);
//    }
//
//    String get(int columnIndex){
//        if(isMissing(columnIndex)){
//            return " ";
//        }
//        return current[columnIndex];
//    }
//
//    String get(String columnLabel){
//        if(isMissing(columnLabel)){
//            return " ";
//        }
//        return current[columnLabelsToInt.get(columnLabel)];
//    }
//
//    int getInt(int columnIndex){
//        if(isMissing(columnIndex)){
//            return 0;
//        }
//        return Integer.parseInt(get(columnIndex));
//    }
//
//    int getInt(String columnLabel){
//        if(isMissing(columnLabel)){
//            return 0;
//        }
////        if(out==null){
////            throw new IllegalArgumentException("Column label "+columnLabel+" not found");
////        }
//        return Integer.parseInt(current[columnLabelsToInt.get(columnLabel)]); // (current[columnLabelsToInt.get(columnLabel)]);
//    }
//
//    long getLong(int columnIndex){
//        return Long.parseLong(get(columnIndex));
//    }
//
//    long getLong(String columnLabel){
//        String out = get(columnLabel);
//        if(out==null){
//            return 0;
//        }
//        return Long.parseLong(out);
//    }
//
//    double getDouble(int columnIndex){
//        return Double.parseDouble(get(columnIndex));
//    }
//
//    double getDouble(String columnLabel){
////        String out = get(columnLabel);
////        if(out==null){
////            return 0;
////        }
//        return Double.parseDouble(current[columnLabelsToInt.get(columnLabel)]);
//    }
//}

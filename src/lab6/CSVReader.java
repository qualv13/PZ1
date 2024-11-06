package lab6;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CSVReader {
    BufferedReader reader;
    String delimiter;
    boolean hasHeader;

    // nazwy kolumn w takiej kolejności, jak w pliku
    List<String> columnLabels = new ArrayList<>();
    // odwzorowanie: nazwa kolumny -> numer kolumny
    Map<String,Integer> columnLabelsToInt = new HashMap<>();

    /**
     *
     * @param filename - nazwa pliku
     * @param delimiter - separator pól
     * @param hasHeader - czy plik ma wiersz nagłówkowy
     */

    public CSVReader(String filename,String delimiter,boolean hasHeader) throws IOException {
        try{
            reader = new BufferedReader(new FileReader(filename));
        }catch (FileNotFoundException e){
            System.out.println("File not found");
        }
        this.delimiter = delimiter + "\"(?=([^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*$)\"";
        this.hasHeader = hasHeader;
        if(hasHeader)parseHeader();
    }
    public CSVReader(String filename,String delimiter) throws IOException {
        this(filename,delimiter,true);
    }
    public CSVReader(String filename) throws IOException {
        this(filename,",");
    }
    public CSVReader(Reader reader, String delimiter, boolean hasHeader) throws IOException {
        this.reader = new BufferedReader(reader);
        this.delimiter = delimiter;
        this.hasHeader = hasHeader;
        if(hasHeader)parseHeader();
    }

    void parseHeader() throws IOException {
        // wczytaj wiersz
        String line  = reader.readLine();
        if(line==null){
            return;
        }
        // podziel na pola
        String[]header = line.split(delimiter);
        // przetwarzaj dane w wierszu
        for(int i=0;i<header.length;i++){
            // dodaj nazwy kolumn do columnLabels i numery do columnLabelsToInt
            header[i]=header[i].trim();
            columnLabels.add(header[i]);
            columnLabelsToInt.put(header[i],i);
            System.out.print(header[i]);
        }
        System.out.println();

    }
    //...
    String[]current;
    boolean next() throws IOException {
        // czyta następny wiersz, dzieli na elementy i przypisuje do current
        String currentLine = reader.readLine();

        if(currentLine==null){
            //throw new RuntimeException("zbiór pusty");
            return false;
        }
        current = currentLine.split(delimiter);

//        for(int i = 0; i<data.length; i++){
//            data[i]=data[i].trim();
//
//            columnLabels.add(data[i]);
//            columnLabelsToInt.put(data[i],i);
//        }
//        next();
        return true;
    }

    List<String> getColumnLabels(){
        return columnLabels;
    }

    int getRecordLength(){
        return current.length;
    }

    boolean isMissing(int columnIndex){
        if(current[columnIndex].isEmpty() || columnIndex>current.length-1 || columnIndex<0){
            return true;
        }
        return false;
    }

    boolean isMissing(String columnLabel){
        int index = columnLabelsToInt.getOrDefault(columnLabel,-1);
        if(index==-1){
            return true;
        }
        return isMissing(index);
    }

    String get(int columnIndex){
        if(isMissing(columnIndex)){
            return " ";
        }
        return current[columnIndex];
    }

    String get(String columnLabel){
        if(isMissing(columnLabel)){
            return " ";
        }
        return current[columnLabelsToInt.get(columnLabel)];
    }

    int getInt(int columnIndex){
        if(isMissing(columnIndex)){
            return 0;
        }
        return Integer.parseInt(get(columnIndex));
    }

    int getInt(String columnLabel){
        if(isMissing(columnLabel)){
            return 0;
        }
//        if(out==null){
//            throw new IllegalArgumentException("Column label "+columnLabel+" not found");
//        }
        return Integer.parseInt(current[columnLabelsToInt.get(columnLabel)]); // (current[columnLabelsToInt.get(columnLabel)]);
    }

    long getLong(int columnIndex){
        return Long.parseLong(get(columnIndex));
    }

    long getLong(String columnLabel){
        String out = get(columnLabel);
        if(out==null){
            return 0;
        }
        return Long.parseLong(out);
    }

    double getDouble(int columnIndex){
        return Double.parseDouble(get(columnIndex));
    }

    double getDouble(String columnLabel){
//        String out = get(columnLabel);
//        if(out==null){
//            return 0;
//        }
        return Double.parseDouble(current[columnLabelsToInt.get(columnLabel)]);
    }
}

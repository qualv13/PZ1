package lab6;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CSVReader {
    BufferedReader reader;
    String delimiter;
    boolean hasHeader;

    /**
     *
     * @param filename - nazwa pliku
     * @param delimiter - separator pól
     * @param hasHeader - czy plik ma wiersz nagłówkowy
     */

    public CSVReader(String filename,String delimiter,boolean hasHeader) throws IOException {
        reader = new BufferedReader(new FileReader(filename));
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
        }
    }
    //...
    /*
    // nazwy kolumn w takiej kolejności, jak w pliku
    List<String> columnLabels = new ArrayList<>();
    // odwzorowanie: nazwa kolumny -> numer kolumny
    Map<String,Integer> columnLabelsToInt = new HashMap<>();
    */
}

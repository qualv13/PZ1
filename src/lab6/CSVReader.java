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
            } else {
                //System.out.println(i);
                tmpFullString += currentLine.charAt(i);
            }
            if (i + 1 == currentLine.length()) {
                current.add(tmpFullString);
                //System.out.println(i);
                //System.out.println(tmpFullString);
            }
            //System.out.println(tmpFullString);
        }
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

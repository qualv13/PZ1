package lab4;

import java.io.PrintStream;

public class ListItem {
    String content = "";

    void writeHTML(PrintStream out) {
        out.println("<li>" + content + "</li>\n");
    }
}

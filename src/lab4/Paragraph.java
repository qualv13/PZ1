package lab4;

import java.io.PrintStream;

public class Paragraph {
    String content;

    Paragraph setContent(String content) {
        this.content = content;
        return this;
    }

    void writeHTML(PrintStream out){
        out.println("<p>" + content + "</p>");
    }
}
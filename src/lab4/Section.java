package lab4;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class Section {
    String title;
    List<Paragraph> paragraphs = new ArrayList<>() ;

    Section setTitle(String title){
        this.title = title;
        return this;
    }
    Section addParagraph(String paragraphText){
        Paragraph paragraph = new Paragraph();
        paragraph.setContent(paragraphText);
        paragraphs.add(paragraph);
        return this;
    }
    Section addParagraph(Paragraph p){
        paragraphs.add(p);
        return this;
    }
    void writeHTML(PrintStream out){
        out.printf("<section>");
        out.printf("<h2>" +
                this.title +
                "</h2>");
        for (Paragraph p : paragraphs){
            p.writeHTML(out);
        }
        out.printf("<section>");
    }
}
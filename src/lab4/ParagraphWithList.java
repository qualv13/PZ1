package lab4;

import java.io.PrintStream;

public class ParagraphWithList extends Paragraph {
    String title = "";
    UnorderedList list = new UnorderedList();

    ParagraphWithList setContent(String content){
        this.title = content;
        return this;
    }
    ParagraphWithList addListItem(String item){
        this.list.addItem(item);
        return this;
    }
    void writeHTML(PrintStream out){
        out.printf("<p>" +
                this.title +
                "</p>\n");
        this.list.writeHTML(out);
    }
}
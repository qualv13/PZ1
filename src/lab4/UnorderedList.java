package lab4;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class UnorderedList {
    List<ListItem> items = new ArrayList<>();

    UnorderedList addItem(String item) {
        ListItem listItem = new ListItem();
        listItem.content = item;
        items.add(listItem);
        return this;
    }

    void writeHTML(PrintStream out) {
        out.printf("<ul>");
        for (ListItem item : items) {
            item.writeHTML(out);
        }
        out.printf("</ul>\n");
    }
}

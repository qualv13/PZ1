package lab4;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class Document {
    String title;
    Photo photo;
    List<Section> sections = new ArrayList<>();

    Document setTitle(String title){
        this.title = title;
        return this;
    }

    Document setPhoto(String photoUrl){
        // ???
        this.photo = new Photo(photoUrl);
        return this;
    }

    Section addSection(String sectionTitle){
        // utwórz sekcję o danym tytule i dodaj do sections
        Section section = new Section();
        section.title = sectionTitle;
        this.sections.add(section);
        return null;
    }
    Document addSection(Section s){
        this.sections.add(s);
        return this;
    }


    void writeHTML(PrintStream out){
        // zapisz niezbędne znaczniki HTML
        out.printf("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "  <head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <meta http-equiv=\"X-UA-Compatible\" content=\"ie=edge\">\n" +
                "    <title>\"%s\"</title>\n" +
//                "    <link rel=\"stylesheet\" href=\"./style.css\">\n" +
                "    <link rel=\"icon\" href=\"./favicon.ico\" type=\"image/x-icon\">\n" +
                "  </head>\n" +
                "  <body>\n" +
                "    <main>\n" +
                "        <h1>Welcome to My Website</h1>  \n" +
                "    </main>\n" +
                "  </body>\n" +
                "</html>\n", this.title);
        // dodaj tytuł i obrazek
        // dla każdej sekcji wywołaj section.writeHTML(out)
        for(Section s: this.sections){
            s.writeHTML(out);
        }
    }
}
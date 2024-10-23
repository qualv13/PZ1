package lab4;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;

import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

// danilo.pianini.gson.extras
public class Document {
    String title;
    Photo photo = null;
    List<Section> sections = new ArrayList<>();

    Document(String title) {
        this.title = title;
    }

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
        section.setTitle(sectionTitle);
        this.sections.add(section);
        return section;
    }
    Document addSection(Section s){
        Section section = new Section();
        section.setTitle(s.title);
        section.paragraphs = s.paragraphs;
        this.sections.add(section);
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
                "    <title>\"" +
                        this.title +
                        "\"</title>\n" +
                "    <link rel=\"stylesheet\" href=\"./cv.css\">\n" +
                "    <link rel=\"icon\" href=\"./favicon.ico\" type=\"image/x-icon\">\n" +
                "  </head>\n" +
                "  <body>\n" +
                "    <main>\n"
                );
        // dodaj tytuł i obrazek
        out.printf("<h1>" +
                this.title +
                "</h1>\n");
        // dla każdej sekcji wywołaj section.writeHTML(out)
        if (photo != null) {
            photo.writeHTML(out);
        }
        for(Section s: this.sections){
            s.writeHTML(out);
        }
        out.printf(
//                "        <h1>Welcome to My Website</h1>  \n" +
                "    </main>\n" +
                "  </body>\n" +
                "</html>\n", this.title);
    }

    String toJson() {
        RuntimeTypeAdapterFactory<Paragraph> adapter =
                RuntimeTypeAdapterFactory
                        .of(Paragraph.class)
                        .registerSubtype(Paragraph.class)
                        .registerSubtype(ParagraphWithList.class);
        Gson gson = new GsonBuilder().registerTypeAdapterFactory(adapter).setPrettyPrinting().create();
        return gson.toJson(this);
    }

    static Document fromJson(String jsonString){
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(jsonString, Document.class);
    }

    public static void main(String[] args) {
        Document cv = new Document("Jana Kowalski - CV");
        cv.setPhoto("https://upload.wikimedia.org/wikipedia/commons/thumb/7/71/Calico_tabby_cat_-_Savannah.jpg/1200px-Calico_tabby_cat_-_Savannah.jpg");
        cv.addSection("Wykształcenie")
                .addParagraph("2000-2005 Przedszkole im. Królewny Snieżki w ...")
                .addParagraph("2006-2012 SP7 im Ronalda Regana w ...")
                .addParagraph(
                        new ParagraphWithList().setContent("Kursy")
                                .addListItem("Języka Angielskiego")
                                .addListItem("Języka Hiszpańskiego")
                                .addListItem("Szydełkowania")
                );
        cv.addSection("Umiejętności")
                .addParagraph(
                        new ParagraphWithList().setContent("Znane technologie")
                                .addListItem("C")
                                .addListItem("C++")
                                .addListItem("Java")
                );
        try {
            cv.writeHTML(new PrintStream("cv.html", StandardCharsets.UTF_8));
            //cv.toJson(new PrintStream("cv.json", StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //cv.toJson();

        System.out.println("\n---------------");
        String json = cv.toJson();
        System.out.println(json);
        System.out.println("\n---------------");
        Document doc = Document.fromJson(json);
        cv.writeHTML(System.out);
        System.out.println("\n---------------");;
        json = cv.toJson();
        System.out.println(json);
        System.out.println("\n---------------");

    }
}
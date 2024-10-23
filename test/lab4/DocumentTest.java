package lab4;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import static org.junit.jupiter.api.Assertions.*;

public class DocumentTest {
    @org.junit.jupiter.api.Test
    void testDocumentInit(){
        String test = "title_of_document";
        String test2 = "title_of_another_document";
        Document document = new Document(test);
        assertArrayEquals(test.toCharArray(), document.title.toCharArray(), "Title text should be the same");
        //assertThrows(NullPointerException.class, () -> new Document(null));
        //assertThrows(RuntimeException.class, () -> new Document(test2));
    }

    @org.junit.jupiter.api.Test
    void testDocumentSetTitle(){
        String test = "title_of_document";
        Document document = new Document(test);
        String test2 = "title_of_another_document";
        document.setTitle(test2);
        assertArrayEquals(test2.toCharArray(), document.title.toCharArray(), "Title text of document should be the same");
    }

    @org.junit.jupiter.api.Test
    void testDocumentAddSection(){
        String test = "title_of_document";
        Document document = new Document(test);
        String test2 = "title of section";
        document.addSection(test2);
        assertArrayEquals(test2.toCharArray(), document.sections.getFirst().title.toCharArray(), "Title text of setions should be the same");
    }

    @org.junit.jupiter.api.Test
    void testDocumentAddSection2(){
        String test = "title_of_document";
        Document document = new Document(test);
        String test2 = "title of section";
        Section section = new Section();
        section.setTitle(test2);
        document.addSection(section);
        assertArrayEquals(test2.toCharArray(), document.sections.getFirst().title.toCharArray(), "Title text of setions should be the same");
    }

    @org.junit.jupiter.api.Test
    void testWriteHTML(){
        Document document = new Document("title_of_document");

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);
        String result = null;
        document.writeHTML(ps);
        try {
            result = os.toString("ISO-8859-2");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        System.out.println(result);

        assertTrue(result.contains("<!DOCTYPE html>"));
        assertTrue(result.contains("title_of_document"));
        assertTrue(result.contains("<main>"));
        assertTrue(result.contains("</main>"));
        assertTrue(result.contains("<body>"));
        assertTrue(result.contains("</body>"));

    }

    // TODO: test JSON żeby poprawnie porównywał przy serializacja -> deserializacja -> serializacja i porównianie ich wszystkich
    @org.junit.jupiter.api.Test
    void testJSON(){
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
        String json = cv.toJson();

        Document doc = Document.fromJson(json);

        String json2 = cv.toJson();


        assertEquals(json.toCharArray(), json2.toCharArray(), "JSON should be the same");
        //assertArrayEquals(cv.writeHTML(), doc.writeHTML());


    }

}

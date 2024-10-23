package lab4;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import static org.junit.jupiter.api.Assertions.*;

public class SectionTest {

    @org.junit.jupiter.api.Test
    void testSetTitle() {
        String test = "test_title";
        Section section = new Section();
        section.setTitle(test);
        assertArrayEquals(test.toCharArray(), section.title.toCharArray(), "Title should be the same");

    }

    @org.junit.jupiter.api.Test
    void testAddParagraphText(){
        Section section = new Section();
        String test = "paragraph_text";
        section.addParagraph(test);
        assertArrayEquals(test.toCharArray(), section.paragraphs.getFirst().content.toCharArray(), "Paragraph text should be the same");
    }

    @org.junit.jupiter.api.Test
    void testAddParagraph(){
        Section section = new Section();
        Paragraph paragraph = new Paragraph();
        String test = "paragraph";
        paragraph.setContent(test);
        section.addParagraph(paragraph);
        assertArrayEquals(test.toCharArray(), section.paragraphs.getFirst().content.toCharArray(), "Paragraph should be the same");
    }

    @org.junit.jupiter.api.Test
    void testWriteHTML(){
        Section section = new Section();
        String test = "test_title";
        section.setTitle(test);

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);
        String result = null;
        section.writeHTML(ps);
        //result = os.toString();
        try {
            result = os.toString("ISO-8859-2");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        System.out.println(result);

        assertTrue(result.contains("<section>"));
        assertTrue(result.contains("</section>"));
        assertTrue(result.contains(test));
    }
}

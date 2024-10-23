package lab4;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import static org.junit.jupiter.api.Assertions.*;

public class ParagraphTest {
    @org.junit.jupiter.api.Test
    void testSetTitle() {
        Paragraph paragraph = new Paragraph();
        String test = "This is a paragraph";
        paragraph.setContent(test);
        assertArrayEquals(test.toCharArray(), paragraph.content.toCharArray(), "Paragraph text should be the same");
    }

    @org.junit.jupiter.api.Test
    void testWriteHTML() throws Exception{
        Paragraph paragraph = new Paragraph();
        String test = "test_title";
        paragraph.setContent(test);

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);
        String result = null;
        paragraph.writeHTML(ps);
        //result = os.toString();
        try {
            result = os.toString("ISO-8859-2");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        System.out.println(result);

        assertTrue(result.contains("<p>"));
        assertTrue(result.contains("</p>"));
        assertTrue(result.contains(test));
    }
}

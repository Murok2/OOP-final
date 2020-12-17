package Tests;

import classes.BookInfo;
import org.junit.Test;

import static org.junit.Assert.*;

public class BookInfoTest {
    BookInfo bookInfo = new BookInfo("Java : How to Program", "02-3456-7890", "Paul Deitel", "Java", 15);

    @Test
    public void getTitleTest() {
        assertEquals("Java : How to Program", bookInfo.getTitle());
    }

    @Test
    public void setTitleTest() {
        bookInfo.setTitle("Java : How to Program");
        assertEquals("Java : How to Program", bookInfo.getTitle());
    }

    @Test
    public void getIsbnTest() {
        assertEquals("02-3456-7890", bookInfo.getIsbn());
    }

    @Test
    public void setIsbnTest() {
        bookInfo.setIsbn("02-3456-7890");
        assertEquals("02-3456-7890", bookInfo.getIsbn());
    }

    @Test
    public void getAuthorTest() {
        assertEquals("Paul Deitel", bookInfo.getAuthor());
    }

    @Test
    public void setAuthorTest() {
        bookInfo.setAuthor("Paul Deitel");
        assertEquals("Paul Deitel", bookInfo.getAuthor());
    }

    @Test
    public void getPublisherTest() {
        assertEquals("Java", bookInfo.getPublisher());
    }

    @Test
    public void setPublisherTest() {
        bookInfo.setPublisher("Java");
        assertEquals("Java", bookInfo.getPublisher());
    }

    @Test
    public void getNumcopiesTest() {
        assertEquals(15, bookInfo.getNumcopies());
    }

    @Test
    public void setNumcopiesTest() {
        bookInfo.setNumcopies(15);
        assertEquals(15, bookInfo.getNumcopies());
    }

    @Test
    public void toStringTest() {
        assertEquals("bookInfo{title=Java : How to Program, isbn=02-3456-7890, author=Paul Deitel, publisher=Java, numcopies=15}", bookInfo.toString());
    }
}
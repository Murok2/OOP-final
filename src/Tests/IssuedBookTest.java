package Tests;

import classes.IssuedBook;
import org.junit.Test;

import static org.junit.Assert.*;

public class IssuedBookTest {
    IssuedBook issuedBook = new IssuedBook("Java : How to Program", "02-3456-7890", "Paul Deitel", "Java", "02-04-2019");

    @Test
    public void getTitleTest() {
        assertEquals("Java : How to Program", issuedBook.getTitle());
    }

    @Test
    public void setTitleTest() {
        issuedBook.setTitle("Java : How to Program");
        assertEquals("Java : How to Program", issuedBook.getTitle());
    }

    @Test
    public void getIsbnTest() {
        assertEquals("02-3456-7890", issuedBook.getIsbn());
    }

    @Test
    public void setIsbnTest() {
        issuedBook.setIsbn("02-3456-7890");
        assertEquals("02-3456-7890", issuedBook.getIsbn());
    }

    @Test
    public void getAuthorTest() {
        assertEquals("Paul Deitel", issuedBook.getAuthor());
    }

    @Test
    public void setAuthorTest() {
        issuedBook.setAuthor("Paul Deitel");
        assertEquals("Paul Deitel", issuedBook.getAuthor());
    }

    @Test
    public void getPublisherTest() {
        assertEquals("Java", issuedBook.getPublisher());
    }

    @Test
    public void setPublisherTest() {
        issuedBook.setPublisher("Java");
        assertEquals("Java", issuedBook.getPublisher());
    }

    @Test
    public void getDatepickerTest() {
        assertEquals("02-04-2019", issuedBook.getDatepicker());
    }

    @Test
    public void setDatepickerTest() {
        issuedBook.setDatepicker("02-04-2019");
        assertEquals("02-04-2019", issuedBook.getDatepicker());
    }

    @Test
    public void toStringTest() {
        assertEquals("IssuedBook{title='Java : How to Program', isbn='02-3456-7890', author='Paul Deitel', publisher='Java', datepicker='02-04-2019'}", issuedBook.toString());
    }
}
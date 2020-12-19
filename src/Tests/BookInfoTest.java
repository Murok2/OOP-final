package Tests;

import classes.BookInfo;
import org.junit.Test;

import static org.junit.Assert.*;

public class BookInfoTest {
    BookInfo bookInfo = new BookInfo("Java : How to Program", "02-3456-7890", "Paul Deitel", "Java", "Science and Education", "Technical",  2017, 5, 15);

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
    public void getCategoriesTest() {
        assertEquals("Science and Education", bookInfo.getCategories());
    }

    @Test
    public void setCategoriesTest() {
        bookInfo.setCategories("Science and Education");
        assertEquals("Science and Education", bookInfo.getCategories());
    }

    @Test
    public void getSubcategoriesTest() {
        assertEquals("Technical", bookInfo.getSubcategories());
    }

    @Test
    public void setSubcategoriesTest() {
        bookInfo.setSubcategories("Technical");
        assertEquals("Technical", bookInfo.getSubcategories());
    }

    @Test
    public void getYearTest() {
        assertEquals(2017, bookInfo.getYear());
    }

    @Test
    public void setYearTest() {
        bookInfo.setYear(2017);
        assertEquals(2017, bookInfo.getYear());
    }

    @Test
    public void getRatingTest() {
        assertEquals(5, bookInfo.getRating());
    }

    @Test
    public void setRatingTest() {
        bookInfo.setRating(5);
        assertEquals(5, bookInfo.getRating());
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
        assertEquals("BookInfo{title='Java : How to Program', isbn='02-3456-7890', author='Paul Deitel', publisher='Java', categories='Science and Education', subcategories='Technical', year=2017, rating=5, numcopies=15}", bookInfo.toString());
    }
}
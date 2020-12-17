package classes;

public class IssuedBook {
    private String title;
    private String isbn;
    private String author;
    private String publisher;
    private String datepicker;

    public  IssuedBook(){}

    public IssuedBook(String title, String isbn, String author, String publisher, String datepicker) {
        this.title = title;
        this.isbn = isbn;
        this.author = author;
        this.publisher = publisher;
        this.datepicker = datepicker;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getDatepicker() {
        return datepicker;
    }

    public void setDatepicker(String datepicker) {
        this.datepicker = datepicker;
    }

    @Override
    public String toString() {
        return "IssuedBook{" +
                "title='" + title + '\'' +
                ", isbn='" + isbn + '\'' +
                ", author='" + author + '\'' +
                ", publisher='" + publisher + '\'' +
                ", datepicker='" + datepicker + '\'' +
                '}';
    }
}
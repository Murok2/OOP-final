package classes;

public class BookInfo {

    protected String title;
    protected String isbn;
    protected String author;
    protected String publisher;
    private String categories;
    private String subcategories;
    private int year;
    private int rating;
    private int numcopies;

    public BookInfo(String title, String isbn, String author, String publisher, String categories, String subcategories, int year, int rating, int numcopies) {
        this.title = title;
        this.isbn = isbn;
        this.author = author;
        this.publisher = publisher;
        this.categories = categories;
        this.subcategories = subcategories;
        this.year = year;
        this.rating = rating;
        this.numcopies = numcopies;
    }

    public BookInfo(String title, String isbn, String author, String publisher) {
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

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public String getSubcategories() {
        return subcategories;
    }

    public void setSubcategories(String subcategories) {
        this.subcategories = subcategories;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getNumcopies() {
        return numcopies;
    }

    public void setNumcopies(int numcopies) {
        this.numcopies = numcopies;
    }

    @Override
    public String toString() {
        return "BookInfo{" +
                "title='" + title + '\'' +
                ", isbn='" + isbn + '\'' +
                ", author='" + author + '\'' +
                ", publisher='" + publisher + '\'' +
                ", categories='" + categories + '\'' +
                ", subcategories='" + subcategories + '\'' +
                ", year=" + year +
                ", rating=" + rating +
                ", numcopies=" + numcopies +
                '}';
    }
}
package hw05;

public class Book {
    private String title;
    private String author;
    private int pages;
    private String publisher;
    private int copies;
    private int id;

    public Book(String title, String author, int pages, String publisher, int copies, int id){
        this.title = title;
        this.author = author;
        this.pages = pages;
        this.publisher = publisher;
        this.copies = copies;
        this.id = id;
    }


    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getCopies() {
        return copies;
    }

    public void setCopies(int copies) {
        this.copies = copies;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public String toString(){
        return "Title: " + getTitle() + "\nAuthor: " + getAuthor() + "\nPages: " + getPages() + "\nPublisher: " + getPublisher() + "\nCopies Available: " + getCopies() +
                "\nID: " + getId();
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}


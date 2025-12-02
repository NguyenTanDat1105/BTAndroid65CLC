package clc65.nguyentandat.vidulamviecsqlite;

public class Book {
    private int id;
    private String name;
    private int pages;

    private  float price;
    private String description;

    public Book(int id, String name, int pages, float price, String description) {
        this.id = id;
        this.name = name;
        this.pages = pages;
        this.price = price;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

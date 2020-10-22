package ua.alevel.dto;

public class Product {
    private String sku;
    private String name;
    private int price;
    private String description;
    private String categoryName;
    private int supplierId;

    public Product() {
    }

    public Product(String sku, String name, int price, String description, String categoryName, int supplierId) {
        this.sku = sku;
        this.name = name;
        this.price = price;
        this.description = description;
        this.categoryName = categoryName;
        this.supplierId = supplierId;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    @Override
    public String toString() {
        return "Product{" +
                "sku='" + sku + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", categoryName='" + categoryName + '\'' +
                ", supplierId=" + supplierId +
                '}';
    }
}

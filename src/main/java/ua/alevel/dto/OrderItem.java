package ua.alevel.dto;

public class OrderItem {
    private int id;
    private String sku;
    private int quantity;
    private int productPrice;
    private int customerOrderId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    public int getCustomerId() {
        return customerOrderId;
    }

    public void setCustomerId(int customerId) {
        this.customerOrderId = customerId;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", sku='" + sku + '\'' +
                ", quantity=" + quantity +
                ", productPrice=" + productPrice +
                ", customerOrderId=" + customerOrderId +
                '}';
    }
}

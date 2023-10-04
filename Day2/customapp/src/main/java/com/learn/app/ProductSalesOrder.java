package com.learn.app;

public class ProductSalesOrder {

    long orderId;
    String productNo;
    String productName;
    int quantity;
    double price;

    /**
     * @return the orderId
     */
    public long getOrderId() {
        return orderId;
    }
    /**
     * @param orderId the orderId to set
     */
    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }
    /**
     * @return the productNo
     */
    public String getProductNo() {
        return productNo;
    }
    /**
     * @param productNo the productNo to set
     */
    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }
    /**
     * @return the productName
     */
    public String getProductName() {
        return productName;
    }
    /**
     * @param productName the productName to set
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }
    /**
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }
    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    /**
     * @return the price
     */
    public double getPrice() {
        return price;
    }
   
    /**
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "ProductSalesOrder [orderId=" + orderId + ", productNo=" + productNo + ", productName=" + productName
                + ", quantity=" + quantity + ", price=" + price + "]";
    }
   

    
}
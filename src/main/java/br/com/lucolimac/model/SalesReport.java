package br.com.lucolimac.model;

import java.time.LocalDate;

public class SalesReport {
    private String productName;
    private Long salesQuantity;
    private LocalDate lastSale;

    public SalesReport(String productName, Long salesQuantity, LocalDate lastSale) {
        this.productName = productName;
        this.salesQuantity = salesQuantity;
        this.lastSale = lastSale;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Long getSalesQuantity() {
        return salesQuantity;
    }

    public void setSalesQuantity(Long salesQuantity) {
        this.salesQuantity = salesQuantity;
    }

    public LocalDate getLastSale() {
        return lastSale;
    }

    public void setLastSale(LocalDate lastSale) {
        this.lastSale = lastSale;
    }
}

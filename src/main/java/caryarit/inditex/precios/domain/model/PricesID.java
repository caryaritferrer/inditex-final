package caryarit.inditex.precios.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Embeddable
public class PricesID implements Serializable {

    @Column(name = "BRAND_ID", nullable = false)
    private Long brandID;

    @Column(name = "PRODUCT_ID", nullable = false)
    private Long productID;

    @Column(name = "START_DATE", nullable = false)
    private LocalDateTime startDate;

    @Column(name = "END_DATE", nullable = false)
    private LocalDateTime endDate;

    public Long getBrandID() {
        return brandID;
    }

    public void setBrandID(Long brandID) {
        this.brandID = brandID;
    }

    public Long getProductID() {
        return productID;
    }

    public void setProductID(Long productID) {
        this.productID = productID;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PricesID pricesID = (PricesID) o;
        return Objects.equals(brandID, pricesID.brandID) && Objects.equals(productID, pricesID.productID) && Objects.equals(startDate, pricesID.startDate) && Objects.equals(endDate, pricesID.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brandID, productID, startDate, endDate);
    }
}

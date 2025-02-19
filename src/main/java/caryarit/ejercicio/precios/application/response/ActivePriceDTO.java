package caryarit.inditex.precios.application.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ActivePriceDTO implements Serializable {

    @JsonProperty("brand_id")
    private Long brandID;

    @JsonProperty("product_id")
    private Long productID;

    @JsonProperty("start_date")
    private LocalDateTime startDate;

    @JsonProperty("end_date")
    private LocalDateTime endDate;

    @JsonProperty("price")
    private BigDecimal price;

    @JsonProperty("currency")
    private String currency;

    public Long getBrandID() {
        return brandID;
    }

    public ActivePriceDTO() {
    }

    public ActivePriceDTO(Long brandID, Long productID, LocalDateTime startDate, LocalDateTime endDate, BigDecimal price, String currency) {
        this.brandID = brandID;
        this.productID = productID;
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
        this.currency = currency;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}

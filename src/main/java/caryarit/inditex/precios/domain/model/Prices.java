package caryarit.inditex.precios.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name="PRICES")
public class Prices {

    @EmbeddedId
    private PricesID id;

    @Column(name = "PRICE_LIST", nullable = false)
    private Long priceList;

    @Column(name = "PRIORITY", nullable = false)
    private Long priority;

    @Column(name = "PRICE", nullable = false, precision=20, scale=2)
    private BigDecimal price;

    @Column(name = "CURRENCY", nullable = false)
    private String currency;

    public PricesID getId() {
        return id;
    }

    public void setId(PricesID id) {
        this.id = id;
    }


    public void setPriceList(Long priceList) {
        this.priceList = priceList;
    }


    public void setPriority(Long priority) {
        this.priority = priority;
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

    public static Prices.PriceBuilder builder() {
        return new Prices.PriceBuilder();
    }
    public static final class PriceBuilder {

        private Prices prices;

        public PriceBuilder() {
            prices = new Prices();
            prices.setId(new PricesID());
        }

        public Prices.PriceBuilder productID(Long productID) {
            prices.getId().setProductID(productID);
            return this;
        }

        public Prices.PriceBuilder brandID(Long brandID) {
            prices.getId().setBrandID(brandID);
            return this;
        }

        public Prices.PriceBuilder startDate(LocalDateTime startDate) {
            prices.getId().setStartDate(startDate);
            return this;
        }

        public Prices.PriceBuilder endDate(LocalDateTime endDate) {
            prices.getId().setEndDate(endDate);
            return this;
        }

        public Prices.PriceBuilder priceList(Long priceList) {
            prices.setPriceList(priceList);
            return this;
        }

        public Prices.PriceBuilder priority(Long priority) {
            prices.setPriority(priority);
            return this;
        }

        public Prices.PriceBuilder price(BigDecimal price) {
            prices.setPrice(price);
            return this;
        }

        public Prices.PriceBuilder currency(String currency) {
            prices.setCurrency(currency);
            return this;
        }

        public Prices build() {
            return prices;
        }
    }
}

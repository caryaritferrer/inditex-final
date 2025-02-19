package caryarit.inditex.precios;

import caryarit.inditex.precios.domain.model.Prices;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Utils {

    public static Prices buildPrices(Long brandID, Long productID, LocalDateTime startDate, LocalDateTime endDate,
                               BigDecimal price) {
        Prices.PriceBuilder builder = Prices.builder();
        builder.brandID(brandID)
                .productID(productID)
                .startDate(startDate)
                .endDate(endDate)
                .price(price);

        return builder.build();
    }

}

package caryarit.inditex.precios.application.controller;

import caryarit.inditex.precios.application.exceptions.NotFoundException;
import caryarit.inditex.precios.application.response.ActivePriceDTO;
import caryarit.inditex.precios.domain.model.Prices;
import caryarit.inditex.precios.domain.usecase.FindActivePrice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/precio")
public class PriceController {

    private FindActivePrice findActivePrice;

    @Autowired
    public PriceController(@Autowired FindActivePrice findActivePrice) {
        this.findActivePrice = findActivePrice;
    }

    @GetMapping(value="/encuentraActivo", produces = MediaType.APPLICATION_JSON_VALUE)
    public ActivePriceDTO findActivePrice(@RequestParam("product_id") Long productID,
                                          @RequestParam("brand_id") Long brandID,
                                          @RequestParam(value = "date", required = false) LocalDateTime dateRequest) throws Exception {
        if (dateRequest == null) {
            dateRequest = LocalDateTime.now();
        }

        Prices activePrice = findActivePrice.execute(productID, brandID, dateRequest);

        if (activePrice == null) {
            throw new NotFoundException(String.format("No encontrado: %d - Brand ID: %d - fecha: %s ",
                    productID, brandID, dateRequest));
        }

        return new ActivePriceDTO(activePrice.getId().getBrandID(), activePrice.getId().getProductID(),
                activePrice.getId().getStartDate(), activePrice.getId().getEndDate(),
                 activePrice.getPrice(), activePrice.getCurrency());
    }
}

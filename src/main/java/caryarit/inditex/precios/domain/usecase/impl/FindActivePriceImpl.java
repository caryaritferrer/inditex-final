package caryarit.inditex.precios.domain.usecase.impl;

import caryarit.inditex.precios.domain.model.Prices;
import caryarit.inditex.precios.port.repository.PriceRepository;
import caryarit.inditex.precios.domain.usecase.FindActivePrice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class FindActivePriceImpl implements FindActivePrice {

    private PriceRepository priceRepository;

    @Autowired
    public FindActivePriceImpl(@Autowired PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    @Override
    public Prices execute(Long productID, Long brandID, LocalDateTime requestDate) {
        return priceRepository.findActiveByProduct(productID, brandID, requestDate);
    }

}
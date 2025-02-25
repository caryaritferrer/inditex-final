package caryarit.inditex.precios.domain.usecase;

import caryarit.inditex.precios.domain.model.Prices;

import java.time.LocalDateTime;

public interface FindActivePriceUseCase {

    Prices execute(Long productID, Long brandID, LocalDateTime requestDate);
}

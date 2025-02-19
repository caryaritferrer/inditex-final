package caryarit.inditex.precios.units.controller;

import caryarit.inditex.precios.application.controller.PriceController;
import caryarit.inditex.precios.application.exceptions.NotFoundException;
import caryarit.inditex.precios.application.response.ActivePriceDTO;
import caryarit.inditex.precios.domain.model.Prices;
import caryarit.inditex.precios.domain.usecase.FindActivePrice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static caryarit.inditex.precios.Utils.buildPrices;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = caryarit.inditex.precios.PreciosApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PriceControllerTest {

    @InjectMocks
    private PriceController priceController;

    @Mock
    private FindActivePrice findActivePrice;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Test con todo correcto")
    public void testSuccess() throws Exception {
        Long brandID = 1L;
        Long productID = 2L;
        LocalDateTime requestDate = LocalDateTime.now();
        Prices prices = buildPrices(1L, 2L, requestDate.minusDays(1), requestDate.plusHours(2), BigDecimal.ONE);

        when(findActivePrice.execute(productID, brandID, requestDate)).thenReturn(prices);

        ActivePriceDTO result = priceController.findActivePrice(productID, brandID, requestDate);

        assertEquals(brandID, result.getBrandID());
        assertEquals(productID, result.getProductID());
        assertEquals(prices.getId().getStartDate(), result.getStartDate());
        assertEquals(prices.getId().getEndDate(), result.getEndDate());
        assertEquals(prices.getPrice(), result.getPrice());
        //assertEquals(1, 2);

    }

    @Test
    @DisplayName("Test sin resultado y sin error")
    public void testNoExiste() throws Exception {
        Long brandID = 1L;
        Long productID = 3L;
        LocalDateTime requestDate = LocalDateTime.now();

        when(findActivePrice.execute(productID, brandID, requestDate)).thenReturn(null);

        assertThrows(NotFoundException.class, () -> {
            priceController.findActivePrice(productID, brandID, requestDate);
        });
    }

    @Test
    @DisplayName("Test devuelve exception")
    public void testException() {
        Long brandID = 1L;
        Long productID = 2L;
        LocalDateTime requestDate = LocalDateTime.now();

        when(findActivePrice.execute(productID, brandID, requestDate))
                .thenThrow(new RuntimeException("NOT ALL DATA"));

        assertThrows(RuntimeException.class, () -> {
            priceController.findActivePrice(productID, brandID, requestDate);
        });
    }
}

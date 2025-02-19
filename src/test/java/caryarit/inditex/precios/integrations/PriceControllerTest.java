package caryarit.inditex.precios.integrations;

import caryarit.inditex.precios.PreciosApplication;
import caryarit.inditex.precios.application.response.ActivePriceDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = PreciosApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PriceControllerTest {

    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();

    HttpHeaders headers = new HttpHeaders();
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void testApiCalls() {
        testApiCall(LocalDateTime.of(2020, 6, 14, 10, 0, 0), 1L, 35455L, new BigDecimal("35.50"), "EUR", LocalDateTime.of(2020, 6, 14, 0, 0, 0), LocalDateTime.of(2020, 12, 31, 23, 59, 59));
        testApiCall(LocalDateTime.of(2020, 6, 14, 16, 0, 0), 1L, 35455L, new BigDecimal("25.45"), "EUR", LocalDateTime.of(2020, 6, 14, 15, 0, 0), LocalDateTime.of(2020, 6, 14, 18, 30, 0));
        testApiCall(LocalDateTime.of(2020, 6, 15, 10, 0, 0), 1L, 35455L,  new BigDecimal("30.50"), "EUR", LocalDateTime.of(2020, 6, 15, 0, 0, 0), LocalDateTime.of(2020, 6, 15, 11, 0, 0));
        testApiCall(LocalDateTime.of(2020, 6, 16, 21, 0, 0), 1L, 35455L,  new BigDecimal("38.95"), "EUR", LocalDateTime.of(2020, 6, 15, 16, 0, 0), LocalDateTime.of(2020, 12, 31, 23, 59, 59));
    }

    private void testApiCall(LocalDateTime dateTime, Long expectedBrandID, Long expectedProductID, BigDecimal expectedPrice, String expectedCurrency, LocalDateTime expectedStartDate, LocalDateTime expectedEndDate) {
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<ActivePriceDTO> response = restTemplate.exchange(
                "http://localhost:" + port + "/precio/encuentraActivo?product_id=" + expectedProductID + "&brand_id=" + expectedBrandID + "&date=" + dateTime.toString(),
                HttpMethod.GET, entity, ActivePriceDTO.class);

        ActivePriceDTO result = response.getBody();

        Assertions.assertNotNull(result);
        Assertions.assertEquals(expectedBrandID, result.getBrandID());
        Assertions.assertEquals(expectedProductID, result.getProductID());
        Assertions.assertEquals(expectedPrice, result.getPrice());
        Assertions.assertEquals(expectedCurrency, result.getCurrency());
        Assertions.assertEquals(expectedStartDate, result.getStartDate());
        Assertions.assertEquals(expectedEndDate, result.getEndDate());
    }

}

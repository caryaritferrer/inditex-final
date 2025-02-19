package caryarit.inditex.precios.domain.repository;

import caryarit.inditex.precios.domain.model.Prices;
import caryarit.inditex.precios.domain.model.PricesID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface PriceRepository extends JpaRepository<Prices, PricesID> {

    @Query("SELECT p FROM Prices p WHERE p.id.productID = :product_id AND p.id.brandID = :brand_id AND p.id.startDate <= :date AND p.id.endDate > :date ORDER BY p.priority DESC LIMIT 1")
    Prices findActiveByProduct(
            @Param("product_id") Long productID, @Param("brand_id") Long brandID, @Param("date") LocalDateTime date);
}

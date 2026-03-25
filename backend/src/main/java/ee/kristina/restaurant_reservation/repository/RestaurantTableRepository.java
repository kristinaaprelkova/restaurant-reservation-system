package ee.kristina.restaurant_reservation.repository;

import ee.kristina.restaurant_reservation.entity.RestaurantTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantTableRepository extends JpaRepository<RestaurantTable, Long> {
}

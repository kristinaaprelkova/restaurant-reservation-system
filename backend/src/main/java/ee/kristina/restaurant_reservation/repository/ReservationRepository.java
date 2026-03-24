package ee.kristina.restaurant_reservation.repository;

import ee.kristina.restaurant_reservation.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}

package ee.kristina.restaurant_reservation.service;

import ee.kristina.restaurant_reservation.entity.Reservation;
import ee.kristina.restaurant_reservation.repository.ReservationRepository;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {

    private final ReservationRepository repository;

    public ReservationService(ReservationRepository repository) {
        this.repository = repository;
    }

    public Reservation save(Reservation reservation) {
        return repository.save(reservation);
    }
}

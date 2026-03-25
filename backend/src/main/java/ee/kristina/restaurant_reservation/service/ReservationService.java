package ee.kristina.restaurant_reservation.service;

import ee.kristina.restaurant_reservation.entity.Reservation;
import ee.kristina.restaurant_reservation.entity.RestaurantTable;
import ee.kristina.restaurant_reservation.repository.ReservationRepository;
import ee.kristina.restaurant_reservation.repository.RestaurantTableRepository;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {

    private final ReservationRepository repository;
    private final RestaurantTableRepository tableRepository;

    public ReservationService(ReservationRepository repository, RestaurantTableRepository tableRepository) {
        this.repository = repository;
        this.tableRepository = tableRepository;
    }

    public Reservation save(Reservation reservation) {
        if (reservation.getTable() != null && reservation.getTable().getId() != null) {
            Long tableId = reservation.getTable().getId();

            RestaurantTable table = tableRepository.findById(tableId)
                    .orElseThrow(() -> new RuntimeException("Table not found"));

            reservation.setTable(table);
        }

        return repository.save(reservation);
    }
}

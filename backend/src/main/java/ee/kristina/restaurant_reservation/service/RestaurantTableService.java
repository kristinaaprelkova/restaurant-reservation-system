package ee.kristina.restaurant_reservation.service;

import ee.kristina.restaurant_reservation.entity.Reservation;
import ee.kristina.restaurant_reservation.entity.RestaurantTable;
import ee.kristina.restaurant_reservation.repository.ReservationRepository;
import ee.kristina.restaurant_reservation.repository.RestaurantTableRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RestaurantTableService {

    private static final int RESERVATION_DURATION_MINUTES = 150; // 2.5 hours

    private final RestaurantTableRepository tableRepository;
    private final ReservationRepository reservationRepository;

    public RestaurantTableService(RestaurantTableRepository tableRepository,
                                  ReservationRepository reservationRepository) {
        this.tableRepository = tableRepository;
        this.reservationRepository = reservationRepository;
    }

    public List<RestaurantTable> filterTables(Integer guestCount,
                                              String date,
                                              String zone,
                                              String time) {

        List<RestaurantTable> tables = tableRepository.findAll();

        if (guestCount != null) {
            Set<Integer> recommendedSeats = getRecommendedSeats(guestCount);

            tables = tables.stream()
                    .filter(table -> table.getSeats() != null && recommendedSeats.contains(table.getSeats()))
                    .collect(Collectors.toList());
        }

        if (zone != null && !zone.isBlank()) {
            tables = tables.stream()
                    .filter(table -> table.getZone() != null && table.getZone().equalsIgnoreCase(zone))
                    .collect(Collectors.toList());
        }

        if (date != null && !date.isBlank() && time != null && !time.isBlank()) {
            LocalDate reservationDate = LocalDate.parse(date);
            LocalTime requestedStartTime = LocalTime.parse(time);

            List<Reservation> reservations =
                    reservationRepository.findByReservationDate(reservationDate);

            Set<Long> occupiedTableIds = reservations.stream()
                    .filter(reservation -> reservation.getTable() != null)
                    .filter(reservation -> isOverlapping(
                            reservation.getReservationTime(),
                            requestedStartTime
                    ))
                    .map(reservation -> reservation.getTable().getId())
                    .collect(Collectors.toSet());

            tables = tables.stream()
                    .filter(table -> !occupiedTableIds.contains(table.getId()))
                    .collect(Collectors.toList());
        }

        return tables;
    }

    private Set<Integer> getRecommendedSeats(Integer guestCount) {
        if (guestCount <= 3) {
            return Set.of(2, 4);
        } else if (guestCount <= 5) {
            return Set.of(4, 6);
        } else if (guestCount <= 7) {
            return Set.of(6, 8);
        } else {
            return Set.of(8, 10);
        }
    }

    private boolean isOverlapping(LocalTime existingStartTime, LocalTime requestedStartTime) {
        LocalTime existingEndTime = existingStartTime.plusMinutes(RESERVATION_DURATION_MINUTES);
        LocalTime requestedEndTime = requestedStartTime.plusMinutes(RESERVATION_DURATION_MINUTES);

        return requestedStartTime.isBefore(existingEndTime)
                && requestedEndTime.isAfter(existingStartTime);
    }
}
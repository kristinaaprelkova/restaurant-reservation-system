package ee.kristina.restaurant_reservation.controller;

import ee.kristina.restaurant_reservation.entity.RestaurantTable;
import ee.kristina.restaurant_reservation.service.RestaurantTableService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/tables")
@CrossOrigin(origins = "http://localhost:5173")
public class RestaurantTableController {

    private final RestaurantTableService service;

    public RestaurantTableController(RestaurantTableService service) {
        this.service = service;
    }

    @GetMapping("/filter")
    public List<RestaurantTable> filterTables(
            @RequestParam(required = false) Integer guestCount,
            @RequestParam(required = false) String date,
            @RequestParam(required = false) String zone,
            @RequestParam(required = false) String time
    ) {
        return service.filterTables(guestCount, date, zone, time);
    }
}

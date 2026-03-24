package ee.kristina.restaurant_reservation;

import ee.kristina.restaurant_reservation.entity.RestaurantTable;
import ee.kristina.restaurant_reservation.repository.RestaurantTableRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initTables(RestaurantTableRepository tableRepository) {
        return args -> {
            if (tableRepository.count() == 0) {
                tableRepository.save(new RestaurantTable(1, 2, "terrass"));
                tableRepository.save(new RestaurantTable(2, 4, "terrass"));
                tableRepository.save(new RestaurantTable(3, 2, "terrass"));
                tableRepository.save(new RestaurantTable(4, 4, "terrass"));
                tableRepository.save(new RestaurantTable(5, 6, "terrass"));
                tableRepository.save(new RestaurantTable(6, 8, "terrass"));
                tableRepository.save(new RestaurantTable(7, 2, "terrass"));
                tableRepository.save(new RestaurantTable(8, 4, "terrass"));
                tableRepository.save(new RestaurantTable(9, 2, "privaatruum"));
                tableRepository.save(new RestaurantTable(10, 4, "sisesaal"));
                tableRepository.save(new RestaurantTable(11, 6, "sisesaal"));
                tableRepository.save(new RestaurantTable(12, 8, "sisesaal"));
                tableRepository.save(new RestaurantTable(13, 2, "sisesaal"));
                tableRepository.save(new RestaurantTable(14, 4, "sisesaal"));
                tableRepository.save(new RestaurantTable(15, 2, "privaatruum"));
            }
        };
    }
}

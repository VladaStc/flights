-- Dumping structure for table flights.flight
CREATE TABLE IF NOT EXISTS `flight` (
                                        `flight_id` int NOT NULL,
                                        `flight_number` int NOT NULL,
                                        `departure_airport_iata_code` varchar(255) NOT NULL,
                                        `arrival_airport_iata_code` varchar(255) NOT NULL,
                                        `departure_date` varchar(255) NOT NULL,
                                        PRIMARY KEY (`flight_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- Dumping structure for table flights.baggage
CREATE TABLE IF NOT EXISTS `baggage` (
                                         `id` int NOT NULL AUTO_INCREMENT,
                                         `weight` int NOT NULL,
                                         `weight_unit` varchar(255) NOT NULL,
                                         `pieces` int NOT NULL,
                                         `flight_id` int NOT NULL,
                                         PRIMARY KEY (`id`),
                                         KEY `baggage_fk0` (`flight_id`),
                                         CONSTRAINT `baggage_fk0` FOREIGN KEY (`flight_id`) REFERENCES `flight` (`flight_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- Dumping structure for table flights.cargo
CREATE TABLE IF NOT EXISTS `cargo` (
                                       `id` int NOT NULL AUTO_INCREMENT,
                                       `weight` int NOT NULL,
                                       `weight_unit` varchar(255) NOT NULL,
                                       `pieces` int NOT NULL,
                                       `flight_id` int NOT NULL,
                                       PRIMARY KEY (`id`),
                                       KEY `cargo_fk0` (`flight_id`),
                                       CONSTRAINT `cargo_fk0` FOREIGN KEY (`flight_id`) REFERENCES `flight` (`flight_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



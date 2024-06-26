package com.joe.inventoryservice;

import com.joe.inventoryservice.model.Inventory;
import com.joe.inventoryservice.repository.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class InventoryServiceApplication {

	public static void main(String[] args) {SpringApplication.run(InventoryServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadData(InventoryRepository inventoryRepository){
		return args -> {
			Inventory inventory = new Inventory();
			inventory.setSkuCode("T480s");
			inventory.setQuantity(0);

			Inventory inventory1 = new Inventory();
			inventory1.setSkuCode("T1Carbon");
			inventory1.setQuantity(250);

			inventoryRepository.save(inventory);
			inventoryRepository.save(inventory1);
		};
	}

}

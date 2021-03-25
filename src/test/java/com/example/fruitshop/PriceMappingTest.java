package com.example.fruitshop;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("As the shop owner I want to...")
public class PriceMappingTest {

	private final PriceMapping priceMapping = new PriceMapping();

	@Test
	@DisplayName("Set the price of an apple to 60p")
	public void testApplePrice() {
		assertEquals(0.6f, this.priceMapping.getPrice(InventoryItem.APPLE));
	}

	@Test
	@DisplayName("Set the price of an orange to 25p")
	public void testOrangePrice() {
		assertEquals(0.25f, this.priceMapping.getPrice(InventoryItem.ORANGE));
	}

	@Test
	@DisplayName("The mapping to fail for a null product")
	public void testNullProduct() {
		assertThrows(IllegalArgumentException.class, () -> this.priceMapping.getPrice(null));
	}

}

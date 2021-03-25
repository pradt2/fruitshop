package com.example.fruitshop;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("As the shop owner I want to...")
public class InventoryItemTest {

	@Test
	@DisplayName("Offer apples")
	public void testApples() {
		final String apple = "apple";
		assertTrue(apple.equalsIgnoreCase(InventoryItem.APPLE.name()),
				"Apples should be named 'apple'");
	}

	@Test
	@DisplayName("Offer oranges")
	public void testOranges() {
		final String orange = "orange";
		assertTrue(orange.equalsIgnoreCase(InventoryItem.ORANGE.name()),
				"Oranges should be named 'orange'");
	}

	@Test
	@DisplayName("Offer no other items")
	public void testNoOtherItems() {
		assertEquals(2, InventoryItem.values().length,
				"The store should offer only 2 types of items");
	}

}

package com.example.fruitshop;

import java.util.HashMap;
import java.util.Map;

public class PriceMapping {

	private final Map<InventoryItem, Float> prices = new HashMap<>();

	public PriceMapping() {
		prices.put(InventoryItem.APPLE, 0.6f);
		prices.put(InventoryItem.ORANGE, 0.25f);
	}

	public float getPrice(InventoryItem item) {
		if (item == null) {
			throw new IllegalArgumentException("Item cannot be null");
		}

		Float price = this.prices.get(item);
		return price;
	}

}

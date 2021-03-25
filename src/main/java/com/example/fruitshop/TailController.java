package com.example.fruitshop;

import java.util.List;
import java.util.Objects;

public class TailController {

	private final PriceMapping priceMapping;

	public TailController(PriceMapping priceMapping) {
		this.priceMapping = priceMapping;
	}

	public float getTotalAmountDue(final List<InventoryItem> items) {
		boolean containsNullItems = items.stream().anyMatch(Objects::isNull);
		if (containsNullItems) {
			throw new IllegalArgumentException("At least one item is null");
		}

		float totalDue = items.stream()
				.map(this.priceMapping::getPrice)
				.reduce(0.0f, Float::sum);
		return totalDue;
	}

}

package com.example.fruitshop;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TailController {

	private final PriceMapping priceMapping;
	private final Promotion[] promotions;

	public TailController(PriceMapping priceMapping, Promotion... promotions) {
		this.priceMapping = priceMapping;
		this.promotions = promotions;
	}

	public float getTotalAmountDue(List<InventoryItem> items) {
		boolean containsNullItems = items.stream().anyMatch(Objects::isNull);
		if (containsNullItems) {
			throw new IllegalArgumentException("At least one item is null");
		}

		items = new ArrayList<>(items);
		float totalDue = 0;
		boolean atLeastOnePromotionApplied;

		do {
			atLeastOnePromotionApplied = false;
			for (Promotion promotion : this.promotions) {
				if (!promotion.isApplicable(items)) continue;
				totalDue += promotion.apply(items);
				atLeastOnePromotionApplied = true;
			}
		} while (atLeastOnePromotionApplied);

		totalDue = items.stream()
				.map(this.priceMapping::getPrice)
				.reduce(totalDue, Float::sum);
		return totalDue;
	}

}

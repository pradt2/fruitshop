package com.example.fruitshop;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ApplesBuyOneGetOneFreePromotion implements Promotion {

	private final PriceMapping priceMapping;

	public ApplesBuyOneGetOneFreePromotion(PriceMapping priceMapping) {
		this.priceMapping = priceMapping;
	}

	@Override
	public boolean isApplicable(List<InventoryItem> items) {
		Map<InventoryItem, List<InventoryItem>> groupedItems = items.stream()
				.collect(Collectors.groupingBy(Function.identity()));
		List<InventoryItem> applesList = groupedItems.get(InventoryItem.APPLE);
		if (applesList == null || applesList.size() < 2) return false;
		return true;
	}

	@Override
	public float apply(List<InventoryItem> items) {
		if (!this.isApplicable(items)) {
			throw new IllegalArgumentException("The promotion is not applicable");
		}
		items.remove(InventoryItem.APPLE);
		items.remove(InventoryItem.APPLE);
		return this.priceMapping.getPrice(InventoryItem.APPLE);
	}

}

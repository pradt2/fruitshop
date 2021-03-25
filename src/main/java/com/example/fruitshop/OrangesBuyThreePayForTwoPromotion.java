package com.example.fruitshop;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class OrangesBuyThreePayForTwoPromotion implements Promotion {

	private final PriceMapping priceMapping;

	public OrangesBuyThreePayForTwoPromotion(PriceMapping priceMapping) {
		this.priceMapping = priceMapping;
	}

	@Override
	public boolean isApplicable(List<InventoryItem> items) {
		Map<InventoryItem, List<InventoryItem>> groupedItems = items.stream()
				.collect(Collectors.groupingBy(Function.identity()));
		List<InventoryItem> orangesList = groupedItems.get(InventoryItem.ORANGE);
		if (orangesList == null || orangesList.size() < 3) return false;
		return true;
	}

	@Override
	public float apply(List<InventoryItem> items) {
		if (!this.isApplicable(items)) {
			throw new IllegalArgumentException("The promotion is not applicable");
		}
		items.remove(InventoryItem.ORANGE);
		items.remove(InventoryItem.ORANGE);
		items.remove(InventoryItem.ORANGE);
		return this.priceMapping.getPrice(InventoryItem.ORANGE) * 2;
	}

}

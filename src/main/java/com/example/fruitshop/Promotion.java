package com.example.fruitshop;

import java.util.List;

public interface Promotion {

	boolean isApplicable(List<InventoryItem> items);

	float apply(List<InventoryItem> items);

}

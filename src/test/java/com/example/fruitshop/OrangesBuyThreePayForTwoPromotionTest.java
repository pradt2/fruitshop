package com.example.fruitshop;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static com.example.fruitshop.InventoryItem.APPLE;
import static com.example.fruitshop.InventoryItem.ORANGE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@DisplayName("As a shop owner I want to...")
public class OrangesBuyThreePayForTwoPromotionTest {

	private static final float ORANGE_PRICE = 0.17f;

	private static OrangesBuyThreePayForTwoPromotion promotion;

	public static Stream<Arguments> applicabilityTestCases() {
		return Stream.of(
				Arguments.of(false, Collections.emptyList()),
				Arguments.of(false, Collections.singletonList(ORANGE)),
				Arguments.of(true, Arrays.asList(ORANGE, ORANGE, ORANGE)),
				Arguments.of(true, Arrays.asList(ORANGE, ORANGE, ORANGE, ORANGE)),
				Arguments.of(false, Arrays.asList(ORANGE, APPLE)),
				Arguments.of(true, Arrays.asList(ORANGE, ORANGE, ORANGE, APPLE))
		);
	}


	@BeforeAll
	public static void setup() {
		PriceMapping mockPriceMapping = mock(PriceMapping.class);

		when(mockPriceMapping.getPrice(ORANGE))
				.thenReturn(ORANGE_PRICE);

		promotion = new OrangesBuyThreePayForTwoPromotion(mockPriceMapping);
	}

	@MethodSource(value = "applicabilityTestCases")
	@ParameterizedTest(name = "Apply the promotion: {0} for the following products: {1}")
	public void testIsApplicable(boolean isApplicable, List<InventoryItem> items) {
		assertEquals(isApplicable,
				promotion.isApplicable(items),
				"The applicability should match the expected value");
	}

	@Test
	@DisplayName("Charge for two oranges for every three oranges")
	public void testPromotion() {
		final List<InventoryItem> items = new ArrayList<>(Arrays.asList(ORANGE, ORANGE, APPLE, ORANGE));
		assertEquals(ORANGE_PRICE * 2, promotion.apply(items),
				"The price should be equal to the price of two oranges");
		assertEquals(1, items.size(), "Three oranges should be removed from the list");
		assertTrue(items.contains(APPLE), "There should still be an apple in the list");
	}

}

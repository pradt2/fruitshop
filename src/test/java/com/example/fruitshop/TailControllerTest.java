package com.example.fruitshop;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static com.example.fruitshop.InventoryItem.APPLE;
import static com.example.fruitshop.InventoryItem.ORANGE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@DisplayName("As a tail operator I want to...")
public class TailControllerTest {

	private static final float APPLE_PRICE = 0.13f;
	private static final float ORANGE_PRICE = 0.17f;

	private static TailController tailController;

	public static Stream<Arguments> testCases() {
		return Stream.of(
				Arguments.of(0.0f, Collections.emptyList()),
				Arguments.of(APPLE_PRICE, Collections.singletonList(APPLE)),
				Arguments.of(2 * ORANGE_PRICE, Arrays.asList(ORANGE, ORANGE)),
				Arguments.of(APPLE_PRICE + ORANGE_PRICE, Arrays.asList(ORANGE, APPLE)),
				Arguments.of(APPLE_PRICE, Arrays.asList(APPLE, APPLE))
		);
	}

	@BeforeAll
	public static void setup() {
		PriceMapping mockPriceMapping = mock(PriceMapping.class);
		when(mockPriceMapping.getPrice(APPLE))
				.thenReturn(APPLE_PRICE);
		when(mockPriceMapping.getPrice(ORANGE))
				.thenReturn(ORANGE_PRICE);

		tailController = new TailController(mockPriceMapping,
				new ApplesBuyOneGetOneFreePromotion(mockPriceMapping));
	}

	@MethodSource(value = "testCases")
	@ParameterizedTest(name = "Get amount due equal to {0} for the following products: {1}")
	public void testGetTotalAmountDue(float expectedAmountDue, List<InventoryItem> items) {
		assertEquals(expectedAmountDue,
				tailController.getTotalAmountDue(items),
				"The amount due should match the expected value");
	}

	@Test
	@DisplayName("Get an exception when there is a null product in the list")
	public void testGetTotalAmountForListWithNulls() {
		assertThrows(IllegalArgumentException.class,
				() -> tailController.getTotalAmountDue(Collections.singletonList(null)),
				"An exception should be thrown for a list with null values");
	}


}

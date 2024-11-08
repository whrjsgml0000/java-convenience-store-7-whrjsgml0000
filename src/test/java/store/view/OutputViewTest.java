package store.view;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import store.domain.Item;
import store.domain.Item.ItemBuilder;

class OutputViewTest {
    OutputView outputView = new OutputView();

    @Test
    void printItem() {
        Item item = ItemBuilder.builder()
                .name("콜라")
                .price(1000)
                .quantity(1)
                .promotion("2+1!")
                .build();
        outputView.printItem(item);
    }
}
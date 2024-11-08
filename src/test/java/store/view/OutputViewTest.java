package store.view;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import store.convenience.ConvenienceController;
import store.domain.Item;
import store.domain.Item.ItemBuilder;

class OutputViewTest {
    OutputView outputView = new OutputView();
    ConvenienceController convenienceController = new ConvenienceController();

    @Test
    void printItem() {
        convenienceController.run();

    }
}
package store.view;

import store.domain.Item;

public class OutputView {
    private static final String PRINT_ITEM_FORMAT = "- %s %,d원 %s %s";

    public void printItem(Item item) {
        String sQuantity = item.getQuantity().toString() + "개";
        if (item.getQuantity() == 0) {
            sQuantity = "재고 없음";
        }
        System.out.printf((PRINT_ITEM_FORMAT) + "%n", item.getName(), item.getPrice(), sQuantity, item.getPromotion());
    }
}

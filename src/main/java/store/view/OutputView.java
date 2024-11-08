package store.view;

import java.util.List;
import store.domain.Item;

public class OutputView {
    private static final String PRINT_ITEM_FORMAT = "- %s %,d원 %s %s";

    public void introduction(List<Item> items){
        System.out.println("안녕하세요. W편의점입니다.");
        System.out.println("현재 보유하고 있는 상품입니다.");
        System.out.println();
        printAllItem(items);
    }

    private void printAllItem(List<Item> items){
        items.forEach(this::printItem);
    }

    private void printItem(Item item) {
        String sQuantity = item.getQuantity().toString() + "개";
        if (item.getQuantity() == 0) {
            sQuantity = "재고 없음";
        }
        System.out.printf((PRINT_ITEM_FORMAT) + "%n", item.getName(), item.getPrice(), sQuantity, item.getPromotion());
    }
}

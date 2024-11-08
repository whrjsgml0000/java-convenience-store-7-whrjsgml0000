package store.convenience;

import camp.nextstep.edu.missionutils.DateTimes;
import java.util.List;
import store.domain.Item;
import store.domain.Promotion;
import store.repository.ItemRepository;
import store.repository.PromotionRepository;
import store.validator.PurchaseValidator;

public class ConvenienceService {
    private final ItemRepository itemRepository;
    private final PromotionRepository promotionRepository;

    public ConvenienceService(ItemRepository itemRepository, PromotionRepository promotionRepository) {
        this.itemRepository = itemRepository;
        this.promotionRepository = promotionRepository;
    }

    public List<Item> getCurrentState() {
        return itemRepository.findAll();
    }

    public List<Item> getValidItem() {
        List<String> promotionNames = getValidPromotion().stream()
                .map(Promotion::getName)
                .toList();
        return itemRepository.findAll().stream()
                .filter(item -> item.getPromotion().isBlank() || promotionNames.contains(item.getPromotion()))
                .toList();
    }

    public List<Item> getItemAllWithName(String name) {
        return getValidItem().stream()
                .filter(item -> item.getName().equals(name))
                .toList();
    }

    public List<Promotion> getValidPromotion() {
        return promotionRepository.findByPromotionWhereBetweenStartDateAndEndDate(DateTimes.now());
    }

    public boolean validNameAndQuantity(String input) {
        return PurchaseValidator.validInputForm(input)
                && PurchaseValidator.noDuplicatedItemInput(input)
                && PurchaseValidator.existItemName(input, itemRepository.findAll())
                && PurchaseValidator.validQuantity(input, getValidItem());
    }

    public boolean hasPromotion(String name) {
        return getValidItem().stream().anyMatch(item -> item.getName().equals(name) && !item.getPromotion().isBlank());
    }

    // 초과할 경우 못받는 양을 -로 전달하고, 부족할 경우 +로 안내함.
    public int compareQuantity(String name, int quantity) {
        List<Item> allItem = getValidItem().stream().filter(item -> item.getName().equals(name)).toList();
        Item promotionItem = allItem.stream().filter(item -> !item.getPromotion().isBlank()).findAny().get();
        Promotion promotion = promotionRepository.findByName(promotionItem.getPromotion()).get();
        // 프로모션이 충분히 남아있고, 추가받을 양을 안가지고 온 경우
        if (promotion.getBuy() + promotion.getGet() < promotionItem.getQuantity()
                && quantity == promotion.getBuy()) {
            return promotion.getGet(); // 이 만큼 추가 가능함을 알림.
        }
        // 프로모션 받지 못하는 양을 리턴함.
        if (promotionItem.getQuantity() < quantity) {
            return -(quantity - (promotionItem.getQuantity() % (promotion.getBuy() + promotion.getGet())));
        }
        return 0;
    }
}

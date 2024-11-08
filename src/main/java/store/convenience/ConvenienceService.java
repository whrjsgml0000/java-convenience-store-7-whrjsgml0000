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

    public List<Item> getItemAllWithName(String name){
        return getValidItem().stream()
                .filter(item->item.getName().equals(name))
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
}

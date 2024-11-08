package store.convenience;

import java.util.List;
import store.domain.Item;
import store.repository.ItemRepository;
import store.repository.PromotionRepository;

public class ConvenienceService {
    private final ItemRepository itemRepository;
    private final PromotionRepository promotionRepository;
    public ConvenienceService(ItemRepository itemRepository, PromotionRepository promotionRepository){
        this.itemRepository = itemRepository;
        this.promotionRepository = promotionRepository;
    }

    public List<Item> getCurrentState(){
        return itemRepository.findAll();
    }
}

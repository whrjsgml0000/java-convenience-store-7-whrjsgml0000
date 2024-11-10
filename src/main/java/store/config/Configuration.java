package store.config;

import store.convenience.ConvenienceController;
import store.convenience.ConvenienceService;
import store.convenience.LoadDataService;
import store.repository.ItemRepository;
import store.repository.PromotionRepository;

public class Configuration {
    private final ConvenienceController convenienceController;
    private final ItemRepository itemRepository = ItemRepository.getInstance();
    private final PromotionRepository promotionRepository = PromotionRepository.getInstance();
    private final ConvenienceService convenienceService;
    private final LoadDataService loadDataService;

    public Configuration() {
        loadDataService = new LoadDataService(itemRepository, promotionRepository);
        convenienceService = new ConvenienceService(itemRepository, promotionRepository);
        convenienceController = new ConvenienceController(loadDataService, convenienceService);
    }

    public ConvenienceController getConvenienceController() {
        return convenienceController;
    }
}

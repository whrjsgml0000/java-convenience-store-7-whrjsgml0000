package store.config;

import store.convenience.ConvenienceController;
import store.convenience.LoadDataService;
import store.repository.ItemRepository;
import store.repository.PromotionRepository;

public class Configuration {
    ConvenienceController convenienceController = new ConvenienceController();
    ItemRepository itemRepository = ItemRepository.getInstance();
    PromotionRepository promotionRepository = PromotionRepository.getInstance();
    LoadDataService loadDataService;
    public static Configuration getInstance() {
        return ConfigurationHolder.CONFIGURATION;
    }
    private static class ConfigurationHolder{
        private static final Configuration CONFIGURATION = new Configuration();
    }
    private Configuration(){
        loadDataService = new LoadDataService(itemRepository,promotionRepository);
    }

    public ConvenienceController getConvenienceController() {
        return convenienceController;
    }
}

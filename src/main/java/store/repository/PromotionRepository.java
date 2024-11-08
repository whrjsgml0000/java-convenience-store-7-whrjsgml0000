package store.repository;

import java.util.ArrayList;
import store.domain.Promotion;

public class PromotionRepository {
    private final ArrayList<Promotion> promotions = new ArrayList<>();
    public static PromotionRepository getInstance() {
        return PromotionRepositoryHolder.ITEM_REPOSITORY;
    }
    private static class PromotionRepositoryHolder {
        private static final PromotionRepository ITEM_REPOSITORY = new PromotionRepository();
    }
    private PromotionRepository(){

    }

    public void saveAll(){

    }
}

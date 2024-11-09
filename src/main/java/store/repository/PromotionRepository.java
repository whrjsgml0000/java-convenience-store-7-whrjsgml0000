package store.repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import store.domain.Promotion;

public class PromotionRepository {
    private final ArrayList<Promotion> promotions = new ArrayList<>();

    public static PromotionRepository getInstance() {
        return PromotionRepositoryHolder.ITEM_REPOSITORY;
    }

    private static class PromotionRepositoryHolder {
        private static final PromotionRepository ITEM_REPOSITORY = new PromotionRepository();
    }

    private PromotionRepository() {

    }

    public void saveAll(List<Promotion> promotions) {
        this.promotions.addAll(promotions);
    }

    public List<Promotion> findByPromotionWhereBetweenStartDateAndEndDate(LocalDateTime today) {
        return promotions.stream()
                .filter(promotion -> promotion.getStartDate().isBefore(today) && promotion.getEndDate().isAfter(today))
                .toList();
    }

    public Optional<Promotion> findByName(String name) {
        return promotions.stream().filter(promotion -> promotion.getName().equals(name)).findAny();
    }
}

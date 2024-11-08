package store.domain;

import java.time.LocalDateTime;

public class Promotion {
    private String name;
    private Integer buy;
    private Integer get;
    private LocalDateTime startDate;
    private LocalDateTime endDate;


    public static final class PromotionBuilder {
        private String name;
        private Integer buy;
        private Integer get;
        private LocalDateTime startDate;
        private LocalDateTime endDate;

        private PromotionBuilder() {
        }

        public static PromotionBuilder builder() {
            return new PromotionBuilder();
        }

        public PromotionBuilder name(String name) {
            this.name = name;
            return this;
        }

        public PromotionBuilder buy(Integer buy) {
            this.buy = buy;
            return this;
        }

        public PromotionBuilder get(Integer get) {
            this.get = get;
            return this;
        }

        public PromotionBuilder startDate(LocalDateTime startDate) {
            this.startDate = startDate;
            return this;
        }

        public PromotionBuilder endDate(LocalDateTime endDate) {
            this.endDate = endDate;
            return this;
        }

        public Promotion build() {
            Promotion promotion = new Promotion();
            promotion.buy = this.buy;
            promotion.endDate = this.endDate;
            promotion.startDate = this.startDate;
            promotion.name = this.name;
            promotion.get = this.get;
            return promotion;
        }
    }
}

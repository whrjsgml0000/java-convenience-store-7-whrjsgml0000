package store.domain;

import java.time.LocalDateTime;

public class Promotion {
    private String name;
    private Integer buy;
    private Integer get;
    private LocalDateTime start_date;
    private LocalDateTime end_date;


    public static final class PromotionBuilder {
        private String name;
        private Integer buy;
        private Integer get;
        private LocalDateTime start_date;
        private LocalDateTime end_date;

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

        public PromotionBuilder start_date(LocalDateTime start_date) {
            this.start_date = start_date;
            return this;
        }

        public PromotionBuilder end_date(LocalDateTime end_date) {
            this.end_date = end_date;
            return this;
        }

        public Promotion build() {
            Promotion promotion = new Promotion();
            promotion.buy = this.buy;
            promotion.end_date = this.end_date;
            promotion.start_date = this.start_date;
            promotion.name = this.name;
            promotion.get = this.get;
            return promotion;
        }
    }
}

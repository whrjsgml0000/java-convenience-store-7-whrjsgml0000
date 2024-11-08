package store.domain;

public class Item {
    private String name;
    private Integer price;
    private Integer quantity;
    private Promotion promotion;

    public String getName() {
        return name;
    }

    public Integer getPrice() {
        return price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Promotion getPromotion() {
        return promotion;
    }

    public void cell(int quantity){
        this.quantity -= quantity;
    }

    public static final class ItemBuilder {
        private String name;
        private Integer price;
        private Integer quantity;
        private Promotion promotion;

        private ItemBuilder() {
        }

        public static ItemBuilder builder() {
            return new ItemBuilder();
        }

        public ItemBuilder name(String name) {
            this.name = name;
            return this;
        }

        public ItemBuilder price(Integer price) {
            this.price = price;
            return this;
        }

        public ItemBuilder quantity(Integer quantity) {
            this.quantity = quantity;
            return this;
        }

        public ItemBuilder promotion(Promotion promotion) {
            this.promotion = promotion;
            return this;
        }

        public Item build() {
            Item item = new Item();
            item.price = this.price;
            item.quantity = this.quantity;
            item.promotion = this.promotion;
            item.name = this.name;
            return item;
        }
    }
}

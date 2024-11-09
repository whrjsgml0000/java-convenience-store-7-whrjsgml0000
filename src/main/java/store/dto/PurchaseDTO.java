package store.dto;

public class PurchaseDTO {
    private String name;
    private Integer quantity;
    private Integer price;
    private Integer freeQuantity;
    private boolean promotion;

    public String getName() {
        return name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Integer getPrice() {
        return price;
    }

    public Integer getFreeQuantity() {
        return freeQuantity;
    }

    public boolean isPromotion() {
        return promotion;
    }

    public static final class PurchaseDTOBuilder {
        private String name;
        private Integer quantity;
        private Integer price;
        private Integer freeQuantity;
        private boolean promotion;

        private PurchaseDTOBuilder() {
        }

        public static PurchaseDTOBuilder builder() {
            return new PurchaseDTOBuilder();
        }

        public PurchaseDTOBuilder name(String name) {
            this.name = name;
            return this;
        }

        public PurchaseDTOBuilder quantity(Integer quantity) {
            this.quantity = quantity;
            return this;
        }

        public PurchaseDTOBuilder price(Integer price) {
            this.price = price;
            return this;
        }

        public PurchaseDTOBuilder freeQuantity(Integer freeQuantity) {
            this.freeQuantity = freeQuantity;
            return this;
        }

        public PurchaseDTOBuilder promotion(boolean promotion) {
            this.promotion = promotion;
            return this;
        }

        public PurchaseDTO build() {
            PurchaseDTO purchaseDTO = new PurchaseDTO();
            purchaseDTO.name = this.name;
            purchaseDTO.price = this.price;
            purchaseDTO.promotion = this.promotion;
            purchaseDTO.freeQuantity = this.freeQuantity;
            purchaseDTO.quantity = this.quantity;
            return purchaseDTO;
        }
    }
}

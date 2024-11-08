package store.convenience;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import store.domain.Item;
import store.domain.Item.ItemBuilder;
import store.domain.Promotion;
import store.domain.Promotion.PromotionBuilder;
import store.repository.ItemRepository;
import store.repository.PromotionRepository;

public class LoadDataService {
    private static final Integer PRODUCT_NAME_INDEX = 0;
    private static final Integer PRODUCT_PRICE_INDEX = 1;
    private static final Integer PRODUCT_QUANTITY_INDEX = 2;
    private static final Integer PRODUCT_PROMOTION_INDEX = 3;
    private static final Integer PROMOTION_NAME_INDEX = 0;
    private static final Integer PROMOTION_BUY_INDEX = 1;
    private static final Integer PROMOTION_GET_INDEX = 2;
    private static final Integer PROMOTION_START_INDEX = 3;
    private static final Integer PROMOTION_END_INDEX = 4;

    private final ItemRepository itemRepository;
    private final PromotionRepository promotionRepository;

    public LoadDataService(ItemRepository itemRepository, PromotionRepository promotionRepository){
        this.itemRepository = itemRepository;
        this.promotionRepository = promotionRepository;
    }

    public void saveProductData(List<String> productDatas) {
        List<Item> items = new LinkedList<>();
        productDatas.stream().map(this::parseProductData)
                .forEach(items::add);
        itemRepository.saveAll(items);
    }

    private Item parseProductData(String productData) {
        String[] data = productData.split(",");
        return ItemBuilder.builder()
                .name(validName(data[PRODUCT_NAME_INDEX]))
                .price(validProductPrice(data[PRODUCT_PRICE_INDEX]))
                .quantity(validProductQuantity(data[PRODUCT_QUANTITY_INDEX]))
                .promotion(validProductPromotion(data[PRODUCT_PROMOTION_INDEX]))
                .build();
    }

    public void savePromotionData(List<String> promotionDatas) {
        List<Promotion> promotions = new LinkedList<>();
        promotionDatas.stream().map(this::parsePromotionData)
                .forEach(promotions::add);
        promotionRepository.saveAll(promotions);
    }

    private Promotion parsePromotionData(String promotionData) {
        String[] data = promotionData.split(",");
        return PromotionBuilder.builder()
                .name(validName(data[PROMOTION_NAME_INDEX]))
                .buy(validPromotionBuy(data[PROMOTION_BUY_INDEX]))
                .get(validPromotionGet(data[PROMOTION_GET_INDEX]))
                .startDate(validPromotionStartDate(data[PROMOTION_START_INDEX]))
                .endDate(validPromotionEndDate(data[PROMOTION_END_INDEX]))
                .build();
    }

    private String validName(String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException("");
        }
        return name;
    }

    private Integer validProductPrice(String price) {
        try {
            int iPrice = Integer.parseInt(price);
            if (iPrice < 0) {
                throw new NumberFormatException();
            }
            return iPrice;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("상품 목록에 잘못된 가격 값이 존재합니다.");
        }
    }

    private Integer validProductQuantity(String quantity) {
        try {
            int iQuantity = Integer.parseInt(quantity);
            if (iQuantity < 0) {
                throw new NumberFormatException();
            }
            return iQuantity;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("상품 목록에 잘못된 상품 재고가 존재합니다.");
        }
    }

    private String validProductPromotion(String promotion) {
        if (promotion.equals("null")) {
            return "";
        }
        return promotion;
    }

    private int validPromotionBuy(String buy){

    }

    private int validPromotionGet(String get){

    }

    private LocalDateTime validPromotionStartDate(String startDate){

    }

    private LocalDateTime validPromotionEndDate(String endDate){

    }
}

package store.convenience;

import java.time.LocalDateTime;
import java.util.Arrays;
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

    public LoadDataService(ItemRepository itemRepository, PromotionRepository promotionRepository) {
        this.itemRepository = itemRepository;
        this.promotionRepository = promotionRepository;
        itemRepository.reset();
        promotionRepository.reset();
    }

    //todo 리팩토링 필수
    public void saveProductData(List<String> productDatas) {
        List<Item> items = new LinkedList<>();
        productDatas.stream().map(this::parseProductData)
                .forEach(items::add);
        for (int index = 0; index < items.size(); index++) {
            Item frontItem = items.get(index);
            if (!frontItem.getPromotion().isBlank() && (items.get(index + 1) == null || !items.get(index + 1)
                    .getName().equals(frontItem.getName()))) {
                Item build = ItemBuilder.builder()
                        .name(frontItem.getName())
                        .promotion("")
                        .price(frontItem.getPrice())
                        .quantity(0)
                        .build();
                items.add(index + 1, build);
            }
        }
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

    private int validPromotionBuy(String buy) {
        try {
            int iBuy = Integer.parseInt(buy);
            if (iBuy <= 0) {
                throw new NumberFormatException();
            }
            return iBuy;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("프로모션 목록에 잘못된 구매 개수가 존재합니다.");
        }
    }

    private int validPromotionGet(String get) {
        try {
            int iGet = Integer.parseInt(get);
            if (iGet <= 0) {
                throw new NumberFormatException();
            }
            return iGet;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("프로모션 목록에 잘못된 증정 개수가 존재합니다.");
        }
    }

    // todo 나중에 더 자세하게 만들기
    private LocalDateTime validPromotionStartDate(String startDate) {
        List<Integer> yearMonthDay = Arrays.stream(startDate.split("-"))
                .map(Integer::parseInt)
                .toList();
        return LocalDateTime.of(yearMonthDay.get(0), yearMonthDay.get(1), yearMonthDay.get(2), 0, 0, 0);
    }

    private LocalDateTime validPromotionEndDate(String endDate) {
        List<Integer> yearMonthDay = Arrays.stream(endDate.split("-"))
                .map(Integer::parseInt)
                .toList();
        return LocalDateTime.of(yearMonthDay.get(0), yearMonthDay.get(1), yearMonthDay.get(2), 23, 59, 59);
    }
}

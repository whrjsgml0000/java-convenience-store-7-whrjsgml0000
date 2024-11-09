package store.domain;

import java.util.LinkedList;
import store.dto.PurchaseDTO;

public class Receipt {
    private static final String ITEM_FORMAT = "%-16s%-10s%-,5d\n";
    private static final String TITLE_FORMAT = "%-16s%-10s%s\n";
    private static final String TOTAL_FORMAT = "%-26s%-,5d\n";
    private static final String SALE_FORMAT = "%-26s-%-,5d\n";
    private static final String FREE_FORMAT = "%-16s%d\n";
    LinkedList<PurchaseDTO> list;

    public Receipt() {
        list = new LinkedList<>();
    }

    public void addPurchase(PurchaseDTO purchaseDTO) {
        list.add(purchaseDTO);
    }

    public void setMemberShip(boolean memberShip) {
        this.memberShip = memberShip;
    }

    private boolean memberShip;

    // todo 여기에요 여기가 지옥이에요

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("==============W 편의점================\n");
        sb.append(String.format(TITLE_FORMAT, "상품명", "수량", "금액"));
        list.forEach(purchaseDTO ->
                sb.append(String.format(ITEM_FORMAT, purchaseDTO.getName(), purchaseDTO.getQuantity(),
                        getTotalPrice(purchaseDTO))));
        sb.append("=============증    정===============\n");
        list.stream().filter(PurchaseDTO::isPromotion)
                .forEach(purchaseDTO ->
                        sb.append(String.format(FREE_FORMAT, purchaseDTO.getName(), purchaseDTO.getFreeQuantity())));
        sb.append("====================================\n");
        sb.append(String.format(ITEM_FORMAT, "총구매액", list.stream().mapToInt(PurchaseDTO::getQuantity).sum(),
                getTotalPrice()));
        sb.append(String.format(SALE_FORMAT, "행사할인", getPromotionSale()));
        sb.append(String.format(SALE_FORMAT, "멤버십할인" , getMemberShipSale()));
        sb.append(String.format(TOTAL_FORMAT, "내실돈", getTotalPrice() - getPromotionSale() - getMemberShipSale()));
        return sb.toString();
    }

    private int getMemberShipSale() {
        if (memberShip) {
            return list.stream().filter(purchaseDTO -> !purchaseDTO.isPromotion())
                    .mapToInt(purchaseDTO -> purchaseDTO.getPrice() * purchaseDTO.getQuantity()).sum() / 10 * 3;
        }
        return 0;
    }

    private int getPromotionSale() {
        return list.stream().filter(PurchaseDTO::isPromotion)
                .mapToInt(purchaseDTO -> purchaseDTO.getFreeQuantity() * purchaseDTO.getPrice()).sum();
    }

    private int getTotalPrice() {
        return list.stream().mapToInt(this::getTotalPrice).sum();
    }

    private int getTotalPrice(PurchaseDTO purchaseDTO) {
        return purchaseDTO.getQuantity() * purchaseDTO.getPrice();
    }
}

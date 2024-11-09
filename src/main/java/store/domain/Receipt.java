package store.domain;

import java.util.LinkedList;
import store.dto.PurchaseDTO;

public class Receipt {
    LinkedList<PurchaseDTO> list;
    public Receipt(){
        list = new LinkedList<>();
    }

    public void addPurchase(PurchaseDTO purchaseDTO){
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
        sb.append(String.format("%-16s%-10s%s\n","상품명","수량","금액"));
        list.forEach(purchaseDTO ->
                sb.append(String.format("%-20s%-10d%,d\n", purchaseDTO.getName(), purchaseDTO.getQuantity(),
                        getTotalPrice(purchaseDTO))));
        sb.append("=============증\t정===============\n");
        list.stream().filter(purchaseDTO -> purchaseDTO.getFreeQuantity() != 0)
                .forEach(purchaseDTO ->
                        sb.append(String.format("%-20s%-4d\n", purchaseDTO.getName(), purchaseDTO.getFreeQuantity())));
        sb.append("====================================\n");
        sb.append(String.format("%-20s%-10d%,d\n", "총구매액", list.stream().mapToInt(PurchaseDTO::getQuantity).sum(),
                getTotalPrice()));
        sb.append(String.format("%-20s%-10s-%,d\n", "행사할인", "", getPromotionSale()));
        sb.append(String.format("%-20s%-10s-%,d\n", "멤버십할인", "", getMemberShipSale()));
        sb.append(String.format("%-20s%-10s%,d\n", "내실돈", "", getTotalPrice()-getPromotionSale()-getMemberShipSale()));
        return sb.toString();
    }

    private int getMemberShipSale() {
        if(memberShip)
            return list.stream().filter(purchaseDTO -> !purchaseDTO.isPromotion())
                .mapToInt(purchaseDTO -> purchaseDTO.getPrice() * purchaseDTO.getQuantity()).sum() / 10 * 3;
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

package store.validator;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import store.domain.Item;
import store.error.Input;
import store.util.Extractor;
import store.view.ErrorPrinter;

public class PurchaseValidator {
    public static boolean validInputForm(String input) {
        Pattern pattern = Pattern.compile("[\\[가-힣a-zA-Z\\d]+-\\d+](,\\[[가-힣a-zA-Z\\d]+-\\d+])*");
        Matcher matcher = pattern.matcher(input);
        if (matcher.matches()) {
            return true;
        }
        ErrorPrinter.printError(Input.PURCHASE_FORM);
        return false;
    }

    public static boolean noDuplicatedItemInput(String input) {
        if (Arrays.stream(input.split(","))
                .map(s -> s.substring(1, s.indexOf("-")))
                .distinct()
                .count() == input.split(",").length) {
            return true;
        }
        ErrorPrinter.printError(Input.EXIST_ALREADY);
        return false;
    }

    public static boolean existItemName(String input, List<Item> items) {
        List<String> list = items.stream().map(Item::getName).toList();
        if (new HashSet<>(list).containsAll(Extractor.getNameAndQuantityMap(input)
                .keySet())) {
            return true;
        }
        ErrorPrinter.printError(Input.NULL_ITEM_NAME);

        return false;
    }

    public static boolean validQuantity(String input, List<Item> validItems) {
        Map<String, Integer> nameAndQuantityMap = Extractor.getNameAndQuantityMap(input);
        if (nameAndQuantityMap.values().stream().anyMatch(value -> value <= 0)) {
            ErrorPrinter.printError(Input.INVALID_QUANTITY);
            return false;
        }
        for (String name : nameAndQuantityMap.keySet()) {
            if (validItems.stream()
                    .filter(item -> item.getName().equals(name))
                    .mapToInt(Item::getQuantity)
                    .sum() < nameAndQuantityMap.get(name)) {
                ErrorPrinter.printError(Input.OVER_QUANTITY);
                return false;
            }
        }
        return true;
    }
}

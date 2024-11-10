package store.repository;

import java.util.ArrayList;
import java.util.List;
import store.domain.Item;

public class ItemRepository {
    private final ArrayList<Item> items = new ArrayList<>();

    public static ItemRepository getInstance() {
        return ItemRepositoryHolder.ITEM_REPOSITORY;
    }

    private static class ItemRepositoryHolder {
        private static final ItemRepository ITEM_REPOSITORY = new ItemRepository();
    }

    private ItemRepository() {

    }

    public void saveAll(List<Item> items) {
        this.items.addAll(items);
    }

    public List<Item> findAll() {
        return items;
    }

    public List<Item> findByName(String name) {
        return items.stream()
                .filter(item -> item.getName().equals(name))
                .toList();
    }

    public void reset(){
        items.clear();
    }
}

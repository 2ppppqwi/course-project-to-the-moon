package java.entities;

import java.util.List;
import java.util.ArrayList;

public class Shop {
    /* simple implementation of inventory right now, will create an interface(?) in phase 1 */
    protected List<ShopItem> inventory;

    public Shop() {
        this.inventory = new ArrayList<>();
    }

    public void addItem(ShopItem item) {
        inventory.add(item);
    }

    public void removeItem(ShopItem item) {
        inventory.remove(item);
    }

    /* sell item method for when the player buys something? */
}

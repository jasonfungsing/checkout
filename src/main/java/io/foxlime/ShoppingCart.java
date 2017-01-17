package io.foxlime;

import java.util.HashMap;
import java.util.Map;


public class ShoppingCart {

    private Map<Product, Integer> shoppingCartItems = new HashMap<>();

    public Map<Product, Integer> getShoppingCartItems() {
        return shoppingCartItems;
    }

    public void addProduct(Product product) {
        if (shoppingCartItems.containsKey(product)) {
            int newQuantity = shoppingCartItems.get(product) + 1;
            shoppingCartItems.put(product, newQuantity);
        } else {
            shoppingCartItems.put(product, 1);
        }
    }
}

package io.foxlime.pricingRule;

import io.foxlime.Product;
import io.foxlime.ShoppingCart;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Map;

public class ShoppingCartTotalPaymentCalculator {

    private ShoppingCart shoppingCart;
    private ArrayList<IPricingRule> pricingRules;

    public ShoppingCartTotalPaymentCalculator(ShoppingCart shoppingCart, ArrayList<IPricingRule> pricingRules) {
        this.shoppingCart = shoppingCart;
        this.pricingRules = pricingRules;
    }

    public BigDecimal calculate() {
        Map<Product, Integer> products = shoppingCart.getShoppingCartItems();
        BigDecimal totalPayment = BigDecimal.ZERO;
        for (IPricingRule pricingRule : pricingRules) {
            totalPayment = totalPayment.add(pricingRule.calculateDiscount(products));
        }

        for (Map.Entry o : products.entrySet()) {
            Map.Entry<Product, Integer> entry = (Map.Entry) o;
            totalPayment = totalPayment.add(entry.getKey().getPrice().multiply(BigDecimal.valueOf(entry.getValue())));
        }

        return totalPayment;
    }
}

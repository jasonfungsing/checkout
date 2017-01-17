package io.foxlime;

import io.foxlime.pricingRule.IPricingRule;
import io.foxlime.pricingRule.ShoppingCartTotalPaymentCalculator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;

public class Checkout {

    private ArrayList<IPricingRule> pricingRules = new ArrayList<>();
    private ShoppingCart shoppingCart = new ShoppingCart();

    public Checkout(IPricingRule... pricingRules) {
        this.pricingRules = new ArrayList<>(Arrays.asList(pricingRules));
    }

    public void scan(Product product) {
        shoppingCart.addProduct(product);
    }

    public BigDecimal total() {
        ShoppingCartTotalPaymentCalculator shoppingCartTotalPaymentCalculator = new ShoppingCartTotalPaymentCalculator(shoppingCart, pricingRules);

        return shoppingCartTotalPaymentCalculator.calculate();
    }
}

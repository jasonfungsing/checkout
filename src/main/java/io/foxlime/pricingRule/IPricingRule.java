package io.foxlime.pricingRule;

import io.foxlime.Product;

import java.math.BigDecimal;
import java.util.Map;

public interface IPricingRule {

    BigDecimal calculateDiscount(Map<Product, Integer> products);
}

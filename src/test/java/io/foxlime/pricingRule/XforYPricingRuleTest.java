package io.foxlime.pricingRule;


import io.foxlime.Product;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;


@RunWith(JUnit4.class)
public class XforYPricingRuleTest {

    private XforYPricingRule xforYPricingRule = new XforYPricingRule();
    private Map<Product, Integer> products = new HashMap<>();
    private Product atv = new Product("atv", "Apple TV", BigDecimal.valueOf(109.50D));

    @Before
    public void setUp() {

    }

    @Test
    public void calculateDiscount_with_reminder() {
        xforYPricingRule.addXforYPricingRule(atv, 3, 2);
        products.put(atv, 8);

        BigDecimal total = xforYPricingRule.calculateDiscount(products);
        assertEquals(BigDecimal.valueOf(109.50D * 4), total);
        int atvCount = products.get(atv);
        assertEquals(2, atvCount);
    }

    @Test
    public void calculateDiscount_with_out_reminder() {
        xforYPricingRule.addXforYPricingRule(atv, 3, 2);
        products.put(atv, 6);

        BigDecimal total = xforYPricingRule.calculateDiscount(products);
        assertEquals(BigDecimal.valueOf(109.50D * 4), total);
        assertEquals(false, products.containsKey(atv));
    }
}
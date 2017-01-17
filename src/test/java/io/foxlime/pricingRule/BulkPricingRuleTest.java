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
public class BulkPricingRuleTest {

    private BulkPricingRule bulkPricingRule = new BulkPricingRule();
    private Map<Product, Integer> products = new HashMap<>();
    private Product ipd = new Product("ipd", "Super iPad", BigDecimal.valueOf(549.99D));

    @Before
    public void setUp() {

    }

    @Test
    public void testCalculateDiscountApplied() {
        bulkPricingRule.addBulkPricingRule(ipd, 4, BigDecimal.valueOf(499.99D));
        products.put(ipd, 5);

        BigDecimal total = bulkPricingRule.calculateDiscount(products);
        assertEquals(BigDecimal.valueOf(499.99D * 5), total);
        assertEquals(false, products.containsKey(ipd));
    }

    @Test
    public void testCalculateDiscountNotApplied() {

        bulkPricingRule.addBulkPricingRule(ipd, 4, BigDecimal.valueOf(499.99D));
        products.put(ipd, 3);

        BigDecimal total = bulkPricingRule.calculateDiscount(products);
        assertEquals(BigDecimal.valueOf(0), total);
    }
}

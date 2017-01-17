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
public class BundlePricingRuleTest {

    private BundlePricingRule bundlePricingRule = new BundlePricingRule();
    private Map<Product, Integer> products = new HashMap<>();
    private Product mbp = new Product("mbp", "MacBook Pro", BigDecimal.valueOf(1399.99D));
    private Product vga = new Product("vga", "VGA adapter", BigDecimal.valueOf(30.00D));

    @Before
    public void setUp() {

    }

    @Test
    public void testCalculateDiscountApplied_buy_more_than_free() {
        bundlePricingRule.addBulkPricingRule(mbp, vga);
        products.put(mbp, 5);
        products.put(vga, 4);

        BigDecimal total = bundlePricingRule.calculateDiscount(products);
        assertEquals(BigDecimal.valueOf(0), total);
        int mbpCount = products.get(mbp);
        assertEquals(false, products.containsKey(vga));
        assertEquals(5, mbpCount);
    }

    @Test
    public void testCalculateDiscountApplied_free_more_than_buy() {
        bundlePricingRule.addBulkPricingRule(mbp, vga);
        products.put(mbp, 4);
        products.put(vga, 5);

        BigDecimal total = bundlePricingRule.calculateDiscount(products);
        assertEquals(BigDecimal.valueOf(0), total);
        int mbpCount = products.get(mbp);
        int vgaCount = products.get(vga);
        assertEquals(1, vgaCount);
        assertEquals(4, mbpCount);
    }

    @Test
    public void testCalculateDiscountNotApplied() {
        bundlePricingRule.addBulkPricingRule(mbp, vga);
        products.put(vga, 5);

        BigDecimal total = bundlePricingRule.calculateDiscount(products);
        assertEquals(BigDecimal.valueOf(0), total);
        int vgaCount = products.get(vga);
        assertEquals(5, vgaCount);
    }
}

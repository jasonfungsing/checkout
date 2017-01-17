package io.foxlime;

import io.foxlime.pricingRule.BulkPricingRule;
import io.foxlime.pricingRule.BundlePricingRule;
import io.foxlime.pricingRule.ShoppingCartTotalPaymentCalculator;
import io.foxlime.pricingRule.XforYPricingRule;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * Created by jasonfungsing on 18/1/17.
 */
public class CheckoutTest {

    private Product mbp = new Product("mbp", "MacBook Pro", BigDecimal.valueOf(1399.99D));
    private Product vga = new Product("vga", "VGA adapter", BigDecimal.valueOf(30.00D));
    private Product atv = new Product("atv", "Apple TV", BigDecimal.valueOf(109.50D));
    private Product ipd = new Product("ipd", "Super iPad", BigDecimal.valueOf(549.99D));

    private XforYPricingRule xforYPricingRule = new XforYPricingRule();
    private BundlePricingRule bundlePricingRule = new BundlePricingRule();
    private BulkPricingRule bulkPricingRule = new BulkPricingRule();

    private Checkout checkout;

    @Before
    public void setUp() throws Exception {

        bundlePricingRule.addBulkPricingRule(mbp, vga);
        xforYPricingRule.addXforYPricingRule(atv, 3, 2);
        bulkPricingRule.addBulkPricingRule(ipd, 4, BigDecimal.valueOf(499.99D));

        checkout = new Checkout(xforYPricingRule, bulkPricingRule, bundlePricingRule);
    }

    // SKUs Scanned: atv, atv, atv, vga Total expected: $249.00
    @Test
    public void total_test_case_1() throws Exception {
        checkout.scan(atv);
        checkout.scan(atv);
        checkout.scan(atv);
        checkout.scan(vga);

        assertEquals(BigDecimal.valueOf(249.00D), checkout.total());
    }

    // SKUs Scanned: atv, ipd, ipd, atv, ipd, ipd, ipd Total expected: $2718.95
    @Test
    public void calculate_test_case_2() throws Exception {
        checkout.scan(atv);
        checkout.scan(ipd);
        checkout.scan(ipd);
        checkout.scan(atv);
        checkout.scan(ipd);
        checkout.scan(ipd);
        checkout.scan(ipd);

        assertEquals(BigDecimal.valueOf(2718.95D), checkout.total());
    }

    // SKUs Scanned: mbp, vga, ipd Total expected: $1949.98
    @Test
    public void calculate_test_case_3() throws Exception {
        checkout.scan(mbp);
        checkout.scan(vga);
        checkout.scan(ipd);

        assertEquals(BigDecimal.valueOf(1949.98D), checkout.total());
    }
}
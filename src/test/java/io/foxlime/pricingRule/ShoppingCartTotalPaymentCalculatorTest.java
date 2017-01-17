package io.foxlime.pricingRule;

import io.foxlime.Product;
import io.foxlime.ShoppingCart;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class ShoppingCartTotalPaymentCalculatorTest {

    private Product mbp = new Product("mbp", "MacBook Pro", BigDecimal.valueOf(1399.99D));
    private Product vga = new Product("vga", "VGA adapter", BigDecimal.valueOf(30.00D));
    private Product atv = new Product("atv", "Apple TV", BigDecimal.valueOf(109.50D));
    private Product ipd = new Product("ipd", "Super iPad", BigDecimal.valueOf(549.99D));
    private ShoppingCartTotalPaymentCalculator calculator;
    private ShoppingCart shoppingCart = new ShoppingCart();

    private XforYPricingRule xforYPricingRule = new XforYPricingRule();
    private BundlePricingRule bundlePricingRule = new BundlePricingRule();
    private BulkPricingRule bulkPricingRule = new BulkPricingRule();

    private ArrayList<IPricingRule> pricingRules = new ArrayList<>();

    @Before
    public void setUp() throws Exception {
        bundlePricingRule.addBulkPricingRule(mbp, vga);
        xforYPricingRule.addXforYPricingRule(atv, 3, 2);
        bulkPricingRule.addBulkPricingRule(ipd, 4, BigDecimal.valueOf(499.99D));

        pricingRules.add(bulkPricingRule);
        pricingRules.add(xforYPricingRule);
        pricingRules.add(bundlePricingRule);
    }

    // SKUs Scanned: atv, atv, atv, vga Total expected: $249.00
    @Test
    public void calculate_test_case_1() throws Exception {
        shoppingCart.addProduct(atv);
        shoppingCart.addProduct(atv);
        shoppingCart.addProduct(atv);
        shoppingCart.addProduct(vga);

        calculator = new ShoppingCartTotalPaymentCalculator(shoppingCart, pricingRules);

        assertEquals(BigDecimal.valueOf(249.00D), calculator.calculate());
    }

    // SKUs Scanned: atv, ipd, ipd, atv, ipd, ipd, ipd Total expected: $2718.95
    @Test
    public void calculate_test_case_2() throws Exception {
        shoppingCart.addProduct(atv);
        shoppingCart.addProduct(ipd);
        shoppingCart.addProduct(ipd);
        shoppingCart.addProduct(atv);
        shoppingCart.addProduct(ipd);
        shoppingCart.addProduct(ipd);
        shoppingCart.addProduct(ipd);

        calculator = new ShoppingCartTotalPaymentCalculator(shoppingCart, pricingRules);

        assertEquals(BigDecimal.valueOf(2718.95D), calculator.calculate());
    }

    // SKUs Scanned: mbp, vga, ipd Total expected: $1949.98
    @Test
    public void calculate_test_case_3() throws Exception {
        shoppingCart.addProduct(mbp);
        shoppingCart.addProduct(vga);
        shoppingCart.addProduct(ipd);

        calculator = new ShoppingCartTotalPaymentCalculator(shoppingCart, pricingRules);

        assertEquals(BigDecimal.valueOf(1949.98D), calculator.calculate());
    }

}
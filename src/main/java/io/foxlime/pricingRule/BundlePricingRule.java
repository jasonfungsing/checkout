package io.foxlime.pricingRule;

import io.foxlime.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

public class BundlePricingRule implements IPricingRule {

    private ArrayList<Bundle> rules = new ArrayList<>();

    public void addBulkPricingRule(Product buyProduct, Product freeProduct) {
        Bundle bundle = new Bundle(buyProduct, freeProduct);
        this.rules.add(bundle);
    }

    public BigDecimal calculateDiscount(Map<Product, Integer> products) {
        for (Bundle rule : rules) {
            if (products.containsKey(rule.getBuyProduct()) && products.containsKey(rule.getFreeProduct())) {
                if (products.get(rule.getBuyProduct()) >= products.get(rule.getFreeProduct())) {
                    products.remove(rule.getFreeProduct());
                } else {
                    products.put(rule.getFreeProduct(), products.get(rule.getFreeProduct()) - products.get(rule.getBuyProduct()));
                }
            }
        }
        return BigDecimal.ZERO;
    }

    class Bundle {
        private Product buyProduct;
        private Product freeProduct;

        Bundle(Product buyProduct, Product freeProduct) {
            this.buyProduct = buyProduct;
            this.freeProduct = freeProduct;
        }

        Product getBuyProduct() {
            return buyProduct;
        }


        Product getFreeProduct() {
            return freeProduct;
        }


        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Bundle bundle = (Bundle) o;
            return Objects.equals(buyProduct, bundle.buyProduct) &&
                    Objects.equals(freeProduct, bundle.freeProduct);
        }

        @Override
        public int hashCode() {
            return Objects.hash(buyProduct, freeProduct);
        }

        @Override
        public String toString() {
            return "Bundle{" +
                    "buyProduct=" + buyProduct +
                    ", freeProduct=" + freeProduct +
                    '}';
        }
    }
}

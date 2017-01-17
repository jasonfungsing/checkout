package io.foxlime.pricingRule;

import io.foxlime.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;


public class BulkPricingRule implements IPricingRule {

    private ArrayList<Bulk> rules = new ArrayList<>();

    public void addBulkPricingRule(Product product, int quantity, BigDecimal bulkUnitPrice) {
        Bulk bulk = new Bulk(product, quantity, bulkUnitPrice);
        this.rules.add(bulk);
    }

    public BigDecimal calculateDiscount(Map<Product, Integer> products) {
        BigDecimal total = BigDecimal.ZERO;
        for (Bulk rule : rules) {
            if (products.containsKey(rule.getProduct()) && products.get(rule.getProduct()) >= rule.getQuantity()) {
                BigDecimal totalPayForThisDeal = rule.getBulkUnitPrice().multiply(BigDecimal.valueOf(products.get(rule.getProduct())));
                total = total.add(totalPayForThisDeal);
                products.remove(rule.getProduct());
            }
        }

        return total;
    }

    class Bulk {
        private Product product;
        private int quantity;
        private BigDecimal bulkUnitPrice;

        Bulk(Product product, int quantity, BigDecimal bulkUnitPrice) {
            this.product = product;
            this.quantity = quantity;
            this.bulkUnitPrice = bulkUnitPrice;
        }

        Product getProduct() {
            return product;
        }


        int getQuantity() {
            return quantity;
        }


        BigDecimal getBulkUnitPrice() {
            return bulkUnitPrice;
        }


        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Bulk bulk = (Bulk) o;
            return quantity == bulk.quantity &&
                    Objects.equals(product, bulk.product) &&
                    Objects.equals(bulkUnitPrice, bulk.bulkUnitPrice);
        }

        @Override
        public int hashCode() {
            return Objects.hash(product, quantity, bulkUnitPrice);
        }

        @Override
        public String toString() {
            return "Bulk{" +
                    "product=" + product +
                    ", quantity=" + quantity +
                    ", bulkUnitPrice=" + bulkUnitPrice +
                    '}';
        }
    }
}

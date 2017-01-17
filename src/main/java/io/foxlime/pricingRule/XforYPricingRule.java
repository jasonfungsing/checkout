package io.foxlime.pricingRule;

import io.foxlime.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

public class XforYPricingRule implements IPricingRule {

    private ArrayList<XforY> rules = new ArrayList();

    public void addXforYPricingRule(Product product, int buyXquantity, int payYquantity) {
        XforY xforY = new XforY(product, buyXquantity, payYquantity);
        this.rules.add(xforY);
    }

    public BigDecimal calculateDiscount(Map<Product, Integer> products) {
        BigDecimal total = BigDecimal.ZERO;
        for (XforY rule : rules) {
            if (products.containsKey(rule.getProduct()) && products.get(rule.getProduct()) >= rule.getBuyXquantity()) {
                int dealCount = products.get(rule.getProduct()) / rule.getBuyXquantity();
                int dealReminder = products.get(rule.getProduct()) % rule.getBuyXquantity();
                BigDecimal totalPayForThisDeal = rule.getProduct().getPrice().multiply(BigDecimal.valueOf(dealCount)).multiply(BigDecimal.valueOf(rule.getPayYquantity()));
                total = total.add(totalPayForThisDeal);
                if (dealReminder == 0) {
                    products.remove(rule.getProduct());
                } else {
                    products.put(rule.getProduct(), dealReminder);

                }
            }
        }
        return total;
    }

    class XforY {

        private Product product;
        private int buyXquantity;
        private int payYquantity;

        XforY(Product product, int buyXquantity, int payYquantity) {
            this.product = product;
            this.buyXquantity = buyXquantity;
            this.payYquantity = payYquantity;
        }

        Product getProduct() {
            return product;
        }


        int getBuyXquantity() {
            return buyXquantity;
        }


        int getPayYquantity() {
            return payYquantity;
        }


        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            XforY xforY = (XforY) o;
            return buyXquantity == xforY.buyXquantity &&
                    payYquantity == xforY.payYquantity &&
                    Objects.equals(product, xforY.product);
        }

        @Override
        public int hashCode() {
            return Objects.hash(product, buyXquantity, payYquantity);
        }

        @Override
        public String toString() {
            return "XforY{" +
                    "product=" + product +
                    ", buyXquantity=" + buyXquantity +
                    ", payYquantity=" + payYquantity +
                    '}';
        }
    }
}

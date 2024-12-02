package org.zmz.sb3.newfeatures.cf;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.text.NumberFormat;
import java.util.concurrent.TimeUnit;

@Getter
@Slf4j
public enum Discount {
    LEVEL0(0), LEVEL5(5), LEVEL10(10), LEVEL15(15), LEVEL20(20);
    private final int percent;

    Discount(int percent) {
        this.percent = percent;
    }

    public static void delay() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static DiscountRecord applyDiscount(Quote quote) {
        String discountPriceStr = apply(quote.getPrice(), quote.getDiscount());
        Double discountPrice = Double.parseDouble(discountPriceStr);
        String desc = quote.getShop() + " price is " + discountPrice;
        return new DiscountRecord(discountPrice, desc);
    }

    public static String apply(Double price, Discount discount) {
        delay();
        return NumberFormat.getInstance().format(price * (100 - discount.getPercent()) / 100);
    }

    public static void main(String[] args) {
        Quote quote = new Quote("bestShop", 99.99, Discount.LEVEL20);
        DiscountRecord dr = applyDiscount(quote);
        log.info("{}", dr);
    }

    public record DiscountRecord(Double discountPrice, String finalShopPrice) {
        @Override
        public String toString() {
            return "DiscountRecord{" +
                    "discountPrice=" + discountPrice +
                    ", finalShopPrice='" + finalShopPrice + '\'' +
                    '}';
        }
    }
}
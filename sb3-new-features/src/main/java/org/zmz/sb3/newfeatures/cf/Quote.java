package org.zmz.sb3.newfeatures.cf;

import cn.hutool.core.util.StrUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@Slf4j
@ToString
@NoArgsConstructor
public class Quote {
    private String shop;
    private double price;
    private Discount discount;

    public Quote(String shop, double price, Discount discount) {
        this.shop = shop;
        this.price = price;
        this.discount = discount;
    }

    public static Quote parse(String content) {
        String[] items = StrUtil.splitToArray(content, ":");
        return new Quote(items[0], Double.parseDouble(items[1]), Discount.valueOf(items[2]));
    }

    public static void main(String[] args) {
        String content = "bestShop:99.99:LEVEL5";
        Quote quote = Quote.parse(content);
        log.info("{}", quote);
    }
}
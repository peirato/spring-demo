package com.peirato.demo.right;

import com.peirato.demo.Cart;
import com.peirato.demo.Db;
import com.peirato.demo.Item;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class AbstractCart {

    public Cart process(long userId, Map<Long, Integer> items) {

        Cart cart = new Cart();

        List<Item> itemList = new ArrayList<>();

        items.forEach((key, value) -> {
            Item item = new Item();
            item.setId(key);
            item.setPrice(Db.getItemPrice(key));
            item.setQuantity(value);
            itemList.add(item);
        });

        cart.setItems(itemList);

        itemList.forEach(item -> {
            // 将不同的实现方式放在实现类中完成
            processCouponPrice(userId,item);
            processDeliveryPrice(userId,item);
        });

        cart.setTotalItemPrice(cart.getItems().stream().map(item -> item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity()))).reduce(BigDecimal.ZERO,BigDecimal::add));
        cart.setTotalDeliveryPrice(cart.getItems().stream().map(Item::getDeliveryPrice).reduce(BigDecimal.ZERO,BigDecimal::add));
        cart.setTotalDiscount(cart.getItems().stream().map(Item::getCouponPrice).reduce(BigDecimal.ZERO,BigDecimal::add));
        cart.setPayPrice(cart.getTotalItemPrice().add(cart.getTotalDeliveryPrice()).subtract(cart.getTotalDiscount()));
        return cart;
    }

    protected abstract void processDeliveryPrice(long userId, Item item);

    protected abstract void processCouponPrice(long userId, Item item);
}

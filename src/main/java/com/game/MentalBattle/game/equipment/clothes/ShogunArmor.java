package com.game.MentalBattle.game.equipment.clothes;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("shogunArmorBean")
public class ShogunArmor implements Cloth{
    @Value("${priceShogunArmor}")
    private int price;
    @Value("${protectionValueShogunArmor}")
    private int protectionValue;

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public int getProtectionValue() {
        return protectionValue;
    }
}

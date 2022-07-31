package com.game.MentalBattle.game.lightSide.equipment.clothes;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("protectiveShieldBean")
public class ProtectiveShield implements Cloth{
    @Value("${priceProtectiveShield}")
    private int price;
    @Value("${protectionValueProtectiveShield}")
    private int protectionValue;
    @Value("${descriptionProtectiveShield}")
    private String description;

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public int getProtectionValue() {
        return protectionValue;
    }

    @Override
    public String getDescription() {
        return description;
    }
}

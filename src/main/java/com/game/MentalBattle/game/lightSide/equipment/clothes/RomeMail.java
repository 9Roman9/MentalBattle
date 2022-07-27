package com.game.MentalBattle.game.lightSide.equipment.clothes;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("romeMailBean")
public class RomeMail implements Cloth{
    @Value("${priceRomeMail}")
    private int price;
    @Value("${protectionValueRomeMail}")
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

package com.game.MentalBattle.game.lightSide.magic.fire;

import com.game.MentalBattle.game.lightSide.Impact;
import com.game.MentalBattle.game.lightSide.magic.MagicSkill;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

@Component("flamingWhipBean")
public class FlamingWhip implements MagicSkill {
    @Value("${priceFlamingWhip}")
    private int price;
    @Value("${descriptionFlamingWhip}")
    private String description;
    @Value("${damageFlamingWhip}")
    private int damage;
    private final Impact impact = Impact.ATTACK_PLUS;
    @Value("${impactVolumeFlamingWhip}")
    private int impactVolume;

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public String getDescription(){
        return description;
    }

    @Override
    public Impact getImpact() {
        return impact;
    }

    @Override
    public int getImpactVolume() { return impactVolume; }

    @Override
    public int getDamage() {
        return damage;
    }
}

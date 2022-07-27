package com.game.MentalBattle.game.lightSide.magic.air;

import com.game.MentalBattle.game.lightSide.Impact;
import com.game.MentalBattle.game.lightSide.magic.MagicSkill;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("burningWindBean")
public class BurningWind implements MagicSkill {
    @Value("${priceBurningWind}")
    private int price;
    @Value("${descriptionBurningWind}")
    private String description;
    private final Impact impact = Impact.ATTACK_MINUS;
    @Value("${impactVolumeBurningWind}")
    private int impactVolume;
    @Value("${renewPeriodBurningWind}")
    private int renewPeriod;
    @Value("${renewPeriodBurningWind}")
    boolean additionalStrike;

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public String getDescription() { return description; }

    @Override
    public Impact getImpact() {
        return impact;
    }

    @Override
    public int getRenewPeriod() { return renewPeriod; }

    @Override
    public int getImpactVolume() { return impactVolume; }

    @Override
    public int getPercentage() {
        return 0;
    }

    @Override
    public int getDuration() { return 0; }

    @Override
    public boolean isAdditionalStrike() {
        return additionalStrike;
    }
}

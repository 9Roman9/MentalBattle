package com.game.MentalBattle.game.magic.fire;

import com.game.MentalBattle.game.Impact;
import com.game.MentalBattle.game.magic.MagicSkill;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("dragonBreathBean")
public class DragonBreath implements MagicSkill {
    @Value("${priceDragonBreath}")
    private int price;
    @Value("${descriptionDragonBreath}")
    private String description;
    private final Impact impact = Impact.DAMAGE;
    @Value("${impactVolumeDragonBreath}")
    private int impactVolume;
    @Value("${renewPeriodDragonBreath}")
    private int renewPeriod;
    @Value("${additionalStrikeDragonBreath}")
    private boolean additionalStrike;

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
    public int getDuration() {
        return 0;
    }

    public boolean isAdditionalStrike() { return additionalStrike; }
}

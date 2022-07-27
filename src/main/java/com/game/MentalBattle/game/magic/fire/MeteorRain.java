package com.game.MentalBattle.game.magic.fire;

import com.game.MentalBattle.game.magic.Impact;
import com.game.MentalBattle.game.magic.MagicSkill;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("meteorRainBean")
public class MeteorRain implements MagicSkill {
    @Value("${priceMeteorRain}")
    private int price;
    @Value("${descriptionMeteorRain}")
    private String description;
    private Impact impact = Impact.DAMAGE;
    @Value("${impactVolumeMeteorRain}")
    private int impactVolume;
    @Value("${renewPeriodMeteorRain}")
    private int renewPeriod;
    @Value("${additionalStrikeMeteorRain}")
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

    @Override
    public boolean isAdditionalStrike() {
        return additionalStrike;
    }
}

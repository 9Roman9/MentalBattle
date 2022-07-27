package com.game.MentalBattle.game.lightSide.magic;

import com.game.MentalBattle.game.lightSide.Impact;

public interface MagicSkill {
    int getPrice();
    String getDescription();
    Impact getImpact();
    int getRenewPeriod();
    int getImpactVolume();
    int getPercentage();
    int getDuration();
    boolean isAdditionalStrike();
}

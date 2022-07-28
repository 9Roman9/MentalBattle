package com.game.MentalBattle.game.lightSide.magic;

import com.game.MentalBattle.game.lightSide.Impact;

public interface MagicSkill {
    int getPrice();
    String getDescription();
    int getDamage();
    Impact getImpact();
    int getImpactVolume();
}

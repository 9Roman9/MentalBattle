package com.game.MentalBattle.game.lightSide.magic.earth;

import com.game.MentalBattle.Config;
import com.game.MentalBattle.game.lightSide.magic.Element;
import com.game.MentalBattle.game.lightSide.magic.MagicSkill;
import com.game.MentalBattle.game.lightSide.magic.fire.DragonBreath;
import com.game.MentalBattle.game.lightSide.magic.fire.FlamingWhip;
import com.game.MentalBattle.game.lightSide.magic.fire.MeteorRain;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Earth implements Element {
    AnnotationConfigApplicationContext context;
    private MagicSkill skill1;
    private MagicSkill skill2;
    private MagicSkill skill3;

    public void setEarthMagic(){
        context = new AnnotationConfigApplicationContext(Config.class);
        skill1 = context.getBean("EarthquakeBean", Earthquake.class);
        skill2 = context.getBean("leafProtectionBreathBean", LeafProtection.class);
        skill3 = context.getBean("lianaBean", Liana.class);
    }

    @Override
    public MagicSkill getSkill1() {
        return skill1;
    }

    @Override
    public MagicSkill getSkill2() {
        return skill2;
    }

    @Override
    public MagicSkill getSkill3() {
        return skill3;
    }
}

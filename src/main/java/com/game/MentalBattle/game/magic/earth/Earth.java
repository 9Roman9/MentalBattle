package com.game.MentalBattle.game.magic.earth;

import com.game.MentalBattle.Config;
import com.game.MentalBattle.game.magic.Element;
import com.game.MentalBattle.game.magic.MagicSkill;
import com.game.MentalBattle.game.magic.fire.DragonBreath;
import com.game.MentalBattle.game.magic.fire.FlamingWhip;
import com.game.MentalBattle.game.magic.fire.MeteorRain;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Earth implements Element {
    AnnotationConfigApplicationContext context;
    private final MagicSkill skill1;
    private final MagicSkill skill2;
    private final MagicSkill skill3;

    public Earth() {
        context = new AnnotationConfigApplicationContext(Config.class);
        skill1 = context.getBean("EarthquakeBean", MeteorRain.class);
        skill2 = context.getBean("leafProtectionBreathBean", DragonBreath.class);
        skill3 = context.getBean("lianaBean", FlamingWhip.class);
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

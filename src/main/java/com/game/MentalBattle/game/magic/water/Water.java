package com.game.MentalBattle.game.magic.water;

import com.game.MentalBattle.Config;
import com.game.MentalBattle.game.magic.Element;
import com.game.MentalBattle.game.magic.MagicSkill;
import com.game.MentalBattle.game.magic.fire.DragonBreath;
import com.game.MentalBattle.game.magic.fire.FlamingWhip;
import com.game.MentalBattle.game.magic.fire.MeteorRain;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

@Component("waterBean")
public class Water implements Element {
    AnnotationConfigApplicationContext context;
    private final MagicSkill skill1;
    private final MagicSkill skill2;
    private final MagicSkill skill3;

    public Water() {
        context = new AnnotationConfigApplicationContext(Config.class);
        skill1 = context.getBean("floodBean", MeteorRain.class);
        skill2 = context.getBean("healingSpringBean", DragonBreath.class);
        skill3 = context.getBean("iceSpikeBean", FlamingWhip.class);
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

package org.parabot.el.maestro.eldeathrunes.strategies.randoms;

import org.parabot.el.maestro.eldeathrunes.core.Core;
import org.parabot.el.maestro.eldeathrunes.data.Variables;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.api.methods.Inventory;
import org.rev317.api.methods.Npcs;
import org.rev317.api.methods.Players;
import org.rev317.api.wrappers.interactive.Npc;

/**
 * Created by Sully on 2/2/14.
 */
public class SandwichLady implements Strategy {

    Npc npc32;

    @Override
    public boolean activate() {

        for (Npc npa : Npcs.getNearest(3117)) {
            if (npa != null
                    && npa.isOnScreen()
                    && !Players.getLocal().isInCombat()
                    && (Players.getLocal().getAnimation() == -1
                    || Players.getLocal().getAnimation() == 1353 || Players
                    .getLocal().getAnimation() == 791)
                    && !Inventory.isFull()) {
                npc32 = npa;
                return true;
            }
        }
        return false;

    }

    @Override
    public void execute() {
        npc32.interact("Talk-to");
        Core.gameTimer.restart();
        while (npc32 != null) {
            if (Core.gameTimer.getElapsedTime() > 5000) {
                Core.gameTimer.stop();
                Core.gameTimer.reset();
                break;
            }
        }

    }

}
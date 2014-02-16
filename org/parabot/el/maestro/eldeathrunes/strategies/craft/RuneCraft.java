package org.parabot.el.maestro.eldeathrunes.strategies.craft;

import org.parabot.el.maestro.eldeathrunes.core.Core;
import org.parabot.el.maestro.eldeathrunes.data.Constants;
import org.parabot.el.maestro.eldeathrunes.data.Variables;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.input.Mouse;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.api.methods.Bank;
import org.rev317.api.methods.Inventory;
import org.rev317.api.methods.Players;
import org.rev317.api.methods.SceneObjects;
import org.rev317.api.wrappers.hud.Item;
import org.rev317.api.wrappers.hud.Tab;
import org.rev317.api.wrappers.scene.SceneObject;

import java.awt.*;

/**
 * Created by Bautista on 2/15/14.
 */
public class RuneCraft implements Strategy {
    SceneObject altar = SceneObjects.getNearest(Constants.ALTAR_ID)[0];

    @Override
    public boolean activate() {
        return !Inventory.isEmpty() && !Bank.isOpen() && altar != null && !Players.getLocal().isWalking()
                && Inventory.getCount(Constants.RUNE_ESS) > 0;
    }

    @Override
    public void execute() {
        Item runeEss = Inventory.getItems(Constants.RUNE_ESS)[0];
        if (altar != null) {
            if (altar.isOnScreen()) {
                if (Tab.INVENTORY.isOpen()) {
                    if (runeEss != null) {
                        try {
                            Variables.setRunesMade(Variables.getRunesMade() + Inventory.getCount(Constants.RUNE_ESS));
                            Mouse.getInstance().click(altar.getCenterPointOnScreen());
                            Time.sleep(1000);
                        } catch (Exception e) {

                        }
                    }
                } else {
                    Mouse.getInstance().click(new Point(643, 184));
                }
            } else {
                Core.setCamera();
            }
        }
    }
}

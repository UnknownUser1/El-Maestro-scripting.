package org.parabot.el.maestro.elprayer.strategies.prayer;

/**
 * Created by Bautista on 2/13/14.
 */

import org.parabot.el.maestro.elprayer.data.Constants;
import org.parabot.el.maestro.elprayer.data.Variables;
import org.parabot.el.maestro.elprayer.strategies.bank.BankOpen;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.input.Mouse;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.api.methods.*;
import org.rev317.api.methods.Menu;
import org.rev317.api.wrappers.hud.Item;
import org.rev317.api.wrappers.hud.Tab;
import org.rev317.api.wrappers.scene.SceneObject;

import java.awt.*;

public class BoneOffer implements Strategy {
    SceneObject altar = SceneObjects.getNearest(Constants.ALTAR)[0];
    Variables var = new Variables();

    @Override
    public boolean activate() {
        return !Inventory.isEmpty() && !Players.getLocal().isWalking()
                && !Bank.isOpen() && altar != null;
    }

    @Override
    public void execute() {
        Variables.setStatus("Offering Bones.");
        Item bone = Inventory.getItems(Constants.BONE_DATA[Variables
                .getBoneIndex()][0])[0];
        if (altar != null) {
            if (altar.isOnScreen()) {
                if (Inventory.getCount(bone.getId()) > 0) {
                    if (Tab.INVENTORY.isOpen()) {
                        if (!Variables.getXpLocked()) {
                            try {
                                bone.interact("Use");
                                altar.interact("Use " + Variables.getBoneType());
                                Time.sleep(800);
                            } catch (Exception e) {

                            }
                        } else {
                            //turn exp lock off so exp can be gained.
                            Mouse.getInstance().click(new Point(674, 186));
                            Time.sleep(500, 1000);
                            Mouse.getInstance().click(new Point(644, 435));
                            Variables.setExLocked(false);
                        }
                    } else {
                        Mouse.getInstance().click(new Point(643, 184));
                    }
                    if (Interfaces.getChatboxInterfaceId() == Constants.OFFER_INTERFACE_ID) {
                        Menu.interact("Ok", new Point(264, 448));
                        var.setBonesOffered(var.getBonesOffered()
                                + Inventory.getCount());
                        hoverMouseOn(BankOpen.bank);
                        Time.sleep(Inventory.getCount(bone.getId()) * 1250);
                    }
                }
            } else {
                Camera.turnTo(altar);
            }
        }
    }

    void hoverMouseOn(SceneObject object) {
        int x = object.getCenterPointOnScreen().x;
        int y = object.getCenterPointOnScreen().y;
        Mouse.getInstance().moveMouse(x, y);
    }
}


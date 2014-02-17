package org.parabot.el.maestro.eldeathrunes.strategies.bank;

import org.parabot.el.maestro.eldeathrunes.core.Core;
import org.parabot.el.maestro.eldeathrunes.data.Constants;
import org.parabot.el.maestro.eldeathrunes.data.Variables;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.api.methods.Bank;
import org.rev317.api.methods.Camera;
import org.rev317.api.methods.Inventory;
import org.rev317.api.wrappers.scene.SceneObject;


public class BankOpen implements Strategy {
    public static SceneObject bank = Bank.getNearestBanks()[0];

    @Override
    public boolean activate() {
        if (bank != null && !Bank.isOpen()) {
            if (Inventory.getCount(Constants.RUNE_ESS) < 1 || Inventory.isEmpty()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void execute() {
        try {
            if (bank != null) {
                if (bank.isOnScreen()) {
                    bank.interact("Use-quickly");
                    Core.gameTimer.restart();
                    while (!Bank.isOpen()) {
                        Time.sleep(300);
                        if (Core.gameTimer.getElapsedTime() > 5000) {
                            Core.gameTimer.stop();
                            Core.gameTimer.reset();
                            break;
                        }
                    }
                } else {
                    Camera.turnTo(bank);
                }
            }
        } catch (Exception e) {

        }
    }
}

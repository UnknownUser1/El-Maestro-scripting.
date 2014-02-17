package org.parabot.el.maestro.eldeathrunes.strategies.bank;

import org.parabot.core.ui.components.LogArea;
import org.parabot.el.maestro.eldeathrunes.data.Constants;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.api.methods.Bank;
import org.rev317.api.methods.Inventory;
import org.rev317.api.methods.Menu;

import java.awt.*;


public class BankHandler implements Strategy {
    @Override
    public boolean activate() {
        return Bank.isOpen();
    }

    @Override
    public void execute() {
        if (Bank.getCount(Constants.RUNE_ESS) > 0) {
            if (!Inventory.isFull()) {
                withdrawAll(Constants.RUNE_ESS);
            } else {
                Menu.interact("Close", new Point(487, 27));
            }
        } else {
            LogArea.log("Out of ess!");
        }
    }

    void withdrawAll(int item) {
        try {
            if (Bank.getCount(item) > 0) {
                Bank.getItem(item).interact("Withdraw All");
                Time.sleep(350);
            }
        } catch (Exception e) {

        }
    }
}

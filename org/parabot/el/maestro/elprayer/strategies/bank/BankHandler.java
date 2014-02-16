package org.parabot.el.maestro.elprayer.strategies.bank;

/**
 * Created by Bautista on 2/13/14.
 */

import org.parabot.core.ui.components.LogArea;
import org.parabot.el.maestro.elprayer.data.Constants;
import org.parabot.el.maestro.elprayer.data.Variables;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.input.Mouse;
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
        Variables.setStatus("Banking.");
		try {
			if (Inventory
					.getCount(Constants.BONE_DATA[Variables.getBoneIndex()][0]) < 1) {
				if (Bank.getCount(Constants.BONE_DATA[Variables.getBoneIndex()][0]) > 0) {
					withdrawAll(Constants.BONE_DATA[Variables.getBoneIndex()][0]);
					Time.sleep(800);
				} else {
					LogArea.error("Out of Bones");
				}
			} else if (Inventory.getCount(Constants.BONE_DATA[Variables
					.getBoneIndex()][0]) > 0) {
				Menu.interact("Close", new Point(487, 27));
			}
			if (!Inventory.isEmpty()
					&& Inventory.getCount(Constants.BONE_DATA[Variables
					.getBoneIndex()][0]) < 1) {
				Mouse.getInstance().click(new Point(398, 298));
				Time.sleep(1000);
			}
		} catch (Exception e) {

		}

	}

	void withdrawAll(int item) {
		try {
			if (Bank.getCount(item) > 0) {
				Bank.getItem(item).interact("Withdraw All");
				Time.sleep(200);
			}
		} catch (Exception e) {

		}
	}

}
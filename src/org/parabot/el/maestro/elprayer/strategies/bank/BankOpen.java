package org.parabot.el.maestro.elprayer.strategies.bank;

/**
 * Created by Bautista on 2/13/14.
 */

import org.parabot.el.maestro.elprayer.data.Variables;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.api.methods.Bank;
import org.rev317.api.methods.Inventory;
import org.rev317.api.wrappers.scene.SceneObject;

public class BankOpen implements Strategy {
	public static SceneObject bank = Bank.getNearestBanks()[0];

	@Override
	public boolean activate() {
		return Inventory.isEmpty() && bank != null && !Bank.isOpen();
	}

	@Override
	public void execute() {
        Variables.setStatus("Opening Bank.");
		try {
			if (bank != null) {
				bank.interact("Use-quickly");
				Time.sleep(1000);
			}
		} catch (Exception e) {

		}
	}

}

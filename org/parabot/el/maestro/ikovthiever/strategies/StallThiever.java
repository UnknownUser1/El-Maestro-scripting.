package org.parabot.el.maestro.ikovthiever.strategies;

import org.parabot.el.maestro.ikovthiever.data.Constants;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.api.methods.Players;
import org.rev317.api.methods.SceneObjects;
import org.rev317.api.wrappers.SceneObject;

/**
 * Created by El Maestro on 4/7/2014.
 */
public class StallThiever implements Strategy {
	SceneObject stall = SceneObjects.getNearest(Constants.STALL)[0];

	@Override
	public boolean activate() {
		return stall != null && Players.getMyPlayer().getAnimation() == -1;
	}

	@Override
	public void execute() {
		if (stall != null) {
			stall.interact(0);
			Time.sleep(550,700);
		}
	}
}

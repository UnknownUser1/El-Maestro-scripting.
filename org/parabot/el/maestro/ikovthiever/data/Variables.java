package org.parabot.el.maestro.ikovthiever.data;

import org.parabot.environment.scripts.framework.Strategy;

import java.util.ArrayList;

/**
 * Created by El Maestro on 4/7/2014.
 */
public class Variables {
	private static ArrayList<Strategy> strategies = new ArrayList<>();

	public static void setStrategy(Strategy e) {
		strategies.add(e);
	}

	public static ArrayList<Strategy> getStrategyArray() {
		return strategies;

	}
}

package org.parabot.el.maestro.eldeathrunes.data;

import org.parabot.environment.scripts.framework.Strategy;

import java.util.ArrayList;

/**
 * Created by Bautista on 2/15/14.
 */
public class Variables {
    private static ArrayList<Strategy> strategies = new ArrayList<>();
    private static int pkpEarned;
    private static int runesMade;

    public static int getRunesMade() {
        return runesMade;
    }

    public static void setRunesMade(int i) {
        runesMade = i;
    }

    public static void setStrategy(Strategy e) {
        strategies.add(e);
    }

    public static ArrayList<Strategy> getStrategyArray() {
        return strategies;

    }

    public static int getPkpEarned() {
        return pkpEarned;

    }

    public static void setPkpEarned(int i) {
        pkpEarned = i;
    }

}

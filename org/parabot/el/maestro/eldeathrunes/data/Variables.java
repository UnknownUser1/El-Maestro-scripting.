package org.parabot.el.maestro.eldeathrunes.data;

import org.parabot.environment.scripts.framework.Strategy;

import java.util.ArrayList;


public class Variables {
    private static ArrayList<Strategy> strategies = new ArrayList<>();
    private static int pkpEarned;
    private static int randomsSolved;
    private static String status;
    private static int runesMade;
    private static boolean hidePaint = false;

    public static boolean getHidePaint() {
        return hidePaint;
    }

    public static void setHidePaint(boolean i) {
        hidePaint = i;
    }

    public static String getStatus() {
        return status;
    }

    public static void setStatus(String i) {
        status = i;
    }

    public static int getRandomsSolved() {
        return randomsSolved;
    }

    public static void setRandomsSolved(int i) {
        randomsSolved = i;
    }

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

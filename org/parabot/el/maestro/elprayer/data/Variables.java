package org.parabot.el.maestro.elprayer.data;

/**
 * Created by Bautista on 2/13/14.
 */

import org.parabot.environment.api.utils.Timer;
import org.parabot.environment.scripts.framework.Strategy;

import java.util.ArrayList;

public class Variables {
    private static ArrayList<Strategy> strategies = new ArrayList<>();
    private static int bonesOffered;
    private static int pkpEarned;
    private static int boneIndex;
    private static String status;
    private static int expGained;
    private static Timer t = new Timer();
    private static String boneType;
    private static String location;
    private static boolean xpLocked;

    public static boolean getXpLocked() {
        return xpLocked;
    }

    public static void setExLocked(boolean i) {
        xpLocked = i;
    }

    public static String getLocation() {
        return location;
    }

    public static void setLocation(String i) {
        location = i;
    }

    public static String getBoneType() {
        return boneType;
    }

    public static void setBoneType(String i) {
        boneType = i;
    }

    public static int getExpGained() {
        return expGained;
    }

    public static void setExpGained(int i) {
        expGained = i;
    }

    public static Timer getTimer() {
        return t;
    }

    public static void setTimer(Timer i) {
        t = i;
    }

    public static String getStatus() {
        return status;
    }

    public static void setStatus(String i) {
        status = i;
    }

    public static int getBoneIndex() {
        return boneIndex;
    }

    public static void setBoneIndex(int i) {
        boneIndex = i;
    }

    public static int getPkpEarned() {
        return pkpEarned;

    }

    public static void setPkpEarned(int i) {
        pkpEarned = i;
    }

    public static int getBonesOffered() {
        return bonesOffered;

    }

    public static void setBonesOffered(int i) {
        bonesOffered = i;
    }

    public static void setStrategy(Strategy e) {
        strategies.add(e);
    }

    public static ArrayList<Strategy> getStrategyArray() {
        return strategies;

    }

}

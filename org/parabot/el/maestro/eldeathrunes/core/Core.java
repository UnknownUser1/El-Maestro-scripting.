package org.parabot.el.maestro.eldeathrunes.core;

import org.parabot.core.ui.components.LogArea;
import org.parabot.el.maestro.eldeathrunes.data.Variables;
import org.parabot.el.maestro.eldeathrunes.strategies.bank.BankHandler;
import org.parabot.el.maestro.eldeathrunes.strategies.bank.BankOpen;
import org.parabot.el.maestro.eldeathrunes.strategies.craft.RuneCraft;
import org.parabot.el.maestro.eldeathrunes.strategies.randoms.OldMan;
import org.parabot.el.maestro.eldeathrunes.strategies.randoms.SandwichLady;
import org.parabot.environment.api.interfaces.Paintable;
import org.parabot.environment.api.utils.Timer;
import org.parabot.environment.scripts.Category;
import org.parabot.environment.scripts.Script;
import org.parabot.environment.scripts.ScriptManifest;
import org.rev317.api.events.MessageEvent;
import org.rev317.api.events.listeners.MessageListener;
import org.rev317.api.methods.Camera;
import org.rev317.api.methods.Skill;

import java.awt.*;
import java.text.DecimalFormat;

/**
 * Created by Bautista on 2/15/14.
 */
@ScriptManifest(author = "El Maestro", category = Category.RUNECRAFTING, description = "Makes death runes.", name = "ELDeathRunes", version = 1.0, servers = {"PKHonor"})
public class Core extends Script implements MessageListener, Paintable {
    public static Timer paintTimer = new Timer();
    public static Timer gameTimer = new Timer();
    static int startExp;
    private final Color color1 = new Color(102, 102, 102);
    private final Color color2 = new Color(255, 255, 255);
    private final Font font1 = new Font("Monotype Corsiva", 0, 14);
    private final Font font2 = new Font("Monotype Corsiva", 0, 23);

    public static void setCamera() {
        Camera.setRotation(85);
        Camera.setPitch(true);
    }

    public static String formatNumber(int start) {
        DecimalFormat nf = new DecimalFormat("0.0");
        double i = start;
        if (i >= 1000000) {
            return nf.format((i / 1000000)) + "M";
        }
        if (i >= 1000) {
            return nf.format((i / 1000)) + "K";
        }
        return "" + start;
    }

    public boolean onExecute() {

        startExp = Skill.RUNECRAFTING.getExperience();
        setCamera();
        Variables.setStrategy(new OldMan());
        Variables.setStrategy(new SandwichLady());
        Variables.setStrategy(new BankOpen());
        Variables.setStrategy(new BankHandler());
        Variables.setStrategy(new RuneCraft());
        provide(Variables.getStrategyArray());
        LogArea.log("ELDeathRune has initiated.");
        return true;
    }

    public void onFinish() {
        LogArea.log("Thank you for using ELDeathRunes.");
    }

    @Override
    public void messageReceived(MessageEvent message) {
        if (message.getMessage().contains("You received")) {
            Variables.setPkpEarned(Variables.getPkpEarned() + 1);
        }
    }

    @Override
    public void paint(Graphics g1) {
        Graphics2D g = (Graphics2D) g1;
        g.setColor(color1);
        g.fillRect(544, 320, 196, 149);
        g.setFont(font1);
        g.setColor(color2);
        g.drawString("Exp: " + (Skill.RUNECRAFTING.getExperience() - startExp)
                + "("
                + formatNumber(paintTimer.getPerHour(
                (Skill.RUNECRAFTING.getExperience() - startExp)))
                + ")", 557, 435);
        g.setFont(font2);
        g.drawString("ELDeathRunes", 579, 338);
        g.setFont(font1);
        g.drawString("Time: " + paintTimer.toString(), 553, 365);
        g.drawString("Runes Made: " + Variables.getRunesMade() + "(" + formatNumber(paintTimer.getPerHour(Variables.getRunesMade())) + ")", 555, 387);
        g.drawString("PKP: " + Variables.getPkpEarned() + "(" + formatNumber(paintTimer.getPerHour(Variables.getPkpEarned())) + ")", 557, 412);
        g.drawString("El Maestro Scripting.", 631, 465);
    }
}
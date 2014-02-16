package org.parabot.el.maestro.elprayer.core;

import org.parabot.core.ui.components.LogArea;
import org.parabot.el.maestro.elprayer.data.Variables;
import org.parabot.el.maestro.elprayer.strategies.bank.BankHandler;
import org.parabot.el.maestro.elprayer.strategies.bank.BankOpen;
import org.parabot.el.maestro.elprayer.strategies.prayer.BoneOffer;
import org.parabot.el.maestro.elprayer.ui.Gui;
import org.parabot.environment.api.interfaces.Paintable;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.Category;
import org.parabot.environment.scripts.Script;
import org.parabot.environment.scripts.ScriptManifest;
import org.rev317.api.events.MessageEvent;
import org.rev317.api.events.listeners.MessageListener;
import org.rev317.api.methods.BotMouse;
import org.rev317.api.methods.Skill;

import java.awt.*;

/**
 * Created by Bautista on 2/13/14.
 */
@ScriptManifest(author = "El Maestro", category = Category.PRAYER, description = "Trains prayer by using any bone on any altar in the selected location.", name = "ELPrayer", version = 1.0, servers = {"PKHonor"})
public class Core extends Script implements Paintable, MessageListener {
    static int startExp;
    static int startLevel;
    private final Color color1 = new Color(0, 0, 0);
    private final Color color2 = new Color(255, 255, 255);
    private final BasicStroke stroke1 = new BasicStroke(1);
    private final Font font1 = new Font("Mongolian Baiti", 0, 11);
    private final Font font2 = new Font("Mongolian Baiti", 0, 18);
    private final Font font3 = new Font("Mongolian Baiti", 0, 13);

    public boolean onExecute() {
        startLevel = Skill.PRAYER.getLevel();
        startExp = Skill.PRAYER.getExperience();
        Gui gui = new Gui();
        gui.setVisible(true);
        while (gui.isVisible()) {
            Time.sleep(400);
        }
        Variables.getTimer().restart();
        Variables.setStrategy(new BoneOffer());
        Variables.setStrategy(new BankHandler());
        Variables.setStrategy(new BankOpen());
        provide(Variables.getStrategyArray());
        LogArea.log("Script is initiated.");
        LogArea.log("If the script get enough demand i will add none-premium altar area.");
        return true;
    }

    public void onFinish() {
        LogArea.error("Script has been ended.");
    }

    void drawMouse(Graphics g) {
        g.setColor(Color.RED);
        g.drawOval(BotMouse.getMouseX() - 1, BotMouse.getMouseY() - 1, 2, 2);
        g.fillOval(BotMouse.getMouseX() - 1, BotMouse.getMouseY() - 1, 2, 2);
        g.setColor(Color.BLACK);
        g.drawOval(BotMouse.getMouseX() - 8, BotMouse.getMouseY() - 8, 16, 16);
        g.drawLine(0, BotMouse.getMouseY(), BotMouse.getMouseX() - 8,
                BotMouse.getMouseY());
        g.drawLine(BotMouse.getMouseX() + 8, BotMouse.getMouseY(), 765,
                BotMouse.getMouseY());
        g.drawLine(BotMouse.getMouseX(), 0, BotMouse.getMouseX(),
                BotMouse.getMouseY() - 8);
        g.drawLine(BotMouse.getMouseX(), BotMouse.getMouseY() + 8,
                BotMouse.getMouseX(), 503);
    }

    @Override
    public void paint(Graphics g1) {
        Graphics2D g = (Graphics2D) g1;
        g.setColor(color1);
        g.fillRect(548, 289, 193, 182);
        g.setColor(color2);
        g.setStroke(stroke1);
        g.drawLine(646, 303, 738, 303);
        g.setFont(font1);
        g.drawString("El Maestro Scripting", 646, 300);
        g.setFont(font2);
        g.drawString("ELPrayer", 555, 306);
        g.setFont(font3);
        g.drawString(
                "Bones offered: "
                        + "("
                        + Variables.getTimer().getPerHour(
                        Variables.getBonesOffered()) + ")"
                        + Variables.getBonesOffered(), 553, 390);
        g.drawLine(556, 308, 621, 308);
        g.drawString(
                "Exp: "
                        + (Skill.PRAYER.getExperience() - startExp)
                        + "("
                        + Variables.getTimer().getPerHour(
                        (Skill.PRAYER.getExperience() - startExp))
                        + ")", 554, 412);
        g.drawString("Bone Type: " + Variables.getBoneType(), 553, 431);
        g.drawString(
                "Prayer Level: "
                        + Skill.PRAYER.getLevel()
                        + "("
                        + Variables.getTimer().getPerHour(
                        (Skill.PRAYER.getLevel() - startLevel)) + ")",
                553, 368);
        g.drawString("Location: " + Variables.getLocation(), 552, 348);
        g.drawString("PKP: " + Variables.getPkpEarned() + "("
                + Variables.getTimer().getPerHour(Variables.getPkpEarned())
                + ")", 553, 329);
        g.drawString("Status: " + Variables.getStatus(), 553, 450);
        g.drawString("Time: " + Variables.getTimer().toString(), 554, 467);
        drawMouse(g1);

    }

    @Override
    public void messageReceived(MessageEvent message) {
        if (message.getMessage().contains("You received")) {
            Variables.setPkpEarned(Variables.getPkpEarned()+ 1);
        }
        if (message.getMessage().contains("Your XP is locked")) {
            Variables.setExLocked(true);
        }

    }
}
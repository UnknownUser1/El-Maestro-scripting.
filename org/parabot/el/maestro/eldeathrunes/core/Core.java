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

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;


@ScriptManifest(author = "El Maestro", category = Category.RUNECRAFTING, description = "Makes death runes.", name = "ELDeathRunes", version = 1.0, servers = {"PKHonor"})
public class Core extends Script implements MessageListener, Paintable, MouseListener {
    public static final Rectangle button = new Rectangle(489, 288, 20, 17);
    public static Timer paintTimer = new Timer();
    public static Timer gameTimer = new Timer();
    static int startExp;
    private final Color color1 = new Color(153, 153, 153);
    private final Color color2 = new Color(255, 255, 255);
    private final Font font1 = new Font("Tahoma", 1, 16);
    private final Image img1 = getImage("https://dl.dropboxusercontent.com/s/htoxx0xwupexv0l/FinalVersiont.png?dl=1&token_hash=AAFDT4FndGSCjkNW9O71xAMzx1RLznO1To4WiS7uy7BFew");

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

    private Image getImage(String url) {
        try {
            return ImageIO.read(new URL(url));
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    public void paint(Graphics g1) {
        if (!Variables.getHidePaint()) {
            Graphics2D g = (Graphics2D) g1;
            g.drawImage(img1, 3, 280, null);
            g.setFont(font1);
            g.setColor(color2);
            g.drawString("status", 266, 334);
            g.setColor(color1);
            g.drawString(Variables.getPkpEarned() + "(" + formatNumber(paintTimer.getPerHour(Variables.getPkpEarned())) + ")", 375, 412);
            g.drawString("" + Variables.getRandomsSolved(), 451, 430);
            g.drawString("" + Variables.getRunesMade(), 393, 451);
            g.drawString(formatNumber(paintTimer.getPerHour(Variables.getRunesMade())), 136, 451);
            g.drawString("" + (Skill.RUNECRAFTING.getExperience() - startExp)
                    + "("
                    + formatNumber(paintTimer.getPerHour(
                    (Skill.RUNECRAFTING.getExperience() - startExp)))
                    + ")", 83, 431);
            g.drawString(paintTimer.toString(), 59, 410);
        }

    }

    @Override
    public void mouseClicked(MouseEvent event) {
        Point point = event.getPoint();
        if (button.contains(point) && !Variables.getHidePaint()) {
            Variables.setHidePaint(true);
        } else if (button.contains(point) && Variables.getHidePaint()) {
            Variables.setHidePaint(false);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}

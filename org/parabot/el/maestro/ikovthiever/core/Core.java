package org.parabot.el.maestro.ikovthiever.core;

import org.parabot.el.maestro.ikovthiever.data.Variables;
import org.parabot.el.maestro.ikovthiever.strategies.StallThiever;
import org.parabot.environment.scripts.Category;
import org.parabot.environment.scripts.Script;
import org.parabot.environment.scripts.ScriptManifest;

/**
 * Created by El Maestro on 4/7/2014.
 */
@ScriptManifest(author = "El Maestro", category = Category.THIEVING, description = "Steals from stalls at home.", name = "IkovThiever", version = 1.0, servers = {"Ikov"})
public class Core extends Script {
    public boolean onExecute() {
        Variables.setStrategy(new StallThiever());
        provide(Variables.getStrategyArray());
        return true;
    }

    public void onFinish() {

    }


}

package dev.landonjw.gooeyui.forge;

import dev.landonjw.gooeyui.GooeyUI;
import net.minecraftforge.fml.common.Mod;

@Mod(GooeyUI.MOD_ID)
public class GooeyUIForge {

    public GooeyUIForge() {
        GooeyUI.init();
    }

}

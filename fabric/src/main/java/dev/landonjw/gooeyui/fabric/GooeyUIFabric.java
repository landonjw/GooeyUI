package dev.landonjw.gooeyui.fabric;

import dev.landonjw.gooeyui.GooeyUI;
import net.fabricmc.api.ModInitializer;

public class GooeyUIFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        GooeyUI.init();
    }
}

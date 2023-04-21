package dev.landonjw.gooeyui;

import ca.landonjw.gooeylibs2.api.button.Button;
import ca.landonjw.gooeylibs2.api.button.GooeyButton;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GooeyUI {

    public static final String MOD_ID = "gooeyui";
    private static final Logger logger = LogManager.getLogger();

    public static void init() {
        logger.info("Initialized");
        Button button = GooeyButton.builder()
                .display(new ItemStack(Items.DIAMOND))
                .build();
    }

}
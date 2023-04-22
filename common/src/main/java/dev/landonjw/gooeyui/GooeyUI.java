package dev.landonjw.gooeyui;

import dev.architectury.event.events.common.CommandRegistrationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GooeyUI {

    public static final String MOD_ID = "gooeyui";
    private static final Logger logger = LogManager.getLogger();

    public static void init() {
        logger.info("Initialized");
        CommandRegistrationEvent.EVENT.register((dispatcher, registry, selection) -> {
            
        });
    }

}
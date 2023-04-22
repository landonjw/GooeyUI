package dev.landonjw.gooeyui.adapter.deluxemenu;

import ca.landonjw.gooeylibs2.api.page.Page;
import dev.landonjw.gooeyui.configuration.GuiConfig;
import org.apache.logging.log4j.Logger;

public class DeluxeMenuGuiConfig implements GuiConfig {

    private final Logger logger;

    public DeluxeMenuGuiConfig(Logger logger) {
        this.logger = logger;
    }

    @Override
    public Page toPage() {
        return null;
    }

}

package dev.landonjw.gooeyui.adapter.deluxemenu.commands.parser;

import ca.landonjw.gooeylibs2.api.UIManager;
import dev.landonjw.gooeyui.adapter.deluxemenu.commands.DeluxeMenuCommand;

import java.util.Optional;

public class DeluxeMenuCloseGuiCommandParser implements DeluxeMenuCommandParser {

    @Override
    public Optional<DeluxeMenuCommand> parse(String input) {
        return Optional.of(UIManager::closeUI);
    }

}

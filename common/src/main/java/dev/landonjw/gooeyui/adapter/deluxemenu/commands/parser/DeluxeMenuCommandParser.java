package dev.landonjw.gooeyui.adapter.deluxemenu.commands.parser;

import dev.landonjw.gooeyui.adapter.deluxemenu.commands.DeluxeMenuCommand;

import java.util.Optional;

public interface DeluxeMenuCommandParser {
    Optional<DeluxeMenuCommand> parse(String input);
}
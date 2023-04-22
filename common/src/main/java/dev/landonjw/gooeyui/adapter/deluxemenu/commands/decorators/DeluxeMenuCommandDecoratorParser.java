package dev.landonjw.gooeyui.adapter.deluxemenu.commands.decorators;

import dev.landonjw.gooeyui.adapter.deluxemenu.commands.DeluxeMenuCommand;

import java.util.Optional;

public interface DeluxeMenuCommandDecoratorParser {
    Optional<DeluxeMenuCommand> parse(DeluxeMenuCommand command, String input);
}
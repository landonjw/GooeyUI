package dev.landonjw.gooeyui.adapter.deluxemenu.commands.parser;

import dev.landonjw.gooeyui.adapter.deluxemenu.commands.DeluxeMenuCommand;
import dev.landonjw.gooeyui.adapter.deluxemenu.commands.decorators.DeluxeMenuCommandDecoratorParser;
import org.apache.logging.log4j.Logger;

import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;

public class DeluxeMenuCommandParsingService {

    private final Logger logger;
    private final Map<String, DeluxeMenuCommandParser> commandParsers;
    private final Map<String, DeluxeMenuCommandDecoratorParser> decoratorParsers;

    private final Pattern COMMAND_LOCATOR = Pattern.compile("\\[(\\w+)\\]\\s*(\\w+)?\\s*(<.*>)*");
    private final Pattern DECORATOR_ARG_LOCATOR = Pattern.compile("\\<(\\w+)=(\\w+)\\>");

    public DeluxeMenuCommandParsingService(Logger logger,
                                           Map<String, DeluxeMenuCommandParser> parsersByCommandType,
                                           Map<String, DeluxeMenuCommandDecoratorParser> decoratorParsers) {
        this.logger = logger;
        this.commandParsers = parsersByCommandType;
        this.decoratorParsers = decoratorParsers;
    }

    public Optional<DeluxeMenuCommand> parse(String input) {
        var matcher = COMMAND_LOCATOR.matcher(input);
        if (!matcher.find()) {
            logger.warn("Could not find command in input '" + input + "'");
            return Optional.empty();
        }

        var commandType = matcher.group(1);
        var commandArgs = matcher.group(2);
        var decorators = matcher.group(3);
        var parser = commandParsers.get(commandType);

        if (parser == null) {
            logger.warn("No command parser registered for type '" + commandType + "' in input '" + input + "'");
            return Optional.empty();
        }

        Optional<DeluxeMenuCommand> command = parser.parse(commandArgs);

        if (command.isEmpty() || decorators == null) return command;

        DeluxeMenuCommand decoratedCommand = parseDecorators(command.get(), decorators);
        return Optional.of(decoratedCommand);
    }

    private DeluxeMenuCommand parseDecorators(DeluxeMenuCommand command, String input) {
        var matcher = DECORATOR_ARG_LOCATOR.matcher(input);

        /* Any time a decorator parser is found, the current decoratedCommand is decorated and reassigned to the result.
           If no parser is found, this will simply be the initial command given in the function parameters.
         */
        var decoratedCommand = command;
        while (matcher.find()) {
            var decoratorType = matcher.group(1);
            var decoratorArgs = matcher.group(2);

            var decoratorParser = decoratorParsers.get(decoratorType);
            if (decoratorParser == null) {
                logger.warn("No decorator parser registered for type '" + decoratorType + "' in input '" + input + "'");
            }
            else {
                var maybeDecorator = decoratorParser.parse(decoratedCommand, decoratorArgs);
                if (maybeDecorator.isPresent()) {
                    decoratedCommand = maybeDecorator.get();
                }
            }
        }
        return decoratedCommand;
    }

}
package dev.landonjw.gooeyui.adapter.deluxemenu.commands.parser;

import dev.landonjw.gooeyui.adapter.deluxemenu.commands.DeluxeMenuCommand;
import dev.landonjw.gooeyui.adapter.deluxemenu.commands.decorators.DeluxeMenuCommandDecoratorParser;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DeluxeMenuCommandParsingServiceTest {

    @Mock
    Logger logger;

    @Test
    public void parse_KnownCommand_ReturnsCommand() {
        DeluxeMenuCommand command = mock(DeluxeMenuCommand.class);
        var commandParsers = getTestCommandParserMap(command);
        Map<String, DeluxeMenuCommandDecoratorParser> decoratorParsers = new HashMap<>();
        var service = new DeluxeMenuCommandParsingService(logger, commandParsers, decoratorParsers);

        var result = service.parse("[test]");

        assertTrue(result.isPresent());
        assertEquals(command, result.get());
    }

    @Test
    public void parse_UnknownCommand_ReturnsEmptyOptional() {
        Map<String, DeluxeMenuCommandParser> commandParsers = new HashMap<>();
        Map<String, DeluxeMenuCommandDecoratorParser> decoratorParsers = new HashMap<>();
        var service = new DeluxeMenuCommandParsingService(logger, commandParsers, decoratorParsers);

        var result = service.parse("[test]");

        assertTrue(result.isEmpty());
    }

    @Test
    public void parse_UnknownCommand_LogsWarning() {
        Map<String, DeluxeMenuCommandParser> commandParsers = new HashMap<>();
        Map<String, DeluxeMenuCommandDecoratorParser> decoratorParsers = new HashMap<>();
        var service = new DeluxeMenuCommandParsingService(logger, commandParsers, decoratorParsers);

        service.parse("[test]");

        verify(logger, times(1)).warn("No command parser registered for type 'test' in input '[test]'");
    }

    @Test
    public void parse_NoCommand_LogsWarning() {
        Map<String, DeluxeMenuCommandParser> commandParsers = new HashMap<>();
        Map<String, DeluxeMenuCommandDecoratorParser> decoratorParsers = new HashMap<>();
        var service = new DeluxeMenuCommandParsingService(logger, commandParsers, decoratorParsers);

        service.parse("abc");

        verify(logger, times(1)).warn("Could not find command in input 'abc'");
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "[test] abcdef <foo=bar>",
            "[test]abcdef<foo=bar>",
            "[test]   abcdef     <foo=bar>",
    })
    public void parse_KnownCommand_PassesStrippedArgumentToCommand(String input) {
        DeluxeMenuCommandParser parser = mock(DeluxeMenuCommandParser.class);
        Map<String, DeluxeMenuCommandParser> commandParsers = new HashMap<>();
        commandParsers.put("test", parser);
        Map<String, DeluxeMenuCommandDecoratorParser> decoratorParsers = new HashMap<>();
        var service = new DeluxeMenuCommandParsingService(logger, commandParsers, decoratorParsers);

        service.parse(input);

        verify(parser, times(1)).parse("abcdef");
    }

    private Map<String, DeluxeMenuCommandParser> getTestCommandParserMap(DeluxeMenuCommand command) {
        DeluxeMenuCommandParser parser = mock(DeluxeMenuCommandParser.class);
        when(parser.parse(any())).thenReturn(Optional.of(command));
        Map<String, DeluxeMenuCommandParser> parsers = new HashMap<>();
        parsers.put("test", parser);
        return parsers;
    }

}
package dev.landonjw.gooeyui.adapter.deluxemenu.commands.parser;

import ca.landonjw.gooeylibs2.api.UIManager;
import ca.landonjw.gooeylibs2.api.container.GooeyContainer;
import dev.landonjw.gooeyui.adapter.deluxemenu.commands.DeluxeMenuCommand;
import net.minecraft.server.level.ServerPlayer;

import java.util.Optional;

public class DeluxeMenuRefreshGuiCommandParser implements DeluxeMenuCommandParser {

    @Override
    public Optional<DeluxeMenuCommand> parse(String input) {
        return Optional.of(this::refreshPage);
    }

    private void refreshPage(ServerPlayer player) {
        if (player.containerMenu instanceof GooeyContainer gooeyContainer) {
            var page = gooeyContainer.getPage();
            UIManager.openUIForcefully(player, page);
        }
    }

}

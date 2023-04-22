package dev.landonjw.gooeyui.adapter.deluxemenu.commands.decorators;

import ca.landonjw.gooeylibs2.api.tasks.Task;
import dev.landonjw.gooeyui.adapter.deluxemenu.commands.DeluxeMenuCommand;
import net.minecraft.server.level.ServerPlayer;

public class DelayCommandDecorator implements DeluxeMenuCommand {

    private final DeluxeMenuCommand command;
    private final long delayTicks;

    public DelayCommandDecorator(DeluxeMenuCommand command, long delayTicks) {
        this.command = command;
        this.delayTicks = delayTicks;
    }

    @Override
    public void run(ServerPlayer player) {
        Task.builder()
                .delay(delayTicks)
                .execute(() -> command.run(player))
                .build();
    }

}

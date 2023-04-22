package dev.landonjw.gooeyui.adapter.deluxemenu.commands.decorators;

import dev.landonjw.gooeyui.adapter.deluxemenu.commands.DeluxeMenuCommand;
import net.minecraft.server.level.ServerPlayer;

import java.util.Random;

public class ChanceCommandDecorator implements DeluxeMenuCommand {

    private final DeluxeMenuCommand command;
    private final double chance;
    private final Random random;

    public ChanceCommandDecorator(DeluxeMenuCommand command, double chance, Random random) {
        this.command = command;
        this.chance = chance;
        this.random = random;
    }

    @Override
    public void run(ServerPlayer player) {
        if (random.nextDouble() < chance) {
            command.run(player);
        }
    }

}

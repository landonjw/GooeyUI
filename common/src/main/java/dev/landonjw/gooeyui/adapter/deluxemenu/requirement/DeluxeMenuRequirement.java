package dev.landonjw.gooeyui.adapter.deluxemenu.requirement;

import net.minecraft.server.level.ServerPlayer;

public interface DeluxeMenuRequirement {
    boolean test(ServerPlayer player);
}
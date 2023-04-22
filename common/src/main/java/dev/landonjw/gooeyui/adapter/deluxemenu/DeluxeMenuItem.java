package dev.landonjw.gooeyui.adapter.deluxemenu;

import ca.landonjw.gooeylibs2.api.button.ButtonAction;
import ca.landonjw.gooeylibs2.api.button.ButtonClick;
import ca.landonjw.gooeylibs2.api.data.UpdateEmitter;
import dev.landonjw.gooeyui.adapter.deluxemenu.requirement.DeluxeMenuRequirement;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.List;
import java.util.Map;

public class DeluxeMenuItem extends UpdateEmitter<DeluxeMenuItem> {

    private final Item material;
    private final int data;
    private final boolean updateInterval;
    private final List<DeluxeMenuRequirement> viewRequirements;
    private final Map<ButtonClick, List<DeluxeMenuRequirement>> clickRequirements;

    public DeluxeMenuItem(Item material,
                          int data,
                          boolean updateInterval,
                          List<DeluxeMenuRequirement> viewRequirements,
                          Map<ButtonClick, List<DeluxeMenuRequirement>> clickRequirements) {
        this.material = material;
        this.data = data;
        this.updateInterval = updateInterval;
        this.viewRequirements = viewRequirements;
        this.clickRequirements = clickRequirements;
    }

    public ItemStack toItemStack() {
        return null; // TODO
    }

    public boolean canView(ServerPlayer player) {
        for (DeluxeMenuRequirement requirement : viewRequirements) {
            if (!requirement.test(player)) {
                return false;
            }
        }
        return true;
    }

    public void onClick(ButtonAction action) {
        ButtonClick clickType = action.getClickType();
        List<DeluxeMenuRequirement> requirements = clickRequirements.get(clickType);
        if (requirements == null) return;


    }

    private void runDenyCommands(ButtonAction action) {

    }

}
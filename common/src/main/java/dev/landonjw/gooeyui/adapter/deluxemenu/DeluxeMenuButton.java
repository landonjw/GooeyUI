package dev.landonjw.gooeyui.adapter.deluxemenu;

import ca.landonjw.gooeylibs2.api.button.Button;
import ca.landonjw.gooeylibs2.api.button.ButtonAction;
import ca.landonjw.gooeylibs2.api.data.UpdateEmitter;
import net.minecraft.world.item.ItemStack;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.List;

public class DeluxeMenuButton extends UpdateEmitter<Button> implements Button {

    private final List<DeluxeMenuItem> possibleItemViews;
    private DeluxeMenuItem currentItemView;

    public DeluxeMenuButton(List<DeluxeMenuItem> possibleItemViews) {
        // TODO: Sort by priority
        this.possibleItemViews = possibleItemViews;
    }

    @Override
    public ItemStack getDisplay() {
        return (currentItemView != null) ? currentItemView.toItemStack() : ItemStack.EMPTY;
    }

    @Override
    public void onClick(@NonNull ButtonAction action) {

    }

}
package dev.landonjw.gooeyui.configuration;

import dev.landonjw.gooeyui.configuration.GuiConfig;

import java.io.File;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public interface GuiConfigAdapter {

    CompletableFuture<Optional<GuiConfig>> parse(File file);

}

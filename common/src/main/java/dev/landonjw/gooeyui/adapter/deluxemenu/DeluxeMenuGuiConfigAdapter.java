package dev.landonjw.gooeyui.adapter.deluxemenu;

import ca.landonjw.gooeylibs2.api.button.Button;
import dev.landonjw.gooeyui.configuration.GuiConfigAdapter;
import dev.landonjw.gooeyui.configuration.GuiConfig;
import org.apache.logging.log4j.Logger;
import org.spongepowered.configurate.CommentedConfigurationNode;
import org.spongepowered.configurate.yaml.YamlConfigurationLoader;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public class DeluxeMenuGuiConfigAdapter implements GuiConfigAdapter {

    private final Logger logger;

    public DeluxeMenuGuiConfigAdapter(Logger logger) {
        this.logger = logger;
    }

    @Override
    public CompletableFuture<Optional<GuiConfig>> parse(File file) {
        return CompletableFuture.supplyAsync(() -> {
            CommentedConfigurationNode rootNode = loadFile(file);
            if (rootNode == null) return Optional.empty();

            // TODO Properly parse into DeluxeMenuGuiConfig
            return Optional.empty();
        });
    }

    private CommentedConfigurationNode loadFile(File file) {
        try {
            return YamlConfigurationLoader.builder()
                    .file(file)
                    .build()
                    .load();
        }
        catch (IOException ex)
        {
            logger.error("Error occurred while loading configuration file '" + file.getPath() + "'", ex);
            return null;
        }
    }

    private List<Button> parseButtons() {
        return null;
    }
}
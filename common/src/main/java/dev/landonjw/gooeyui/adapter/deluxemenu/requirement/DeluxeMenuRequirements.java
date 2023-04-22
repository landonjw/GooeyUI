package dev.landonjw.gooeyui.adapter.deluxemenu.requirement;

import net.minecraft.world.item.ItemStack;
import org.apache.logging.log4j.Logger;

public class DeluxeMenuRequirements {

    private final double DOUBLE_COMPARISON_EPSILON = 0.000001d;

    private final Logger logger;

    public DeluxeMenuRequirements(Logger logger) {
        this.logger = logger;
    }

    public DeluxeMenuRequirement not(DeluxeMenuRequirement requirement) {
        return (player) -> !requirement.test(player);
    }

    public DeluxeMenuRequirement or(DeluxeMenuRequirement a, DeluxeMenuRequirement b) {
        return (player) -> a.test(player) || b.test(player);
    }

    public DeluxeMenuRequirement and(DeluxeMenuRequirement a, DeluxeMenuRequirement b) {
        return (player) -> a.test(player) && b.test(player);
    }

    public DeluxeMenuRequirement hasPermission(String permission) {
        // TODO: Money support
        return (player) -> {
            return true;
        };
    }

    public DeluxeMenuRequirement hasMoney() {
        return (player) -> {
            // TODO: Currency support
            return true;
        };
    }

    public DeluxeMenuRequirement hasItem(ItemStack itemStack) {
        return (player) -> player.getInventory().contains(itemStack);
    }

    public DeluxeMenuRequirement javascript(String expression) {
        // TODO: JavaScript support
        return (player) -> {
            return true;
        };
    }

    public DeluxeMenuRequirement stringEquals(String input, String output) {
        return (player) -> input.equals(output);
    }

    public DeluxeMenuRequirement stringEqualsIgnoreCase(String input, String output) {
        return (player) -> input.equalsIgnoreCase(output);
    }

    public DeluxeMenuRequirement stringContains(String input, String output) {
        return (player) -> output.contains(input);
    }

    public DeluxeMenuRequirement equal(String input, String output) {
        return (player) -> {
            Double inputNum = tryParseDouble(input);
            Double outputNum = tryParseDouble(output);
            if (inputNum == null || outputNum == null) return false;
            return Math.abs(inputNum - outputNum) < DOUBLE_COMPARISON_EPSILON;
        };
    }

    public DeluxeMenuRequirement greaterThan(String input, String output) {
        return (player) -> {
            Double inputNum = tryParseDouble(input);
            Double outputNum = tryParseDouble(output);
            if (inputNum == null || outputNum == null) return false;
            return inputNum > outputNum;
        };
    }

    public DeluxeMenuRequirement lessThan(String input, String output) {
        return (player) -> {
            Double inputNum = tryParseDouble(input);
            Double outputNum = tryParseDouble(output);
            if (inputNum == null || outputNum == null) return false;
            return inputNum < outputNum;
        };
    }

    public DeluxeMenuRequirement greaterThanOrEqual(String input, String output) {
        return or(greaterThan(input, output), equal(input, output));
    }

    public DeluxeMenuRequirement lessThanOrEqual(String input, String output) {
        return or(lessThan(input, output), equal(input, output));
    }

    private Double tryParseDouble(String maybeDouble) {
        try {
            return Double.parseDouble(maybeDouble);
        }
        catch (NumberFormatException ex) {
            logger.error("Failed to coerce input '" + maybeDouble + "' into a double.");
            return null;
        }
    }

}
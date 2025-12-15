package com.estonic.autofishing;

import net.minecraft.client.MinecraftClient;
import net.minecraft.item.FishingRodItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

import java.util.Random;

/**
 * Handles the automation of fishing actions
 */
public class FishingAutomation {
    private static final Random random = new Random();
    private static long lastActionTime = 0;
    private static final long ACTION_DELAY = 100; // Minimum delay between actions in ms
    private static int previousSlot = -1; // Track previous slot for returning

    public static void performFishingAction(MinecraftClient client) {
        if (client.player == null || client.interactionManager == null) {
            return;
        }

        long currentTime = System.currentTimeMillis();
        if (currentTime - lastActionTime < ACTION_DELAY) {
            return; // Prevent too-frequent actions
        }

        // Check if player is holding a fishing rod
        ItemStack mainHandStack = client.player.getMainHandStack();
        ItemStack offHandStack = client.player.getOffHandStack();
        
        boolean hasRodInMainHand = mainHandStack.getItem() instanceof FishingRodItem;
        boolean hasRodInOffHand = offHandStack.getItem() instanceof FishingRodItem;

        if (!hasRodInMainHand && !hasRodInOffHand) {
            return; // No fishing rod equipped
        }

        // Perform the fishing actions
        try {
            // 1. Pull in the fishing rod (right-click action)
            client.interactionManager.interactItem(
                client.player, 
                hasRodInMainHand ? net.minecraft.util.Hand.MAIN_HAND : net.minecraft.util.Hand.OFF_HAND
            );

            // 2. Add a small delay
            Thread.sleep(50 + random.nextInt(50)); // 50-100ms delay

            // 3. Check for leather boots and use them if found
            if (hasLeatherBootsInHotbar(client)) {
                useLeatherBoots(client, hasRodInMainHand);
            }

            // 4. Randomize crosshair slightly
            randomizeCrosshair(client);

            // 5. Cast the rod again (right-click action)
            Thread.sleep(100 + random.nextInt(100)); // 100-200ms delay
            client.interactionManager.interactItem(
                client.player, 
                hasRodInMainHand ? net.minecraft.util.Hand.MAIN_HAND : net.minecraft.util.Hand.OFF_HAND
            );

            lastActionTime = currentTime;
            AutofishingMod.LOGGER.info("Fishing action performed");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            AutofishingMod.LOGGER.error("Fishing action interrupted", e);
        }
    }

    /**
     * Checks if leather boots are in the hotbar
     */
    private static boolean hasLeatherBootsInHotbar(MinecraftClient client) {
        if (client.player == null) return false;
        
        for (int i = 0; i < 9; i++) {
            ItemStack stack = client.player.getInventory().getStack(i);
            if (stack.getItem() == Items.LEATHER_BOOTS) {
                return true;
            }
        }
        return false;
    }

    /**
     * Finds the slot containing leather boots
     */
    private static int findLeatherBootsSlot(MinecraftClient client) {
        if (client.player == null) return -1;
        
        for (int i = 0; i < 9; i++) {
            ItemStack stack = client.player.getInventory().getStack(i);
            if (stack.getItem() == Items.LEATHER_BOOTS) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Uses leather boots and returns to fishing rod
     */
    private static void useLeatherBoots(MinecraftClient client, boolean rodWasInMainHand) throws InterruptedException {
        if (client.player == null || client.interactionManager == null) return;

        // Save current slot
        int currentSlot = client.player.getInventory().selectedSlot;
        previousSlot = currentSlot;

        // Find leather boots slot
        int bootsSlot = findLeatherBootsSlot(client);
        if (bootsSlot == -1) return;

        AutofishingMod.LOGGER.info("Found leather boots in slot " + bootsSlot + ", using them...");

        // Switch to leather boots slot
        client.player.getInventory().selectedSlot = bootsSlot;
        
        // Small delay for slot switch
        Thread.sleep(50 + random.nextInt(50)); // 50-100ms delay

        // Right-click with leather boots
        client.interactionManager.interactItem(
            client.player, 
            net.minecraft.util.Hand.MAIN_HAND
        );

        AutofishingMod.LOGGER.info("Used leather boots");

        // Add delay before switching back
        Thread.sleep(100 + random.nextInt(100)); // 100-200ms delay

        // Switch back to fishing rod
        if (rodWasInMainHand) {
            // Rod was in main hand, switch back to previous slot
            client.player.getInventory().selectedSlot = previousSlot;
        } else {
            // Rod is in off-hand, just switch back to previous slot
            client.player.getInventory().selectedSlot = previousSlot;
        }

        // Small delay after switching back
        Thread.sleep(50 + random.nextInt(50)); // 50-100ms delay

        AutofishingMod.LOGGER.info("Switched back to fishing rod");
    }

    /**
     * Slightly randomizes the player's crosshair to mimic human movement
     */
    private static void randomizeCrosshair(MinecraftClient client) {
        if (client.player == null) return;

        // Small random pitch and yaw adjustments
        float yawChange = (random.nextFloat() - 0.5f) * 2.0f; // -1 to +1 degrees
        float pitchChange = (random.nextFloat() - 0.5f) * 1.5f; // -0.75 to +0.75 degrees

        float newYaw = client.player.getYaw() + yawChange;
        float newPitch = client.player.getPitch() + pitchChange;

        // Clamp pitch to valid range (-90 to 90)
        newPitch = Math.max(-90.0f, Math.min(90.0f, newPitch));

        client.player.setYaw(newYaw);
        client.player.setPitch(newPitch);
    }
}

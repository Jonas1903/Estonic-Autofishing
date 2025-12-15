package com.estonic.autofishing;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.text.Text;

/**
 * Detects fishing cues by monitoring the game's rendering context
 */
public class FishingDetector {
    private static boolean fishingCueDetected = false;
    private static long lastDetectionTime = 0;
    private static final long DETECTION_COOLDOWN = 1000; // 1 second cooldown

    /**
     * Called from rendering mixin to check for fishing cues
     */
    public static void checkForFishingCue(DrawContext context, Text text) {
        if (text == null) return;
        
        String textContent = text.getString();
        
        // Check for "Reel it in!" text
        if (textContent.contains("Reel it in!")) {
            long currentTime = System.currentTimeMillis();
            if (currentTime - lastDetectionTime > DETECTION_COOLDOWN) {
                fishingCueDetected = true;
                lastDetectionTime = currentTime;
                AutofishingMod.LOGGER.info("Fishing cue detected!");
            }
        }
    }

    /**
     * Checks if the green exclamation mark is visible
     * This is typically rendered as part of the HUD
     */
    public static void checkForExclamationMark() {
        // This would be called from a mixin that intercepts particle or HUD rendering
        // For now, we rely primarily on text detection
    }

    public static boolean shouldAutoFish() {
        if (fishingCueDetected) {
            fishingCueDetected = false; // Reset flag
            return true;
        }
        return false;
    }

    public static void reset() {
        fishingCueDetected = false;
    }
}

package com.estonic.autofishing;

import net.fabricmc.fabric.api.client.message.v1.ClientReceiveMessageEvents;
import net.minecraft.text.Text;

/**
 * Detects fishing cues by monitoring overlay/action bar messages
 */
public class FishingDetector {
    private static boolean fishingCueDetected = false;
    private static long lastDetectionTime = 0;
    private static final long DETECTION_COOLDOWN = 1000; // 1 second cooldown

    /**
     * Register the message listener for overlay/action bar text
     */
    public static void registerMessageListener() {
        // Listen for overlay/action bar messages
        // Note: overlay parameter is true when the message is an overlay/action bar message
        ClientReceiveMessageEvents.GAME.register((message, overlay) -> {
            if (!overlay) {
                return; // Skip non-overlay messages, only process overlay/action bar messages
            }
            
            checkForFishingCue(message);
        });
        
        AutofishingMod.LOGGER.info("Registered fishing cue message listener");
    }

    /**
     * Check if the message contains fishing cues
     * Note: Looking for "Rell it in!" (with two L's) as specified in requirements
     */
    private static void checkForFishingCue(Text text) {
        if (text == null) return;
        
        String textContent = text.getString().toLowerCase(); // Case-insensitive
        
        // Check for "Rell it in!" text (case-insensitive, specific spelling per requirements)
        if (textContent.contains("rell it in!")) {
            long currentTime = System.currentTimeMillis();
            if (currentTime - lastDetectionTime > DETECTION_COOLDOWN) {
                fishingCueDetected = true;
                lastDetectionTime = currentTime;
                AutofishingMod.LOGGER.info("Fishing cue detected: {}", textContent);
            }
        }
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

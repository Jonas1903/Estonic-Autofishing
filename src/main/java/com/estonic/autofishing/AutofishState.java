package com.estonic.autofishing;

import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

/**
 * Manages the autofish toggle state
 * State is stored in memory during runtime, default is OFF on load
 */
public class AutofishState {
    private static boolean enabled = false; // Default OFF on load
    
    /**
     * Check if autofishing is currently enabled
     */
    public static boolean isEnabled() {
        return enabled;
    }
    
    /**
     * Toggle the autofishing state and show HUD message
     * @param client the Minecraft client instance
     * @return the new state (true = ON, false = OFF)
     */
    public static boolean toggle(MinecraftClient client) {
        enabled = !enabled;
        
        // Show HUD overlay message
        if (client.player != null) {
            String message = enabled ? "§aAutofishing ENABLED" : "§cAutofishing DISABLED";
            client.player.sendMessage(Text.literal(message), true); // true = overlay/action bar
        }
        
        AutofishingMod.LOGGER.info("Autofishing toggled: {}", enabled ? "ENABLED" : "DISABLED");
        return enabled;
    }
    
    /**
     * Set the autofishing state
     * @param state the new state
     */
    public static void setState(boolean state) {
        enabled = state;
    }
}

package com.estonic.autofishing;

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
     * Toggle the autofishing state
     * @return the new state (true = ON, false = OFF)
     */
    public static boolean toggle() {
        enabled = !enabled;
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

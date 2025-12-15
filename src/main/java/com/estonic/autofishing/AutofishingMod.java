package com.estonic.autofishing;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AutofishingMod implements ModInitializer {
    public static final String MOD_ID = "estonic-autofishing";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("Initializing Estonic Autofishing Mod");
        
        // Register tick event for fishing detection
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player != null && FishingDetector.shouldAutoFish()) {
                FishingAutomation.performFishingAction(client);
            }
        });
    }
}

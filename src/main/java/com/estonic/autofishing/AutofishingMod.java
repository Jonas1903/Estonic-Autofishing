package com.estonic.autofishing;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AutofishingMod implements ClientModInitializer {
    public static final String MOD_ID = "estonic-autofishing";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitializeClient() {
        LOGGER.info("Initializing Estonic Autofishing Mod");
        
        // Register the /autofish toggle command
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
            AutofishCommand.register(dispatcher);
        });
        
        // Register tick event for fishing detection (only when enabled)
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (AutofishState.isEnabled() && client.player != null && FishingDetector.shouldAutoFish()) {
                FishingAutomation.performFishingAction(client);
            }
        });
        
        LOGGER.info("Estonic Autofishing Mod initialized (default: OFF, use /autofish toggle to enable)");
    }
}

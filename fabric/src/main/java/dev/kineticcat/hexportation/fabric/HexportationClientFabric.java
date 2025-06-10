package dev.kineticcat.hexportation.fabric;

import dev.kineticcat.hexportation.HexportationClient;
import net.fabricmc.api.ClientModInitializer;

/**
 * Fabric client loading entrypoint.
 */
public class HexportationClientFabric implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        HexportationClient.init();
    }
}

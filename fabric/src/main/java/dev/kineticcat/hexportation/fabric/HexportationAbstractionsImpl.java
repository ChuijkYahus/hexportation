package dev.kineticcat.hexportation.fabric;

import dev.kineticcat.hexportation.HexportationAbstractions;
import net.fabricmc.loader.api.FabricLoader;

import java.nio.file.Path;

public class HexportationAbstractionsImpl {
    /**
     * This is the actual implementation of {@link HexportationAbstractions#getConfigDirectory()}.
     */
    public static Path getConfigDirectory() {
        return FabricLoader.getInstance().getConfigDir();
    }
	
    public static void initPlatformSpecific() {
        HexportationConfigFabric.init();
    }
}

package dev.kineticcat.hexportation.fabric.api.casting.iota;

import at.petrak.hexcasting.api.casting.iota.Iota;
import at.petrak.hexcasting.api.casting.iota.IotaType;
import at.petrak.hexcasting.common.lib.hex.HexIotaTypes;
import dev.kineticcat.hexportation.Hexportation;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;

import java.util.LinkedHashMap;
import java.util.Map;

public class HexportationIotaTypes {
    private static final Map<ResourceLocation, IotaType<?>> TYPES = new LinkedHashMap<>();

    public static void init() {
        for (Map.Entry<ResourceLocation, IotaType<?>> entry : TYPES.entrySet()) {
            Registry.register(HexIotaTypes.REGISTRY, entry.getKey(), entry.getValue());
        }
    }

    public static final IotaType<ConduitIota> CONDUIT = type("conduit", ConduitIota.TYPE);
    public static final IotaType<StorageViewIota> STORAGEVIEW = type("storageview", StorageViewIota.TYPE);

    private static <U extends Iota, T extends IotaType<U>> T type(String name, T type) {
        IotaType<?> old = TYPES.put(new ResourceLocation(Hexportation.MOD_ID, name), type);
        if (old != null) {
            throw new IllegalArgumentException("duplicate id :" + name + ", dingus");
        }
        return type;
    }
}

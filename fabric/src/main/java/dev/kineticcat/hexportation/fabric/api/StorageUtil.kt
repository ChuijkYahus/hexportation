package dev.kineticcat.hexportation.fabric.api

import at.petrak.hexcasting.api.casting.mishaps.MishapInvalidIota
import dev.kineticcat.hexportation.fabric.api.casting.iota.ConduitIota.Conduit
import dev.kineticcat.hexportation.fabric.casting.actions.OpSendThing
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant
import net.fabricmc.fabric.api.transfer.v1.item.ItemVariant
import net.fabricmc.fabric.api.transfer.v1.storage.StorageUtil
import net.fabricmc.fabric.api.transfer.v1.storage.StorageView
import net.minecraft.server.level.ServerLevel
import team.reborn.energy.api.EnergyStorage
import team.reborn.energy.api.EnergyStorageUtil
import java.util.logging.Level

class Storage(val conduit: Conduit, val sLevel: ServerLevel) {
    enum class Modes {
        ITEM, FLUID, ENERGY
    }
    val mode = getModeOrNull()

    fun getModeOrNull(): Modes? {
        return when {
            conduit.getItemStoragesOrNull(sLevel) != null -> Modes.ITEM
            conduit.getFluidStoragesOrNull(sLevel) != null -> Modes.FLUID
            conduit.getEnergyStoragesOrNull(sLevel) != null -> Modes.ENERGY
            else -> null
        }
    }
    fun move(amt: Long) {
        when (mode) {
            Modes.ITEM -> {
                val (source, sink) = conduit.getItemStoragesOrNull(sLevel)
                StorageUtil.move(source, sink, { true }, amt, null)
            }
            Modes.FLUID -> {
                val (source, sink) = conduit.getFluidStoragesOrNull(sLevel)
                StorageUtil.move(source, sink, { true }, amt, null)
            }
            Modes.ENERGY -> {
                val (source, sink) = conduit.getEnergyStoragesOrNull(sLevel)
                EnergyStorageUtil.move(source, sink, amt, null)
            }
            null -> {} // should probably throw but eh
        }
    }
    fun getSourceEnergyStorageOrNull(sink: Boolean = false): EnergyStorage =
             if (sink) conduit.getEnergyStoragesOrNull(sLevel).second else conduit.getEnergyStoragesOrNull(sLevel).first
    fun getSourceItemIterator(sink: Boolean = false): Iterator<StorageView<ItemVariant>> =
            (if (sink) conduit.getItemStoragesOrNull(sLevel).second else conduit.getItemStoragesOrNull(sLevel).first).iterator()
    fun getSourceFluidIterator(sink: Boolean = false): Iterator<StorageView<FluidVariant>> =
            (if (sink) conduit.getFluidStoragesOrNull(sLevel).second else conduit.getFluidStoragesOrNull(sLevel).first).iterator()
}
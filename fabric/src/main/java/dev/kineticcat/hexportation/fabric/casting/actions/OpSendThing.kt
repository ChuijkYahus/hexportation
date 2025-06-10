package dev.kineticcat.hexportation.fabric.casting.actions

import at.petrak.hexcasting.api.casting.*
import at.petrak.hexcasting.api.casting.castables.SpellAction
import at.petrak.hexcasting.api.casting.eval.CastingEnvironment
import at.petrak.hexcasting.api.casting.iota.Iota
import at.petrak.hexcasting.api.casting.mishaps.MishapInvalidIota
import at.petrak.hexcasting.api.misc.MediaConstants
import dev.kineticcat.hexportation.fabric.api.Storage
import dev.kineticcat.hexportation.fabric.api.add
import dev.kineticcat.hexportation.fabric.api.asVec3
import dev.kineticcat.hexportation.fabric.api.casting.iota.ConduitIota.Conduit
import dev.kineticcat.hexportation.fabric.api.getConduit
import net.fabricmc.fabric.api.transfer.v1.storage.StorageUtil

import team.reborn.energy.api.EnergyStorage
import team.reborn.energy.api.EnergyStorageUtil


object OpSendThing : SpellAction {
    override val argc = 2
    private var cost = 0L

    enum class MODES {
        ITEM, FLUID, ENERGY
    }

    override fun execute(args: List<Iota>, env: CastingEnvironment): SpellAction.Result {
        var conduit = args.getConduit(0)
        var amt = args.getPositiveInt(1).toLong()

//        var mode = when {
//            conduit.getItemStoragesOrNull(env.world) != null -> MODES.ITEM
//            conduit.getFluidStoragesOrNull(env.world) != null -> MODES.FLUID
//            conduit.getEnergyStoragesOrNull(env.world) != null -> MODES.ENERGY
//            else -> throw MishapInvalidIota.of(args[0], 0, "conduit")
//        }
//        var storage = StorageUtil.getStorage()
        var storage = Storage(conduit, env.world)
        storage.mode ?: throw MishapInvalidIota.of(args[0], 0, "invalid_conduit")
        return SpellAction.Result(
                Spell(storage, amt),
                MediaConstants.DUST_UNIT * amt,
                listOf(
                        ParticleSpray(
                                conduit.source.center.add(conduit.sourceDir.normal.asVec3().scale(.5)),
                                conduit.sourceDir.normal.asVec3().scale(0.8),
                                0.1, 0.3, 20
                        ),
                        ParticleSpray(
                                conduit.sink.center.add(conduit.sinkDir.normal),
                                conduit.sinkDir.normal.asVec3().scale(-0.8),
                                0.1, 0.3, 20
                        )
                )
        )
    }

    private data class Spell(var storage: Storage, var amt: Long) : RenderedSpell {
        override fun cast(env: CastingEnvironment) {
            storage.move(amt)
        }
    }
}

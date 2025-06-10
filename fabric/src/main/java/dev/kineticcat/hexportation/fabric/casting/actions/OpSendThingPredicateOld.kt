//package dev.kineticcat.hexportation.fabric.casting.actions
//
//import at.petrak.hexcasting.api.casting.SpellList
//import at.petrak.hexcasting.api.casting.castables.Action
//import at.petrak.hexcasting.api.casting.eval.CastingEnvironment
//import at.petrak.hexcasting.api.casting.eval.OperationResult
//import at.petrak.hexcasting.api.casting.eval.vm.CastingImage
//import at.petrak.hexcasting.api.casting.eval.vm.SpellContinuation
//import at.petrak.hexcasting.api.casting.evaluatable
//import at.petrak.hexcasting.api.casting.mishaps.MishapInvalidIota
//import at.petrak.hexcasting.common.lib.hex.HexEvalSounds
//import dev.kineticcat.hexportation.fabric.api.casting.FramePredicate
//import dev.kineticcat.hexportation.fabric.api.getConduit
//
//object OpSendThingPredicateOld : Action {
//
//    enum class MODES {
//        ITEM, FLUID, ENERGY
//    }
//
//    override fun operate(env: CastingEnvironment, image: CastingImage, continuation: SpellContinuation): OperationResult {
//        var stack = image.stack.toMutableList()
//        var args = stack.takeLast(2)
//        var conduit = args.getConduit(0)
//        val code = evaluatable(stack[stack.lastIndex], 1).map({SpellList.LList(0, listOf(it))}, {it})
//        stack.removeLastOrNull()
//        stack.removeLastOrNull()
//        var mode = when {
//            conduit.getItemStoragesOrNull(env.world) != null -> MODES.ITEM
//            conduit.getFluidStoragesOrNull(env.world) != null -> MODES.FLUID
//            conduit.getEnergyStoragesOrNull(env.world) != null -> MODES.ENERGY
//            else -> throw MishapInvalidIota.of(args[0], 0, "conduit")
//        }
//
//        when (mode) {
//            MODES.ITEM -> {
//
////                var trans = Transaction.openOuter()
//                // honestly, if its null *now* i think there's bigger problems
//                val (source, sink) = conduit.getItemStoragesOrNull(env.world)
//                // why the fuck are you trying to extract from something with no slots???
//                if (!source.iterator().hasNext()) {
//                    return OperationResult(
//                            image.withUsedOp().withResetEscape().copy(stack = stack),
//                            listOf(), continuation, HexEvalSounds.NORMAL_EXECUTE
//                    )
//                }
//                return OperationResult(
//                        image.withUsedOp().withResetEscape().copy(stack = stack),
//                        listOf(),
//                        continuation.pushFrame(FramePredicate(conduit, -1, mode, code, null)),
//                        HexEvalSounds.THOTH
//                )
//
//            }
////            MODES.FLUID -> {}
////            MODES.ENERGY -> {}
//            else -> throw NotImplementedError() // :3
//        }
//    }
//}
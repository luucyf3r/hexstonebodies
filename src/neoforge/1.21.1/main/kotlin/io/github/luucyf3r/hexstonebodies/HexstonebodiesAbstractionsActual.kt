@file:JvmName("HexstonebodiesAbstractionsActual")

package io.github.luucyf3r.hexstonebodies

import io.github.luucyf3r.hexstonebodies.registry.HexstonebodiesRegistrar
import net.msrandom.multiplatform.annotations.Actual
import net.neoforged.neoforge.registries.RegisterEvent

actual fun <T : Any> initRegistry(registrar: HexstonebodiesRegistrar<T>) {
        NeoForgeHexstonebodies.container.eventBus!!.addListener { event: RegisterEvent ->
            event.register(registrar.registryKey) { helper ->
                registrar.init(helper::register)
            }
        }
}
@file:JvmName("HexstonebodiesAbstractionsActual")

package io.github.luucyf3r.hexstonebodies

import io.github.luucyf3r.hexstonebodies.registry.HexstonebodiesRegistrar
import net.minecraft.core.Registry
import net.msrandom.multiplatform.annotations.Actual

actual fun <T : Any> initRegistry(registrar: HexstonebodiesRegistrar<T>) {
    val registry = registrar.registry
    registrar.init { id, value -> Registry.register(registry, id, value) }
}

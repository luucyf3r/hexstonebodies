@file:JvmName("HexstonebodiesAbstractions")

package io.github.luucyf3r.hexstonebodies

import io.github.luucyf3r.hexstonebodies.registry.HexstonebodiesRegistrar

fun initRegistries(vararg registries: HexstonebodiesRegistrar<*>) {
    for (registry in registries) {
        initRegistry(registry)
    }
}

expect fun <T : Any> initRegistry(registrar: HexstonebodiesRegistrar<T>)

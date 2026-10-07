package io.github.luucyf3r.hexstonebodies

import net.neoforged.fml.event.lifecycle.FMLDedicatedServerSetupEvent

object NeoForgeHexstonebodiesServer {
    @Suppress("UNUSED_PARAMETER")
    fun init(event: FMLDedicatedServerSetupEvent) {
        Hexstonebodies.initServer()
    }
}


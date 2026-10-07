package io.github.luucyf3r.hexstonebodies

import net.fabricmc.api.DedicatedServerModInitializer

object FabricHexstonebodiesServer : DedicatedServerModInitializer {
    override fun onInitializeServer() {
        Hexstonebodies.initServer()
    }
}

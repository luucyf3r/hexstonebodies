package io.github.luucyf3r.hexstonebodies.client

import io.github.luucyf3r.hexstonebodies.client.HexstonebodiesClient
import net.fabricmc.api.ClientModInitializer

object FabricHexstonebodiesClient : ClientModInitializer {
    override fun onInitializeClient() {
        HexstonebodiesClient.init()
    }
}
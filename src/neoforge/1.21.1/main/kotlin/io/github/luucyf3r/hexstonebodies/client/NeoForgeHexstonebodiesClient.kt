package io.github.luucyf3r.hexstonebodies.client

import io.github.luucyf3r.hexstonebodies.client.HexstonebodiesClient
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent
import net.neoforged.neoforge.client.gui.IConfigScreenFactory
import thedarkcolour.kotlinforforge.neoforge.forge.LOADING_CONTEXT

object NeoForgeHexstonebodiesClient {
    @Suppress("UNUSED_PARAMETER")
    fun init(event: FMLClientSetupEvent) {
        HexstonebodiesClient.init()
    }
}
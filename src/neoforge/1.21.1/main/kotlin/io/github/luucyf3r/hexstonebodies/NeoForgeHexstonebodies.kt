package io.github.luucyf3r.hexstonebodies

import io.github.luucyf3r.hexstonebodies.client.NeoForgeHexstonebodiesClient
import io.github.luucyf3r.hexstonebodies.datagen.NeoForgeHexstonebodiesDatagen
import net.neoforged.bus.api.IEventBus
import net.neoforged.fml.ModContainer
import net.neoforged.fml.ModList
import net.neoforged.fml.common.Mod

@Mod(Hexstonebodies.MODID)
class NeoForgeHexstonebodies(modBus: IEventBus, container: ModContainer) {
    init {
        modBus.apply {
            addListener(NeoForgeHexstonebodiesClient::init)
            addListener(NeoForgeHexstonebodiesDatagen::init)
            addListener(NeoForgeHexstonebodiesServer::init)
        }
        Hexstonebodies.init()
    }

    companion object {
        internal val container: ModContainer
            get() = ModList.get().getModContainerById(Hexstonebodies.MODID).get()
    }
}

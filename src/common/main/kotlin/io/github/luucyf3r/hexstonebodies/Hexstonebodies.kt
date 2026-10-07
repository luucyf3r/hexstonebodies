package io.github.luucyf3r.hexstonebodies

import io.github.luucyf3r.hexstonebodies.config.HexstonebodiesConfigs
import net.minecraft.resources.ResourceLocation
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import io.github.luucyf3r.hexstonebodies.networking.HexstonebodiesNetworking
import io.github.luucyf3r.hexstonebodies.registry.HexstonebodiesActions

object Hexstonebodies {
    const val MODID = "hexstonebodies"

    @JvmField
    val LOGGER: Logger = LogManager.getLogger(MODID)



    @JvmStatic
    fun id(path: String): ResourceLocation = ResourceLocation.fromNamespaceAndPath(MODID, path)

    fun init() {
        initRegistries(
            HexstonebodiesActions,
        )
        HexstonebodiesNetworking.init()
        HexstonebodiesConfigs.init()
    }

    fun initServer() {
    }
}

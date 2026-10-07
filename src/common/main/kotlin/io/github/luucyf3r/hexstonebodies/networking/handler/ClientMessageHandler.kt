package io.github.luucyf3r.hexstonebodies.networking.handler

import io.github.luucyf3r.hexstonebodies.networking.msg.*
import io.wispforest.owo.network.ClientAccess
import net.minecraft.client.Minecraft

fun HexstonebodiesMessageS2C.applyOnClient(access: ClientAccess) = Minecraft.getInstance().execute {
    // NOTE: this is commented out because otherwise it fails to compile if there's nothing inside of the when expression
    /*
    when (this) {
        is MsgExampleNameS2C -> {
           handleMessage(...)
        }
        // add client-side message handlers here
    }
    */
}

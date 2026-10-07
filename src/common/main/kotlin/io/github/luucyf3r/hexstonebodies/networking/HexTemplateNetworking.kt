package io.github.luucyf3r.hexstonebodies.networking

import io.github.luucyf3r.hexstonebodies.Hexstonebodies
import io.github.luucyf3r.hexstonebodies.networking.msg.HexstonebodiesMessageCompanion
import io.wispforest.owo.network.OwoNetChannel

object HexstonebodiesNetworking {
    val CHANNEL: OwoNetChannel = OwoNetChannel.create(Hexstonebodies.id("networking_channel"))

    fun init() {
        for (subclass in HexstonebodiesMessageCompanion::class.sealedSubclasses) {
            subclass.objectInstance?.register(CHANNEL)
        }
    }
}

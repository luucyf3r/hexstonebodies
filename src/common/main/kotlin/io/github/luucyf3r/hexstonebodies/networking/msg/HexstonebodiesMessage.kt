package io.github.luucyf3r.hexstonebodies.networking.msg

import io.github.luucyf3r.hexstonebodies.Hexstonebodies
import io.github.luucyf3r.hexstonebodies.networking.HexstonebodiesNetworking
import io.github.luucyf3r.hexstonebodies.networking.handler.applyOnClient
import io.github.luucyf3r.hexstonebodies.networking.handler.applyOnServer
import io.wispforest.owo.network.ClientAccess
import io.wispforest.owo.network.OwoNetChannel
import io.wispforest.owo.network.ServerAccess
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.server.level.ServerPlayer

sealed interface HexstonebodiesMessage

sealed interface HexstonebodiesMessageC2S : HexstonebodiesMessage {
    fun <T> T.sendToServer() where T : Record {
        HexstonebodiesNetworking.CHANNEL.clientHandle().send(this)
    }
}

sealed interface HexstonebodiesMessageS2C : HexstonebodiesMessage {
}

fun <T> T.sendToPlayer(player: ServerPlayer) where T : Record {
    HexstonebodiesNetworking.CHANNEL.serverHandle(player).send( this)
}

fun <T> T.sendToPlayers(players: Iterable<ServerPlayer>) where T : Record {
    players.forEach { sendToPlayer(it) }
}

sealed interface HexstonebodiesMessageCompanion<T> where T : HexstonebodiesMessage, T : Record {
    val type: Class<T>

    fun apply(msg: T, access: ServerAccess): Unit {
        Hexstonebodies.LOGGER.debug("Server received packet from {}: {}", access.player().name.string, this)
        when (msg) {
            is HexstonebodiesMessageC2S -> msg.applyOnServer(access)
            else -> Hexstonebodies.LOGGER.warn("Message not handled on server: {}", msg::class)
        }
    }

    fun apply(msg: T, access: ClientAccess): Unit {
        Hexstonebodies.LOGGER.debug("Client received packet: {}", this)
        when (msg) {
            is HexstonebodiesMessageS2C -> msg.applyOnClient(access)
            else -> Hexstonebodies.LOGGER.warn("Message not handled on client: {}", msg::class)
        }
    }

    fun register(channel: OwoNetChannel) {
        channel.registerServerbound(type) { msg, access -> apply(msg, access) }
    }
}

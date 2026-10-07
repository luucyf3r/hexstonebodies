package io.github.luucyf3r.hexstonebodies.networking.msg

@JvmRecord
data class MsgExampleS2C(val payload: Int) : HexstonebodiesMessageS2C {
   companion object : HexstonebodiesMessageCompanion<MsgExampleS2C> {
       override val type = MsgExampleS2C::class.java
   }
}

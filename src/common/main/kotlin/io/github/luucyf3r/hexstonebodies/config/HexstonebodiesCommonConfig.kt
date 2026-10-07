package io.github.luucyf3r.hexstonebodies.config

import io.github.luucyf3r.hexstonebodies.Hexstonebodies
import me.fzzyhmstrs.fzzy_config.config.Config

// guide: https://moddedmc.wiki/en/project/fzzy-config/latest/docs/config-design/New-Configs#2-config-creation
class HexstonebodiesCommonConfig : Config(Hexstonebodies.id("common_config")) {

    var testValue = 1.5

}
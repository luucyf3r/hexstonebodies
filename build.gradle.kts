import org.jetbrains.kotlin.gradle.dsl.KotlinVersion
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import at.petrak.pkpcpbp.filters.FlatteningJson5Transmogrifier
import at.petrak.pkpcpbp.filters.Json5Transmogrifier

plugins {
    alias(libs.plugins.cloche)
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.yamlang)
    alias(libs.plugins.pkJson5)
}

val modVersion: String by project
val mavenGroup: String by project

version = modVersion
group = mavenGroup

kotlin {
    jvmToolchain(21)
}


repositories {
    mavenLocal()
    mavenCentral()

    cloche {
        mavenNeoforgedMeta()
        mavenNeoforged()
        mavenFabric()
        mavenParchment()
        librariesMinecraft()
        main()
    }

    maven("https://thedarkcolour.github.io/KotlinForForge")

    // Hex Casting, Paucal
    maven("https://maven.blamejared.com")

    // Cardinal Components
    maven("https://maven.ladysnake.org/releases")

    // Cloth Config
    maven("https://maven.shedaniel.me")

    // Mod Menu
    maven("https://maven.terraformersmc.com/releases")

    // Forgified Fabric Api
    maven("https://maven.su5ed.dev/releases")

    // Caelus
    maven("https://maven.theillusivec4.top/")

    // Accessories, oωo Lib
    maven("https://maven.wispforest.io/releases")

    maven("https://jitpack.io")

    // Fzzy Config
    maven("https://maven.fzzyhmstrs.me/")

    // for deps that are not hosted on any maven repository
    flatDir { dir(rootProject.file("libs")) }

    exclusiveContent {
        filter {
            includeGroup("maven.modrinth")
        }
        forRepository {
            maven { url = uri("https://api.modrinth.com/maven") }
        }
    }
}

cloche {
    metadata {
        modId = "hexstonebodies"
        name = "Hex Corpora Lapidea"
        description = "Hex addon adding extra vessels for your mind to inhabit"
        license = "MIT"

        author("luucyf3r")
        url = "https://luucyf3r.github.io/hexstonebodies"
        sources = "https://github.com/luucyf3r/hexstonebodies"
        issues = "https://github.com/luucyf3r/hexstonebodies/issues"
    }

    common {
        accessWideners.from(file("src/common/hexstonebodies.accesswidener"))
        mixins.from(file("src/common/hexstonebodies.mixins.json"))

        mappings {
            official()
            parchment("2024.11.17")
        }

        dependencies {
            // the parts of mods that are the same in all sourceSets
            // will automatically be available in the common sourceSet
        }
        
        metadata {
            dependencies {
                dependency {
                    modId = "hexcasting"
                    version(libs.versions.hexcasting.get())
                }
            }
        }
    }

    fabric("fabric:1.21.1") {
        loaderVersion = libs.versions.fabric.loader
        minecraftVersion = libs.versions.minecraft

        mixins.from(file("src/fabric/1.21.1/hexstonebodies-fabric.mixins.json"))
        includedClient()
        data()

        mappings {
            official()
            parchment("2024.11.17")
        }

        dependencies {
            fabricApi(libs.versions.fabric.api)
            modImplementation(libs.kotlin.fabric)

            // hex casting and dependencies
            libs.bundles.hexcasting.fabric.get().forEach {
                modImplementation(it)
            }
            libs.bundles.cardinalComponents.get().forEach {
                modImplementation(it)
            }
            modRuntimeOnly(libs.architectury.fabric)

            modImplementation(libs.modMenu)
            modImplementation(libs.owoLib.fabric)
            modImplementation(libs.fzzyConfig.fabric)
        }

        metadata {
            entrypoint("main") {
                value = "io.github.luucyf3r.hexstonebodies.FabricHexstonebodies"
                adapter = "kotlin"
            }
            entrypoint("client") {
                value = "io.github.luucyf3r.hexstonebodies.client.FabricHexstonebodiesClient"
                adapter = "kotlin"
            }
            entrypoint("fabric-datagen") {
                value = "io.github.luucyf3r.hexstonebodies.datagen.FabricHexstonebodiesDatagen"
                adapter = "kotlin"
            }
            dependencies {
                dependency {
                    modId = "owo"
                    version(libs.versions.owoLib.fabric.get())
                }
                dependency {
                    modId = "fzzy_config"
                    version(libs.versions.fzzyConfig.fabric.get())
                }
            }
        }

        runs {
            client {
                jvmArguments.add("-XX:+AllowEnhancedClassRedefinition")
            }
            server()
            data()
        }
    }

    neoforge("neoforge:1.21.1") {
        loaderVersion = libs.versions.neoforge
        minecraftVersion = libs.versions.minecraft

        mixins.from(file("src/neoforge/1.21.1/hexstonebodies-neoforge.mixins.json"))
        data()

        mappings {
            official()
            parchment("2024.11.17")
        }

        dependencies {
            modImplementation(libs.forgifiedFabricApi)
            modImplementation(libs.kotlin.forge)

            // hex casting and dependencies
            libs.bundles.hexcasting.neoforge.get().forEach {
                modImplementation(it)
            }
            modRuntimeOnly(libs.architectury.neoforge)

            modImplementation(libs.owoLib.neoforge)
            modImplementation(libs.fzzyConfig.neoforge)
        }

        metadata {
            dependencies {
                dependency {
                    modId = "owo"
                    version(libs.versions.owoLib.neoforge.get())
                }
                dependency {
                    modId = "fzzy_config"
                    version(libs.versions.fzzyConfig.neoforge.get())
                }
            }
        }

        runs {
            client {
                jvmArguments.add("-XX:+AllowEnhancedClassRedefinition")
            }
            server()
            data()
        }
    }
}

tasks.withType<KotlinCompile>().configureEach {
    compilerOptions {
        languageVersion = KotlinVersion.KOTLIN_2_2
        freeCompilerArgs.addAll(
            "-Xmulti-platform",
            "-Xno-check-actual",
            "-Xexpect-actual-classes",
        )
    }
}


// yaml language files
// https://github.com/Fallen-Breath/yamlang/tree/master
yamlang {
    targetSourceSets = listOf(sourceSets["fabric1211"], sourceSets["neoforge1211"])
    inputDir = "assets/hexstonebodies/lang"
    owolibRichTranslations = true
}
tasks.named("runFabric1211Data").configure {
    dependsOn(tasks.named("yamlangConvertFabric1211Resources"))
}
tasks.named("runNeoforge1211Data").configure {
    dependsOn(tasks.named("yamlangConvertNeoforge1211Resources"))
}

// json5 support
pkJson5 {
    autoProcessJson5 = true
    autoProcessJson5Flattening = true
}

// disable the default compile tasks so the build task works properly
// (cloche doesn't use them to build the jars)
tasks.named("compileKotlin") {
    enabled = false
}
tasks.named("compileJava") {
    enabled = false
}

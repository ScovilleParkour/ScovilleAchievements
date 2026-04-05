package dev.meluhdy.scovilleAchievements

import dev.meluhdy.melodia.MelodiaPlugin
import dev.meluhdy.melodia.command.MelodiaCommand
import dev.meluhdy.melodia.manager.MelodiaSavingManager
import dev.meluhdy.melodia.utils.ConsoleLogger
import dev.meluhdy.melodia.utils.LoggingUtils
import dev.meluhdy.melodia.utils.TranslationFolder
import dev.meluhdy.scoville.achievement.AchievementManager
import dev.meluhdy.scovilleAchievements.core.AchievementEarnerManager
import dev.meluhdy.scovilleAchievements.event.listener.BroadcastListener
import org.bukkit.event.Listener
import java.util.Locale

class ScovilleAchievements : MelodiaPlugin() {

    companion object {
        lateinit var plugin: MelodiaPlugin
    }

    init {
        plugin = this
    }

    override val melodiaCommands: Array<MelodiaCommand> = arrayOf()
    override val resourceFiles: Array<String> = arrayOf(
        "lang/en.properties",
        "lang/de.properties",
        "lang/ja.properties",
        "lang/pl.properties"
    )
    override val listeners: Array<Listener> = arrayOf(
        BroadcastListener
    )
    override val translationFolder: TranslationFolder = TranslationFolder("lang", Locale.of("en"))
    override val logger: ConsoleLogger = ConsoleLogger("ScovilleAchievements", LoggingUtils.ConsoleLevel.DEBUG)
    override val savingManagers: Array<MelodiaSavingManager<*>> = arrayOf(
        AchievementManager,
        AchievementEarnerManager
    )

}

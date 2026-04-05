package dev.meluhdy.scovilleAchievements.event.listener

import dev.meluhdy.melodia.utils.TextUtils
import dev.meluhdy.melodia.utils.TranslatedString
import dev.meluhdy.scovilleAchievements.ScovilleAchievements
import dev.meluhdy.scovilleAchievements.event.event.GrantAchievementEvent
import net.kyori.adventure.text.Component.text
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener

object BroadcastListener: Listener {

    @EventHandler
    fun on(e: GrantAchievementEvent) {
        TextUtils.broadcastChat(ScovilleAchievements.plugin, "chat.achievement.broadcast", text(e.ach.diff.color), text(e.player.name), TranslatedString(e.ach.nameId, arrayOf()))
    }

}
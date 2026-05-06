package dev.meluhdy.scovilleAchievements.core

import dev.meluhdy.melodia.manager.MelodiaItem
import dev.meluhdy.melodia.misc.serialization.MelodiaSerializer
import dev.meluhdy.melodia.misc.serialization.SerializerElement
import dev.meluhdy.scoville.achievement.Achievement
import dev.meluhdy.scovilleAchievements.achievement.AchievementManager
import dev.meluhdy.scoville.core.parkourer.Parkourer
import dev.meluhdy.scoville.core.parkourer.ParkourerManager
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.serializer
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import java.util.UUID

class AchievementEarner(uuid: UUID) : MelodiaItem(uuid) {

    object EarnerSerializer : MelodiaSerializer<AchievementEarner>() {

        class EarnerBuilder : Builder<AchievementEarner>() {

            val achievements: MutableList<String> = mutableListOf()

            override fun build(): AchievementEarner {
                val out = AchievementEarner(uuid)
                out.achievements.addAll(achievements)
                return out
            }

        }

        override val builder: Builder<AchievementEarner> = EarnerBuilder()
        override val steps: Array<SerializerElement<*, AchievementEarner>> = arrayOf(
            SerializerElement("achievements", ListSerializer(String.serializer()), { it.getAchievementList().map { achievement -> achievement.achievementId } }, { list, builder -> (builder as EarnerBuilder).achievements.addAll(list) })
        )

    }

    val achievements: MutableList<String> = mutableListOf()

    fun hasAchievement(achievement: Achievement<*>) = hasAchievement(achievement.achievementId)
    fun hasAchievement(achievementID: String) = achievements.contains(achievementID)

    fun grantAchievement(achievement: Achievement<*>) {
        achievements.add(achievement.achievementId)
    }
    fun grantAchievement(achievement: String) {
        val ach = AchievementManager.get(achievement) ?: throw IllegalArgumentException("Achievement $achievement not found")
        grantAchievement(ach)
    }

    fun getAchievementList(): List<Achievement<*>> = achievements.mapNotNull { achievement -> AchievementManager.get(achievement) }

    fun toPlayer(): Player? = Bukkit.getPlayer(uuid)
    fun toParkourer(): Parkourer? = ParkourerManager.get(uuid)

}

fun Parkourer.toAchievementEarner(): AchievementEarner = AchievementEarnerManager.getOrCreate(uuid) { AchievementEarner(uuid) }
package dev.meluhdy.scovilleAchievements.achievement.easy

import dev.meluhdy.melodia.utils.ItemUtils
import dev.meluhdy.scoville.achievement.Achievement
import dev.meluhdy.scoville.core.course.CourseManager
import dev.meluhdy.scoville.core.parkourer.ParkourerManager
import dev.meluhdy.scoville.event.event.CourseCompleteEvent
import org.bukkit.Color
import org.bukkit.FireworkEffect
import org.bukkit.entity.Player
import org.bukkit.event.EventPriority
import org.bukkit.inventory.ItemStack

object GlobalDominationAchievement : Achievement<CourseCompleteEvent>(EventPriority.LOWEST) {

    override val achievementId: String = "globalDomination"
    override val nameId: String = "achievement.global_domination.name"
    override val descId: String = "achievement.global_domination.desc"
    override val diff: AchievementDifficulty = AchievementDifficulty.EASY
    override val baseStack: ItemStack = ItemUtils.createSkull(
        "https://textures.minecraft.net/texture/fc1e73023352cbc77b896fe7ea242b43143e013bec5bf314d41e5f26548fb2d2"
    )

    override fun check(event: CourseCompleteEvent): Boolean {
        val a = CourseManager.get("World of A") ?: return false
        val b = CourseManager.get("World of B") ?: return false
        val c = CourseManager.get("World of C") ?: return false
        val d = CourseManager.get("World of D") ?: return false

        val player = event.player
        val parkourer = ParkourerManager.get(player) ?: return false

        return parkourer.hasCompletedCourse(a) &&
            parkourer.hasCompletedCourse(b) &&
            parkourer.hasCompletedCourse(c) &&
            parkourer.hasCompletedCourse(d)
    }

    override fun onAchievementGet(p: Player) = spawnFirework(p, FireworkEffect.builder()
        .with(FireworkEffect.Type.BALL)
        .flicker(false)
        .trail(false)
        .withColor(Color.fromRGB(245, 120, 25))
        .withColor(Color.fromRGB(50, 55, 155))
        .withColor(Color.fromRGB(20, 140, 145))
        .withColor(Color.fromRGB(85, 25, 105))
        .build()
    )

}
package dev.meluhdy.scoville.achievement.easy

import dev.meluhdy.melodia.utils.ItemUtils
import dev.meluhdy.scoville.achievement.Achievement
import dev.meluhdy.scoville.core.course.courses.RankupCourse
import dev.meluhdy.scoville.core.parkourer.ParkourerManager
import dev.meluhdy.scoville.event.event.CourseCompleteEvent
import dev.meluhdy.scoville.misc.PermissionUtils
import org.bukkit.entity.Player
import org.bukkit.event.EventPriority
import org.bukkit.inventory.ItemStack

object AddingSomeFlavorAchievement: Achievement<CourseCompleteEvent>(EventPriority.LOWEST) {

    override val achievementId: String = "addingSomeFlavor"
    override val nameId: String = "achievement.adding_some_flavor.name"
    override val descId: String = "achievement.adding_some_flavor.desc"
    override val diff: AchievementDifficulty = AchievementDifficulty.EASY
    override val baseStack: ItemStack = ItemUtils.createSkull(
        "https://textures.minecraft.net/texture/c9cb4a11c6891d663533efa0d0ec6b9e2e3a550e81eeeae4b8a074306784aa"
    )

    override fun check(event: CourseCompleteEvent): Boolean {
        val player = event.player
        val parkourer = ParkourerManager.get(player) ?: return false
        val rank = PermissionUtils.getRank(parkourer)

        return rank > RankupCourse.Rank.GUAJILLO
    }

    override fun onAchievementGet(p: Player) {
        p.sendMessage("You stupid bitch")
    }

}
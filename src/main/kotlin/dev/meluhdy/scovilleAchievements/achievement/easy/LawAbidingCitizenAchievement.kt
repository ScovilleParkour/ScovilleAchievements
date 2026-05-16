package dev.meluhdy.scovilleAchievements.achievement.easy

import dev.meluhdy.melodia.utils.ItemUtils
import dev.meluhdy.scoville.achievement.Achievement
import org.bukkit.Bukkit
import org.bukkit.Color
import org.bukkit.FireworkEffect
import org.bukkit.Location
import org.bukkit.entity.Player
import org.bukkit.event.EventPriority
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.ItemStack
import kotlin.math.roundToInt

object LawAbidingCitizenAchievement : Achievement<PlayerInteractEvent>(EventPriority.LOWEST) {

    override val achievementId: String = "lawAbidingCitizen"
    override val nameId: String = "achievement.law_abiding_citizen.name"
    override val descId: String = "achievement.law_abiding_citizen.desc"
    override val diff: AchievementDifficulty = AchievementDifficulty.EASY
    override val baseStack: ItemStack = ItemUtils.createSkull(
        "https://textures.minecraft.net/texture/ec80f867d9c80f69e1b0a36abd5dc6d636bfad2b62df32e0a081bfe911d755d2"
    )

    override fun check(event: PlayerInteractEvent): Boolean {
        if (event.action != Action.RIGHT_CLICK_BLOCK) return false
        val block = event.clickedBlock ?: return false
        val loc = block.location
        return loc.world.name == "courses_released" &&
                loc.x.roundToInt() == 30000 &&
                loc.y.roundToInt() == 51 &&
                loc.z.roundToInt() == 100147
    }

    override fun onAchievementGet(p: Player) = spawnFirework(p, FireworkEffect.builder()
        .with(FireworkEffect.Type.BALL)
        .flicker(false)
        .trail(false)
        .withColor(Color.fromRGB(254, 216, 61))
        .build()
    )

}
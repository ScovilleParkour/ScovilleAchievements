package dev.meluhdy.scovilleAchievements.achievement

import dev.meluhdy.melodia.manager.MelodiaSavingManager
import dev.meluhdy.melodia.misc.serialization.MelodiaSerializer
import dev.meluhdy.scoville.achievement.Achievement
import dev.meluhdy.scovilleAchievements.achievement.easy.AddingSomeFlavorAchievement
import dev.meluhdy.scovilleAchievements.ScovilleAchievements
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonPrimitive
import java.io.File

object AchievementManager : MelodiaSavingManager<Achievement<*>>() {

    private val achievements: Array<Achievement<*>> = arrayOf(
        AddingSomeFlavorAchievement
    )

    val baseFolder
        get() = "${ScovilleAchievements.plugin.dataFolder}${File.separator}achievements"

    fun get(achievementID: String): Achievement<*>? = get { ach -> ach.achievementId == achievementID }

    override fun load() {
        achievements.forEach { add(it); it.register() }
        loadSaves().forEach {
            val element = serializer.parseToJsonElement(it.readText()) as JsonObject
            val id = element["achievementId"]?.jsonPrimitive?.content ?: return@forEach

            val ach = get(id) ?: return@forEach
            val builder = ach.builder ?: return@forEach

            serializer.decodeFromJsonElement(builder, element)
        }
    }

    override fun getFile(obj: Achievement<*>): File = File(baseFolder, "${obj.diff.name}${File.separator}${obj.achievementId}.json")

    override fun loadSaves(): Array<File> = File(baseFolder).walk().filter { it.isFile }.toList().toTypedArray()

    @Suppress("UNCHECKED_CAST")
    override fun serializeObject(obj: Achievement<*>): JsonElement = serializer.encodeToJsonElement(obj.builder!! as MelodiaSerializer<Achievement<*>>, obj)

    override fun deserializeObject(jsonElement: JsonElement): Achievement<*> = AddingSomeFlavorAchievement

    override fun shouldSave(item: Achievement<*>): Boolean {
        return item.builder != null
    }

}
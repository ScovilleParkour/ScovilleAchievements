package dev.meluhdy.scovilleAchievements.core

import dev.meluhdy.melodia.manager.MelodiaSavingManager
import dev.meluhdy.scovilleAchievements.ScovilleAchievements
import kotlinx.serialization.json.JsonElement
import java.io.File

object AchievementEarnerManager : MelodiaSavingManager<AchievementEarner>() {

    val baseFolder
        get() = "${ScovilleAchievements.plugin.dataFolder}${File.separator}earners"

    override fun getFile(obj: AchievementEarner): File = File(baseFolder, "${obj.uuid}.json")

    override fun loadSaves(): Array<File> = File(baseFolder).listFiles() ?: emptyArray()

    override fun serializeObject(obj: AchievementEarner): JsonElement = serializer.encodeToJsonElement(
        AchievementEarner.EarnerSerializer, obj)

    override fun deserializeObject(jsonElement: JsonElement): AchievementEarner = serializer.decodeFromJsonElement(
        AchievementEarner.EarnerSerializer, jsonElement)

}
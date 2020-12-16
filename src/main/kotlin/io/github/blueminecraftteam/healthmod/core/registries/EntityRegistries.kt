/*
 * Copyright (c) 2020 Blue Minecraft Team.
 *
 * This file is part of HealthMod.
 *
 * HealthMod is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * HealthMod is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with HealthMod.  If not, see <https://www.gnu.org/licenses/>.
 */

package io.github.blueminecraftteam.healthmod.core.registries

import io.github.blueminecraftteam.healthmod.common.entities.DoctorNPCEntity
import io.github.blueminecraftteam.healthmod.core.HealthMod
import net.minecraft.entity.EntityClassification
import net.minecraft.entity.EntityType
import net.minecraft.util.ResourceLocation
import net.minecraftforge.registries.ForgeRegistries
import thedarkcolour.kotlinforforge.forge.KDeferredRegister

object EntityRegistries {
    val ENTITIES = KDeferredRegister(ForgeRegistries.ENTITIES, HealthMod.MOD_ID)

    val DOCTOR: EntityType<DoctorNPCEntity> by ENTITIES.register("doctor") {
        EntityType.Builder.create(::DoctorNPCEntity, EntityClassification.MISC)
            .size(0.6F, 1.95F)
            .build(ResourceLocation(HealthMod.MOD_ID, "doctor").toString())
    }
}

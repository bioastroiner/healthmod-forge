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
package io.github.blueminecraftteam.healthmod.common.entities

import io.github.blueminecraftteam.healthmod.core.HealthMod
import net.minecraft.entity.CreatureEntity
import net.minecraft.entity.EntityType
import net.minecraft.entity.ai.attributes.AttributeModifierMap.MutableAttribute
import net.minecraft.entity.ai.attributes.Attributes
import net.minecraft.util.ResourceLocation
import net.minecraft.world.World

class DoctorNPCEntity(type: EntityType<out CreatureEntity>, worldIn: World) :
	AbstractNPCEntity(type, worldIn) {

	override val texture = ResourceLocation(HealthMod.MOD_ID, "textures/entity/npc_doctor.png")

	override fun populateTradeData() {
		val offers = offers
		addTrades(offers, EntityTrades.DOCTOR[0], 4.coerceAtLeast(rand.nextInt(6) + 1))
	}

	companion object {
		fun registerAttributes(): MutableAttribute {
			return func_233666_p_()
				.createMutableAttribute(Attributes.MAX_HEALTH, 20.0)
				.createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.5)
				.createMutableAttribute(Attributes.FOLLOW_RANGE, 20.0)
		}
	}
}
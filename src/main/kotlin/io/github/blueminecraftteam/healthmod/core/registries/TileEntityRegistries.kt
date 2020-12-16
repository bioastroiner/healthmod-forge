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

import io.github.blueminecraftteam.healthmod.common.tileentity.BandAidBoxTileEntity
import io.github.blueminecraftteam.healthmod.core.HealthMod
import net.minecraft.tileentity.TileEntityType
import net.minecraftforge.registries.ForgeRegistries
import thedarkcolour.kotlinforforge.forge.KDeferredRegister

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
object TileEntityRegistries {
	val TILE_ENTITIES = KDeferredRegister(ForgeRegistries.TILE_ENTITIES, HealthMod.MOD_ID)

	val BAND_AID_BOX: TileEntityType<BandAidBoxTileEntity> by TILE_ENTITIES.register("band_aid_box") {
		TileEntityType.Builder.create(::BandAidBoxTileEntity, BlockRegistries.BAND_AID_BOX)
			.build(null)
	}
}

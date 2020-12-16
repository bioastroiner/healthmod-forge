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
package io.github.blueminecraftteam.healthmod.common.blocks

import io.github.blueminecraftteam.healthmod.core.registries.TileEntityRegistries
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.world.IBlockReader

class BandAidBoxBlock(properties: Properties) : Block(properties) {
	// TODO: Model
	// TODO: Opening gui logic
	override fun hasTileEntity(state: BlockState): Boolean {
		return true
	}

	override fun createTileEntity(state: BlockState, world: IBlockReader) = TileEntityRegistries.BAND_AID_BOX.create()
}
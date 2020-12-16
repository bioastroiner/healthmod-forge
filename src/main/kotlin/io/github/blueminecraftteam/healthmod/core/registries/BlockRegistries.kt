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

import io.github.blueminecraftteam.healthmod.common.blocks.BandAidBoxBlock
import io.github.blueminecraftteam.healthmod.core.HealthMod
import net.minecraft.block.AbstractBlock
import net.minecraft.block.SoundType
import net.minecraft.block.material.Material
import net.minecraft.block.material.MaterialColor
import net.minecraftforge.registries.ForgeRegistries
import thedarkcolour.kotlinforforge.forge.KDeferredRegister

object BlockRegistries {
	val BLOCKS = KDeferredRegister(ForgeRegistries.BLOCKS, HealthMod.MOD_ID)

	val BAND_AID_BOX by BLOCKS.register(
		"band_aid_box") {
		BandAidBoxBlock(AbstractBlock.Properties.create(Material.WOOL, MaterialColor.SNOW)
			.sound(SoundType.CLOTH)
			.noDrops()
			.zeroHardnessAndResistance())
	}
}

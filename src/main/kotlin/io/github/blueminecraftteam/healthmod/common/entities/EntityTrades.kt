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

import com.google.common.collect.ImmutableMap
import io.github.blueminecraftteam.healthmod.core.registries.ItemRegistries
import it.unimi.dsi.fastutil.ints.Int2ObjectMap
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap
import net.minecraft.entity.Entity
import net.minecraft.entity.merchant.villager.VillagerTrades.ITrade
import net.minecraft.item.*
import net.minecraft.util.IItemProvider
import net.minecraft.util.Util
import java.util.Random

object EntityTrades {
	val DOCTOR: Int2ObjectMap<Array<ITrade>> = Util.make {
		val offers = arrayOf<ITrade>(
			ItemsForEmeraldsTrade(ItemRegistries.BAND_AID, 1, 2, 1),
			ItemsForEmeraldsTrade(ItemRegistries.BAND_AID, 1, 2, 1),
			ItemsForEmeraldsTrade(ItemRegistries.BAND_AID, 1, 2, 1)
		)

		getAsIntMap(ImmutableMap.of(0, offers))
	}

	private fun getAsIntMap(map: ImmutableMap<Int, Array<ITrade>>): Int2ObjectMap<Array<ITrade>> {
		return Int2ObjectOpenHashMap(map)
	}

	internal class ItemsForEmeraldsTrade @JvmOverloads constructor(
		private val offerStack: ItemStack,
		private val price: Int,
		private val offerStackCount: Int,
		private val maxUses: Int,
		private val experience: Int,
		private val priceMultiplier: Float = 0.05f,
	) : ITrade {
		constructor(item: IItemProvider, price: Int, offerStackCount: Int, maxUses: Int, experience: Int) :
				this(ItemStack(item), price, offerStackCount, maxUses, experience)

		constructor(item: Item, price: Int, offerStackCount: Int, experience: Int) : this(ItemStack(item),
			price,
			offerStackCount,
			12,
			experience)

		override fun getOffer(trader: Entity, rand: Random) = MerchantOffer(
			ItemStack(Items.EMERALD, price),
			ItemStack(offerStack.item, offerStackCount),
			maxUses,
			experience,
			priceMultiplier
		)
	}
}
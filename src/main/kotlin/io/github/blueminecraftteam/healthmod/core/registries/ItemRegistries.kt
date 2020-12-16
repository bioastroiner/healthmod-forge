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

import io.github.blueminecraftteam.healthmod.common.items.AntibioticsItem
import io.github.blueminecraftteam.healthmod.common.items.BandAidItem
import io.github.blueminecraftteam.healthmod.core.HealthMod
import net.minecraft.item.Food
import net.minecraft.item.Item
import net.minecraft.item.Rarity
import net.minecraft.potion.EffectInstance
import net.minecraftforge.registries.ForgeRegistries
import thedarkcolour.kotlinforforge.forge.KDeferredRegister

@SuppressWarnings("UNUSED")
object ItemRegistries {
	val ITEMS = KDeferredRegister(ForgeRegistries.ITEMS, HealthMod.MOD_ID)

	val BAND_AID by ITEMS.register("band_aid") {
		BandAidItem(Item.Properties()
			.maxStackSize(16)
			.maxDamage(1)
			.rarity(Rarity.UNCOMMON))
	}

	// TODO: do the syringe e.g. extract blood etc
	val SYRINGE by ITEMS.register("syringe") {
		Item(Item.Properties()
			.group(HealthMod.ITEM_GROUP)
			.maxStackSize(1))
	}

	// TODO: Model
	// Do this later, i cba to do the functionality for it -AG6 28/11/2020
	//
	//val FACEMASK = ITEMS.register(
	//		"facemask",
	//		() -> new FaceMaskItem(new Item.Properties()
	//				.group(HealthMod.ITEM_GROUP)
	// 				.maxStackSize(1))
	// );
	//

	val ANTIBIOTICS by ITEMS.register("antibiotics") {
		AntibioticsItem(Item.Properties().group(HealthMod.ITEM_GROUP).maxStackSize(16))
	}

	val BROCCOLI by ITEMS.register("broccoli") {
		Item(Item.Properties()
			.group(HealthMod.ITEM_GROUP)
			.food(Food.Builder()
				.hunger(3)
				.saturation(2F)
				.effect({ EffectInstance(EffectRegistries.HEALTHY, 60 * 20) }, 1F)
				.build()))
	}
}


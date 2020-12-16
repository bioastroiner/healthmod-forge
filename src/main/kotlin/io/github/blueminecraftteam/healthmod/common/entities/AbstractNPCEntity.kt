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

import net.minecraft.entity.CreatureEntity
import net.minecraft.entity.EntityType
import net.minecraft.entity.INPC
import net.minecraft.entity.ai.goal.*
import net.minecraft.entity.merchant.IMerchant
import net.minecraft.entity.merchant.villager.VillagerTrades.ITrade
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.*
import net.minecraft.nbt.CompoundNBT
import net.minecraft.util.*
import net.minecraft.world.World
import java.util.stream.Collectors
import java.util.stream.IntStream

//Credit Mr Crayfish for some bits
abstract class AbstractNPCEntity(type: EntityType<out CreatureEntity>, worldIn: World) :
	CreatureEntity(type, worldIn), INPC, IMerchant {
	private var customer: PlayerEntity? = null

	private var offers: MerchantOffers? = null

	abstract val texture: ResourceLocation

	protected abstract fun populateTradeData()

	override fun getCustomer() = customer

	override fun setCustomer(player: PlayerEntity?) {
		customer = player
	}

	private fun hasCustomer() = customer != null

	override fun livingTick() {
		super.livingTick()
		updateArmSwingProgress()
	}

	override fun registerGoals() {
		goalSelector.addGoal(0, SwimGoal(this))
		goalSelector.addGoal(7, WaterAvoidingRandomWalkingGoal(this, 0.4))
		goalSelector.addGoal(8, MoveTowardsRestrictionGoal(this, 0.4))
		goalSelector.addGoal(9, LookAtWithoutMovingGoal(this, PlayerEntity::class.java, 4.0f, 1.0f))
		goalSelector.addGoal(10, LookAtGoal(this, PlayerEntity::class.java, 8.0f))
	}

	override fun func_230254_b_(player: PlayerEntity, hand: Hand): ActionResultType {
		val heldItem = player.getHeldItem(hand)

		if (heldItem.item === Items.NAME_TAG) {
			heldItem.interactWithEntity(player, this, hand)

			return ActionResultType.SUCCESS
		} else if (this.isAlive && !hasCustomer() && !this.isChild) {

			if (getOffers()!!.isEmpty()) {
				return super.func_230254_b_(player, hand)
			} else if (!world.isRemote && (revengeTarget == null || revengeTarget !== player)) {
				setCustomer(player)
				openMerchantContainer(player, this.displayName, 1)
			}

			return ActionResultType.SUCCESS
		}

		return super.func_230254_b_(player, hand)
	}

	protected fun addTrades(offers: MerchantOffers?, trades: Array<ITrade>?, max: Int) {
		if (trades == null) return

		var randomIndex = IntStream.range(0, trades.size).boxed().collect(Collectors.toList())

		randomIndex.shuffle()

		randomIndex = randomIndex.subList(0, trades.size.coerceAtMost(max))

		for (index in randomIndex) {
			val trade = trades[index!!]
			val offer = trade.getOffer(this, rand)
			if (offer != null) {
				offers?.add(offer)
			}
		}
	}

	override fun readAdditional(compound: CompoundNBT) {
		super.readAdditional(compound)

		if (compound.contains("Offers", 10)) {
			offers = MerchantOffers(compound.getCompound("Offers"))
		}
	}

	override fun writeAdditional(compound: CompoundNBT) {
		super.writeAdditional(compound)
		val merchantOffers = getOffers()

		if (merchantOffers != null) {
			if (!merchantOffers.isEmpty()) {
				compound.put("Offers", merchantOffers.write())
			}
		}
	}

	override fun setClientSideOffers(offers: MerchantOffers?) {}

	override fun onTrade(offer: MerchantOffer) {
		offer.increaseUses()
	}

	override fun verifySellingItem(stack: ItemStack) {}

	override fun getOffers(): MerchantOffers? = offers

	override fun getWorld(): World = world

	override fun getXp() = 0

	override fun setXP(xpIn: Int) {}

	override fun hasXPBar() = false

	override fun canRestockTrades() = false

	override fun getYesSound(): SoundEvent = SoundEvents.ENTITY_VILLAGER_YES

	override fun getHurtSound(damageSourceIn: DamageSource): SoundEvent? = super.getHurtSound(damageSourceIn)
}
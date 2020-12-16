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

package io.github.blueminecraftteam.healthmod.entities;

import io.github.blueminecraftteam.healthmod.registries.EntityRegistries;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.merchant.villager.AbstractVillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.MerchantOffer;
import net.minecraft.item.MerchantOffers;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import org.jetbrains.annotations.Nullable;

public class DoctorNPCEntity extends AbstractVillagerEntity {
    public DoctorNPCEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
        super(EntityRegistries.DOCTOR.get(), worldIn);
    }

    @Override
    protected ActionResultType func_230254_b_(PlayerEntity player, Hand hand) {
        ItemStack heldItem = player.getHeldItem(hand);
        if (heldItem.getItem() == Items.NAME_TAG) {
            heldItem.interactWithEntity(player, this, hand);
            return ActionResultType.SUCCESS;
        } else if (this.isAlive() && !this.hasCustomer() && !this.isChild()) {
            if (this.getOffers().isEmpty()) {
                return super.func_230254_b_(player, hand);
            } else if (!this.world.isRemote && (this.getRevengeTarget() == null || this.getRevengeTarget() != player)) {
                this.setCustomer(player);
                this.openMerchantContainer(player, this.getDisplayName(), 1);
            }
            return ActionResultType.SUCCESS;
        }
        return super.func_230254_b_(player, hand);
    }

    @Override
    public void tick() {
        super.tick();
        if (this.getShakeHeadTicks() > 0) {
            this.setShakeHeadTicks(this.getShakeHeadTicks() - 1);
        }

    }

    @Override
    protected void onVillagerTrade(MerchantOffer offer) {
        offer.increaseUses();
    }

    @Override
    protected void populateTradeData() {
        MerchantOffers offers = this.getOffers();
        this.addTrades(offers, EntityTrades.DOCTOR.get(0), Math.max(4, this.rand.nextInt(6) + 1));
    }

    @Nullable
    @Override
    public AgeableEntity func_241840_a(ServerWorld world, AgeableEntity entity) {
        return null;
    }


    public static AttributeModifierMap.MutableAttribute registerAttributes() {
        return MobEntity.func_233666_p_()
                .createMutableAttribute(Attributes.MAX_HEALTH, 20D)
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.5D)
                .createMutableAttribute(Attributes.FOLLOW_RANGE, 20.0D);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(7, new WaterAvoidingRandomWalkingGoal(this, 0.4D));
        this.goalSelector.addGoal(8, new MoveTowardsRestrictionGoal(this, 0.4D));
        this.goalSelector.addGoal(9, new LookAtWithoutMovingGoal(this, PlayerEntity.class, 4.0F, 1.0F));
        this.goalSelector.addGoal(10, new LookAtGoal(this, PlayerEntity.class, 8.0F));
    }

    @Override
    public boolean hasXPBar() {
        return false;
    }

    /*

    @Override
    public ResourceLocation getTexture() {
        return new ResourceLocation(HealthMod.MOD_ID, "textures/entity/npc_doctor.png");
    }

    @Override
    protected void populateTradeData() {
        MerchantOffers offers = this.getOffers();
        this.addTrades(offers, EntityTrades.DOCTOR.get(0), Math.max(4, this.rand.nextInt(6) + 1));
    }
    */
}

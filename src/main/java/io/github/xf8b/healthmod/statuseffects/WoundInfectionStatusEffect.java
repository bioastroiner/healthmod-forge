/*
 * Copyright (c) 2020 xf8b.
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

package io.github.xf8b.healthmod.statuseffects;

import io.github.xf8b.healthmod.mixins.DamageSourceAccessorMixin;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;

public class WoundInfectionStatusEffect extends Effect {
    public WoundInfectionStatusEffect(EffectType type, int color) {
        super(type, color);
    }

    @Override
    public void performEffect(LivingEntity entity, int amplifier) {
        entity.attackEntityFrom(DamageSourceAccessorMixin.newDamageSource("wound_infection"), 2.5F);
    }

    @Override
    public boolean isReady(int duration, int amplifier) {
        int k = 25 >> amplifier;
        if (k > 0) {
            return duration % k == 0;
        } else {
            return true;
        }
    }
}

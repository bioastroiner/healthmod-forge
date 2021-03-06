/*
 * Copyright (c) 2020 Blue Minecraft Team.
 *
 * This file is part of HealthMod.
 *
 * HealthMod is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * HealthMod is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with HealthMod.  If not, see <https://www.gnu.org/licenses/>.
 */

package io.github.blueminecraftteam.healthmod.client.entity.doctor;

import io.github.blueminecraftteam.healthmod.common.entities.AbstractNPCEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class DoctorNPCEntityRenderer extends MobRenderer<AbstractNPCEntity, DoctorNPCEntityModel> {
    public DoctorNPCEntityRenderer(EntityRendererManager rendererManager) {
        super(rendererManager, new DoctorNPCEntityModel(), 0.5F);
    }

    @Override
    public ResourceLocation getEntityTexture(AbstractNPCEntity entity) {
        return entity.getTexture();
    }
}

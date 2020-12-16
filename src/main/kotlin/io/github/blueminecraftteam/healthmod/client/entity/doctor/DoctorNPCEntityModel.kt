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
package io.github.blueminecraftteam.healthmod.client.entity.doctor

import com.google.common.collect.ImmutableList
import io.github.blueminecraftteam.healthmod.common.entities.AbstractNPCEntity
import net.minecraft.client.renderer.entity.model.IHasHead
import net.minecraft.client.renderer.entity.model.SegmentedModel
import net.minecraft.client.renderer.model.ModelRenderer
import net.minecraft.util.math.MathHelper
import net.minecraftforge.api.distmarker.Dist
import net.minecraftforge.api.distmarker.OnlyIn
import kotlin.math.sin

@OnlyIn(Dist.CLIENT)
class DoctorNPCEntityModel : SegmentedModel<AbstractNPCEntity>(), IHasHead {
	private val parts: ImmutableList<ModelRenderer>
	var head: ModelRenderer
	private var hood: ModelRenderer
	private var body: ModelRenderer
	private var rightArm: ModelRenderer
	private var leftArm: ModelRenderer
	private var rightLeg: ModelRenderer
	private var leftLeg: ModelRenderer
	private var nose: ModelRenderer
	private var rightEar: ModelRenderer
	private var leftEar: ModelRenderer
	private var bag: ModelRenderer

	private fun setRotateAngle(modelRenderer: ModelRenderer, x: Float, y: Float, z: Float) {
		modelRenderer.rotateAngleX = x
		modelRenderer.rotateAngleY = y
		modelRenderer.rotateAngleZ = z
	}

	override fun getParts(): Iterable<ModelRenderer> {
		return parts
	}

	override fun setRotationAngles(
		entityIn: AbstractNPCEntity,
		limbSwing: Float,
		limbSwingAmount: Float,
		ageInTicks: Float,
		headYaw: Float,
		headPitch: Float,
	) {
		var rotateFactor: Float
		rotateFactor = entityIn.motion.lengthSquared().toFloat()
		rotateFactor /= 0.2f
		rotateFactor *= rotateFactor * rotateFactor

		if (rotateFactor < 1.0f) {
			rotateFactor = 1.0f
		}

		rightArm.rotateAngleX =
			MathHelper.cos(limbSwing * 0.6662f + Math.PI.toFloat()) * 2.0f * limbSwingAmount * 0.5f / rotateFactor
		leftArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f) * 2.0f * limbSwingAmount * 0.5f / rotateFactor
		rightArm.rotateAngleY = 0.0f
		rightArm.rotateAngleZ = 0.0f
		leftArm.rotateAngleY = 0.0f
		leftArm.rotateAngleZ = 0.0f
		rightLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount / rotateFactor
		leftLeg.rotateAngleX =
			MathHelper.cos(limbSwing * 0.6662f + Math.PI.toFloat()) * 1.4f * limbSwingAmount / rotateFactor

		head.rotateAngleY = headYaw * (Math.PI.toFloat() / 180f)
		head.rotateAngleX = headPitch * (Math.PI.toFloat() / 180f)
		hood.copyModelAngles(head)

		if (swingProgress > 0.0f) {
			val arm = rightArm
			var progress = swingProgress
			body.rotateAngleY = MathHelper.sin(MathHelper.sqrt(progress) * (Math.PI.toFloat() * 2f)) * 0.2f
			rightArm.rotateAngleY += body.rotateAngleY
			leftArm.rotateAngleY += body.rotateAngleY
			leftArm.rotateAngleX += body.rotateAngleY
			progress = 1.0f - swingProgress
			progress *= progress
			progress *= progress
			progress = 1.0f - progress
			val f2 = MathHelper.sin(progress * Math.PI.toFloat())
			val f3 = MathHelper.sin(swingProgress * Math.PI.toFloat()) * -(head.rotateAngleX - 0.7f) * 0.75f
			arm.rotateAngleX = (arm.rotateAngleX.toDouble() - (f2.toDouble() * 1.2 + f3.toDouble())).toFloat()
			arm.rotateAngleY += body.rotateAngleY * 2.0f
			arm.rotateAngleZ += MathHelper.sin(swingProgress * Math.PI.toFloat()) * -0.4f
		}

		if (entityIn.isHandActive) {
			rightArm.rotateAngleX = Math.toRadians(-90f + 5f * sin(ageInTicks.toDouble()))
				.toFloat()
			leftArm.rotateAngleX = Math.toRadians(-90f + 5f * sin(ageInTicks.toDouble())).toFloat()
		}
	}

	override fun getModelHead(): ModelRenderer {
		return head
	}

	init {
		textureWidth = 46
		textureHeight = 46

		rightEar = ModelRenderer(this, 0, 8)
		rightEar.setRotationPoint(-4.0f, -3.0f, 1.0f)
		rightEar.addBox(0.0f, -2.0f, 0.0f, 0f, 4f, 4f, 0.0f)
		setRotateAngle(rightEar, 0.0f, -0.7853981633974483f, 0.0f)

		leftEar = ModelRenderer(this, 8, 8)
		leftEar.setRotationPoint(4.0f, -3.0f, 1.0f)
		leftEar.addBox(0.0f, -2.0f, 0.0f, 0f, 4f, 4f, 0.0f)
		setRotateAngle(leftEar, 0.0f, 0.7853981633974483f, 0.0f)

		rightLeg = ModelRenderer(this, 26, 9)
		rightLeg.setRotationPoint(-1.0f, 20.0f, 0.0f)
		rightLeg.addBox(-1.0f, 0.0f, -1.5f, 2f, 4f, 3f, 0.0f)

		rightArm = ModelRenderer(this, 30, 0)
		rightArm.setRotationPoint(-3.0f, 17.0f, 0.0f)
		rightArm.addBox(-1.0f, -1.0f, -1.0f, 2f, 5f, 2f, 0.0f)

		head = ModelRenderer(this, 0, 0)
		head.setRotationPoint(0.0f, 16.0f, 0.0f)
		head.addBox(-4.0f, -6.0f, -3.0f, 8f, 6f, 6f, 0.0f)

		body = ModelRenderer(this, 12, 12)
		body.setRotationPoint(0.0f, 16.0f, 0.0f)
		body.addBox(-2.0f, 0.0f, -2.0f, 4f, 4f, 4f, 0.0f)

		nose = ModelRenderer(this, 22, 0)
		nose.setRotationPoint(0.0f, -3.0f, -3.0f)
		nose.addBox(-1.0f, 0.0f, -2.0f, 2f, 4f, 2f, 0.0f)

		hood = ModelRenderer(this, 0, 32)
		hood.setRotationPoint(0.0f, 16.0f, 0.0f)
		hood.addBox(-4.0f, -6.0f, -3.0f, 8f, 8f, 6f, 0.5f)

		bag = ModelRenderer(this, 0, 20)
		bag.setRotationPoint(0.0f, 0.0f, 0.0f)
		bag.addBox(-2.5f, -2.0f, 2.0f, 5f, 7f, 3f, 0.0f)

		leftArm = ModelRenderer(this, 38, 0)
		leftArm.setRotationPoint(3.0f, 17.0f, 0.0f)
		leftArm.addBox(-1.0f, -1.0f, -1.0f, 2f, 5f, 2f, 0.0f)
		leftLeg = ModelRenderer(this, 36, 9)
		leftLeg.setRotationPoint(1.0f, 20.0f, 0.0f)
		leftLeg.addBox(-1.0f, 0.0f, -1.5f, 2f, 4f, 3f, 0.0f)

		head.addChild(rightEar)
		head.addChild(leftEar)
		head.addChild(nose)
		body.addChild(bag)

		parts = ImmutableList.of(head, hood, body, leftArm, rightArm, leftLeg, rightLeg)
	}
}
package net.dollar.apex.mixin;

import net.dollar.apex.util.MixinUtils;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.item.HeldItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Arm;
import net.minecraft.util.Hand;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(HeldItemRenderer.class)
public class MixinHeldItemRenderer {
	/**
	 * Overrides HeldItemRenderer.isChargedCrossbow() to identify and return true for any item that
	 * 	derives from CrossbowItem.
	 * @param stack ItemStack passed into the original method
	 * @param cir Returnable callback info of Boolean type, used for replacing return value
	 */
	@Inject(at = @At("HEAD"), method = "isChargedCrossbow", cancellable = true)
	private static void injectTailGetArmPose(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
		//HERE, checks if the ItemStack's item is an instanceof CrossbowItem INSTEAD OF checking for
		//	Items.CROSSBOW equality, adding support for modded crossbows to be rendered correctly
		//	when fully charged.
		cir.setReturnValue(stack.getItem() instanceof CrossbowItem && CrossbowItem.isCharged(stack));
	}

	/**
	 * Injects custom behavior to head of HeldItemRenderer.renderFirstPersonItem() (generic, used for all items)
	 *  which allows modded crossbows that derive from CrossbowItem to be properly rendered in first-person view.
	 * @param player Player holding the item
	 * @param tickDelta	Tick delta
	 * @param pitch	Pitch of the player's view?
	 * @param hand Player's hand holding the Crossbow
	 * @param swingProgress Current swing progress (if applicable)
	 * @param item ItemStack of the held Item
	 * @param equipProgress Current equip progress
	 * @param matrices MatrixStack for transform
	 * @param vertexConsumers ???
	 * @param light Light level which affects render brightness
	 * @param ci CallbackInfo object used by mixins
	 */
	@Inject(at = @At("HEAD"), method = "renderFirstPersonItem", cancellable = true)
	private void injectHeadRenderFirstPersonItem(AbstractClientPlayerEntity player, float tickDelta, float pitch, Hand hand, float swingProgress, ItemStack item, float equipProgress, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, CallbackInfo ci) {
		if (item.getItem() instanceof CrossbowItem) {
			//Variables used in the vanilla function, also push a new matrix entry for this use ONLY (popped later).
			boolean bl = hand == Hand.MAIN_HAND;
			Arm arm = bl ? player.getMainArm() : player.getMainArm().getOpposite();
			matrices.push();

			//CODE BLOCK BELOW is pulled directly from the Items.CROSSBOW conditional check in-method.
			int i;
			boolean bl2 = CrossbowItem.isCharged(item);
			boolean bl3 = arm == Arm.RIGHT;
			i = bl3 ? 1 : -1;
			if (player.isUsingItem() && player.getItemUseTimeLeft() > 0 && player.getActiveHand() == hand) {
				MixinUtils.applyEquipOffset(matrices, arm, equipProgress);
				matrices.translate((float)i * -0.4785682f, -0.094387f, 0.05731531f);
				matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(-11.935f));
				matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees((float)i * 65.3f));
				matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees((float)i * -9.785f));
				float f = (float)item.getMaxUseTime() - ((float)player.getItemUseTimeLeft() - tickDelta + 1.0f);
				float g = f / (float)CrossbowItem.getPullTime(item);
				if (g > 1.0f) {
					g = 1.0f;
				}
				if (g > 0.1f) {
					float h = MathHelper.sin((f - 0.1f) * 1.3f);
					float j = g - 0.1f;
					float k = h * j;
					matrices.translate(k * 0.0f, k * 0.004f, k * 0.0f);
				}
				matrices.translate(g * 0.0f, g * 0.0f, g * 0.04f);
				matrices.scale(1.0f, 1.0f, 1.0f + g * 0.2f);
				matrices.multiply(RotationAxis.NEGATIVE_Y.rotationDegrees((float)i * 45.0f));
			} else {
				float f = -0.4f * MathHelper.sin(MathHelper.sqrt(swingProgress) * (float)Math.PI);
				float g = 0.2f * MathHelper.sin(MathHelper.sqrt(swingProgress) * ((float)Math.PI * 2));
				float h = -0.2f * MathHelper.sin(swingProgress * (float)Math.PI);
				matrices.translate((float)i * f, g, h);
				MixinUtils.applyEquipOffset(matrices, arm, equipProgress);
				MixinUtils.applySwingOffset(matrices, arm, swingProgress);
				if (bl2 && swingProgress < 0.001f && bl) {
					matrices.translate((float)i * -0.641864f, 0.0f, 0.0f);
					matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees((float)i * 10.0f));
				}
			}

			//Perform a chain of calls to get the current HeldItemRenderer object, allowing using its renderItem().
			HeldItemRenderer renderer = MinecraftClient.getInstance().getEntityRenderDispatcher().getHeldItemRenderer();
			renderer.renderItem(player, item, bl3 ? ModelTransformationMode.FIRST_PERSON_RIGHT_HAND : ModelTransformationMode.FIRST_PERSON_LEFT_HAND, !bl3, matrices, vertexConsumers, light);

			//Pop the previously-added matrix entry.
			matrices.pop();

			//IMPORTANT: Cancel the rest of the method if it was intercepted successfully with a CrossbowItem instance.
			ci.cancel();
		}
	}
}
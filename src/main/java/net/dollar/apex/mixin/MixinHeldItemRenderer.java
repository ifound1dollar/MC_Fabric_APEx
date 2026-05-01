package net.dollar.apex.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.dollar.apex.util.MixinUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemInHandRenderer.class)
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
	 * @param orderedRenderCommandQueue ???
	 * @param light Light level which affects render brightness
	 * @param ci CallbackInfo object used by mixins
	 */
	@Inject(at = @At("HEAD"), method = "renderArmWithItem", cancellable = true)
	private void injectHeadRenderFirstPersonItem(AbstractClientPlayer player, float tickDelta, float pitch, InteractionHand hand, float swingProgress, ItemStack item, float equipProgress, PoseStack matrices, SubmitNodeCollector orderedRenderCommandQueue, int light, CallbackInfo ci) {
		if (item.getItem() instanceof CrossbowItem) {
			//Variables used in the vanilla function, also push a new matrix entry for this use ONLY (popped later).
			boolean bl = hand == InteractionHand.MAIN_HAND;
			HumanoidArm arm = bl ? player.getMainArm() : player.getMainArm().getOpposite();
			matrices.pushPose();

			//CODE BLOCK BELOW is pulled directly from the Items.CROSSBOW conditional check in-method.
			int i;
			boolean bl2 = CrossbowItem.isCharged(item);
			boolean bl3 = arm == HumanoidArm.RIGHT;
			i = bl3 ? 1 : -1;
			if (player.isUsingItem() && player.getUseItemRemainingTicks() > 0 && player.getUsedItemHand() == hand) {
				MixinUtils.applyEquipOffset(matrices, arm, equipProgress);
				matrices.translate((float)i * -0.4785682f, -0.094387f, 0.05731531f);
				matrices.mulPose(Axis.XP.rotationDegrees(-11.935f));
				matrices.mulPose(Axis.YP.rotationDegrees((float)i * 65.3f));
				matrices.mulPose(Axis.ZP.rotationDegrees((float)i * -9.785f));
				float f = (float)item.getUseDuration(player) - ((float)player.getUseItemRemainingTicks() - tickDelta + 1.0f);
				float g = f / (float)CrossbowItem.getChargeDuration(item, player);
				if (g > 1.0f) {
					g = 1.0f;
				}
				if (g > 0.1f) {
					float h = Mth.sin((f - 0.1f) * 1.3f);
					float j = g - 0.1f;
					float k = h * j;
					matrices.translate(k * 0.0f, k * 0.004f, k * 0.0f);
				}
				matrices.translate(g * 0.0f, g * 0.0f, g * 0.04f);
				matrices.scale(1.0f, 1.0f, 1.0f + g * 0.2f);
				matrices.mulPose(Axis.YN.rotationDegrees((float)i * 45.0f));
			} else {
				float f = -0.4f * Mth.sin(Mth.sqrt(swingProgress) * (float)Math.PI);
				float g = 0.2f * Mth.sin(Mth.sqrt(swingProgress) * ((float)Math.PI * 2));
				float h = -0.2f * Mth.sin(swingProgress * (float)Math.PI);
				matrices.translate((float)i * f, g, h);
				MixinUtils.applyEquipOffset(matrices, arm, equipProgress);
				MixinUtils.applySwingOffset(matrices, arm, swingProgress);
				if (bl2 && swingProgress < 0.001f && bl) {
					matrices.translate((float)i * -0.641864f, 0.0f, 0.0f);
					matrices.mulPose(Axis.YP.rotationDegrees((float)i * 10.0f));
				}
			}

			//Perform a chain of calls to get the current HeldItemRenderer object, allowing using its renderItem().
			ItemInHandRenderer renderer = Minecraft.getInstance().getEntityRenderDispatcher().getItemInHandRenderer();
			renderer.renderItem(player, item, bl3 ? ItemDisplayContext.FIRST_PERSON_RIGHT_HAND : ItemDisplayContext.FIRST_PERSON_LEFT_HAND, matrices, orderedRenderCommandQueue, light);

			//Pop the previously-added matrix entry.
			matrices.popPose();

			//IMPORTANT: Cancel the rest of the method if it was intercepted successfully with a CrossbowItem instance.
			ci.cancel();
		}
	}
}
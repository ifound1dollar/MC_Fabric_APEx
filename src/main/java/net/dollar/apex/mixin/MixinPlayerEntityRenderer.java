package net.dollar.apex.mixin;

import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntityRenderer.class)
public class MixinPlayerEntityRenderer {
	/**
	 * Injects functionality into PlayerEntityRenderer.getArmPose() that checks for a charged crossbow
	 * 	using instanceof INSTEAD OF checking Items.CROSSBOW (which only works for the vanilla crossbow).
	 * @param player Client-side PlayerEntity passed into the original method
	 * @param hand Player's hand passed into the original method
	 * @param cir Return value of the callback method, returns the correct ArmPose
	 */
	@Inject(at = @At("TAIL"), method = "getArmPose", cancellable = true)
	private static void injectTailGetArmPose(AbstractClientPlayerEntity player, Hand hand,
							 CallbackInfoReturnable<BipedEntityModel.ArmPose> cir) {
		//Store the ItemStack that the player is holding.
		ItemStack itemStack = player.getStackInHand(hand);

		//HERE, checks if the ItemStack's item is an instanceof CrossbowItem INSTEAD OF checking for
		//	Items.CROSSBOW equality, adding support for modded crossbows to be rendered correctly
		//	when fully charged.
		if (!player.handSwinging && itemStack.getItem() instanceof CrossbowItem && CrossbowItem.isCharged(itemStack)) {
			cir.setReturnValue(BipedEntityModel.ArmPose.CROSSBOW_HOLD);
		}
	}
}
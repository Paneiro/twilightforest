package twilightforest.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import twilightforest.entity.monster.LoyalZombie;
import twilightforest.init.TFEntities;
import twilightforest.init.TFItems;
import twilightforest.init.TFSounds;
import twilightforest.util.TFItemStackUtils;

import java.util.List;

public class ZombieWandItem extends Item {

	public ZombieWandItem(Properties properties) {
		super(properties);
	}

	@Override
	public InteractionResult use(Level level, Player player, InteractionHand hand) {

		ItemStack stack = player.getItemInHand(hand);

		if (stack.getDamageValue() == stack.getMaxDamage() && !player.getAbilities().instabuild) {
			return InteractionResult.FAIL;
		}

		if (!level.isClientSide()) {
			// what block is the player pointing at?
			BlockHitResult result = getPlayerPOVHitResult(level, player, ClipContext.Fluid.NONE);

			if (result.getType() != HitResult.Type.MISS) {
				LoyalZombie zombie = TFEntities.LOYAL_ZOMBIE.get().create(level, EntitySpawnReason.MOB_SUMMONED);
				zombie.moveTo(result.getLocation());
				if (!level.noCollision(zombie, zombie.getBoundingBox())) {
					return InteractionResult.PASS;
				}
				zombie.spawnAnim();
				zombie.setTame(true, false);
				zombie.setOwnerUUID(player.getUUID());
				zombie.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 1200, 1));
				if (player.getItemBySlot(EquipmentSlot.HEAD).is(TFItems.MYSTIC_CROWN) && level.getRandom().nextFloat() <= 0.1f) {
					zombie.setBaby(true);
				}
				level.addFreshEntity(zombie);
				level.gameEvent(player, GameEvent.ENTITY_PLACE, result.getBlockPos());

				if (!player.getAbilities().instabuild) {
					TFItemStackUtils.hurtButDontBreak(stack, 1, (ServerLevel) level, player);
				}
				zombie.playSound(TFSounds.ZOMBIE_SCEPTER_USE.get(), 1.0F, 1.0F);
			}
		}

		return InteractionResult.SUCCESS;
	}

	@Override
	public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
		return false;
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
		super.appendHoverText(stack, context, tooltip, flag);
		tooltip.add(Component.translatable("item.twilightforest.scepter.desc", stack.getMaxDamage() - stack.getDamageValue()).withStyle(ChatFormatting.GRAY));
	}
}
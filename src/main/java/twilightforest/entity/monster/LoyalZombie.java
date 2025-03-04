package twilightforest.entity.monster;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Ghast;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
import twilightforest.init.TFDamageTypes;
import twilightforest.init.TFSounds;

public class LoyalZombie extends TamableAnimal {

	public LoyalZombie(EntityType<? extends LoyalZombie> type, Level world) {
		super(type, world);
		this.xpReward = 0;
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(1, new FloatGoal(this));
		this.goalSelector.addGoal(4, new MeleeAttackGoal(this, 1.0D, true));
		this.goalSelector.addGoal(5, new FollowOwnerGoal(this, 1.0D, 10.0F, 2.0F));
		this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 1.0D));
		this.goalSelector.addGoal(9, new LookAtPlayerGoal(this, Player.class, 8.0F));
		this.goalSelector.addGoal(9, new RandomLookAroundGoal(this));
		this.targetSelector.addGoal(1, new OwnerHurtByTargetGoal(this));
		this.targetSelector.addGoal(2, new OwnerHurtTargetGoal(this));
		this.targetSelector.addGoal(3, new HurtByTargetGoal(this));
		this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, Monster.class, true));
	}

	@Override
	public Animal getBreedOffspring(ServerLevel level, AgeableMob animal) {
		return null;
	}

	public static AttributeSupplier.Builder registerAttributes() {
		return Mob.createMobAttributes()
			.add(Attributes.MAX_HEALTH, 40.0D)
			.add(Attributes.MOVEMENT_SPEED, 0.3D)
			.add(Attributes.ARMOR, 3.0D);
	}

	@Override
	public boolean doHurtTarget(ServerLevel level, Entity entity) {
		boolean success = entity.hurtServer(level, this.damageSources().mobAttack(this), 7.0F);

		if (success) {
			entity.push(0.0D, 0.2D, 0.0D);
		}

		return success;
	}

	@Override
	protected void customServerAiStep(ServerLevel level) {
		// once our damage boost effect wears out, start to decay
		// the effect here is that we die shortly after our 60 second lifespan
		if (this.getEffect(MobEffects.DAMAGE_BOOST) == null) {
			if (this.tickCount % 20 == 0) {
				this.hurtServer(level, this.damageSources().source(TFDamageTypes.EXPIRED), 2);
			}
		}
		super.customServerAiStep(level);
	}

	@Override
	public InteractionResult interactAt(Player player, Vec3 vec3, InteractionHand hand) {
		//feeding a loyal zombie rotten flesh will refresh its death timer, allowing your minions to stick around for longer
		if (this.getOwner() != null && this.getOwner().is(player) && player.getItemInHand(hand).is(Items.ROTTEN_FLESH)) {
			this.removeEffect(MobEffects.DAMAGE_BOOST);
			this.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 1200, 1));
			this.heal(1.0F);
			this.playSound(SoundEvents.ZOMBIE_INFECT, this.getSoundVolume(), this.getVoicePitch());
			player.getItemInHand(hand).consume(1, player);
			return InteractionResult.SUCCESS;
		}

		return super.interactAt(player, vec3, hand);
	}

	/**
	 * [VanillaCopy] {@link Wolf#wantsToAttack(LivingEntity, LivingEntity)} ()}, substituting with our class
	 */
	@Override
	public boolean wantsToAttack(LivingEntity target, LivingEntity owner) {
		if (target instanceof Creeper || target instanceof Ghast || target instanceof ArmorStand) {
			return false;
		} else if (target instanceof LoyalZombie zombie) {
			return !zombie.isTame() || zombie.getOwner() != owner;
		} else {
			return switch (target) {
				case Player player when owner instanceof Player player1 && !player1.canHarmPlayer(player) -> false;
				case AbstractHorse abstracthorse when abstracthorse.isTamed() -> false;
				case TamableAnimal tamableanimal when tamableanimal.isTame() -> false;
				default -> true;
			};

		}
	}

	@Override
	public boolean removeWhenFarAway(double distanceToClosestPlayer) {
		return !this.isTame();
	}

	@Override
	public boolean isFood(ItemStack stack) {
		return false;
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return TFSounds.LOYAL_ZOMBIE_AMBIENT.get();
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return TFSounds.LOYAL_ZOMBIE_HURT.get();
	}

	@Override
	protected SoundEvent getDeathSound() {
		return TFSounds.LOYAL_ZOMBIE_DEATH.get();
	}

	@Override
	protected void playStepSound(BlockPos pos, BlockState state) {
		playSound(TFSounds.LOYAL_ZOMBIE_STEP.get(), 0.15F, 1.0F);
	}
}

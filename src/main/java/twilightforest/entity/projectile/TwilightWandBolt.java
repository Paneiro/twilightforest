package twilightforest.entity.projectile;

import net.minecraft.core.particles.ColorParticleOption;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import twilightforest.init.TFDamageTypes;
import twilightforest.init.TFEntities;
import twilightforest.init.TFSounds;

public class TwilightWandBolt extends TFThrowable {

	public TwilightWandBolt(EntityType<? extends TwilightWandBolt> type, Level world) {
		super(type, world);
	}

	public TwilightWandBolt(Level world, LivingEntity thrower) {
		super(TFEntities.WAND_BOLT.get(), world, thrower);
		this.shootFromRotation(thrower, thrower.getXRot(), thrower.getYRot(), 0, 1.5F, 1.0F);
	}

	public TwilightWandBolt(Level worldIn, double x, double y, double z) {
		super(TFEntities.WAND_BOLT.get(), worldIn, x, y, z);
	}

	@Override
	public void tick() {
		super.tick();
		this.makeTrail();
	}

	private void makeTrail() {
		for (int i = 0; i < 5; i++) {
			double dx = this.getX() + 0.5D * (this.random.nextDouble() - this.random.nextDouble());
			double dy = this.getY() + 0.5D * (this.random.nextDouble() - this.random.nextDouble());
			double dz = this.getZ() + 0.5D * (this.random.nextDouble() - this.random.nextDouble());

			float s1 = ((this.random.nextFloat() * 0.5F) + 0.5F) * 0.17F;  // color
			float s2 = ((this.random.nextFloat() * 0.5F) + 0.5F) * 0.80F;  // color
			float s3 = ((this.random.nextFloat() * 0.5F) + 0.5F) * 0.69F;  // color

			this.level().addParticle(ColorParticleOption.create(ParticleTypes.ENTITY_EFFECT, s1, s2, s3), dx, dy, dz, 0.0D, 0.0D, 0.0D);
		}
	}

	@Override
	protected double getDefaultGravity() {
		return 0.003F;
	}

	@Override
	public void handleEntityEvent(byte id) {
		if (id == EntityEvent.DEATH) {
			ParticleOptions particle = new ItemParticleOption(ParticleTypes.ITEM, new ItemStack(Items.ENDER_PEARL));
			for (int i = 0; i < 8; i++) {
				this.level().addParticle(particle, false, this.getX(), this.getY(), this.getZ(), this.random.nextGaussian() * 0.05D, random.nextDouble() * 0.2D, random.nextGaussian() * 0.05D);
			}
		} else {
			super.handleEntityEvent(id);
		}
	}

	@Override
	protected void onHitEntity(EntityHitResult result) {
		super.onHitEntity(result);
		if (this.level() instanceof ServerLevel level && result.getEntity().hurtServer(level, level.damageSources().source(TFDamageTypes.TWILIGHT_SCEPTER, this, this.getOwner()), 6)) {
			this.level().playSound(null, result.getEntity().blockPosition(), TFSounds.TWILIGHT_SCEPTER_HIT.get(), this.getOwner() != null ? this.getOwner().getSoundSource() : SoundSource.PLAYERS);
			this.level().broadcastEntityEvent(this, (byte) 3);
			this.discard();
		}
	}

	@Override
	protected void onHitBlock(BlockHitResult result) {
		super.onHitBlock(result);
		if (!this.level().isClientSide()) {

			this.level().broadcastEntityEvent(this, (byte) 3);
			this.discard();
		}
	}

	@Override
	public boolean hurtServer(ServerLevel level, DamageSource source, float amount) {
		if (!this.level().isClientSide() && source.getEntity() != null) {
			Vec3 vec3d = source.getEntity().getLookAngle();
			// reflect faster and more accurately
			this.shoot(vec3d.x(), vec3d.y(), vec3d.z(), 1.5F, 0.1F);

			if (source.getDirectEntity() instanceof LivingEntity) {
				this.setOwner(source.getDirectEntity());
			}
			return true;
		}

		return super.hurtServer(level, source, amount);
	}
}

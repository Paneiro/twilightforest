package twilightforest.entity.projectile;

import net.minecraft.core.particles.ColorParticleOption;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import twilightforest.entity.boss.Lich;
import twilightforest.init.TFDamageTypes;
import twilightforest.init.TFEntities;

public class LichBolt extends TFThrowable {

	public LichBolt(EntityType<? extends LichBolt> type, Level level) {
		super(type, level);
	}

	public LichBolt(Level level, LivingEntity owner) {
		super(TFEntities.LICH_BOLT.get(), level, owner);
	}

	@Override
	public void tick() {
		super.tick();
		this.makeTrail();
	}

	private void makeTrail() {
		float s1 = ((this.random.nextFloat() * 0.5F) + 0.5F) * 0.17F;
		float s2 = ((this.random.nextFloat() * 0.5F) + 0.5F) * 0.80F;
		float s3 = ((this.random.nextFloat() * 0.5F) + 0.5F) * 0.69F;

		this.makeTrail(ColorParticleOption.create(ParticleTypes.ENTITY_EFFECT, s1, s2, s3), 5);
	}

	@Override
	public boolean isPickable() {
		return true;
	}

	@Override
	public float getPickRadius() {
		return 1.0F;
	}

	@Override
	public boolean hurtServer(ServerLevel level, DamageSource damagesource, float amount) {
		if (!this.level().isClientSide() && damagesource.getEntity() != null) {
			Vec3 vec3d = damagesource.getEntity().getLookAngle();
			// reflect faster and more accurately
			this.shoot(vec3d.x(), vec3d.y(), vec3d.z(), 1.5F, 0.1F);  // reflect faster and more accurately

			if (damagesource.getDirectEntity() instanceof LivingEntity)
				this.setOwner(damagesource.getDirectEntity());

			return true;
		}

		return super.hurtServer(level, damagesource, amount);
	}

	@Override
	protected double getDefaultGravity() {
		return 0.001F;
	}

	@Override
	public void handleEntityEvent(byte id) {
		if (id == EntityEvent.DEATH) {
			ItemStack itemId = new ItemStack(Items.ENDER_PEARL);
			for (int i = 0; i < 8; ++i) {
				this.level().addParticle(new ItemParticleOption(ParticleTypes.ITEM, itemId), this.getX(), this.getY(), this.getZ(), random.nextGaussian() * 0.05D, random.nextDouble() * 0.2D, random.nextGaussian() * 0.05D);
			}
		} else {
			super.handleEntityEvent(id);
		}
	}

	@Override
	protected void onHitBlock(BlockHitResult result) {
		super.onHitBlock(result);
		this.level().broadcastEntityEvent(this, (byte) 3);
		this.discard();
	}

	@Override
	protected boolean canHitEntity(Entity target) {
		if (target instanceof LichBolt || target instanceof LichBomb || (target instanceof Lich lich && lich.isShadowClone())) {
			return false;
		}
		return super.canHitEntity(target);
	}

	@Override
	protected void onHitEntity(EntityHitResult result) {
		Entity hit = result.getEntity();

		if (this.level() instanceof ServerLevel level) {
			if (hit instanceof LivingEntity && hit.hurtServer(level, TFDamageTypes.getIndirectEntityDamageSource(level, TFDamageTypes.LICH_BOLT, this, this.getOwner(), TFEntities.LICH.get()), 6)) {
				this.level().broadcastEntityEvent(this, (byte) 3);
				this.discard();
			}
		}
	}
}

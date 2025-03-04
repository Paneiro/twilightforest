package twilightforest.entity.monster;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import org.jetbrains.annotations.Nullable;
import twilightforest.data.tags.BlockTagGenerator;
import twilightforest.init.TFDamageTypes;
import twilightforest.init.TFItems;
import twilightforest.util.entities.EntityUtil;

import java.util.List;

public class GiantMiner extends Monster {

	public GiantMiner(EntityType<? extends GiantMiner> type, Level world) {
		super(type, world);

		for (EquipmentSlot slot : EquipmentSlot.values()) {
			this.setDropChance(slot, 0);
		}
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(1, new FloatGoal(this));
		this.goalSelector.addGoal(4, new MeleeAttackGoal(this, 1.0D, false));
		this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.0D));
		this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0F));
		this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
		this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
	}

	public static AttributeSupplier.Builder registerAttributes() {
		return Monster.createMonsterAttributes()
			.add(Attributes.MAX_HEALTH, 80.0D)
			.add(Attributes.MOVEMENT_SPEED, 0.23D)
			.add(Attributes.ATTACK_DAMAGE, 2.0D)
			.add(Attributes.FOLLOW_RANGE, 40.0D)
			.add(Attributes.STEP_HEIGHT, 1.2D);
	}

	@Nullable
	@Override
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor accessor, DifficultyInstance difficulty, EntitySpawnReason reason, @Nullable SpawnGroupData spawnDataIn) {
		SpawnGroupData data = super.finalizeSpawn(accessor, difficulty, reason, spawnDataIn);
		populateDefaultEquipmentSlots(accessor.getRandom(), difficulty);
		populateDefaultEquipmentEnchantments(accessor, accessor.getRandom(), difficulty);

		return data;
	}

	@Override
	protected void populateDefaultEquipmentSlots(RandomSource random, DifficultyInstance difficulty) {
		this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(TFItems.GIANT_PICKAXE.get()));
	}

	@Override
	protected void enchantSpawnedWeapon(ServerLevelAccessor accessor, RandomSource random, DifficultyInstance instance) {
	}

	@Override
	protected void enchantSpawnedArmor(ServerLevelAccessor accessor, RandomSource random, EquipmentSlot slot, DifficultyInstance instance) {

	}

	@Override
	public boolean doHurtTarget(ServerLevel level, Entity entity) {
		return EntityUtil.properlyApplyCustomDamageSource(level, this, entity, TFDamageTypes.getEntityDamageSource(this.level(), TFDamageTypes.ANT, this), null);
	}

	@Override
	public int getMaxSpawnClusterSize() {
		return 1;
	}

	@Override
	public boolean checkSpawnRules(LevelAccessor accessor, EntitySpawnReason reason) {
		List<GiantMiner> giantsNearby = accessor.getEntitiesOfClass(GiantMiner.class, this.getBoundingBox().inflate(100, 10, 100));
		return giantsNearby.size() < 5;
	}

	public static boolean checkGiantSpawnRules(EntityType<? extends GiantMiner> type, ServerLevelAccessor accessor, EntitySpawnReason reason, BlockPos pos, RandomSource rand) {
		return accessor.getBlockState(pos.below()).is(BlockTagGenerator.GIANTS_SPAWNABLE_ON);
	}

	@Override
	protected boolean canRide(Entity entity) {
		return false;
	}
}

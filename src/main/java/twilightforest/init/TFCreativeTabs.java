package twilightforest.init;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.EnchantedBookItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import twilightforest.TFConfig;
import twilightforest.TFRegistries;
import twilightforest.TwilightForestMod;
import twilightforest.entity.MagicPainting;
import twilightforest.entity.MagicPaintingVariant;

import java.util.Collection;
import java.util.Comparator;

public class TFCreativeTabs {

	public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, TwilightForestMod.ID);

	public static final DeferredHolder<CreativeModeTab, CreativeModeTab> BLOCKS = TABS.register("blocks", () -> CreativeModeTab.builder()
			.title(Component.translatable("itemGroup.twilightforest.blocks"))
			.icon(() -> new ItemStack(TFBlocks.NAGA_COURTYARD_MINIATURE_STRUCTURE.value()))
			.displayItems((parameters, output) -> {
				output.accept(TFBlocks.TWILIGHT_OAK_PLANKS.value());
				output.accept(TFBlocks.CANOPY_PLANKS.value());
				output.accept(TFBlocks.MANGROVE_PLANKS.value());
				output.accept(TFBlocks.DARK_PLANKS.value());
				output.accept(TFBlocks.TIME_PLANKS.value());
				output.accept(TFBlocks.TRANSFORMATION_PLANKS.value());
				output.accept(TFBlocks.MINING_PLANKS.value());
				output.accept(TFBlocks.SORTING_PLANKS.value());
				output.accept(TFBlocks.TWILIGHT_OAK_LEAVES.value());
				output.accept(TFBlocks.TWILIGHT_OAK_STAIRS.value());
				output.accept(TFBlocks.CANOPY_STAIRS.value());
				output.accept(TFBlocks.MANGROVE_STAIRS.value());
				output.accept(TFBlocks.DARK_STAIRS.value());
				output.accept(TFBlocks.TIME_STAIRS.value());
				output.accept(TFBlocks.TRANSFORMATION_STAIRS.value());
				output.accept(TFBlocks.MINING_STAIRS.value());
				output.accept(TFBlocks.SORTING_STAIRS.value());
				output.accept(TFBlocks.CANOPY_LEAVES.value());
				output.accept(TFBlocks.TWILIGHT_OAK_SLAB.value());
				output.accept(TFBlocks.CANOPY_SLAB.value());
				output.accept(TFBlocks.MANGROVE_SLAB.value());
				output.accept(TFBlocks.DARK_SLAB.value());
				output.accept(TFBlocks.TIME_SLAB.value());
				output.accept(TFBlocks.TRANSFORMATION_SLAB.value());
				output.accept(TFBlocks.MINING_SLAB.value());
				output.accept(TFBlocks.SORTING_SLAB.value());
				output.accept(TFBlocks.MANGROVE_LEAVES.value());
				output.accept(TFBlocks.TWILIGHT_OAK_FENCE.value());
				output.accept(TFBlocks.CANOPY_FENCE.value());
				output.accept(TFBlocks.MANGROVE_FENCE.value());
				output.accept(TFBlocks.DARK_FENCE.value());
				output.accept(TFBlocks.TIME_FENCE.value());
				output.accept(TFBlocks.TRANSFORMATION_FENCE.value());
				output.accept(TFBlocks.MINING_FENCE.value());
				output.accept(TFBlocks.SORTING_FENCE.value());
				output.accept(TFBlocks.DARK_LEAVES.value());
				output.accept(TFBlocks.TWILIGHT_OAK_BANISTER.value());
				output.accept(TFBlocks.CANOPY_BANISTER.value());
				output.accept(TFBlocks.MANGROVE_BANISTER.value());
				output.accept(TFBlocks.DARK_BANISTER.value());
				output.accept(TFBlocks.TIME_BANISTER.value());
				output.accept(TFBlocks.TRANSFORMATION_BANISTER.value());
				output.accept(TFBlocks.MINING_BANISTER.value());
				output.accept(TFBlocks.SORTING_BANISTER.value());
				output.accept(TFBlocks.TIME_LEAVES.value());
				output.accept(TFBlocks.TWILIGHT_OAK_GATE.value());
				output.accept(TFBlocks.CANOPY_GATE.value());
				output.accept(TFBlocks.MANGROVE_GATE.value());
				output.accept(TFBlocks.DARK_GATE.value());
				output.accept(TFBlocks.TIME_GATE.value());
				output.accept(TFBlocks.TRANSFORMATION_GATE.value());
				output.accept(TFBlocks.MINING_GATE.value());
				output.accept(TFBlocks.SORTING_GATE.value());
				output.accept(TFBlocks.TRANSFORMATION_LEAVES.value());
				output.accept(TFBlocks.TWILIGHT_OAK_TRAPDOOR.value());
				output.accept(TFBlocks.CANOPY_TRAPDOOR.value());
				output.accept(TFBlocks.MANGROVE_TRAPDOOR.value());
				output.accept(TFBlocks.DARK_TRAPDOOR.value());
				output.accept(TFBlocks.TIME_TRAPDOOR.value());
				output.accept(TFBlocks.TRANSFORMATION_TRAPDOOR.value());
				output.accept(TFBlocks.MINING_TRAPDOOR.value());
				output.accept(TFBlocks.SORTING_TRAPDOOR.value());
				output.accept(TFBlocks.MINING_LEAVES.value());
				output.accept(TFBlocks.TWILIGHT_OAK_PLATE.value());
				output.accept(TFBlocks.CANOPY_PLATE.value());
				output.accept(TFBlocks.MANGROVE_PLATE.value());
				output.accept(TFBlocks.DARK_PLATE.value());
				output.accept(TFBlocks.TIME_PLATE.value());
				output.accept(TFBlocks.TRANSFORMATION_PLATE.value());
				output.accept(TFBlocks.MINING_PLATE.value());
				output.accept(TFBlocks.SORTING_PLATE.value());
				output.accept(TFBlocks.SORTING_LEAVES.value());
				output.accept(TFBlocks.TWILIGHT_OAK_BUTTON.value());
				output.accept(TFBlocks.CANOPY_BUTTON.value());
				output.accept(TFBlocks.MANGROVE_BUTTON.value());
				output.accept(TFBlocks.DARK_BUTTON.value());
				output.accept(TFBlocks.TIME_BUTTON.value());
				output.accept(TFBlocks.TRANSFORMATION_BUTTON.value());
				output.accept(TFBlocks.MINING_BUTTON.value());
				output.accept(TFBlocks.SORTING_BUTTON.value());
				output.accept(TFBlocks.RAINBOW_OAK_LEAVES.value());
				output.accept(TFBlocks.TWILIGHT_OAK_DOOR.value());
				output.accept(TFBlocks.CANOPY_DOOR.value());
				output.accept(TFBlocks.MANGROVE_DOOR.value());
				output.accept(TFBlocks.DARK_DOOR.value());
				output.accept(TFBlocks.TIME_DOOR.value());
				output.accept(TFBlocks.TRANSFORMATION_DOOR.value());
				output.accept(TFBlocks.MINING_DOOR.value());
				output.accept(TFBlocks.SORTING_DOOR.value());
				output.accept(TFBlocks.TWILIGHT_OAK_SAPLING.value());
				output.accept(TFItems.TWILIGHT_OAK_SIGN.value());
				output.accept(TFItems.CANOPY_SIGN.value());
				output.accept(TFItems.MANGROVE_SIGN.value());
				output.accept(TFItems.DARK_SIGN.value());
				output.accept(TFItems.TIME_SIGN.value());
				output.accept(TFItems.TRANSFORMATION_SIGN.value());
				output.accept(TFItems.MINING_SIGN.value());
				output.accept(TFItems.SORTING_SIGN.value());
				output.accept(TFBlocks.CANOPY_SAPLING.value());
				output.accept(TFItems.TWILIGHT_OAK_HANGING_SIGN.value());
				output.accept(TFItems.CANOPY_HANGING_SIGN.value());
				output.accept(TFItems.MANGROVE_HANGING_SIGN.value());
				output.accept(TFItems.DARK_HANGING_SIGN.value());
				output.accept(TFItems.TIME_HANGING_SIGN.value());
				output.accept(TFItems.TRANSFORMATION_HANGING_SIGN.value());
				output.accept(TFItems.MINING_HANGING_SIGN.value());
				output.accept(TFItems.SORTING_HANGING_SIGN.value());
				output.accept(TFBlocks.MANGROVE_SAPLING.value());
				output.accept(TFBlocks.TWILIGHT_OAK_CHEST.value());
				output.accept(TFBlocks.CANOPY_CHEST.value());
				output.accept(TFBlocks.MANGROVE_CHEST.value());
				output.accept(TFBlocks.DARK_CHEST.value());
				output.accept(TFBlocks.TIME_CHEST.value());
				output.accept(TFBlocks.TRANSFORMATION_CHEST.value());
				output.accept(TFBlocks.MINING_CHEST.value());
				output.accept(TFBlocks.SORTING_CHEST.value());
				output.accept(TFBlocks.DARKWOOD_SAPLING.value());
				output.accept(TFBlocks.TWILIGHT_OAK_WOOD.value());
				output.accept(TFBlocks.CANOPY_WOOD.value());
				output.accept(TFBlocks.MANGROVE_WOOD.value());
				output.accept(TFBlocks.DARK_WOOD.value());
				output.accept(TFBlocks.TIME_WOOD.value());
				output.accept(TFBlocks.TRANSFORMATION_WOOD.value());
				output.accept(TFBlocks.MINING_WOOD.value());
				output.accept(TFBlocks.SORTING_WOOD.value());
				output.accept(TFBlocks.TIME_SAPLING.value());
				output.accept(TFBlocks.TWILIGHT_OAK_LOG.value());
				output.accept(TFBlocks.CANOPY_LOG.value());
				output.accept(TFBlocks.MANGROVE_LOG.value());
				output.accept(TFBlocks.DARK_LOG.value());
				output.accept(TFBlocks.TIME_LOG.value());
				output.accept(TFBlocks.TRANSFORMATION_LOG.value());
				output.accept(TFBlocks.MINING_LOG.value());
				output.accept(TFBlocks.SORTING_LOG.value());
				output.accept(TFBlocks.TRANSFORMATION_SAPLING.value());
				output.accept(TFBlocks.STRIPPED_TWILIGHT_OAK_LOG.value());
				output.accept(TFBlocks.STRIPPED_CANOPY_LOG.value());
				output.accept(TFBlocks.STRIPPED_MANGROVE_LOG.value());
				output.accept(TFBlocks.STRIPPED_DARK_LOG.value());
				output.accept(TFBlocks.STRIPPED_TIME_LOG.value());
				output.accept(TFBlocks.STRIPPED_TRANSFORMATION_LOG.value());
				output.accept(TFBlocks.STRIPPED_MINING_LOG.value());
				output.accept(TFBlocks.STRIPPED_SORTING_LOG.value());
				output.accept(TFBlocks.MINING_SAPLING.value());
				output.accept(TFBlocks.STRIPPED_TWILIGHT_OAK_WOOD.value());
				output.accept(TFBlocks.STRIPPED_CANOPY_WOOD.value());
				output.accept(TFBlocks.STRIPPED_MANGROVE_WOOD.value());
				output.accept(TFBlocks.STRIPPED_DARK_WOOD.value());
				output.accept(TFBlocks.STRIPPED_TIME_WOOD.value());
				output.accept(TFBlocks.STRIPPED_TRANSFORMATION_WOOD.value());
				output.accept(TFBlocks.STRIPPED_MINING_WOOD.value());
				output.accept(TFBlocks.STRIPPED_SORTING_WOOD.value());
				output.accept(TFBlocks.SORTING_SAPLING.value());
				output.accept(TFBlocks.ROOT_BLOCK.value());
				output.accept(TFBlocks.LIVEROOT_BLOCK.value());
				output.accept(TFBlocks.MANGROVE_ROOT.value());
				output.accept(TFBlocks.CANOPY_BOOKSHELF.value());
				output.accept(TFBlocks.TIME_LOG_CORE.value());
				output.accept(TFBlocks.TRANSFORMATION_LOG_CORE.value());
				output.accept(TFBlocks.MINING_LOG_CORE.value());
				output.accept(TFBlocks.SORTING_LOG_CORE.value());
				output.accept(TFBlocks.RAINBOW_OAK_SAPLING.value());
				output.accept(TFItems.HOLLOW_TWILIGHT_OAK_LOG.value());
				output.accept(TFItems.HOLLOW_CANOPY_LOG.value());
				output.accept(TFItems.HOLLOW_MANGROVE_LOG.value());
				output.accept(TFItems.HOLLOW_DARK_LOG.value());
				output.accept(TFItems.HOLLOW_TIME_LOG.value());
				output.accept(TFItems.HOLLOW_TRANSFORMATION_LOG.value());
				output.accept(TFItems.HOLLOW_MINING_LOG.value());
				output.accept(TFItems.HOLLOW_SORTING_LOG.value());
				output.accept(TFBlocks.HOLLOW_OAK_SAPLING.value());
				output.accept(TFBlocks.OAK_BANISTER.value());
				output.accept(TFBlocks.SPRUCE_BANISTER.value());
				output.accept(TFBlocks.BIRCH_BANISTER.value());
				output.accept(TFBlocks.JUNGLE_BANISTER.value());
				output.accept(TFBlocks.ACACIA_BANISTER.value());
				output.accept(TFBlocks.DARK_OAK_BANISTER.value());
				output.accept(TFBlocks.CRIMSON_BANISTER.value());
				output.accept(TFBlocks.WARPED_BANISTER.value());
				output.accept(TFBlocks.VANGROVE_BANISTER.value());
				output.accept(TFBlocks.BAMBOO_BANISTER.value());
				output.accept(TFBlocks.CHERRY_BANISTER.value());
				output.accept(TFItems.HOLLOW_OAK_LOG.value());
				output.accept(TFItems.HOLLOW_SPRUCE_LOG.value());
				output.accept(TFItems.HOLLOW_BIRCH_LOG.value());
				output.accept(TFItems.HOLLOW_JUNGLE_LOG.value());
				output.accept(TFItems.HOLLOW_ACACIA_LOG.value());
				output.accept(TFItems.HOLLOW_DARK_OAK_LOG.value());
				output.accept(TFItems.HOLLOW_CRIMSON_STEM.value());
				output.accept(TFItems.HOLLOW_WARPED_STEM.value());
				output.accept(TFItems.HOLLOW_VANGROVE_LOG.value());
				output.accept(TFItems.HOLLOW_CHERRY_LOG.value());
				output.accept(TFBlocks.MOSS_PATCH.value());
				output.accept(TFBlocks.MAYAPPLE.value());
				output.accept(TFBlocks.CLOVER_PATCH.value());
				output.accept(TFBlocks.FIDDLEHEAD.value());
				output.accept(TFBlocks.MUSHGLOOM.value());
				output.accept(TFBlocks.TORCHBERRY_PLANT.value());
				output.accept(TFItems.FALLEN_LEAVES.value());
				output.accept(TFBlocks.ROOT_STRAND.value());
				output.accept(TFBlocks.TROLLVIDR.value());
				output.accept(TFBlocks.UNRIPE_TROLLBER.value());
				output.accept(TFBlocks.TROLLBER.value());
				output.accept(TFBlocks.HUGE_STALK.value());
				output.accept(TFBlocks.BEANSTALK_LEAVES.value());
				output.accept(TFItems.HUGE_WATER_LILY.value());
				output.accept(TFItems.HUGE_LILY_PAD.value());
				output.accept(TFBlocks.BROWN_THORNS.value());
				output.accept(TFBlocks.GREEN_THORNS.value());
				output.accept(TFBlocks.BURNT_THORNS.value());
				output.accept(TFBlocks.THORN_ROSE.value());
				output.accept(TFBlocks.THORN_LEAVES.value());
				output.accept(TFBlocks.HEDGE.value());
				output.accept(TFBlocks.FIREFLY_JAR.value());
				output.accept(TFBlocks.CICADA_JAR.value());
				output.accept(TFBlocks.FIREFLY_SPAWNER.value());
				output.accept(TFItems.FIREFLY.value());
				output.accept(TFItems.CICADA.value());
				output.accept(TFItems.MOONWORM.value());
				output.accept(TFBlocks.IRONWOOD_BLOCK.value());
				output.accept(TFBlocks.STEELEAF_BLOCK.value());
				output.accept(TFBlocks.FIERY_BLOCK.value());
				output.accept(TFBlocks.KNIGHTMETAL_BLOCK.value());
				output.accept(TFBlocks.CARMINITE_BLOCK.value());
				output.accept(TFBlocks.ARCTIC_FUR_BLOCK.value());
				output.accept(TFItems.NAGA_TROPHY.value());
				output.accept(TFItems.LICH_TROPHY.value());
				output.accept(TFItems.MINOSHROOM_TROPHY.value());
				output.accept(TFItems.HYDRA_TROPHY.value());
				output.accept(TFItems.KNIGHT_PHANTOM_TROPHY.value());
				output.accept(TFItems.UR_GHAST_TROPHY.value());
				output.accept(TFItems.ALPHA_YETI_TROPHY.value());
				output.accept(TFItems.SNOW_QUEEN_TROPHY.value());
				output.accept(TFItems.QUEST_RAM_TROPHY.value());
				output.accept(TFBlocks.NAGA_BOSS_SPAWNER.value());
				output.accept(TFBlocks.LICH_BOSS_SPAWNER.value());
				output.accept(TFBlocks.MINOSHROOM_BOSS_SPAWNER.value());
				output.accept(TFBlocks.HYDRA_BOSS_SPAWNER.value());
				output.accept(TFBlocks.KNIGHT_PHANTOM_BOSS_SPAWNER.value());
				output.accept(TFBlocks.UR_GHAST_BOSS_SPAWNER.value());
				output.accept(TFBlocks.ALPHA_YETI_BOSS_SPAWNER.value());
				output.accept(TFBlocks.SNOW_QUEEN_BOSS_SPAWNER.value());
				output.accept(TFBlocks.FINAL_BOSS_BOSS_SPAWNER.value());
				output.accept(TFBlocks.TWILIGHT_PORTAL_MINIATURE_STRUCTURE.value());
				output.accept(TFBlocks.NAGA_COURTYARD_MINIATURE_STRUCTURE.value());
				output.accept(TFBlocks.LICH_TOWER_MINIATURE_STRUCTURE.value());
				output.accept(TFBlocks.ETCHED_NAGASTONE.value());
				output.accept(TFBlocks.CRACKED_ETCHED_NAGASTONE.value());
				output.accept(TFBlocks.MOSSY_ETCHED_NAGASTONE.value());
				output.accept(TFBlocks.NAGASTONE_PILLAR.value());
				output.accept(TFBlocks.CRACKED_NAGASTONE_PILLAR.value());
				output.accept(TFBlocks.MOSSY_NAGASTONE_PILLAR.value());
				output.accept(TFBlocks.NAGASTONE_STAIRS_LEFT.value());
				output.accept(TFBlocks.CRACKED_NAGASTONE_STAIRS_LEFT.value());
				output.accept(TFBlocks.MOSSY_NAGASTONE_STAIRS_LEFT.value());
				output.accept(TFBlocks.NAGASTONE_STAIRS_RIGHT.value());
				output.accept(TFBlocks.CRACKED_NAGASTONE_STAIRS_RIGHT.value());
				output.accept(TFBlocks.MOSSY_NAGASTONE_STAIRS_RIGHT.value());
				output.accept(TFBlocks.NAGASTONE_HEAD.value());
				output.accept(TFBlocks.NAGASTONE.value());
				output.accept(TFBlocks.SPIRAL_BRICKS.value());
				output.accept(TFBlocks.BOLD_STONE_PILLAR.value());
				output.accept(TFBlocks.TWISTED_STONE.value());
				output.accept(TFBlocks.TWISTED_STONE_PILLAR.value());
				output.accept(TFBlocks.CANDELABRA.value());
				output.accept(TFBlocks.WROUGHT_IRON_FENCE.value());
				makeSkullCandle(output, TFItems.ZOMBIE_SKULL_CANDLE.value());
				makeSkullCandle(output, TFItems.SKELETON_SKULL_CANDLE.value());
				makeSkullCandle(output, TFItems.WITHER_SKELETON_SKULL_CANDLE.value());
				makeSkullCandle(output, TFItems.CREEPER_SKULL_CANDLE.value());
				makeSkullCandle(output, TFItems.PLAYER_SKULL_CANDLE.value());
				makeSkullCandle(output, TFItems.PIGLIN_SKULL_CANDLE.value());
				output.accept(TFBlocks.DEATH_TOME_SPAWNER.value());
				output.accept(TFBlocks.EMPTY_CANOPY_BOOKSHELF.value());
				output.accept(TFBlocks.KEEPSAKE_CASKET.value());
				output.accept(TFBlocks.SMOKER.value());
				output.accept(TFBlocks.FIRE_JET.value());
				output.accept(TFBlocks.MAZESTONE.value());
				output.accept(TFBlocks.MAZESTONE_BRICK.value());
				output.accept(TFBlocks.CRACKED_MAZESTONE.value());
				output.accept(TFBlocks.MOSSY_MAZESTONE.value());
				output.accept(TFBlocks.DECORATIVE_MAZESTONE.value());
				output.accept(TFBlocks.CUT_MAZESTONE.value());
				output.accept(TFBlocks.MAZESTONE_MOSAIC.value());
				output.accept(TFBlocks.MAZESTONE_BORDER.value());
				output.accept(TFBlocks.UNDERBRICK.value());
				output.accept(TFBlocks.CRACKED_UNDERBRICK.value());
				output.accept(TFBlocks.MOSSY_UNDERBRICK.value());
				output.accept(TFBlocks.UNDERBRICK_FLOOR.value());
				output.accept(TFBlocks.STRONGHOLD_SHIELD.value());
				output.accept(TFBlocks.TROPHY_PEDESTAL.value());
				output.accept(TFBlocks.TOWERWOOD.value());
				output.accept(TFBlocks.CRACKED_TOWERWOOD.value());
				output.accept(TFBlocks.MOSSY_TOWERWOOD.value());
				output.accept(TFBlocks.INFESTED_TOWERWOOD.value());
				output.accept(TFBlocks.ENCASED_TOWERWOOD.value());
				output.accept(TFBlocks.ENCASED_SMOKER.value());
				output.accept(TFBlocks.ENCASED_FIRE_JET.value());
				output.accept(TFBlocks.GHAST_TRAP.value());
				output.accept(TFBlocks.VANISHING_BLOCK.value());
				output.accept(TFBlocks.REAPPEARING_BLOCK.value());
				output.accept(TFBlocks.LOCKED_VANISHING_BLOCK.value());
				output.accept(TFBlocks.CARMINITE_BUILDER.value());
				output.accept(TFBlocks.ANTIBUILDER.value());
				output.accept(TFBlocks.CARMINITE_REACTOR.value());
				output.accept(TFBlocks.AURORA_BLOCK.value());
				output.accept(TFBlocks.AURORA_PILLAR.value());
				output.accept(TFBlocks.AURORA_SLAB.value());
				output.accept(TFBlocks.AURORALIZED_GLASS.value());
				output.accept(TFBlocks.TROLLSTEINN.value());
				output.accept(TFBlocks.UBEROUS_SOIL.value());
				output.accept(TFBlocks.HUGE_MUSHGLOOM.value());
				output.accept(TFBlocks.HUGE_MUSHGLOOM_STEM.value());
				output.accept(TFBlocks.WISPY_CLOUD.value());
				output.accept(TFBlocks.FLUFFY_CLOUD.value());
				output.accept(TFBlocks.RAINY_CLOUD.value());
				output.accept(TFBlocks.SNOWY_CLOUD.value());
				output.accept(TFBlocks.GIANT_COBBLESTONE.value());
				output.accept(TFBlocks.GIANT_LOG.value());
				output.accept(TFBlocks.GIANT_LEAVES.value());
				output.accept(TFBlocks.GIANT_OBSIDIAN.value());
				output.accept(TFBlocks.DEADROCK.value());
				output.accept(TFBlocks.CRACKED_DEADROCK.value());
				output.accept(TFBlocks.WEATHERED_DEADROCK.value());
				output.accept(TFBlocks.CASTLE_BRICK.value());
				output.accept(TFBlocks.WORN_CASTLE_BRICK.value());
				output.accept(TFBlocks.CRACKED_CASTLE_BRICK.value());
				output.accept(TFBlocks.MOSSY_CASTLE_BRICK.value());
				output.accept(TFBlocks.THICK_CASTLE_BRICK.value());
				output.accept(TFBlocks.CASTLE_ROOF_TILE.value());
				output.accept(TFBlocks.ENCASED_CASTLE_BRICK_PILLAR.value());
				output.accept(TFBlocks.ENCASED_CASTLE_BRICK_TILE.value());
				output.accept(TFBlocks.BOLD_CASTLE_BRICK_PILLAR.value());
				output.accept(TFBlocks.BOLD_CASTLE_BRICK_TILE.value());
				output.accept(TFBlocks.CASTLE_BRICK_STAIRS.value());
				output.accept(TFBlocks.WORN_CASTLE_BRICK_STAIRS.value());
				output.accept(TFBlocks.CRACKED_CASTLE_BRICK_STAIRS.value());
				output.accept(TFBlocks.MOSSY_CASTLE_BRICK_STAIRS.value());
				output.accept(TFBlocks.ENCASED_CASTLE_BRICK_STAIRS.value());
				output.accept(TFBlocks.BOLD_CASTLE_BRICK_STAIRS.value());
				output.accept(TFBlocks.PINK_CASTLE_RUNE_BRICK.value());
				output.accept(TFBlocks.YELLOW_CASTLE_RUNE_BRICK.value());
				output.accept(TFBlocks.BLUE_CASTLE_RUNE_BRICK.value());
				output.accept(TFBlocks.VIOLET_CASTLE_RUNE_BRICK.value());
				output.accept(TFBlocks.PINK_CASTLE_DOOR.value());
				output.accept(TFBlocks.YELLOW_CASTLE_DOOR.value());
				output.accept(TFBlocks.BLUE_CASTLE_DOOR.value());
				output.accept(TFBlocks.VIOLET_CASTLE_DOOR.value());
				output.accept(TFBlocks.PINK_FORCE_FIELD.value());
				output.accept(TFBlocks.ORANGE_FORCE_FIELD.value());
				output.accept(TFBlocks.GREEN_FORCE_FIELD.value());
				output.accept(TFBlocks.BLUE_FORCE_FIELD.value());
				output.accept(TFBlocks.VIOLET_FORCE_FIELD.value());
				output.accept(TFBlocks.CINDER_FURNACE.value());
				output.accept(TFBlocks.CINDER_LOG.value());
				output.accept(TFBlocks.CINDER_WOOD.value());
				output.accept(TFBlocks.SLIDER.value());
				output.accept(TFBlocks.UNCRAFTING_TABLE.value());
				output.accept(TFBlocks.IRON_LADDER.value());
				output.accept(TFBlocks.CORD.value());
			}).build());

	public static final DeferredHolder<CreativeModeTab, CreativeModeTab> ITEMS = TABS.register("items", () -> CreativeModeTab.builder()
			.withTabsBefore(BLOCKS.getKey())
			.title(Component.translatable("itemGroup.twilightforest.items"))
			.icon(() -> new ItemStack(TFBlocks.TWILIGHT_PORTAL_MINIATURE_STRUCTURE.value()))
			.displayItems((parameters, output) -> {
				output.accept(TFItems.TORCHBERRIES.value());
				output.accept(TFItems.RAVEN_FEATHER.value());
				output.accept(TFItems.MAGIC_MAP_FOCUS.value());
				output.accept(TFItems.MAZE_MAP_FOCUS.value());
				output.accept(TFItems.MAGIC_MAP.value());
				output.accept(TFItems.MAZE_MAP.value());
				output.accept(TFItems.ORE_MAP.value());
				output.accept(TFItems.ORE_METER.value());
				output.accept(TFItems.MOON_DIAL.value());
				output.accept(TFItems.POCKET_WATCH.value());
				output.accept(TFItems.LIVEROOT.value());
				output.accept(TFItems.RAW_IRONWOOD.value());
				output.accept(TFItems.IRONWOOD_INGOT.value());
				output.accept(TFItems.STEELEAF_INGOT.value());
				output.accept(TFItems.NAGA_SCALE.value());
				output.accept(TFItems.ARMOR_SHARD.value());
				output.accept(TFItems.ARMOR_SHARD_CLUSTER.value());
				output.accept(TFItems.KNIGHTMETAL_INGOT.value());
				output.accept(TFItems.KNIGHTMETAL_RING.value());
				output.accept(TFItems.FIERY_BLOOD.value());
				output.accept(TFItems.FIERY_TEARS.value());
				output.accept(TFItems.FIERY_INGOT.value());
				output.accept(TFItems.ARCTIC_FUR.value());
				output.accept(TFItems.ALPHA_YETI_FUR.value());
				output.accept(TFItems.RAW_VENISON.value());
				output.accept(TFItems.COOKED_VENISON.value());
				output.accept(TFItems.RAW_MEEF.value());
				output.accept(TFItems.COOKED_MEEF.value());
				output.accept(TFItems.MAZE_WAFER.value());
				output.accept(TFItems.MEEF_STROGANOFF.value());
				output.accept(TFItems.HYDRA_CHOP.value());
				output.accept(TFItems.EXPERIMENT_115.value());
				output.accept(TFItems.CHARM_OF_LIFE_1.value());
				output.accept(TFItems.CHARM_OF_LIFE_2.value());
				output.accept(TFItems.CHARM_OF_KEEPING_1.value());
				output.accept(TFItems.CHARM_OF_KEEPING_2.value());
				output.accept(TFItems.CHARM_OF_KEEPING_3.value());
				output.accept(TFItems.BORER_ESSENCE.value());
				output.accept(TFItems.CARMINITE.value());
				output.accept(TFItems.TOWER_KEY.value());
				output.accept(TFItems.TRANSFORMATION_POWDER.value());
				output.accept(TFItems.BRITTLE_FLASK.value());
				output.accept(TFItems.GREATER_FLASK.value());
				output.accept(TFBlocks.RED_THREAD.value());
				output.accept(TFItems.MAGIC_BEANS.value());
				output.accept(TFItems.CUBE_TALISMAN.value());
				output.accept(TFItems.MUSIC_DISC_THREAD.value());
				output.accept(TFItems.MUSIC_DISC_FINDINGS.value());
				output.accept(TFItems.MUSIC_DISC_RADIANCE.value());
				output.accept(TFItems.MUSIC_DISC_STEPS.value());
				output.accept(TFItems.MUSIC_DISC_MOTION.value());
				output.accept(TFItems.MUSIC_DISC_WAYFARER.value());
				output.accept(TFItems.MUSIC_DISC_HOME.value());
				output.accept(TFItems.MUSIC_DISC_MAKER.value());
				output.accept(TFItems.MUSIC_DISC_SUPERSTITIOUS.value());
				parameters.holders().lookup(TFRegistries.Keys.MAGIC_PAINTINGS).ifPresent((lookup) -> createPaintings(output, lookup));
				output.accept(TFItems.NAGA_BANNER_PATTERN.value());
				output.accept(TFItems.LICH_BANNER_PATTERN.value());
				output.accept(TFItems.MINOSHROOM_BANNER_PATTERN.value());
				output.accept(TFItems.HYDRA_BANNER_PATTERN.value());
				output.accept(TFItems.KNIGHT_PHANTOM_BANNER_PATTERN.value());
				output.accept(TFItems.UR_GHAST_BANNER_PATTERN.value());
				output.accept(TFItems.ALPHA_YETI_BANNER_PATTERN.value());
				output.accept(TFItems.SNOW_QUEEN_BANNER_PATTERN.value());
				output.accept(TFItems.QUEST_RAM_BANNER_PATTERN.value());
				output.accept(TFItems.TWILIGHT_OAK_BOAT.value());
				output.accept(TFItems.CANOPY_BOAT.value());
				output.accept(TFItems.MANGROVE_BOAT.value());
				output.accept(TFItems.DARK_BOAT.value());
				output.accept(TFItems.TIME_BOAT.value());
				output.accept(TFItems.TRANSFORMATION_BOAT.value());
				output.accept(TFItems.MINING_BOAT.value());
				output.accept(TFItems.SORTING_BOAT.value());
				output.accept(TFItems.TWILIGHT_OAK_CHEST_BOAT.value());
				output.accept(TFItems.CANOPY_CHEST_BOAT.value());
				output.accept(TFItems.MANGROVE_CHEST_BOAT.value());
				output.accept(TFItems.DARK_CHEST_BOAT.value());
				output.accept(TFItems.TIME_CHEST_BOAT.value());
				output.accept(TFItems.TRANSFORMATION_CHEST_BOAT.value());
				output.accept(TFItems.MINING_CHEST_BOAT.value());
				output.accept(TFItems.SORTING_CHEST_BOAT.value());
				createSpawnEggsAlphabetical(output);
			}).build());

	public static final DeferredHolder<CreativeModeTab, CreativeModeTab> EQUIPMENT = TABS.register("equipment", () -> CreativeModeTab.builder()
			.withTabsBefore(ITEMS.getKey())
			.title(Component.translatable("itemGroup.twilightforest.equipment"))
			.icon(() -> new ItemStack(TFItems.KNIGHTMETAL_PICKAXE.value()))
			.displayItems((parameters, output) -> {
				generateGearWithEnchants(output, TFItems.IRONWOOD_HELMET.value(), new EnchantmentInstance(Enchantments.AQUA_AFFINITY, 1));
				generateGearWithEnchants(output, TFItems.IRONWOOD_CHESTPLATE.value(), new EnchantmentInstance(Enchantments.ALL_DAMAGE_PROTECTION, 1));
				generateGearWithEnchants(output, TFItems.IRONWOOD_LEGGINGS.value(), new EnchantmentInstance(Enchantments.ALL_DAMAGE_PROTECTION, 1));
				generateGearWithEnchants(output, TFItems.IRONWOOD_BOOTS.value(), new EnchantmentInstance(Enchantments.FALL_PROTECTION, 1));
				generateGearWithEnchants(output, TFItems.IRONWOOD_SWORD.value(), new EnchantmentInstance(Enchantments.UNBREAKING, 1));
				generateGearWithEnchants(output, TFItems.IRONWOOD_SHOVEL.value(), new EnchantmentInstance(Enchantments.UNBREAKING, 1));
				generateGearWithEnchants(output, TFItems.IRONWOOD_PICKAXE.value(), new EnchantmentInstance(Enchantments.BLOCK_EFFICIENCY, 1));
				generateGearWithEnchants(output, TFItems.IRONWOOD_AXE.value(), new EnchantmentInstance(Enchantments.BLOCK_FORTUNE, 1));
				generateGearWithEnchants(output, TFItems.IRONWOOD_HOE.value(), new EnchantmentInstance(Enchantments.BLOCK_EFFICIENCY, 1));
				generateGearWithEnchants(output, TFItems.STEELEAF_HELMET.value(), new EnchantmentInstance(Enchantments.PROJECTILE_PROTECTION, 2));
				generateGearWithEnchants(output, TFItems.STEELEAF_CHESTPLATE.value(), new EnchantmentInstance(Enchantments.BLAST_PROTECTION, 2));
				generateGearWithEnchants(output, TFItems.STEELEAF_LEGGINGS.value(), new EnchantmentInstance(Enchantments.FIRE_PROTECTION, 2));
				generateGearWithEnchants(output, TFItems.STEELEAF_BOOTS.value(), new EnchantmentInstance(Enchantments.FALL_PROTECTION, 2));
				generateGearWithEnchants(output, TFItems.STEELEAF_SWORD.value(), new EnchantmentInstance(Enchantments.MOB_LOOTING, 2));
				generateGearWithEnchants(output, TFItems.STEELEAF_SHOVEL.value(), new EnchantmentInstance(Enchantments.BLOCK_EFFICIENCY, 2));
				generateGearWithEnchants(output, TFItems.STEELEAF_PICKAXE.value(), new EnchantmentInstance(Enchantments.BLOCK_FORTUNE, 2));
				generateGearWithEnchants(output, TFItems.STEELEAF_AXE.value(), new EnchantmentInstance(Enchantments.BLOCK_EFFICIENCY, 2));
				generateGearWithEnchants(output, TFItems.STEELEAF_HOE.value(), new EnchantmentInstance(Enchantments.BLOCK_FORTUNE, 2));
				output.accept(TFItems.KNIGHTMETAL_HELMET.value());
				output.accept(TFItems.KNIGHTMETAL_CHESTPLATE.value());
				output.accept(TFItems.KNIGHTMETAL_LEGGINGS.value());
				output.accept(TFItems.KNIGHTMETAL_BOOTS.value());
				output.accept(TFItems.KNIGHTMETAL_SWORD.value());
				output.accept(TFItems.KNIGHTMETAL_PICKAXE.value());
				output.accept(TFItems.KNIGHTMETAL_AXE.value());
				output.accept(TFItems.BLOCK_AND_CHAIN.value());
				output.accept(TFItems.KNIGHTMETAL_SHIELD.value());
				output.accept(TFItems.FIERY_HELMET.value());
				output.accept(TFItems.FIERY_CHESTPLATE.value());
				output.accept(TFItems.FIERY_LEGGINGS.value());
				output.accept(TFItems.FIERY_BOOTS.value());
				output.accept(TFItems.FIERY_SWORD.value());
				output.accept(TFItems.FIERY_PICKAXE.value());
				generateGearWithEnchants(output, TFItems.MAZEBREAKER_PICKAXE.value(), new EnchantmentInstance(Enchantments.BLOCK_EFFICIENCY, 4), new EnchantmentInstance(Enchantments.UNBREAKING, 3), new EnchantmentInstance(Enchantments.BLOCK_FORTUNE, 2));
				output.accept(TFItems.GOLDEN_MINOTAUR_AXE.value());
				output.accept(TFItems.DIAMOND_MINOTAUR_AXE.value());
				output.accept(TFItems.ARCTIC_HELMET.value());
				output.accept(TFItems.ARCTIC_CHESTPLATE.value());
				output.accept(TFItems.ARCTIC_LEGGINGS.value());
				output.accept(TFItems.ARCTIC_BOOTS.value());
				output.accept(TFItems.ICE_SWORD.value());
				output.accept(TFItems.TRIPLE_BOW.value());
				output.accept(TFItems.SEEKER_BOW.value());
				output.accept(TFItems.ICE_BOW.value());
				output.accept(TFItems.ENDER_BOW.value());
				generateGearWithEnchants(output, TFItems.YETI_HELMET.value(), new EnchantmentInstance(Enchantments.ALL_DAMAGE_PROTECTION, 2));
				generateGearWithEnchants(output, TFItems.YETI_CHESTPLATE.value(), new EnchantmentInstance(Enchantments.ALL_DAMAGE_PROTECTION, 2));
				generateGearWithEnchants(output, TFItems.YETI_LEGGINGS.value(), new EnchantmentInstance(Enchantments.ALL_DAMAGE_PROTECTION, 2));
				generateGearWithEnchants(output, TFItems.YETI_BOOTS.value(), new EnchantmentInstance(Enchantments.ALL_DAMAGE_PROTECTION, 2), new EnchantmentInstance(Enchantments.FALL_PROTECTION, 4));
				output.accept(TFItems.GIANT_SWORD.value());
				output.accept(TFItems.GIANT_PICKAXE.value());
				createGlassSwordAndLoreVer(output);
				output.accept(TFItems.ICE_BOMB.value());
				output.accept(TFItems.PHANTOM_HELMET.value());
				output.accept(TFItems.PHANTOM_CHESTPLATE.value());
				generateGearWithEnchants(output, TFItems.NAGA_CHESTPLATE.value(), new EnchantmentInstance(Enchantments.FIRE_PROTECTION, 3));
				generateGearWithEnchants(output, TFItems.NAGA_LEGGINGS.value(), new EnchantmentInstance(Enchantments.ALL_DAMAGE_PROTECTION, 3));
				output.accept(TFItems.TWILIGHT_SCEPTER.value());
				output.accept(TFItems.LIFEDRAIN_SCEPTER.value());
				output.accept(TFItems.ZOMBIE_SCEPTER.value());
				output.accept(TFItems.FORTIFICATION_SCEPTER.value());
				output.accept(TFItems.LAMP_OF_CINDERS.value());
				output.accept(TFItems.ORE_MAGNET.value());
				output.accept(TFItems.CRUMBLE_HORN.value());
				output.accept(TFItems.PEACOCK_FEATHER_FAN.value());
				output.accept(TFItems.MOONWORM_QUEEN.value());
				output.accept(TFItems.CUBE_OF_ANNIHILATION.value());
				tfEnchants(output, CreativeModeTab.TabVisibility.PARENT_TAB_ONLY);
				tfEnchants(output, CreativeModeTab.TabVisibility.SEARCH_TAB_ONLY);
			}).build());


	private static void tfEnchants(CreativeModeTab.Output output, CreativeModeTab.TabVisibility visibility) {
		for (Holder<Enchantment> enchantment : TFEnchantments.ENCHANTMENTS.getEntries()) {
			if (visibility == CreativeModeTab.TabVisibility.PARENT_TAB_ONLY) {
				output.accept(EnchantedBookItem.createForEnchantment(new EnchantmentInstance(enchantment.value(), enchantment.value().getMaxLevel())), visibility);
			} else {
				for (int i = enchantment.value().getMinLevel(); i <= enchantment.value().getMaxLevel(); ++i) {
					output.accept(EnchantedBookItem.createForEnchantment(new EnchantmentInstance(enchantment.value(), i)), visibility);
				}
			}
		}
	}

	private static void generateGearWithEnchants(CreativeModeTab.Output output, Item item, EnchantmentInstance... instances) {
		ItemStack stack = new ItemStack(item);
		if (TFConfig.COMMON_CONFIG.defaultItemEnchants.get()) {
			for (EnchantmentInstance enchant : instances) {
				stack.enchant(enchant.enchantment, enchant.level);
			}
		}
		output.accept(stack);
	}

	private static void createSpawnEggsAlphabetical(CreativeModeTab.Output output) {
		Collection<? extends Item> eggs = TFEntities.SPAWN_EGGS.getEntries().stream().map(DeferredHolder::value).toList();
		eggs.forEach(output::accept);
	}

	private static void makeSkullCandle(CreativeModeTab.Output output, ItemLike item) {
		ItemStack stack = new ItemStack(item);
		CompoundTag tag = new CompoundTag();
		tag.putInt("CandleAmount", 1);
		tag.putInt("CandleColor", 0);
		stack.addTagElement("BlockEntityTag", tag);
		output.accept(stack);
	}

	private static void createGlassSwordAndLoreVer(CreativeModeTab.Output output) {
		output.accept(TFItems.GLASS_SWORD.value());
		ItemStack loreSword = new ItemStack(TFItems.GLASS_SWORD.value());
		CompoundTag tags = new CompoundTag();
		tags.putBoolean("Unbreakable", true);

		ListTag lore = new ListTag();
		lore.add(StringTag.valueOf("{\"translate\":\"item.twilightforest.glass_sword.desc\",\"italic\":false,\"color\":\"gray\"}"));
		// uncomment if someone asks if this will ever generate as loot
//		lore.add(StringTag.valueOf("{\"translate\":\"item.twilightforest.glass_sword.desc2\",\"italic\":false,\"color\":\"gray\"}"));

		CompoundTag display = new CompoundTag();
		display.put("Lore", lore);

		tags.put("display", display);
		loreSword.setTag(tags);
		output.accept(loreSword);
	}

	private static final Comparator<Holder<MagicPaintingVariant>> MAGIC_COMPARATOR = Comparator.comparing(Holder::value, Comparator.<MagicPaintingVariant>comparingInt((variant) ->
			variant.height() * variant.width()).thenComparing(MagicPaintingVariant::width));

	private static void createPaintings(CreativeModeTab.Output output, HolderLookup.RegistryLookup<MagicPaintingVariant> lookup) {
		lookup.listElements().sorted(MAGIC_COMPARATOR).forEach((holder) -> {
			ItemStack itemstack = new ItemStack(TFItems.MAGIC_PAINTING.value());
			CompoundTag tag = itemstack.getOrCreateTagElement("EntityTag");
			tag.putString("variant", holder.unwrapKey().map(ResourceKey::location).map(ResourceLocation::toString).orElse(MagicPainting.EMPTY));
			output.accept(itemstack);
		});
	}
}

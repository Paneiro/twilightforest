package twilightforest.item;

import net.minecraft.item.Item;

//TODO 1.16: Baubles is dead, switch to curios
public class CuriosCharmItem extends Item {
    CuriosCharmItem(Properties props) {
        super(props);
    }

//    @Nullable
//    @Override
//    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundNBT nbt) {
//        return TFCompat.BAUBLES.isActivated() ? new Baubles.BasicBaubleProvider() : null;
//    }
}
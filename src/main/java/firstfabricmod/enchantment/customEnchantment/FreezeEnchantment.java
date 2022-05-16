package firstfabricmod.enchantment.customEnchantment;

import firstfabricmod.effect.ModEffects;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;

public class FreezeEnchantment extends Enchantment {

    public FreezeEnchantment() {
        super(Rarity.UNCOMMON, EnchantmentTarget.WEAPON, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    }

    @Override
    public int getMinPower(int level) {
        return 10*level;
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level) {
        if (target instanceof LivingEntity){
            ((LivingEntity) target).addStatusEffect(new StatusEffectInstance(ModEffects.FREEZE, 20*level, level-1));
        }
        super.onTargetDamaged(user, target, level);
    }
}

package firstfabricmod.combat.armor;

import firstfabricmod.FirstFabricMod;
import firstfabricmod.item.OreItem;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class AmethystArmorMaterial implements ArmorMaterial {

    public static final int[] BASE_DURABILITY = new int[]{10,12,13,10};
    public static final int[] PROTECTION_VALUES = new int[]{1,2,3,1};

    public static final ArmorMaterial AMETHYST_ARMOR_MATERIAL = new AmethystArmorMaterial();
    public static final Item AMETHYST_HELMET = new ArmorItem(AMETHYST_ARMOR_MATERIAL, EquipmentSlot.HEAD,new Item.Settings().group(FirstFabricMod.LOSTsMOD));
    public static final Item AMETHYST_CHESTPLATE = new ArmorItem(AMETHYST_ARMOR_MATERIAL, EquipmentSlot.CHEST,new Item.Settings().group(FirstFabricMod.LOSTsMOD));
    public static final Item AMETHYST_LEGGINGS = new ArmorItem(AMETHYST_ARMOR_MATERIAL, EquipmentSlot.LEGS,new Item.Settings().group(FirstFabricMod.LOSTsMOD));
    public static final Item AMETHYST_BOOTS = new ArmorItem(AMETHYST_ARMOR_MATERIAL, EquipmentSlot.FEET,new Item.Settings().group(FirstFabricMod.LOSTsMOD));

    public static void r_AmethystArmor(){
        Registry.register(Registry.ITEM, new Identifier("firstfabricmod","amethyst_helmet"), AMETHYST_HELMET);
        Registry.register(Registry.ITEM, new Identifier("firstfabricmod","amethyst_chestpalte"), AMETHYST_CHESTPLATE);
        Registry.register(Registry.ITEM, new Identifier("firstfabricmod","amethyst_leggings"), AMETHYST_LEGGINGS);
        Registry.register(Registry.ITEM, new Identifier("firstfabricmod","amethyst_boots"), AMETHYST_BOOTS);
    }

    @Override
    public int getDurability(EquipmentSlot slot) {
        return BASE_DURABILITY[slot.getEntitySlotId()]*35;
    }

    @Override
    public int getProtectionAmount(EquipmentSlot slot) {
        return PROTECTION_VALUES[slot.getEntitySlotId()];
    }

    @Override
    public int getEnchantability() {
        return 20;
    }

    @Override
    public SoundEvent getEquipSound() {
        return SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(OreItem.Amethyst);
    }

    @Override
    public String getName() {
        return "amethyst";
    }

    @Override
    public float getToughness() {
        return 1.0F;
    }

    @Override
    public float getKnockbackResistance() {
        return 0.05F;
    }
}

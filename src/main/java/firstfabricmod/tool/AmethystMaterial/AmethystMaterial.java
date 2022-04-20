package firstfabricmod.tool.AmethystMaterial;

import firstfabricmod.FirstFabricMod;
import net.minecraft.item.*;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class AmethystMaterial implements ToolMaterial {

    public static final AmethystMaterial AMETHYST_MATERIAL = new AmethystMaterial();
    public static final ToolItem AMETHYST_SHOVEL = new ShovelItem(AMETHYST_MATERIAL,
            1, -2.5F, new Item.Settings().group(FirstFabricMod.LOSTsMOD));
    public static final ToolItem AMETHYST_SWORD = new SwordItem(AMETHYST_MATERIAL,
            3, -2.0F, new Item.Settings().group(FirstFabricMod.LOSTsMOD));
    public static final ToolItem AMETHYST_AXE = new AmethystAxeItem(AMETHYST_MATERIAL,
            4,-2.5F, new Item.Settings().group(FirstFabricMod.LOSTsMOD));
    public static final  ToolItem AMETHYST_HOE = new AmethystHoeItem(AMETHYST_MATERIAL,
            1, -2.5F, new Item.Settings().group(FirstFabricMod.LOSTsMOD));
    public static final ToolItem AMETHYST_PICKAXE = new AmethystPickaxeItem(AMETHYST_MATERIAL,
            1, -2.5F, new Item.Settings().group(FirstFabricMod.LOSTsMOD));

    public static void r_AmethystTool(){
        Registry.register(Registry.ITEM, new Identifier("firstfabricmod","amethyst_shovel"), AMETHYST_SHOVEL);
        Registry.register(Registry.ITEM, new Identifier("firstfabricmod","amethyst_sword"), AMETHYST_SWORD);
        Registry.register(Registry.ITEM, new Identifier("firstfabricmod","amethyst_axe"), AMETHYST_AXE);
        Registry.register(Registry.ITEM, new Identifier("firstfabricmod","amethyst_hoe"), AMETHYST_HOE);
        Registry.register(Registry.ITEM, new Identifier("firstfabricmod","amethyst_pickaxe"), AMETHYST_PICKAXE);
    }

    @Override
    public int getDurability() {
        return 750;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return 7.5F;
    }

    @Override
    public float getAttackDamage() {
        return 3.0F;
    }

    @Override
    public int getMiningLevel() {
        return 3;
    }

    @Override
    public int getEnchantability() {
        return 15;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return null;
    }
}

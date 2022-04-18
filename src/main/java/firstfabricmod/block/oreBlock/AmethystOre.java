package firstfabricmod.block.oreBlock;

import firstfabricmod.FirstFabricMod;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class AmethystOre extends Block {

    public static final AmethystOre AMETHYST_ORE = new AmethystOre(FabricBlockSettings.of(Material.STONE).hardness(3.0F));
    public static final AmethystOre DEEPSLATE_AMETHYST_ORE = new AmethystOre(FabricBlockSettings.of(Material.STONE).hardness(4.5F));
    public AmethystOre(Settings settings) {
        super(settings);
    }

    public static void c_r_AmethystOre(){
        Registry.register(Registry.BLOCK, new Identifier("firstfabricmod","amethyst_ore"), AMETHYST_ORE);
        Registry.register(Registry.ITEM, new Identifier("firstfabricmod","amethyst_ore"),
                new BlockItem(AMETHYST_ORE, new Item.Settings().group(FirstFabricMod.LOSTsMOD)));

        Registry.register(Registry.BLOCK, new Identifier("firstfabricmod","deepslate_amethyst_ore"), DEEPSLATE_AMETHYST_ORE);
        Registry.register(Registry.ITEM, new Identifier("firstfabricmod","deepslate_amethyst_ore"),
                new BlockItem(DEEPSLATE_AMETHYST_ORE, new Item.Settings().group(FirstFabricMod.LOSTsMOD)));
    }
}

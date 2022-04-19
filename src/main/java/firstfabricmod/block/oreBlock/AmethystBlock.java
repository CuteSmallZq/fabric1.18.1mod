package firstfabricmod.block.oreBlock;

import firstfabricmod.FirstFabricMod;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class AmethystBlock extends Block {

    public AmethystBlock(Settings settings) {
        super(settings);
    }

    public static final AmethystBlock AMETHYST_BLOCK = new AmethystBlock(FabricBlockSettings.of(Material.METAL).hardness(4.0F));

    public static void r_AmethystBlock(){
        Registry.register(Registry.BLOCK, new Identifier("firstfabricmod","amethyst_block"), AMETHYST_BLOCK);
        Registry.register(Registry.ITEM, new Identifier("firstfabricmod","amethyst_block"),
                new BlockItem(AMETHYST_BLOCK, new Item.Settings().group(FirstFabricMod.LOSTsMOD)));
    }
}

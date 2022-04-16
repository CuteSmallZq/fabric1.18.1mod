package firstfabricmod.block;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class AmethystBlock extends Block {

    public static final AmethystBlock Amethyst_Block = new AmethystBlock(FabricBlockSettings.of(Material.METAL).hardness(4.0F));

    public AmethystBlock(Settings settings) {
        super(settings);
    }

    public static void c_r_Amethyst_Block(){
        Registry.register(Registry.BLOCK, new Identifier("firstfabricmod","amethyst_block"), Amethyst_Block);
        Registry.register(Registry.ITEM, new Identifier("firstfabricmod","amethyst_block"),
                new BlockItem(Amethyst_Block, new Item.Settings().group(ItemGroup.MISC)));
    }
}

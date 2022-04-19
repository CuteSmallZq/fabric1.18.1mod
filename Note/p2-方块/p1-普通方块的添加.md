
# 方块

    一.添加一个方块

## 一.添加一个方块

    1.创建方块对象
    2.注册方块以及注册方块物品
    3.添加方块外观
    4.配置掉落物
    5.添加物品说明
    6.创建自定义方块类
    7.自定义VoxelShape

### 1.创建方块对象
- 首先创建Block实例，该实例可以存储在任何位置
- 该实例需要一个AbstractBlock.Settings实例，也就是用于配置方块属性的构造器
- Fabric提供了新的FabricBlockSettings构造器类以及更多可用的选项
```java
public class ExampleMod implements ModInitializer {
 
    /* Material为方块材质，设置为金属则需要镐子来高效采集
       
       hardness为方块硬度，石头为1.5f，黑曜石为50.0f
       
       可以在`Blocks`类中查找所有原版方块的统计 */
    public static final Block FIRST_BLOCK = new Block(FabricBlockSettings.of(Material.METAL).hardness(4.0f));
 
    @Override
    public void onInitialize() {
 
    }
}
```

### 2.注册方块以及注册方块物品
- 对创建的Block实例进行注册
- 需要注册在Registry.BLOCK
```java
public class ExampleMod implements ModInitializer {
 
    public static final Block FIRST_BLOCK = new Block(FabricBlockSettings.of(Material.METAL).hardness(4.0f));
 
    @Override
    public void onInitialize()
    {
        Registry.register(Registry.BLOCK, new Identifier("firstfabricmod", "first_block"), EXAMPLE_BLOCK);
    }
}
```
- 直到目前，已经成功向游戏内添加了一个BLOCK，但是目前还不能将其拿在手里
- 我们需要为方块注册物品
```java
public class ExampleMod implements ModInitializer {
 
    public static final Block FIRST_BLOCK = new Block(FabricBlockSettings.of(Material.METAL));
 
    @Override
    public void onInitialize() {
        Registry.register(Registry.BLOCK, new Identifier("firstfabricmod", "first_block"), EXAMPLE_BLOCK);
        Registry.register(Registry.ITEM, new Identifier("firstfabricmod", "first_block"), new BlockItem(FIRST_BLOCK, new Item.Settings().group(ItemGroup.MISC)));
    }
}
```
- 通过Registry.ITEM来注册一个new BlockItem来达到目的

### 3.添加方块外观
- 在完成方块与对应的Item的注册后，还需要为方块添加外观
- 方块需要的外观文件如下


        方块状态文件
        方块模型文件
        纹理
        物品模型文件（如果方块有与之关联的物品）


- 外观文件对应的目录如下


        方块状态：src/main/resources/assets/(MOD-ID)/blockstates/first_block.json
        方块模型：src/main/resources/assets/(MOD-ID)/models/block/first_block.json
        物品模型：src/main/resources/assets/(MOD-ID)/models/item/first_block.json
        方块纹理：src/main/resources/assets/(MOD-ID)/textures/block/first_block.png


- 方块状态文件根据其方块装填确定该方块应使用的模型，由于我们的方块没有所谓状态，所以我们用空字符串表示所有
```json
{
  "variants": {
    "": { "model": "firstfabricmod:block/first_block" }
  }
}
```
- 方块模型文件定义了方块的形状和纹理。我们将使用block/cube_all作为父模型，这将使我们能够轻松地在方块的所有面上设置相同的纹理。
```json
{
  "parent": "block/cube_all",
  "textures": {
    "all": "firstfabricmod:block/first_block"
  }
}
```
- 在大多数情况下，您想让方块作为物品时外观还是这个方块。为此，可以制作一个从方块模型文件继承的项目文件，这会使得该物品外观和方块相同
```json
{
  "parent": "firstfabricmod:block/first_block"
}
```

### 4.配置掉落物
- 方块必须有一个战利品列表，这样才能让方块被破坏后有掉落物，一般为其本身
- 战利品列表目录如下


        战利品列表：src/main/resources/data/(MOD-ID)/loot_tables/blocks/first_block.json


- 以下代码让该方块被破坏后掉落其本身
```json
{
  "type": "minecraft:block",
  "pools": [
    {
      "rolls": 1,
      "entries": [
        {
          "type": "minecraft:item",
          "name": "firstfabricmod:first_block"
        }
      ],
      "conditions": [
        {
          "condition": "minecraft:survives_explosion"
        }
      ]
    }
  ]
}
```
- 从1.17开始，破坏方块有所改变，定义采集工具和采集等级需要使用标签，我们需要将方块添加到以下标签


        采集工具：src/main/resources/data/minecraft/tags/blocks/mineable/<tooltype>.json，其中'tooltype'可以是'axe'、'pickaxe'、'shovel'、'hoe'
        采集等级：src/main/resources/data/minecraft/tags/blocks/needs_<tier>_tool.json，其中'tier'可以是：'stone'、'iron'、'diamond'


- 采集工具代码如下
```json
{
  "replace": false,
  "values": [
    "firstfabricmod:first_block"
  ]
}
```
- 采集等级代码如下
```json
{
  "replace": false,
  "values": [
    "firstfabricmod:first_block"
  ]
}
```

### 5.添加物品说明
- 可以在方块类中对appendTooltip进行重写来添加提示，与给Item添加提示方式相同

### 6.创建自定义方块类以及自定义VoxelShape
- 以上便完整添加了一个完备的方块，同时我们也可以通过扩展Block类进行重写来实现更多的功能，并且可以使用VoxelShape来解决非完整方块的显示问题
- 由于1.17将Block中很多方法弃用并修改了结构，因此不展示相关内容

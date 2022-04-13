
# 物品

    一.添加物品
    二语言支持
    三.添加简单合成配方
    四.添加工具
    五.添加盔甲
    六.添加自定义附魔

## 一.添加物品

    1.创建物品对象
    2.注册新物品
    3.添加纹理
    4.创建物品类
    5.添加物品提示
    6.额外属性添加
    7.创建物品组

### 1.创建物品对象
````java
    public class ExampleMod implements ModInitializer //主类
    {
        // 新物品的实例
        public static final Item FIRST_ITEM = new Item(new FabricItemSettings().group(ItemGroup.MISC));
    }
````
- FabricItemSettings()与Item.Setting()分别为fabric与mc本身所提供的API

### 2.注册新物品
````java
public class ExampleMod implements ModInitializer
{
    // 新物品的实例
    public static final Item FABRIC_ITEM = new Item(new FabricItemSettings().group(ItemGroup.MISC));

    @Override
    public void onInitialize()
    {
        // 新物品的注册
        Registry.register(Registry.ITEM, new Identifier("firstfabricmod", "first_item"), FIRST_ITEM);
    }
}
````
- Identifier的两个参数分别为“模组ID”与“翻译键（translation key）”
- 翻译键（translation key）为进行本地化需要的内容

### 3.添加纹理
- MC物品纹理需要两个文件，如下所示


    物品模型: <根目录>/resources/assets/(MOD-ID)/models/item/first_item.json
    物品纹理: <根目录>/resources/assets/(MOD-ID)/textures/item/first_item.png


- 其中json文件需要如下格式
```json
{
  "parent": "builtin/generated",
  "textures": {
    "layer0": "firstfabricmod:item/first_item"
  }
}
```
- parent表示其继承的父模型，这里的builtin/generated父模型告诉MC用给出的纹理文件生成一个模型
- textures/layer0表示其纹理文件所在的位置

### 4.创建物品类
- 如果要为物品添加自定义行为，则需要创建一个物品类来继承MC的Item类
- 其构造函数需要一个Settings参数
```java
public class FirstItem extends Item {

    public FirstItem(Settings settings) {
        super(settings);
    }
}
```
- 为物品添加一个右键时发出原木被破坏的声音
```java
public class FirstItem extends Item {

    public FirstItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
        playerEntity.playSound(SoundEvents.BLOCK_WOOL_BREAK, 1.0F, 1.0F);
        return TypedActionResult.success(playerEntity.getStackInHand(hand));
    }
}
```
- 在新创建的物品类中对public TypedActionResult<ItemStack> use方法进行重写
- 该方法需要的参数为固定参数
- 在创建完物品类之后，需要在主类物品创建处用新创建的类来代替原来的Item类
```java
    public class ExampleMod implements ModInitializer
    {
        public static final FirstItem FIRST_ITEM = new Item(new FabricItemSettings().group(ItemGroup.MISC));
    }
```
### 5.添加物品提示

- 在需要添加物品提示的物品类中，对appendTooltip进行重写
```java
public class FirstItem extends Item {
    public FirstItem(Settings settings) {

        super(settings);
    }
    
    @Override
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext){

        // 默认为白色文本
        tooltip.add(new TranslatableText("first_item_tooltip"));
        // 修改为红色文本
        tooltip.add(new TranslatableText("first_item_tooltip").formatted(Formatting.RED));
    }
}
```

### 6.创建简单物品组

- 如果想要创建简单物品组，需要在主类中创建一个ItemGroup实例并使用FabricItemGroupBuilder来创建具体的物品组
```java
public class ExampleMod implements ModInitializer {
 
    // 使用.build来创建一个新的简单物品组
	public static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.build(
		new Identifier("tutorial", "general"),
		() -> new ItemStack(Blocks.COBBLESTONE));
 
    // 使用.create来创建一个可以创建的同时向其中添加物品的简单物品组
	public static final ItemGroup OTHER_GROUP = FabricItemGroupBuilder.create(
		new Identifier("tutorial", "other"))
		.icon(() -> new ItemStack(Items.BOWL))
		.build();
}
```
- 在创建物品组之后，就可以在创建物品的同时向物品组中进行添加操作
```java
public class ExampleMod implements ModInitializer {
    public static final Item FIRST_ITEM = new FirstItem(new FabricItemSettings().group(ExampleMod.ITEM_GROUP));
}
```
- 使用特定的顺序向物品组添加物品，使用.create来创建物品组
```java
public class ExampleMod implements ModInitializer {
 
	public static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.build(
		new Identifier("firstfabricmod", "general"),
		() -> new ItemStack(Blocks.COBBLESTONE));
 
	public static final ItemGroup OTHER_GROUP = FabricItemGroupBuilder.create(
		new Identifier("firstfabricmod", "other"))
		.icon(() -> new ItemStack(Items.BOWL))
		.appendItems(stacks -> {
			stacks.add(new ItemStack(Blocks.BONE_BLOCK)); // 添加骨块方块
			stacks.add(new ItemStack(Items.APPLE)); // 添加苹果
			stacks.add(PotionUtil.setPotion(new ItemStack(Items.POTION), Potions.WATER)); // 添加一个装满水的水瓶
			stacks.add(ItemStack.EMPTY); // 添加一个空方块
			stacks.add(new ItemStack(Items.IRON_SHOVEL)); // 添加一个铁锹
		})
		.build();
}
```

## 二.语言支持
- 创建一个语言文件，英文为en_us.json，简体中文为zh_cn.json


        语言文件：resources/assets/(MOD-ID)/lang/en_us.json
        语言文件：resources/assets/(MOD-ID)/lang/zh_cn.json


- 添加翻译的格式
```json
{
  "item.firstfabricmod.first_item": "第一个MOD物品",
  "item.firstfabricmod.first_item_tooltip": "我的第一个物品介绍"
}
```

## 三.添加简单合成表
- 添加简单合成表
- 合成表目录如下


        合成配方：resources/data/(MOD-ID)/recipes/first_item.json


- 合成配方代码格式
```json
{
  "type": "minecraft:crafting_shaped",
  "pattern": [
    "WWW",
    "WR ",
    "WWW"
  ],
  "key": {
    "W": {
      "item": "minecraft:stick"
    },
    "R": {
      "item": "minecraft:redstone"
    }
  },
  "result": {
    "item": "firstfabricmod:first_item",
    "count": 4
  }
}
```
- type：代表该合成表的类型，minecraft:crafting_shaped表示工作台有序合成表
- pattern：代表九宫格合成顺序，如果九宫格的某个位置为空，则用空格代替
- key：代表用何种缩写来代替何种物品
- result：代表合成结果，count表示合成数目

## 四.添加工具


        1.创建工具类
        2.创建工具
        3.注册工具
        4.让添加的工具对非原版方块生效


### 1.创建工具类
- 工具需要ToolMaterial类中来定义以下行为


        耐久
        挖掘速度
        攻击伤害
        挖掘等级
        附魔级别
        修复原料


- 原版的工具材料在ToolMaterials，我们需要对该类进行继承
```java
public class FirstToolMaterial implements ToolMaterial {
}
```
- 耐久
```java
public class FirstToolMaterial implements ToolMaterial {
    @Override
    public int getDurability() {
        return 500;
    }
    // 在原版中，每套相同素材的工具的耐久度相同
}
```
- 挖掘速度
```java
public class FirstToolMaterial implements ToolMaterial {
    @Override
    public float getMiningSpeedMultiplier() {
        return 5.0F;
    }
    // 木制工具的速度为2.0F，钻石工具的速度为8.0F
}
```
- 攻击伤害
```java
public class FirstToolMaterial implements ToolMaterial {
    @Override
    public float getAttackDamage() {
        return 3.0F;
    }
    // 注意大多数工具在其构造器中都需要一个整数，这意味着最终的攻击伤害为(float) materialDamage + (int) toolDamage + 1
    // 如果你需要工具在其构造器中完全控制其伤害数量，你可以让材料返回0F的攻击伤害
}
```
- 挖掘等级
```java
public class FirstToolMaterial implements ToolMaterial {
    @Override
    public int getMiningLevel() {
        return 2;
    }
    // 钻石的挖掘等级为3，黑曜石需要3+的挖掘等级来挖掘
}
```
- 附魔等级
```java
public class FirstToolMaterial implements ToolMaterial {
    @Override
    public int getEnchantability() {
        return 15;
    }
    // 金有22的附魔能力，钻石的附魔能力为10。更高的附魔能力意味着更好（更高等级）的附魔
}
```
- 修复原料
```java
public class FirstToolMaterial implements ToolMaterial {
    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(ExampleMod.FIRST_ITEM);
    }
    // 返回在铁砧中修复物品所需要的Ingredient
}
```
- 工具类注册
```java
public class PotatoToolMaterial implements ToolMaterial {
 
    public static final PotatoToolMaterial INSTANCE = new PotatoToolMaterial();
    //ToolMaterial不需要注册,将其传递给需要的工具的一种较好的方法，是将实例保存在某个地方（然后在需要时引用它）
}
```

### 2.创建工具
- 所有的基本工具类（PickaxeItem、ShovelItem、HoeItem、AxeItem、SwordItem）都需要一个ToolMaterial、额外攻击伤害数量（整数）、攻击速度（浮点）、Item.Settings实例
```java
public class ExampleMod implements ModInitializer {
    public static final ToolItem FIRST_SHOVEL = new ShovelItem(FirstToolMaterial.INSTANCE, 1,-2.5F,new Item.Settings().group(ExampleMod.ITEM_GROUP));
    public static final ToolItem FIRST_SWORD = new SwordItem(FirstToolMaterial.INSTANCE, 3,-2.0F,new Item.Settings().group(ExampleMod.ITEM_GROUP));    
    // 攻击速度的计算公式：标准值4.0F+攻击速度（F）
}
```
- 镐（PickaxeItem）、锄（HoeItem）、斧头（AxeItem）都有受保护的构造器，这意味着需要用公开的构造器创建子类
```java
public class FirstPickaxeItem extends PickaxeItem {
    public CustomPickaxeItem(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }
    // 此处以镐子为例，锄头与斧头只是类名不同
}
```
- 使用自定义的子类来创建工具
```java
public class ExampleMod implements ModInitializer {
    public static final ToolItem FIRST_AXE = new FirstAxeItem(FirstToolMaterial.INSTANCE, 4,-2.5F,new Item.Settings().group(ExampleMod.ITEM_GROUP));
    public static final ToolItem FIRST_HOE = new FirstHoeItem(FirstToolMaterial.INSTANCE, 1,-2.5F,new Item.Settings().group(ExampleMod.ITEM_GROUP));
    public static final ToolItem FIRST_PICKAXE = new FirstPickaxeItem(FirstToolMaterial.INSTANCE, 1,-2.5F,new Item.Settings().group(ExampleMod.ITEM_GROUP));
}
```
- 如果要向工具添加任何特殊属性或行为，则创建一个子类来扩展基本工具类，并重写任何需要的的方法

### 3.注册工具
- 工具的注册与物品的注册相同
```java
public class ExampleMod implements ModInitializer {
    @Override
    public void onInitialize() {
        // 将创建的工具进行注册
        Registry.register(Registry.ITEM,new Identifier("firstfabricmod","first_sword"),FIRST_SWORD);
        Registry.register(Registry.ITEM,new Identifier("firstfabricmod","first_axe"),FIRST_AXE);
        Registry.register(Registry.ITEM,new Identifier("firstfabricmod","first_hoe"),FIRST_HOE);
        Registry.register(Registry.ITEM,new Identifier("firstfabricmod","first_pickaxe"),FIRST_PICKAXE);
        Registry.register(Registry.ITEM,new Identifier("firstfabricmod","first_shovel"),FIRST_SHOVEL);
    }
}
```

### 4.让添加的工具对非原版方块生效
- 需要将自定义的工具添加到Fabric工具标签中以支持支持非原版的方块
- 以添加镐子为例子


        文件地址：/src/main/resources/data/fabric/tags/items/pickaxes.json


- 代码实例
```json
{
  "replace": false,
  "values": [
    "firstfabricmod:first_pickaxe"
  ]
}
```

## 五.添加盔甲


        1.创建盔甲材料类
        2.创建盔甲物品
        3.提供纹理
        4.添加击退保护


### 1.创建盔甲材料类
- 我们需要创建一个盔甲材料类FirstArmorMaterial
- 并向其中声明两个实例，基础耐久度与盔甲点
```java
public class FirstArmorMaterial implements ArmorMaterial {
    // 基础耐久度
    public static final int[] BASE_DURABILITY = new int[]{10, 12, 13, 10};
    // 盔甲点
    public static final int[] PROTECTION_VALUES = new int[] {1, 2, 3, 1};
    // 四个参数分别为头盔、胸甲、护腿与靴子
    // 皮革使用{1, 2, 3, 1}，钻石和下界合金使用{3, 6, 8, 3}

}
```
- 在完成类的声明后，还需在其中对以下方法进行重载


        getDurability：盔甲在遭受多少次伤害后会被破坏，使用这个参数与'BASE_DURABILITY'进行计算，皮革盔甲为5, 钻石盔甲为33, 下界合金盔甲为37
        getProtectionAmount：调用上方声明的'PROTECTION_VALUES'
        getEnchantability：盔甲可以从附魔书处得到的附魔等级或多个附魔的可能性
        SoundEvent getEquipSound：盔甲使用的标准是 SoundEvents.ITEM_ARMOR_EQUIP_X，X是盔甲的类型
        Ingredient getRepairIngredient：什么样的素材可以被拿来对盔甲进行维修，他可以是普通物品也可以是拥有的物品
        String getName：盔甲的父类物品，对于钻石盔甲来说是“diamond”
        getToughness：这是相关盔甲在面对高额伤害攻击时的耐用度的第二个保护值，值为'X.0F'
        下面的方法为1.16新增方法
        getKnockbackResistance：填写一个大于0（但小于1）的数来表示你想让他获得多大程度的抗击退效果，值为'0.XF'


- 将上方的方法进行重载之后如下所示
```java
public class FirstArmorMaterial implements ArmorMaterial {
    public static final int[] BASE_DURABILITY = new int[]{10, 12, 13, 10};
    public static final int[] PROTECTION_VALUES = new int[] {1, 2, 3, 1};

    @Override
    public int getDurability(EquipmentSlot slot) {
        return BASE_DURABILITY[slot.getEntitySlotId()] * 35;
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
        return Ingredient.ofItems(FirstRegisterArmorItems.FIRST_CUSTOM_MATERIAL);
    }

    @Override
    public String getName() {
        return "fabric_item";
    }

    @Override
    public float getToughness() {
        return 3.0F;
    }

    @Override
    public float getKnockbackResistance() {
        return 0.15F;
    }
}
```

### 2.创建盔甲物品
- 在完成盔甲素材类之后，便可以使用该类来创建对应的盔甲物品
- 我们创建一个新的类，在其中存放盔甲物品的声明以及注册
```java
public class FirstRegisterArmorItems {

    // 盔甲材料（盔甲属性绑定于此）
    public static final ArmorMaterial FIRST_CUSTOM_ARMOR_MATERIAL = new FirstArmorMaterial();
    // 创建材料（与盔甲材料不同，此为盔甲合成素材）
    public static final Item FIRST_CUSTOM_MATERIAL = new FirstArmorItem(new Item.Settings().group(ExampleMod.ITEM_GROUP));
    public static final Item FIRST_CUSTOM_MATERIAL_HELMET = new ArmorItem(FIRST_CUSTOM_ARMOR_MATERIAL, EquipmentSlot.HEAD,
            new Item.Settings().group(ExampleMod.ITEM_GROUP));
    public static final Item FIRST_CUSTOM_MATERIAL_CHESTPLATE = new ArmorItem(FIRST_CUSTOM_ARMOR_MATERIAL, EquipmentSlot.CHEST,
            new Item.Settings().group(ExampleMod.ITEM_GROUP));
    public static final Item FIRST_CUSTOM_MATERIAL_LEGGINGS = new ArmorItem(FIRST_CUSTOM_ARMOR_MATERIAL, EquipmentSlot.LEGS,
            new Item.Settings().group(ExampleMod.ITEM_GROUP));
    public static final Item FIRST_CUSTOM_MATERIAL_BOOTS = new ArmorItem(FIRST_CUSTOM_ARMOR_MATERIAL, EquipmentSlot.FEET,
            new Item.Settings().group(ExampleMod.ITEM_GROUP));

    // 在盔甲注册类中直接进行注册，方便主类中进行调用
    public static void register() {
        Registry.register(Registry.ITEM, new Identifier("firstfabricmod", "first_custom_material"), FIRST_CUSTOM_MATERIAL);
        Registry.register(Registry.ITEM, new Identifier("firstfabricmod", "first_custom_material_helmet"), FIRST_CUSTOM_MATERIAL_HELMET);
        Registry.register(Registry.ITEM, new Identifier("firstfabricmod", "first_custom_material_chestplate"), FIRST_CUSTOM_MATERIAL_CHESTPLATE);
        Registry.register(Registry.ITEM, new Identifier("firstfabricmod", "first_custom_material_leggings"), FIRST_CUSTOM_MATERIAL_LEGGINGS);
        Registry.register(Registry.ITEM, new Identifier("firstfabricmod", "first_custom_material_boots"), FIRST_CUSTOM_MATERIAL_BOOTS);
    }
}
```
- 我们在上面同时创建了一个新的Item叫做FIRST_CUSTOM_MATERIAL，目的是为了合成创建的盔甲，当然也可以使用之前声明的Item来实现
- 在创建完成后，在主类中调用register方法就可以完成盔甲的注册

### 3.提供纹理
- 完成创建与注册之后，我们需要给盔甲对应的模型与纹理
- 盔甲的纹理分为两部分
- 第一部分是盔甲物品的纹理
- 第二部分是盔甲穿在玩家身上的纹理


        盔甲模型：resources/assets/firstfabricmod/models/item/first_material_helmet.json
        盔甲纹理第一部分：resources/assets/firstfabricmod/textures/item/***.png
        盔甲纹理第二部分：resources/assets/minecraft/textures/models/armor/***.png


- 盔甲模型代码如下
```json
{
    "parent": "item/generated",
    "textures": {
        "layer0": "firstfabricmod:item/first_material_helmet"
    }
}
```

### 4.添加击退保护
- 如果要为盔甲添加击退保护，需要用到mixin
- 首先要在<modid>.mixins.json文件夹中添加注册代码
```json
{
    "mixins": [
        "AmethystArmorItemMixin"
      ]
}
```
- 在完成mixin注册之后，就可以去添加相应的文件
- 代码如下所示，仅需要对其中的
- material == AmethystRegisterArmorItems.AMETHYST_MATERIAL
- 部分代码的盔甲素材进行修改即可
```java
@Mixin(ArmorItem.class)
public abstract class AmethystArmorItemMixin {

    @Shadow @Final private static UUID[] MODIFIERS;
    @Shadow @Final @Mutable private Multimap<EntityAttribute, EntityAttributeModifier> attributeModifiers;
    @Shadow @Final protected float knockbackResistance;

    @Inject(method = "<init>", at = @At(value = "RETURN"))
    private void constructor(ArmorMaterial material, EquipmentSlot slot, Item.Settings settings, CallbackInfo ci) {
        UUID uUID = MODIFIERS[slot.getEntitySlotId()];
        if (material == AmethystRegisterArmorItems.AMETHYST_MATERIAL) {
            ImmutableMultimap.Builder<EntityAttribute, EntityAttributeModifier> builder = ImmutableMultimap.builder();

            this.attributeModifiers.forEach(builder::put);

            builder.put(
                    EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE,
                    new EntityAttributeModifier(uUID,
                            "Armor knockback resistance",
                            this.knockbackResistance,
                            EntityAttributeModifier.Operation.ADDITION
                    )
            );

            this.attributeModifiers = builder.build();
        }
    }
}
```

## 六.添加自定义附魔


        1.添加新附魔类对原本附魔类进行继承
        2.注册新附魔
        3.本地化
        4.如果有需要则添加自定义功能或机制


### 1.添加新附魔类
- 要添加新的附魔需要创建一个新的类来对本体的附魔类进行继承
```java
public class FrostEnchantment extends Enchantment {
    public FrostEnchantment() {
        // 参数1：附魔稀有度
        // 参数2：附魔对象
        // 参数3：装备槽
        super(Rarity.UNCOMMON, EnchantmentTarget.WEAPON, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    }
}
```
- 创建完成后对原版类中的方法进行重写
```java
public class FrostEnchantment extends Enchantment {

    public FrostEnchantment() {
        super(Rarity.UNCOMMON, EnchantmentTarget.WEAPON, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    }

    @Override
    // 附魔权重，大多数的附魔类的这项返回值会像是 10 * level，根据附魔的最高等级和稀有度有不同的等级
    // 附魔的默认最高力量是 min(level) + 5，意味着这个附魔只会出现非常低的等级
    public int getMinPower(int level){
        return 10*level;
    }

    @Override
    // 最高附魔等级，如：锋利最高为6
    public int getMaxLevel(){
        return 3;
    }
}
```
- 之后对目标功能进行实现
```java
public class FrostEnchantment extends Enchantment {

    public FrostEnchantment() {
        super(Rarity.UNCOMMON, EnchantmentTarget.WEAPON, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    }

    @Override
    public int getMinPower(int level){
        return 10*level;
    }

    @Override
    public int getMaxLevel(){
        return 3;
    }

    @Override
    // 实现该附魔的效果
    public void onTargetDamaged(LivingEntity user, Entity target, int level){
        // LivingEntity是可以有状态效果的， 但是不是Entity
        if(target instanceof LivingEntity) {
            ((LivingEntity) target).addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 20 * 2 * level, level - 1));
        }

        super.onTargetDamaged(user, target, level);
    }
}
```

### 2.注册附魔
- 新附魔的注册并不需要在onInitialize()中进行
```java
public class ExampleMod implements ModInitializer {
    
    private static final Enchantment FROST = Registry.register(
            Registry.ENCHANTMENT,
            new Identifier("firstfabricmod", "frost"),
            new FrostEnchantment());
    
    @Override
    public static onInitialize(){
                
    }
}
```

### 3.本地化
- 与其他物品的本地化相同
```json
{
  "enchantment.firstfabricmod.frost": "冰冻"
}
```

### 4.自定义功能与机制实现
- 该部分根据自己的需求进行添加与修改

package ToTheMoon;

import ToTheMoon.MoonWorld.Commands.EssentialsBlock;
import ToTheMoon.MoonWorld.Commands.Oxygen;
import ToTheMoon.MoonWorld.Commands.Principal;
import ToTheMoon.MoonWorld.Effects.FallDamage;
import ToTheMoon.MoonWorld.Effects.Jump;
import ToTheMoon.MoonWorld.Effects.Move;
import ToTheMoon.MoonWorld.Effects.Respawn;
import ToTheMoon.MoonWorld.Game.Entities.EntitySection;
import ToTheMoon.MoonWorld.Game.GravityEffects.NoMilk;
import ToTheMoon.MoonWorld.Game.GravityEffects.Rain;
import ToTheMoon.MoonWorld.Game.GravityEffects.Water;
import ToTheMoon.MoonWorld.Game.GravityEffects.Water2;
import ToTheMoon.MoonWorld.Game.MoonWorldJoinOrLeft.Casco;
import ToTheMoon.MoonWorld.Game.MoonWorldJoinOrLeft.JoinWorld;
import ToTheMoon.MoonWorld.Game.Ossigeno;
import ToTheMoon.MoonWorld.Game.Rocket.Rocket;
import ToTheMoon.MoonWorld.Game.Rocket.Rocket2;
import ToTheMoon.MoonWorld.Generator;
import ToTheMoon.Utils.ItemUtils;
import ToTheMoon.Utils.Join;
import me.arthed.custombiomecolors.nms.NmsBiome;
import me.arthed.custombiomecolors.nms.NmsBiome_1_17;
import me.arthed.custombiomecolors.utils.objects.BiomeColorType;
import me.arthed.custombiomecolors.utils.objects.BiomeColors;
import me.arthed.custombiomecolors.utils.objects.BiomeKey;
import net.minecraft.core.BlockPosition;
import net.minecraft.core.IRegistry;
import net.minecraft.core.IRegistryWritable;
import net.minecraft.data.worldgen.biome.BiomeRegistry;
import net.minecraft.resources.MinecraftKey;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.WorldServer;
import net.minecraft.world.level.biome.BiomeBase;
import net.minecraft.world.level.biome.BiomeFog;
import net.minecraft.world.level.biome.BiomeSettingsGeneration;
import net.minecraft.world.level.biome.BiomeSettingsMobs;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.chunk.BiomeStorage;
import net.minecraft.world.level.chunk.Chunk;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.command.CommandExecutor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.craftbukkit.v1_17_R1.CraftServer;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.mojang.serialization.Lifecycle;


public class Main extends JavaPlugin {
	
	Logger log;
	
	private PluginManager pm;
	
	public File file;
	
	public FileConfiguration fileConfig;
	
	public double maxwater;
	
	private static Main instance;
	
	public double secremove;
	
	public NmsBiome customBiome;
	
	public double drinkingwater;
	public IRegistryWritable<BiomeBase> biomeRegistry =
		((CraftServer) Bukkit.getServer()).getServer().getCustomRegistry().b(IRegistry.aO);
	
	
	public Main() {
		instance = this;
	}
	
	
	public void init() {
		customBiome = getBiomeFromBiomeBase(biomeRegistry.a(Biomes.b));
		BiomeColors biomeColors = new BiomeColors();
		int color = 0x8f0058;
		biomeColors.setFogColor(color);
		biomeColors.setSkyColor(color);
		customBiome = customBiome.cloneWithDifferentColors(new BiomeKey("ttm", "moon"), biomeColors);
	}
	
	public static Main getInstance() {
		return instance;
	}
	
	public void onEnable() {
		init();
		if (getConfig().getBoolean("ToTheMoon.WorldGeneration.TimeLock"))
			Tempo2();
		Data();
		OnLoad2();
		OnLoad();
		registerEvents();
		registerCommands();
		Craftings();
		Craftings2();
		FeatherBoard();
		if (getConfig().getBoolean("ToTheMoon.MoonEffects.Oxygen.Enable"))
			getServer().getScheduler().runTaskTimerAsynchronously((Plugin) this, new Runnable() {
				
				public void run() {
					List<Player> players = (List<Player>) Bukkit.getOnlinePlayers();
					for (int i = 0; i < players.size(); i++) {
						double pw = Main.this.getPlayerWater(players.get(i));
						double last = pw;
						pw -= Main.this.secremove;
						if (last >= Main.this.maxwater / 2.0D)
							;
						if (last >= Main.this.maxwater / 4.0D)
							;
						if (pw <= 0.0D && !((Player) players.get(i)).getGameMode().equals(GameMode.CREATIVE)) {
							pw = 0.0D;
							((Player) players.get(i)).damage(1.0D);
							((Player) players.get(i)).sendTitle(
								Main.this.getConfig().getString("ToTheMoon.MoonEffects.Oxygen.NoOxygenMessage.Title")
									.replace("&", "§"),
								Main.this.getConfig().getString("ToTheMoon.MoonEffects.Oxygen.NoOxygenMessage.SubTitle")
									.replace("&", "§"),
								4, 20, 4);
						}
						int max = Main.this.getConfig().getStringList("ToTheMoon.Worlds").size();
						int n = (new Random()).nextInt(max);
						if (((Player) players.get(i)).getLocation().getWorld().getName()
							.equalsIgnoreCase(Main.this.getConfig().getStringList("ToTheMoon.Worlds").get(n)))
							if (!((Player) players.get(i)).getGameMode().equals(GameMode.CREATIVE))
								Main.this.setPlayerWater(players.get(i), pw);
					}
				}
				
			}, 0L, 20L);
	}
	
	public double getPlayerWater(Player player) {
		this.file = new File("plugins" + File.separator + "ToTheMoon" + File.separator + "save.yml");
		if (!this.file.exists()) {
			this.fileConfig = (FileConfiguration) YamlConfiguration.loadConfiguration(this.file);
			this.fileConfig.set("null", Integer.valueOf(0));
			try {
				this.fileConfig.save(this.file);
			}
			catch (IOException iOException) {}
		}
		this.fileConfig = (FileConfiguration) YamlConfiguration.loadConfiguration(this.file);
		if (!this.fileConfig.contains(String.valueOf(player.getName()) + "." + player.getUniqueId().toString())) {
			this.fileConfig.set(String.valueOf(player.getName()) + "." + player.getUniqueId().toString(),
				Double.valueOf(this.maxwater));
			try {
				this.fileConfig.save(this.file);
			}
			catch (IOException iOException) {}
			this.fileConfig = (FileConfiguration) YamlConfiguration.loadConfiguration(this.file);
		}
		return this.fileConfig.getDouble(String.valueOf(player.getName()) + "." + player.getUniqueId().toString());
	}
	
	public void setPlayerWater(Player player, double water) {
		this.file = new File("plugins" + File.separator + "ToTheMoon" + File.separator + "save.yml");
		this.fileConfig = (FileConfiguration) YamlConfiguration.loadConfiguration(this.file);
		this.fileConfig.set(String.valueOf(player.getName()) + "." + player.getUniqueId().toString(),
			Double.valueOf(water));
		try {
			this.fileConfig.save(this.file);
		}
		catch (IOException iOException) {}
	}
	
	private void registerEvents() {
		this.pm = Bukkit.getPluginManager();
		this.pm.registerEvents((Listener) new Jump(this), (Plugin) this);
		this.pm.registerEvents((Listener) new EntitySection(this), (Plugin) this);
		this.pm.registerEvents((Listener) new FallDamage(this), (Plugin) this);
		this.pm.registerEvents((Listener) new Ossigeno(this), (Plugin) this);
		this.pm.registerEvents((Listener) new Casco(this), (Plugin) this);
		this.pm.registerEvents((Listener) new Rain(this), (Plugin) this);
		this.pm.registerEvents((Listener) new JoinWorld(this), (Plugin) this);
		this.pm.registerEvents((Listener) new Ossigeno(this), (Plugin) this);
		this.pm.registerEvents((Listener) new NoMilk(this), (Plugin) this);
		this.pm.registerEvents((Listener) new Move(this), (Plugin) this);
		this.pm.registerEvents((Listener) new Respawn(this), (Plugin) this);
		this.pm.registerEvents((Listener) new Join(), (Plugin) this);
		this.pm.registerEvents((Listener) new EssentialsBlock(this), (Plugin) this);
		Version();
	}
	
	public void onDisable() {}
	
	public ChunkGenerator getDefaultWorldGenerator(String worldName, String id) {
		return (ChunkGenerator) new Generator((Plugin) this, this.log);
	}
	
	public void OnLoad() {
		this.file = new File("plugins" + File.separator + "ToTheMoon" + File.separator + "config.yml");
		if (!this.file.exists()) {
			this.fileConfig = (FileConfiguration) YamlConfiguration.loadConfiguration(this.file);
			this.fileConfig.set("ToTheMoon.GiveHelmetOnWorldJoin", Boolean.valueOf(true));
			this.fileConfig.set("ToTheMoon.GiveHelmetToTheMobs", Boolean.valueOf(true));
			this.fileConfig.set("ToTheMoon.RemoveGravityOnExitMoonWorld", Boolean.valueOf(true));
			this.fileConfig.set("ToTheMoon.WorldGeneration.CoalSpawnPercentage", Double.valueOf(60.0D));
			this.fileConfig.set("ToTheMoon.WorldGeneration.IronSpawnPercentage", Double.valueOf(40.0D));
			this.fileConfig.set("ToTheMoon.WorldGeneration.GoldSpawnPercentage", Double.valueOf(20.0D));
			this.fileConfig.set("ToTheMoon.WorldGeneration.RedstoneSpawnPercentage", Double.valueOf(10.0D));
			this.fileConfig.set("ToTheMoon.WorldGeneration.LapisSpawnPercentage", Double.valueOf(7.0D));
			this.fileConfig.set("ToTheMoon.WorldGeneration.DiamondSpawnPercentage", Double.valueOf(5.0D));
			this.fileConfig.set("ToTheMoon.WorldGeneration.EmeraldSpawnPercentage", Double.valueOf(2.0D));
			this.fileConfig.set("ToTheMoon.WorldGeneration.TimeLock", Boolean.valueOf(true));
			this.fileConfig.set("ToTheMoon.WorldGeneration.TimeLockTick", Integer.valueOf(18000));
			this.fileConfig.set("ToTheMoon.WorldGeneration.NoRain", Boolean.valueOf(true));
			this.fileConfig.set("ToTheMoon.MoonEffects.Gravity", Boolean.valueOf(true));
			this.fileConfig.set("ToTheMoon.MoonEffects.GravityLevel", Integer.valueOf(3));
			this.fileConfig.set("ToTheMoon.MoonEffects.MiningFatigue", Boolean.valueOf(true));
			this.fileConfig.set("ToTheMoon.MoonEffects.WaterOnMoon", Boolean.valueOf(false));
			this.fileConfig.set("ToTheMoon.MoonEffects.EnhancedMobs", Boolean.valueOf(true));
			this.fileConfig.set("ToTheMoon.MoonEffects.Oxygen.Enable", Boolean.valueOf(true));
			this.fileConfig.set("ToTheMoon.MoonEffects.Oxygen.OnSecRemove", Double.valueOf(0.001D));
			this.fileConfig.set("ToTheMoon.MoonEffects.Oxygen.NoOxygenMessage.Title", "&cCAUTION");
			this.fileConfig.set("ToTheMoon.MoonEffects.Oxygen.NoOxygenMessage.SubTitle",
				"&eYou've finished the oxygen");
			this.fileConfig.set("ToTheMoon.Items.OxygenBottle.Enable", Boolean.valueOf(true));
			this.fileConfig.set("ToTheMoon.Items.OxygenBottle.Name", "&aOxygen");
			this.fileConfig.set("ToTheMoon.Items.OxygenBottle.OxygenGivePercentage", Integer.valueOf(50));
			this.fileConfig.set("ToTheMoon.Items.OxygenBottle.Crafting.Object1", "POTION");
			this.fileConfig.set("ToTheMoon.Items.OxygenBottle.Crafting.Object2", "LEVER");
			this.fileConfig.set("ToTheMoon.Items.OxygenBottle.Crafting.Object3", "POTION");
			this.fileConfig.set("ToTheMoon.Items.OxygenBottle.Crafting.Object4", "IRON_BARS");
			this.fileConfig.set("ToTheMoon.Items.OxygenBottle.Crafting.Object5", "BUCKET");
			this.fileConfig.set("ToTheMoon.Items.OxygenBottle.Crafting.Object6", "IRON_BARS");
			this.fileConfig.set("ToTheMoon.Items.OxygenBottle.Crafting.Object7", "IRON_BARS");
			this.fileConfig.set("ToTheMoon.Items.OxygenBottle.Crafting.Object8", "BUCKET");
			this.fileConfig.set("ToTheMoon.Items.OxygenBottle.Crafting.Object9", "IRON_BARS");
			this.fileConfig.set("ToTheMoon.Items.Rocket.Enable", Boolean.valueOf(true));
			this.fileConfig.set("ToTheMoon.Items.Rocket.Name", "&cRocket");
			this.fileConfig.set("ToTheMoon.Items.Rocket.Particles", Boolean.valueOf(true));
			if (Bukkit.getVersion().contains("1.8"))
				this.fileConfig.set("ToTheMoon.Items.Rocket.Sound", Boolean.valueOf(true));
			this.fileConfig.set("ToTheMoon.Items.Rocket.Crafting.Object1", "REDSTONE_BLOCK");
			this.fileConfig.set("ToTheMoon.Items.Rocket.Crafting.Object2", "GLASS_PANE");
			this.fileConfig.set("ToTheMoon.Items.Rocket.Crafting.Object3", "REDSTONE_BLOCK");
			this.fileConfig.set("ToTheMoon.Items.Rocket.Crafting.Object4", "IRON_BLOCK");
			this.fileConfig.set("ToTheMoon.Items.Rocket.Crafting.Object5", "NETHER_STAR");
			this.fileConfig.set("ToTheMoon.Items.Rocket.Crafting.Object6", "IRON_BLOCK");
			this.fileConfig.set("ToTheMoon.Items.Rocket.Crafting.Object7", "PISTON");
			this.fileConfig.set("ToTheMoon.Items.Rocket.Crafting.Object8", "FLINT_AND_STEEL");
			this.fileConfig.set("ToTheMoon.Items.Rocket.Crafting.Object9", "PISTON");
			this.fileConfig.set("ToTheMoon.Items.Rocket.BlockAboveHead", "&7You have a block over your head.");
			this.fileConfig.set("ToTheMoon.Items.Rocket.MessageArrivedOnTheMoon", "&7You arrived on the moon.");
			this.fileConfig.set("ToTheMoon.Commands.NoPermissions", "&cYou don't have permissions!");
			this.fileConfig.set("ToTheMoon.Commands.Prefix", "&9ToTheMoon» ");
			this.fileConfig.set("ToTheMoon.Commands.UseHelp", "Use /ToTheMoon help. ");
			this.fileConfig.set("ToTheMoon.Commands.Oxygen", "&7The remaining oxygen is equal to ");
			this.fileConfig.set("ToTheMoon.Commands.FixEssentials", Boolean.valueOf(true));
			this.fileConfig.set("ToTheMoon.Commands.EssentialsHealFixMessage", "&7You have been healed.");
			this.fileConfig.set("ToTheMoon.Commands.EssentialsHealFixPermission", "essentials.heal");
			List<String> list = this.fileConfig.getStringList("ToTheMoon.Worlds");
			list.add("MoonWorld");
			this.fileConfig.set("ToTheMoon.Worlds", list);
			try {
				this.fileConfig.save(this.file);
			}
			catch (IOException iOException) {}
		}
		this.fileConfig = (FileConfiguration) YamlConfiguration.loadConfiguration(this.file);
		this.maxwater = 100.0D;
		this.drinkingwater = this.fileConfig.getInt("ToTheMoon.Items.OxygenBottle.OxygenGivePercentage");
		this.secremove = this.fileConfig.getDouble("ToTheMoon.MoonEffects.Oxygen.OnSecRemove");
	}
	
	public void OnLoad2() {
		this.file = new File("plugins" + File.separator + "ToTheMoon" + File.separator + "Mobs.yml");
		if (!this.file.exists()) {
			this.fileConfig = (FileConfiguration) YamlConfiguration.loadConfiguration(this.file);
			this.fileConfig.set("ToTheMoon.Monsters.MobsGravity", Boolean.valueOf(true));
			this.fileConfig.set("ToTheMoon.Monsters.CustomMobs", Boolean.valueOf(true));
			this.fileConfig.set("ToTheMoon.Monsters.Skeleton.Helmet", "GLASS");
			this.fileConfig.set("ToTheMoon.Monsters.Skeleton.Chestplate", "CHAINMAIL_CHESTPLATE");
			this.fileConfig.set("ToTheMoon.Monsters.Skeleton.Leggings", "CHAINMAIL_LEGGINGS");
			this.fileConfig.set("ToTheMoon.Monsters.Skeleton.Boots", "CHAINMAIL_BOOTS");
			this.fileConfig.set("ToTheMoon.Monsters.Skeleton.ItemInHand", "BOW");
			this.fileConfig.set("ToTheMoon.Monsters.Skeleton.Strenght", Boolean.valueOf(true));
			this.fileConfig.set("ToTheMoon.Monsters.Skeleton.StrenghtLevel", Integer.valueOf(1));
			List<String> list1 = this.fileConfig.getStringList("ToTheMoon.Monsters.Skeleton.ItemInHandEnchants");
			list1.add("DURABILITY:3");
			this.fileConfig.set("ToTheMoon.Monsters.Zombie.ItemInHandEnchants", list1);
			this.fileConfig.set("ToTheMoon.Monsters.Zombie.MaxHealth", Integer.valueOf(20));
			this.fileConfig.set("ToTheMoon.Monsters.Zombie.Helmet", "AlienSkull");
			this.fileConfig.set("ToTheMoon.Monsters.Zombie.Chestplate", "CHAINMAIL_CHESTPLATE");
			this.fileConfig.set("ToTheMoon.Monsters.Zombie.Leggings", "CHAINMAIL_LEGGINGS");
			this.fileConfig.set("ToTheMoon.Monsters.Zombie.Boots", "CHAINMAIL_BOOTS");
			this.fileConfig.set("ToTheMoon.Monsters.Zombie.ItemInHand", "DIAMOND_SWORD");
			this.fileConfig.set("ToTheMoon.Monsters.Zombie.Strenght", Boolean.valueOf(true));
			this.fileConfig.set("ToTheMoon.Monsters.Zombie.StrenghtLevel", Integer.valueOf(1));
			List<String> list9 = this.fileConfig.getStringList("ToTheMoon.Monsters.Zombie.ItemInHandEnchants");
			list9.add("DURABILITY:3");
			this.fileConfig.set("ToTheMoon.Monsters.Zombie.ItemInHandEnchants", list9);
			this.fileConfig.set("ToTheMoon.Monsters.Zombie.MaxHealth", Integer.valueOf(20));
			try {
				this.fileConfig.save(this.file);
			}
			catch (IOException iOException) {}
		}
	}
	
	public void Craftings() {
		if (getConfig().getBoolean("ToTheMoon.Items.OxygenBottle.Enable")) {
			ItemStack OxygenBottle = new ItemStack(Material.POTION, 1);
			ItemUtils.setItemName(OxygenBottle,
				getConfig().getString("ToTheMoon.Items.OxygenBottle.Name").replace("&", "§"));
			ShapedRecipe OxygenBottleCraft =
				new ShapedRecipe(NamespacedKey.fromString("oxygenbottlerec", this), OxygenBottle);
			OxygenBottleCraft.shape(new String[] {"ABC",
				"DEF",
				"GHI"});
			Bukkit.getLogger().info(getConfig().getString("ToTheMoon.Items.OxygenBottle.Crafting.Object1"));
			OxygenBottleCraft.setIngredient('A',
				Material.getMaterial(getConfig().getString("ToTheMoon.Items.OxygenBottle.Crafting.Object1")));
			OxygenBottleCraft.setIngredient('B',
				Material.getMaterial(getConfig().getString("ToTheMoon.Items.OxygenBottle.Crafting.Object2")));
			OxygenBottleCraft.setIngredient('C',
				Material.getMaterial(getConfig().getString("ToTheMoon.Items.OxygenBottle.Crafting.Object3")));
			OxygenBottleCraft.setIngredient('D',
				Material.getMaterial(getConfig().getString("ToTheMoon.Items.OxygenBottle.Crafting.Object4")));
			OxygenBottleCraft.setIngredient('E',
				Material.getMaterial(getConfig().getString("ToTheMoon.Items.OxygenBottle.Crafting.Object5")));
			OxygenBottleCraft.setIngredient('F',
				Material.getMaterial(getConfig().getString("ToTheMoon.Items.OxygenBottle.Crafting.Object6")));
			OxygenBottleCraft.setIngredient('G',
				Material.getMaterial(getConfig().getString("ToTheMoon.Items.OxygenBottle.Crafting.Object7")));
			OxygenBottleCraft.setIngredient('H',
				Material.getMaterial(getConfig().getString("ToTheMoon.Items.OxygenBottle.Crafting.Object8")));
			OxygenBottleCraft.setIngredient('I',
				Material.getMaterial(getConfig().getString("ToTheMoon.Items.OxygenBottle.Crafting.Object9")));
			getServer().addRecipe((Recipe) OxygenBottleCraft);
		}
	}
	
	public void Craftings2() {
		if (getConfig().getBoolean("ToTheMoon.Items.Rocket.Enable")) {
			ItemStack Rocket = new ItemStack(Material.FIREWORK_ROCKET, 1);
			ItemUtils.setItemName(Rocket, getConfig().getString("ToTheMoon.Items.Rocket.Name").replace("&", "§"));
			ShapedRecipe RocketCraft = new ShapedRecipe(NamespacedKey.fromString("rocketrec", this), Rocket);
			RocketCraft.shape(new String[] {"ABC",
				"DEF",
				"GHI"});
			RocketCraft.setIngredient('A',
				Material.getMaterial(getConfig().getString("ToTheMoon.Items.Rocket.Crafting.Object1")));
			RocketCraft.setIngredient('B',
				Material.getMaterial(getConfig().getString("ToTheMoon.Items.Rocket.Crafting.Object2")));
			RocketCraft.setIngredient('C',
				Material.getMaterial(getConfig().getString("ToTheMoon.Items.Rocket.Crafting.Object3")));
			RocketCraft.setIngredient('D',
				Material.getMaterial(getConfig().getString("ToTheMoon.Items.Rocket.Crafting.Object4")));
			RocketCraft.setIngredient('E',
				Material.getMaterial(getConfig().getString("ToTheMoon.Items.Rocket.Crafting.Object5")));
			RocketCraft.setIngredient('F',
				Material.getMaterial(getConfig().getString("ToTheMoon.Items.Rocket.Crafting.Object6")));
			RocketCraft.setIngredient('G',
				Material.getMaterial(getConfig().getString("ToTheMoon.Items.Rocket.Crafting.Object7")));
			RocketCraft.setIngredient('H',
				Material.getMaterial(getConfig().getString("ToTheMoon.Items.Rocket.Crafting.Object8")));
			RocketCraft.setIngredient('I',
				Material.getMaterial(getConfig().getString("ToTheMoon.Items.Rocket.Crafting.Object9")));
			getServer().addRecipe((Recipe) RocketCraft);
		}
	}
	
	public void FeatherBoard() {
		if (Bukkit.getPluginManager().isPluginEnabled("MVdWPlaceholderAPI")) {
			
		}
		else {
			Bukkit.getConsoleSender()
				.sendMessage(String.valueOf(getConfig().getString("ToTheMoon.Commands.Prefix").replace("&", "§"))
					+ "§cMVdWPlaceholderAPI is not enabled, the {oxygen} variable will not be activated");
		}
	}
	
	public void Tempo() {
		int max = getConfig().getStringList("ToTheMoon.Worlds").size();
		int n = (new Random()).nextInt(max);
		if (Bukkit.getWorld(getConfig().getStringList("ToTheMoon.Worlds").get(n)) != null) {
			if (Bukkit.getWorld(getConfig().getStringList("ToTheMoon.Worlds").get(n)).getTime()
				!= getConfig().getInt("ToTheMoon.WorldGeneration.TimeLockTick"))
				Bukkit.getWorld(getConfig().getStringList("ToTheMoon.Worlds").get(n))
					.setTime(getConfig().getInt("ToTheMoon.WorldGeneration.TimeLockTick"));
		}
		else {
			Bukkit.getConsoleSender().sendMessage("§9ToTheMoon» §cYou must generate the MoonWorld world.");
		}
	}
	
	public void Tempo2() {
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask((Plugin) this, new Runnable() {
			
			public void run() {
				Main.this.Tempo();
			}
			
		}, 0L, 200L);
	}
	
	public void Version() {
		if (Bukkit.getVersion().contains("1.8")) {
			this.pm.registerEvents((Listener) new Water(this), (Plugin) this);
			this.pm.registerEvents((Listener) new Rocket(this), (Plugin) this);
		}
		else if (Bukkit.getVersion().contains("1.9")) {
			this.pm.registerEvents((Listener) new Water(this), (Plugin) this);
			this.pm.registerEvents((Listener) new Rocket2(this), (Plugin) this);
		}
		else if (Bukkit.getVersion().contains("1.10")) {
			this.pm.registerEvents((Listener) new Water(this), (Plugin) this);
			this.pm.registerEvents((Listener) new Rocket2(this), (Plugin) this);
		}
		else if (Bukkit.getVersion().contains("1.11")) {
			this.pm.registerEvents((Listener) new Water(this), (Plugin) this);
			this.pm.registerEvents((Listener) new Rocket2(this), (Plugin) this);
		}
		else if (Bukkit.getVersion().contains("1.12")) {
			this.pm.registerEvents((Listener) new Water2(this), (Plugin) this);
			this.pm.registerEvents((Listener) new Rocket2(this), (Plugin) this);
		}
	}
	
	public void registerCommands() {
		getCommand("tothemoon").setExecutor((CommandExecutor) new Principal(this));
		getCommand("ttm").setExecutor((CommandExecutor) new Principal(this));
		getCommand("oxygen").setExecutor((CommandExecutor) new Oxygen(this));
	}
	
	public void Data() {
		this.file = new File("plugins" + File.separator + "ToTheMoon" + File.separator + "save.yml");
		if (!this.file.exists()) {
			this.fileConfig = (FileConfiguration) YamlConfiguration.loadConfiguration(this.file);
			this.fileConfig.set("Null", Integer.valueOf(0));
			try {
				this.fileConfig.save(this.file);
			}
			catch (IOException iOException) {}
		}
	}
	
	private void changeBiomeColor(Block[] blocks, BiomeColorType colorType, int color, BiomeKey biomeKey) {
		// Map<BiomeBase, List<Block>> blocksInEachBiome = new HashMap<>();
		// for (Block block : blocks) {
		// boolean added = false;
		// BiomeBase blocksBiomeBase = getBlocksBiomeBase(block);
		// for (Object biomeBase : blocksInEachBiome.keySet()) {
		// if (blocksBiomeBase.equals(biomeBase)) {
		// ((List<Block>) blocksInEachBiome.get(biomeBase)).add(block);
		// added = true;
		// break;
		// }
		// }
		// if (!added) {
		// List<Block> blocksInBiome = new ArrayList<>();
		// blocksInBiome.add(block);
		// blocksInEachBiome.put(blocksBiomeBase, blocksInBiome);
		// }
		// }
		// BiomeKey individualBiomeKey = biomeKey;
		// int i = 0;
		// for (Block block : blocksInEachBiome.get(biomeBase))
		// setBlocksBiome(block, customBiome);
		
	}
	
	public NmsBiome getBiomeFromBiomeBase(BiomeBase biomeBase) {
		return new NmsBiome_1_17(biomeBase);
	}
	
	
	public void setBlockBiome(Block block, NmsBiome nmsBiome) {
		BlockPosition blockPosition = new BlockPosition(block.getX(), block.getY(), block.getZ());
		WorldServer worldServer = ((CraftWorld) block.getWorld()).getHandle();
		Chunk chunk = worldServer.getChunkAtWorldCoords(blockPosition);
		if (chunk != null && chunk.getBiomeIndex() != null) {
			List<BiomeBase> oldBiomes = getBiomeTypesInChunk(chunk.getBiomeIndex());
			chunk.getBiomeIndex().setBiome(blockPosition
				.getX() >> 2,
				blockPosition
					.getY() >> 2,
				blockPosition
					.getZ() >> 2,
				(BiomeBase) nmsBiome
					.getBiomeBase());
			chunk.markDirty();
			List<BiomeBase> list1 = getBiomeTypesInChunk(chunk.getBiomeIndex());
		}
	}
	
	public BiomeBase getBlocksBiomeBase(Block block) {
		BlockPosition blockPosition = new BlockPosition(block.getX(), block.getY(), block.getZ());
		WorldServer worldServer = ((CraftWorld) block.getWorld()).getHandle();
		Chunk chunk = worldServer.getChunkAtWorldCoords(blockPosition);
		if (chunk != null && chunk.getBiomeIndex() != null)
			return chunk.getBiomeIndex().getBiome(blockPosition
				.getX() >> 2,
				block
					.getY() >> 2,
				blockPosition
					.getZ() >> 2);
		return null;
	}
	
	private List<BiomeBase> getBiomeTypesInChunk(BiomeStorage chunksBiomeIndex) {
		List<BiomeBase> biomes = new ArrayList<>();
		for (BiomeBase biomeBase : chunksBiomeIndex.e)
			biomes.add(biomeBase);
		return biomes;
	}
	
}


/*
 * Location:
 * C:\Users\quent.DESKTOP-L0VD1RP\Downloads\ToTheMoon.jar!\ToTheMoon\Main.class
 * Java compiler version: 7 (51.0) JD-Core Version: 1.1.3
 */
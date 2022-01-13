package ToTheMoon.MoonWorld.Game.Entities;

import ToTheMoon.Main;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;


public class EntitySection implements Listener {
	
	private Main main;
	
	public EntitySection(Main main) {
		this.main = main;
	}
	
	@EventHandler
	public void EntitySpawn(CreatureSpawnEvent e) {
		File file = new File("plugins" + File.separator + "ToTheMoon" + File.separator + "Mobs.yml");
		YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(file);
		if (file.exists()) {
			int max = this.main.getConfig().getStringList("ToTheMoon.Worlds").size();
			int n = (new Random()).nextInt(max);
			if (e.getLocation().getWorld().getName()
				.equalsIgnoreCase(this.main.getConfig().getStringList("ToTheMoon.Worlds").get(n))) {
				if (yamlConfiguration.getBoolean("ToTheMoon.Monsters.MobsGravity")) {
					e.getEntity().addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 2147483647,
						this.main.getConfig().getInt("ToTheMoon.MoonEffects.GravityLevel"), true, false));
					e.getEntity().addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 2147483647, 2, true, false));
					e.getEntity()
						.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 2147483647, 1, true, false));
					e.getEntity().addPotionEffect(
						new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 2147483647, 2, true, false));
				}
				if (e.getEntityType() == EntityType.SKELETON &&
					yamlConfiguration.getBoolean("ToTheMoon.Monsters.CustomMobs")) {
					ItemStack SkeletonHelmet = new ItemStack(
						Material.getMaterial(yamlConfiguration.getString("ToTheMoon.Monsters.Skeleton.Helmet")));
					e.getEntity().getEquipment().setHelmet(SkeletonHelmet);
					ItemStack SkeletonChestPlate = new ItemStack(
						Material.getMaterial(yamlConfiguration.getString("ToTheMoon.Monsters.Skeleton.Chestplate")));
					e.getEntity().getEquipment().setChestplate(SkeletonChestPlate);
					ItemStack SkeletonLeggings = new ItemStack(
						Material.getMaterial(yamlConfiguration.getString("ToTheMoon.Monsters.Skeleton.Leggings")));
					e.getEntity().getEquipment().setLeggings(SkeletonLeggings);
					ItemStack SkeletonBoots = new ItemStack(
						Material.getMaterial(yamlConfiguration.getString("ToTheMoon.Monsters.Skeleton.Boots")));
					e.getEntity().getEquipment().setBoots(SkeletonBoots);
					ArrayList<String> ens = (ArrayList<String>) yamlConfiguration
						.getStringList("ToTheMoon.Monsters.Zombie.ItemInHandEnchants");
					HashMap<Enchantment, Integer> enchantmentIntegerMap = new HashMap<>();
					for (String s : ens) {
						String[] ss = s.split(":");
						enchantmentIntegerMap.put(Enchantment.getByName(ss[0]),
							Integer.valueOf(Integer.parseInt(ss[1])));
					}
					Material bowMaterial =
						Material.getMaterial(yamlConfiguration.getString("ToTheMoon.Monsters.Skeleton.ItemInHand"));
					ItemStack Bow = new ItemStack(bowMaterial);
					Bow.addEnchantments(enchantmentIntegerMap);
					e.getEntity().getEquipment().setItemInMainHand(Bow);
					if (yamlConfiguration.getBoolean("ToTheMoon.Monsters.Skeleton.Strenght"))
						e.getEntity().addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 2147483647,
							yamlConfiguration.getInt("ToTheMoon.Monsters.Skeleton.StrenghtLevel")));
					e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH)
						.setBaseValue(yamlConfiguration.getDouble("ToTheMoon.Monsters.Skeleton.MaxHealth"));
					e.getEntity().setHealth(yamlConfiguration.getInt("ToTheMoon.Monsters.Skeleton.MaxHealth"));
				}
				if (e.getEntityType() == EntityType.ZOMBIE &&
					yamlConfiguration.getBoolean("ToTheMoon.Monsters.CustomMobs")) {
					ItemStack ZombieChestPlate = new ItemStack(
						Material.getMaterial(yamlConfiguration.getString("ToTheMoon.Monsters.Zombie.Chestplate")));
					e.getEntity().getEquipment().setChestplate(ZombieChestPlate);
					ItemStack ZombieLeggings = new ItemStack(
						Material.getMaterial(yamlConfiguration.getString("ToTheMoon.Monsters.Zombie.Leggings")));
					e.getEntity().getEquipment().setLeggings(ZombieLeggings);
					ItemStack ZombieBoots = new ItemStack(
						Material.getMaterial(yamlConfiguration.getString("ToTheMoon.Monsters.Zombie.Boots")));
					e.getEntity().getEquipment().setBoots(ZombieBoots);
					ArrayList<String> ens = (ArrayList<String>) yamlConfiguration
						.getStringList("ToTheMoon.Monsters.Zombie.ItemInHandEnchants");
					HashMap<Enchantment, Integer> enchantmentIntegerMap = new HashMap<>();
					for (String s : ens) {
						String[] ss = s.split(":");
						enchantmentIntegerMap.put(Enchantment.getByName(ss[0]),
							Integer.valueOf(Integer.parseInt(ss[1])));
					}
					Material itemInHandMaterial =
						Material.getMaterial(yamlConfiguration.getString("ToTheMoon.Monsters.Zombie.ItemInHand"));
					ItemStack ItemInHand = new ItemStack(itemInHandMaterial);
					ItemInHand.addEnchantments(enchantmentIntegerMap);
					e.getEntity().getEquipment().setItemInMainHand(ItemInHand);
					if (yamlConfiguration.getBoolean("ToTheMoon.Monsters.Zombie.Strenght"))
						e.getEntity().addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 2147483647,
							yamlConfiguration.getInt("ToTheMoon.Monsters.Skeleton.ZombieLevel")));
					e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH)
						.setBaseValue(yamlConfiguration.getDouble("ToTheMoon.Monsters.Zombie.MaxHealth"));
					e.getEntity().setHealth(yamlConfiguration.getInt("ToTheMoon.Monsters.Zombie.MaxHealth"));
					if (yamlConfiguration.getString("ToTheMoon.Monsters.Zombie.Helmet").contains("SpaceHelmet")) {
						ItemStack ZombieHelmet = new ItemStack(Material.GLASS);
						e.getEntity().getEquipment().setHelmet(ZombieHelmet);
					}
					else if (yamlConfiguration.getString("ToTheMoon.Monsters.Zombie.Helmet").contains("AlienSkull")) {
						ItemStack ZombieHelmet = new ItemStack(Material.PLAYER_HEAD, 1);
						SkullMeta killerSkullMeta = (SkullMeta) ZombieHelmet.getItemMeta();
						killerSkullMeta.setOwner("3xclusions");
						ZombieHelmet.setItemMeta((ItemMeta) killerSkullMeta);
						e.getEntity().getEquipment().setHelmet(ZombieHelmet);
					}
				}
			}
		}
	}
	
}


/*
 * Location:
 * C:\Users\quent.DESKTOP-L0VD1RP\Downloads\ToTheMoon.jar!\ToTheMoon\MoonWorld\
 * Game\Entities\EntitySection.class Java compiler version: 7 (51.0) JD-Core
 * Version: 1.1.3
 */
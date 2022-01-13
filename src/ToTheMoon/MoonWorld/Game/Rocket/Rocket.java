package ToTheMoon.MoonWorld.Game.Rocket;

import ToTheMoon.Main;
import ToTheMoon.Utils.ItemUtils;
import java.io.File;
import java.util.Random;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;


public class Rocket implements Listener {
	
	private Main main;
	
	int taskId;
	
	public Rocket(Main main) {
		this.taskId = -1;
		this.main = main;
	}
	
	@EventHandler
	public void RightClick(final PlayerInteractEvent e) {
		if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK)
			if (e.hasItem() && e.getItem().getType() == Material.FIREWORK_ROCKET
				&& ItemUtils.isNamedItem(e.getItem(),
					this.main.getConfig().getString("ToTheMoon.Items.Rocket.Name").replace("&", "§")))
				if (!e.getPlayer().isInsideVehicle())
					if (!this.main.getConfig().getStringList("ToTheMoon.Worlds")
						.contains(e.getPlayer().getWorld().getName()))
						if (this.main.getConfig().getBoolean("ToTheMoon.Items.Rocket.Enable")) {
							Block above = e.getPlayer().getEyeLocation().add(0.0D, 1.0D, 0.0D).getBlock();
							if (above.getType() == Material.AIR) {
								e.setCancelled(true);
								e.getPlayer().setFlying(false);
								final Vector jump = e.getPlayer().getLocation().getDirection().multiply(0).setY(20);
								e.getPlayer().setVelocity(e.getPlayer().getVelocity().add(jump));
								ItemStack itemStack = new ItemStack(Material.FIREWORK_ROCKET, 1);
								ItemUtils.setItemName(itemStack,
									this.main.getConfig().getString("ToTheMoon.Items.Rocket.Name").replace("&", "§"));
								e.getPlayer().getInventory().removeItem(new ItemStack[] {itemStack});
								e.getPlayer().updateInventory();
								this.taskId =
									Bukkit.getScheduler().scheduleSyncRepeatingTask((Plugin) this.main, new Runnable() {
										
										public void run() {
											File file = new File("plugins" + File.separator + "ToTheMoon"
												+ File.separator + "config.yml");
											YamlConfiguration yamlConfiguration =
												YamlConfiguration.loadConfiguration(file);
											if (e.getPlayer().getLocation().getY() < 125.0D) {
												Block above7 =
													e.getPlayer().getEyeLocation().add(0.0D, 4.0D, 0.0D).getBlock();
												if (above7.getType() != Material.AIR) {
													Bukkit.getScheduler().cancelTask(Rocket.this.taskId);
													e.getPlayer()
														.sendMessage(String.valueOf(yamlConfiguration
															.getString("ToTheMoon.Commands.Prefix").replace("&", "§"))
															+
															yamlConfiguration
																.getString("ToTheMoon.Items.Rocket.BlockAboveHead")
																.replace("&", "§"));
												}
												Block above6 =
													e.getPlayer().getEyeLocation().add(0.0D, 3.0D, 0.0D).getBlock();
												if (above6.getType() != Material.AIR) {
													Bukkit.getScheduler().cancelTask(Rocket.this.taskId);
													e.getPlayer()
														.sendMessage(String.valueOf(yamlConfiguration
															.getString("ToTheMoon.Commands.Prefix").replace("&", "§"))
															+
															yamlConfiguration
																.getString("ToTheMoon.Items.Rocket.BlockAboveHead")
																.replace("&", "§"));
												}
												Block above5 =
													e.getPlayer().getEyeLocation().add(0.0D, 2.0D, 0.0D).getBlock();
												if (above5.getType() != Material.AIR) {
													Bukkit.getScheduler().cancelTask(Rocket.this.taskId);
													e.getPlayer()
														.sendMessage(String.valueOf(yamlConfiguration
															.getString("ToTheMoon.Commands.Prefix").replace("&", "§"))
															+
															yamlConfiguration
																.getString("ToTheMoon.Items.Rocket.BlockAboveHead")
																.replace("&", "§"));
												}
												if (yamlConfiguration.getBoolean("ToTheMoon.Items.Rocket.Particles"))
													e.getPlayer().playEffect(e.getPlayer().getLocation(),
														Effect.MOBSPAWNER_FLAMES, Integer.valueOf(100));
												if (yamlConfiguration.getBoolean("ToTheMoon.Items.Rocket.Sound"))
													e.getPlayer().playSound(e.getPlayer().getLocation(),
														Sound.ENTITY_FIREWORK_ROCKET_LARGE_BLAST, 3.0F, 0.5F);
												e.getPlayer().setVelocity(e.getPlayer().getVelocity().add(jump));
											}
											else {
												int max = yamlConfiguration.getStringList("ToTheMoon.Worlds").size();
												int n = (new Random()).nextInt(max);
												World world = Bukkit.getWorld(
													yamlConfiguration.getStringList("ToTheMoon.Worlds").get(n));
												Location loc = new Location(world, 0.0D, 60.0D, 0.0D);
												Bukkit.getScheduler().cancelTask(Rocket.this.taskId);
												e.getPlayer().teleport(loc);
												e.getPlayer()
													.sendMessage(String
														.valueOf(yamlConfiguration
															.getString("ToTheMoon.Commands.Prefix").replace("&", "§"))
														+ yamlConfiguration
															.getString("ToTheMoon.Item.Rocket.MessageArrivedOnTheMoon")
															.replace("&", "§"));
											}
										}
										
									}, 0L, 10L);
							}
							else {
								e.getPlayer()
									.sendMessage(String
										.valueOf(this.main.getConfig().getString("ToTheMoon.Commands.Prefix")
											.replace("&", "§"))
										+
										this.main.getConfig().getString("ToTheMoon.Items.Rocket.BlockAboveHead")
											.replace("&", "§"));
							}
						}
	}
	
}


/*
 * Location:
 * C:\Users\quent.DESKTOP-L0VD1RP\Downloads\ToTheMoon.jar!\ToTheMoon\MoonWorld\
 * Game\Rocket\Rocket.class Java compiler version: 7 (51.0) JD-Core Version:
 * 1.1.3
 */
package ToTheMoon.MoonWorld.Game.GravityEffects;

import ToTheMoon.Main;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockFadeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;


public class Water2 implements Listener {
	
	private Main main;
	
	public Water2(Main main) {
		this.main = main;
	}
	
	@EventHandler
	public void Water1(PlayerInteractEvent e) {
		if (e.hasItem() && e.getItem().getType() == Material.WATER_BUCKET &&
			e.getAction() == Action.RIGHT_CLICK_BLOCK &&
			this.main.getConfig().getStringList("ToTheMoon.Worlds")
				.contains(e.getPlayer().getLocation().getWorld().getName()))
			if (!this.main.getConfig().getBoolean("ToTheMoon.MoonEffects.WaterOnMoon")) {
				e.setCancelled(true);
				e.getPlayer().getInventory().remove(e.getItem());
				e.getPlayer().getInventory().addItem(new ItemStack[] {new ItemStack(Material.BUCKET)});
			}
	}
	
	@EventHandler
	public void Water2(BlockBreakEvent e) {
		if (e.getBlock().getType() == Material.ICE &&
			this.main.getConfig().getStringList("ToTheMoon.Worlds")
				.contains(e.getPlayer().getLocation().getWorld().getName()))
			if (!this.main.getConfig().getBoolean("ToTheMoon.MoonEffects.WaterOnMoon")) {
				e.setCancelled(true);
				e.getBlock().setType(Material.AIR);
			}
	}
	
	@EventHandler (priority = EventPriority.HIGHEST)
	public void ice(BlockFadeEvent e) {
		if (e.getBlock().getType().equals(Material.ICE) &&
			!this.main.getConfig().getBoolean("ToTheMoon.MoonEffects.WaterOnMoon"))
			e.setCancelled(true);
	}
	
}


/*
 * Location:
 * C:\Users\quent.DESKTOP-L0VD1RP\Downloads\ToTheMoon.jar!\ToTheMoon\MoonWorld\
 * Game\GravityEffects\Water2.class Java compiler version: 7 (51.0) JD-Core
 * Version: 1.1.3
 */
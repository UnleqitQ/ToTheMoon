package ToTheMoon.MoonWorld.Game.MoonWorldJoinOrLeft;

import ToTheMoon.Main;
import me.arthed.custombiomecolors.CustomBiomeColors;
import me.arthed.custombiomecolors.utils.objects.BiomeColorType;

import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.world.ChunkLoadEvent;
import org.bukkit.inventory.ItemStack;


public class Casco implements Listener {
	
	private Main main;
	
	public Casco(Main main) {
		this.main = main;
	}
	
	@EventHandler
	public void OnInventoryClick(InventoryClickEvent event) {
		ItemStack itemStack1 = new ItemStack(Material.GLASS);
		ItemStack clicked = event.getCurrentItem();
		if (event.getSlotType().equals(InventoryType.SlotType.ARMOR) && clicked.getType() == Material.GLASS &&
			this.main.getConfig().getStringList("ToTheMoon.Worlds")
				.contains(event.getWhoClicked().getLocation().getWorld().getName())
			&&
			this.main.getConfig().getBoolean("ToTheMoon.GiveHelmetOnWorldJoin"))
			event.setCancelled(true);
	}
	
	@EventHandler
	public void onChunkEnter(ChunkLoadEvent event) {
		if (event.isNewChunk()
			&& main.getConfig().getStringList("ToTheMoon.Worlds").contains(event.getWorld().getName())) {
			Bukkit.getScheduler().runTaskLater(main, () -> {
				Chunk chunk = event.getChunk();
				try {
					for (int x = 0; x < 16; x++) {
						for (int z = 0; z < 16; z++) {
							for (int y = 0; y < 64; y++) {
								main.setBlockBiome(chunk.getBlock(x, y, z), main.customBiome);
							}
						}
					}
				}
				catch (Exception ex) {
					Bukkit.getLogger().log(Level.SEVERE, ex.getMessage(), ex);
				}
			}, 100L);
		}
	}
	
}


/*
 * Location:
 * C:\Users\quent.DESKTOP-L0VD1RP\Downloads\ToTheMoon.jar!\ToTheMoon\MoonWorld\
 * Game\MoonWorldJoinOrLeft\Casco.class Java compiler version: 7 (51.0) JD-Core
 * Version: 1.1.3
 */
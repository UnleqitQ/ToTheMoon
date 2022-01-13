package ToTheMoon.Utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;


public class ItemUtils {
	
	public static boolean isNamedItem(ItemStack item, String name) {
		if (item.hasItemMeta() && item.getItemMeta().hasDisplayName() &&
			ChatColor.stripColor(item.getItemMeta().getDisplayName()).equals(ChatColor.stripColor(name)))
			return true;
		return false;
	}
	
	public static ItemStack setItemName(ItemStack item, String name) {
		ItemMeta im = item.getItemMeta();
		im.setDisplayName(name);
		item.setItemMeta(im);
		return item;
	}
	
	public static ItemStack getPlayerSkull(String owner, int amount) {
		ItemStack item = new ItemStack(Material.PLAYER_HEAD, amount);
		SkullMeta itemMeta = (SkullMeta) item.getItemMeta();
		itemMeta.setOwner(owner);
		item.setItemMeta((ItemMeta) itemMeta);
		return item;
	}
	
	public static ItemStack getColoredBlock(ColoredBlock block, BlockColor color, int amount) {
		ItemStack item = new ItemStack(block.getType(color), amount);
		return item;
	}
	
	public enum ColoredBlock {
		
		WOOL,
		STAINED_GLASS,
		STAINED_GLASS_PANE,
		STAINED_CLAY;
		
		
		ColoredBlock() {}
		
		public Material getType(BlockColor color) {
			switch (this) {
				case WOOL: {
					switch (color) {
						case WHITE:
							return Material.WHITE_WOOL;
						case ORANGE:
							return Material.ORANGE_WOOL;
						case MAGENTA:
							return Material.MAGENTA_WOOL;
						case LIGHT_BLUE:
							return Material.LIGHT_BLUE_WOOL;
						case YELLOW:
							return Material.YELLOW_WOOL;
						case LIME_GREEN:
							return Material.LIME_WOOL;
						case PINK:
							return Material.PINK_WOOL;
						case GRAY:
							return Material.GRAY_WOOL;
						case LIGHT_GRAY:
							return Material.LIGHT_GRAY_WOOL;
						case CYAN:
							return Material.CYAN_WOOL;
						case PURPLE:
							return Material.PURPLE_WOOL;
						case BLUE:
							return Material.BLUE_WOOL;
						case BROWN:
							return Material.BROWN_WOOL;
						case GREEN:
							return Material.GREEN_WOOL;
						case RED:
							return Material.RED_WOOL;
						case BLACK:
							return Material.BLACK_WOOL;
						default:
							return Material.WHITE_WOOL;
					}
				}
				case STAINED_GLASS: {
					switch (color) {
						case WHITE:
							return Material.WHITE_STAINED_GLASS;
						case ORANGE:
							return Material.ORANGE_STAINED_GLASS;
						case MAGENTA:
							return Material.MAGENTA_STAINED_GLASS;
						case LIGHT_BLUE:
							return Material.LIGHT_BLUE_STAINED_GLASS;
						case YELLOW:
							return Material.YELLOW_STAINED_GLASS;
						case LIME_GREEN:
							return Material.LIME_STAINED_GLASS;
						case PINK:
							return Material.PINK_STAINED_GLASS;
						case GRAY:
							return Material.GRAY_STAINED_GLASS;
						case LIGHT_GRAY:
							return Material.LIGHT_GRAY_STAINED_GLASS;
						case CYAN:
							return Material.CYAN_STAINED_GLASS;
						case PURPLE:
							return Material.PURPLE_STAINED_GLASS;
						case BLUE:
							return Material.BLUE_STAINED_GLASS;
						case BROWN:
							return Material.BROWN_STAINED_GLASS;
						case GREEN:
							return Material.GREEN_STAINED_GLASS;
						case RED:
							return Material.RED_STAINED_GLASS;
						case BLACK:
							return Material.BLACK_STAINED_GLASS;
						default:
							return Material.GLASS;
					}
					
				}
				case STAINED_GLASS_PANE: {
					switch (color) {
						case WHITE:
							return Material.WHITE_STAINED_GLASS_PANE;
						case ORANGE:
							return Material.ORANGE_STAINED_GLASS_PANE;
						case MAGENTA:
							return Material.MAGENTA_STAINED_GLASS_PANE;
						case LIGHT_BLUE:
							return Material.LIGHT_BLUE_STAINED_GLASS_PANE;
						case YELLOW:
							return Material.YELLOW_STAINED_GLASS_PANE;
						case LIME_GREEN:
							return Material.LIME_STAINED_GLASS_PANE;
						case PINK:
							return Material.PINK_STAINED_GLASS_PANE;
						case GRAY:
							return Material.GRAY_STAINED_GLASS_PANE;
						case LIGHT_GRAY:
							return Material.LIGHT_GRAY_STAINED_GLASS_PANE;
						case CYAN:
							return Material.CYAN_STAINED_GLASS_PANE;
						case PURPLE:
							return Material.PURPLE_STAINED_GLASS_PANE;
						case BLUE:
							return Material.BLUE_STAINED_GLASS_PANE;
						case BROWN:
							return Material.BROWN_STAINED_GLASS_PANE;
						case GREEN:
							return Material.GREEN_STAINED_GLASS_PANE;
						case RED:
							return Material.RED_STAINED_GLASS_PANE;
						case BLACK:
							return Material.BLACK_STAINED_GLASS_PANE;
						default:
							return Material.GLASS_PANE;
					}
					
				}
				case STAINED_CLAY: {
					switch (color) {
						case WHITE:
							return Material.WHITE_TERRACOTTA;
						case ORANGE:
							return Material.ORANGE_TERRACOTTA;
						case MAGENTA:
							return Material.MAGENTA_TERRACOTTA;
						case LIGHT_BLUE:
							return Material.LIGHT_BLUE_TERRACOTTA;
						case YELLOW:
							return Material.YELLOW_TERRACOTTA;
						case LIME_GREEN:
							return Material.LIME_TERRACOTTA;
						case PINK:
							return Material.PINK_TERRACOTTA;
						case GRAY:
							return Material.GRAY_TERRACOTTA;
						case LIGHT_GRAY:
							return Material.LIGHT_GRAY_TERRACOTTA;
						case CYAN:
							return Material.CYAN_TERRACOTTA;
						case PURPLE:
							return Material.PURPLE_TERRACOTTA;
						case BLUE:
							return Material.BLUE_TERRACOTTA;
						case BROWN:
							return Material.BROWN_TERRACOTTA;
						case GREEN:
							return Material.GREEN_TERRACOTTA;
						case RED:
							return Material.RED_TERRACOTTA;
						case BLACK:
							return Material.BLACK_TERRACOTTA;
						default:
							return Material.TERRACOTTA;
					}
					
				}
				default: {
					return Material.STONE;
				}
			}
		}
		
	}
	
	public enum BlockColor {
		
		WHITE (0),
		ORANGE (1),
		MAGENTA (2),
		LIGHT_BLUE (3),
		YELLOW (4),
		LIME_GREEN (5),
		PINK (6),
		GRAY (7),
		LIGHT_GRAY (8),
		CYAN (
			9),
		PURPLE (10),
		BLUE (11),
		BROWN (12),
		GREEN (13),
		RED (14),
		BLACK (15);
		
		private int color;
		
		BlockColor(int color) {
			this.color = color;
		}
		
		public int getColor() {
			return this.color;
		}
		
	}
	
}


/*
 * Location:
 * C:\Users\quent.DESKTOP-L0VD1RP\Downloads\ToTheMoon.jar!\ToTheMoon\Utils\
 * ItemUtils.class Java compiler version: 7 (51.0) JD-Core Version: 1.1.3
 */
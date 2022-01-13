package ToTheMoon.MoonWorld.Game.MoonWorldJoinOrLeft;

import ToTheMoon.Main;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;


public class JoinWorld implements Listener {
	
	private Main main;
	
	public JoinWorld(Main main) {
		this.main = main;
	}
	
	@EventHandler (priority = EventPriority.HIGH)
	public void JoinWorld(PlayerChangedWorldEvent event) {
		if (this.main.getConfig().getStringList("ToTheMoon.Worlds")
			.contains(event.getPlayer().getLocation().getWorld().getName()) &&
			this.main.getConfig().getBoolean("ToTheMoon.MoonEffects.Gravity")) {
			event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 2147483647,
				this.main.getConfig().getInt("ToTheMoon.MoonEffects.GravityLevel"), true, false));
			event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 2147483647, 1, true, false));
			event.getPlayer()
				.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 2147483647, 1, true, false));
			if (this.main.getConfig().getBoolean("ToTheMoon.GiveHelmetOnWorldJoin")) {
				ItemStack Casco = new ItemStack(Material.GLASS);
				event.getPlayer().getInventory().setHelmet(Casco);
			}
			if (this.main.getConfig().getBoolean("ToTheMoon.MoonEffects.MiningFatigue"))
				event.getPlayer()
					.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 2147483647, 1, true, false));
		}
		else if (this.main.getConfig().getBoolean("ToTheMoon.RemoveGravityOnExitMoonWorld")) {
			event.getPlayer().removePotionEffect(PotionEffectType.JUMP);
			event.getPlayer().removePotionEffect(PotionEffectType.SLOW);
			event.getPlayer().removePotionEffect(PotionEffectType.SLOW_DIGGING);
			event.getPlayer().removePotionEffect(PotionEffectType.SLOW_FALLING);
		}
		if (!this.main.getConfig().getStringList("ToTheMoon.Worlds")
			.contains(event.getPlayer().getLocation().getWorld().getName())
			&& this.main.getConfig().getBoolean("ToTheMoon.GiveHelmetOnWorldJoin")) {
			ItemStack Casco2 = new ItemStack(Material.AIR);
			event.getPlayer().getInventory().setHelmet(Casco2);
		}
	}
	
	@EventHandler (priority = EventPriority.HIGH)
	public void JoinWorld(PlayerQuitEvent event) {
		event.getPlayer().removePotionEffect(PotionEffectType.JUMP);
		event.getPlayer().removePotionEffect(PotionEffectType.SLOW);
		event.getPlayer().removePotionEffect(PotionEffectType.SLOW_DIGGING);
		event.getPlayer().removePotionEffect(PotionEffectType.SLOW_FALLING);
		if (!this.main.getConfig().getStringList("ToTheMoon.Worlds")
			.contains(event.getPlayer().getLocation().getWorld().getName())
			&& this.main.getConfig().getBoolean("ToTheMoon.GiveHelmetOnWorldJoin")) {
			ItemStack Casco2 = new ItemStack(Material.AIR);
			event.getPlayer().getInventory().setHelmet(Casco2);
		}
	}
	
}


/*
 * Location:
 * C:\Users\quent.DESKTOP-L0VD1RP\Downloads\ToTheMoon.jar!\ToTheMoon\MoonWorld\
 * Game\MoonWorldJoinOrLeft\JoinWorld.class Java compiler version: 7 (51.0)
 * JD-Core Version: 1.1.3
 */
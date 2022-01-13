package ToTheMoon.MoonWorld.Effects;

import ToTheMoon.Main;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;


public class Move implements Listener {
	
	private Main main;
	
	public Move(Main main) {
		this.main = main;
	}
	
	@EventHandler
	public void PlayerMove(PlayerMoveEvent e) {
		if (!this.main.getConfig().getBoolean("ToTheMoon.MoonEffects.Gravity")) {
			e.getPlayer().removePotionEffect(PotionEffectType.JUMP);
			e.getPlayer().removePotionEffect(PotionEffectType.SLOW);
			e.getPlayer().removePotionEffect(PotionEffectType.SLOW_DIGGING);
			e.getPlayer().removePotionEffect(PotionEffectType.SLOW_FALLING);
		}
		if (!this.main.getConfig().getStringList("ToTheMoon.Worlds")
			.contains(e.getPlayer().getLocation().getWorld().getName())) {
			e.getPlayer().removePotionEffect(PotionEffectType.JUMP);
			e.getPlayer().removePotionEffect(PotionEffectType.SLOW);
			e.getPlayer().removePotionEffect(PotionEffectType.SLOW_DIGGING);
			e.getPlayer().removePotionEffect(PotionEffectType.SLOW_FALLING);
		}
		if (!this.main.getConfig().getStringList("ToTheMoon.Worlds").contains(e.getPlayer().getWorld().getName()))
			this.main.setPlayerWater(e.getPlayer(), this.main.maxwater);
	}
	
}


/*
 * Location:
 * C:\Users\quent.DESKTOP-L0VD1RP\Downloads\ToTheMoon.jar!\ToTheMoon\MoonWorld\
 * Effects\Move.class Java compiler version: 7 (51.0) JD-Core Version: 1.1.3
 */
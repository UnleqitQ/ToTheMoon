package ToTheMoon.MoonWorld.Effects;

import ToTheMoon.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Respawn implements Listener {
  private Main main;
  
  public Respawn(Main main) {
    this.main = main;
  }
  
  @EventHandler(priority = EventPriority.HIGH)
  public void onFallDamage(PlayerRespawnEvent event) {
    if (this.main.getConfig().getStringList("ToTheMoon.Worlds").contains(event.getPlayer().getLocation().getWorld().getName())) {
      event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 2147483647, this.main.getConfig().getInt("ToTheMoon.MoonEffects.GravityLevel")));
      event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 2147483647, 1));
      this.main.setPlayerWater(event.getPlayer(), this.main.maxwater);
      if (this.main.getConfig().getBoolean("ToTheMoon.MoonEffects.MiningFatigue"))
        event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 2147483647, 1)); 
    } else {
      event.getPlayer().removePotionEffect(PotionEffectType.JUMP);
      event.getPlayer().removePotionEffect(PotionEffectType.SLOW);
      event.getPlayer().removePotionEffect(PotionEffectType.SLOW_DIGGING);
    } 
  }
}


/* Location:              C:\Users\quent.DESKTOP-L0VD1RP\Downloads\ToTheMoon.jar!\ToTheMoon\MoonWorld\Effects\Respawn.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */
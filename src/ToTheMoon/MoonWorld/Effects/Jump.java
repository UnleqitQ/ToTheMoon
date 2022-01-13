package ToTheMoon.MoonWorld.Effects;

import ToTheMoon.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Jump implements Listener {
  private Main main;
  
  public Jump(Main main) {
    this.main = main;
  }
  
  @EventHandler
  public void PlayerJoin(PlayerJoinEvent e) {
    if (this.main.getConfig().getStringList("ToTheMoon.Worlds").contains(e.getPlayer().getLocation().getWorld().getName()) && 
      this.main.getConfig().getBoolean("ToTheMoon.MoonEffects.Gravity")) {
      e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 2147483647, this.main.getConfig().getInt("ToTheMoon.MoonEffects.GravityLevel")));
      e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 2147483647, 1));
      if (this.main.getConfig().getBoolean("ToTheMoon.MoonEffects.MiningFatigue"))
        e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 2147483647, 1)); 
    } else {
      e.getPlayer().removePotionEffect(PotionEffectType.JUMP);
      e.getPlayer().removePotionEffect(PotionEffectType.SLOW);
      e.getPlayer().removePotionEffect(PotionEffectType.SLOW_DIGGING);
    } 
  }
}


/* Location:              C:\Users\quent.DESKTOP-L0VD1RP\Downloads\ToTheMoon.jar!\ToTheMoon\MoonWorld\Effects\Jump.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */
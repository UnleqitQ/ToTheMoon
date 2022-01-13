package ToTheMoon.MoonWorld.Effects;

import ToTheMoon.Main;
import java.io.File;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class FallDamage implements Listener {
  public File file;
  
  public FileConfiguration fileConfig;
  
  private Main main;
  
  public FallDamage(Main main) {
    this.main = main;
  }
  
  @EventHandler(priority = EventPriority.HIGH)
  public void onFallDamage(EntityDamageEvent event) {
    if (event.getCause() == EntityDamageEvent.DamageCause.FALL && 
      this.main.getConfig().getStringList("ToTheMoon.Worlds").contains(event.getEntity().getLocation().getWorld().getName()) && 
      this.main.getConfig().getBoolean("ToTheMoon.MoonEffects.Gravity"))
      event.setCancelled(true); 
  }
}


/* Location:              C:\Users\quent.DESKTOP-L0VD1RP\Downloads\ToTheMoon.jar!\ToTheMoon\MoonWorld\Effects\FallDamage.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */
package ToTheMoon.MoonWorld.Commands;

import ToTheMoon.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class EssentialsBlock implements Listener {
  private Main main;
  
  public EssentialsBlock(Main main) {
    this.main = main;
  }
  
  @EventHandler
  public void onCmd1(PlayerCommandPreprocessEvent e) {
    if (this.main.getConfig().getBoolean("ToTheMoon.Commands.FixEssentials"))
      if (e.getMessage().toLowerCase().startsWith("/heal") || 
        e.getMessage().toLowerCase().startsWith("/eheal") || 
        e.getMessage().toLowerCase().startsWith("/essentials:heal") || 
        e.getMessage().toLowerCase().startsWith("/essentials:eheal"))
        if (e.getPlayer().hasPermission(this.main.getConfig().getString("ToTheMoon.Commands.EssentialsHealFixPermission"))) {
          e.setCancelled(true);
          e.getPlayer().setHealth(20.0D);
          e.getPlayer().setFoodLevel(20);
          e.getPlayer().sendMessage(String.valueOf(this.main.getConfig().getString("ToTheMoon.Commands.Prefix").replace("&", "§")) + 
              this.main.getConfig().getString("ToTheMoon.Commands.EssentialsHealFixMessage").replace("&", "§"));
        }   
  }
}


/* Location:              C:\Users\quent.DESKTOP-L0VD1RP\Downloads\ToTheMoon.jar!\ToTheMoon\MoonWorld\Commands\EssentialsBlock.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */
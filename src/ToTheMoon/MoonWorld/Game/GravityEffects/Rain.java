package ToTheMoon.MoonWorld.Game.GravityEffects;

import ToTheMoon.Main;
import java.util.Random;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;

public class Rain implements Listener {
  private Main main;
  
  public Rain(Main main) {
    this.main = main;
  }
  
  @EventHandler
  public void Water1(WeatherChangeEvent e) {
    int max = this.main.getConfig().getStringList("ToTheMoon.Worlds").size();
    int n = (new Random()).nextInt(max);
    if (this.main.getConfig().getStringList("ToTheMoon.Worlds").contains(e.getWorld().getName()) && 
      this.main.getConfig().getBoolean("ToTheMoon.WorldGeneration.NoRain"))
      e.setCancelled(true); 
  }
}


/* Location:              C:\Users\quent.DESKTOP-L0VD1RP\Downloads\ToTheMoon.jar!\ToTheMoon\MoonWorld\Game\GravityEffects\Rain.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */
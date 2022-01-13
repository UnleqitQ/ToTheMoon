package ToTheMoon.MoonWorld.Game.GravityEffects;

import ToTheMoon.Main;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

public class NoMilk implements Listener {
  private Main main;
  
  public NoMilk(Main main) {
    this.main = main;
  }
  
  @EventHandler
  public void Milk(PlayerItemConsumeEvent e) {
    ItemStack item1 = new ItemStack(Material.MILK_BUCKET);
    if (e.getItem().equals(item1) && e.getPlayer().hasPotionEffect(PotionEffectType.JUMP) && e.getPlayer().hasPotionEffect(PotionEffectType.SLOW)) {
      e.setCancelled(true);
      e.getPlayer().getInventory().remove(item1);
    } 
    if (e.getItem().equals(item1) && e.getPlayer().hasPotionEffect(PotionEffectType.SLOW_DIGGING)) {
      e.setCancelled(true);
      e.getPlayer().getInventory().remove(item1);
    } 
  }
}


/* Location:              C:\Users\quent.DESKTOP-L0VD1RP\Downloads\ToTheMoon.jar!\ToTheMoon\MoonWorld\Game\GravityEffects\NoMilk.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */
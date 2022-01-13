package ToTheMoon.Utils;

import java.io.File;
import java.io.IOException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class Join implements Listener {
  @EventHandler
  public void onJoin(PlayerJoinEvent e) {
    Player player = e.getPlayer();
    File file = new File("plugins" + File.separator + "ToTheMoon" + File.separator + "save.yml");
    YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(file);
    if (!yamlConfiguration.contains(String.valueOf(player.getName()) + "." + player.getUniqueId().toString())) {
      yamlConfiguration.set(String.valueOf(player.getName()) + "." + player.getUniqueId().toString(), Double.valueOf(100.0D));
      try {
        yamlConfiguration.save(file);
      } catch (IOException e1) {
        e1.printStackTrace();
      } 
    } 
  }
}


/* Location:              C:\Users\quent.DESKTOP-L0VD1RP\Downloads\ToTheMoon.jar!\ToTheMoon\Utils\Join.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */
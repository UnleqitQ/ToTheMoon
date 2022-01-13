package ToTheMoon.MoonWorld.Commands;

import ToTheMoon.Main;
import java.io.File;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class Oxygen implements CommandExecutor {
  private Main main;
  
  public Oxygen(Main main) {
    this.main = main;
  }
  
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    Player p = (Player)sender;
    if (label.equalsIgnoreCase("oxygen"))
      if (args.length == 0) {
        if (!(sender instanceof Player)) {
          sender.sendMessage("§4This command is not for console!");
          return true;
        } 
        Player player = (Player)sender;
        if (!p.hasPermission("ToTheMoon.Oxygen")) {
          p.sendMessage(String.valueOf(this.main.getConfig().getString("ToTheMoon.Commands.Prefix").replace("&", "§")) + 
              this.main.getConfig().getString("ToTheMoon.Commands.NoPermissions").replace("&", "§"));
          return true;
        } 
        File file = new File("plugins" + File.separator + "ToTheMoon" + File.separator + "save.yml");
        YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(file);
        p.sendMessage(String.valueOf(this.main.getConfig().getString("ToTheMoon.Commands.Prefix").replace("&", "§")) + 
            this.main.getConfig().getString("ToTheMoon.Commands.Oxygen").replace("&", "§") + 
            yamlConfiguration.getInt(String.valueOf(player.getName()) + "." + player.getUniqueId().toString()) + "%");
        return true;
      }  
    return false;
  }
}


/* Location:              C:\Users\quent.DESKTOP-L0VD1RP\Downloads\ToTheMoon.jar!\ToTheMoon\MoonWorld\Commands\Oxygen.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */
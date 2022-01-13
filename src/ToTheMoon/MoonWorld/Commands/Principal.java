package ToTheMoon.MoonWorld.Commands;

import ToTheMoon.Main;
import java.io.File;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class Principal implements CommandExecutor {
  private Main main;
  
  public Principal(Main main) {
    this.main = main;
  }
  
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if (label.equalsIgnoreCase("ToTheMoon") || label.equalsIgnoreCase("TTM")) {
      if (args.length == 0) {
        if (!(sender instanceof Player)) {
          sender.sendMessage("§4This command is not for console!");
          return true;
        } 
        Player p = (Player)sender;
        if (!p.hasPermission("ToTheMoon.help")) {
          p.sendMessage(String.valueOf(this.main.getConfig().getString("ToTheMoon.Commands.Prefix").replace("&", "§")) + 
              this.main.getConfig().getString("ToTheMoon.Commands.NoPermissions").replace("&", "§"));
          return true;
        } 
        p.sendMessage(String.valueOf(this.main.getConfig().getString("ToTheMoon.Commands.Prefix").replace("&", "§")) + 
            this.main.getConfig().getString("ToTheMoon.Commands.UseHelp").replace("&", "§"));
        return true;
      } 
      if (args.length >= 1) {
        Player p = (Player)sender;
        if (args[0].equalsIgnoreCase("Help")) {
          if (!p.hasPermission("ToTheMoon.help")) {
            p.sendMessage(String.valueOf(this.main.getConfig().getString("ToTheMoon.Commands.Prefix").replace("&", "§")) + 
                this.main.getConfig().getString("ToTheMoon.Commands.NoPermissions").replace("&", "§"));
            return true;
          } 
          p.sendMessage("§9§m-]----- §6☪ §9ToTheMoon §6☪§9§m-----[-");
          p.sendMessage("");
          p.sendMessage("§9●- §7/ToTheMoon help");
          p.sendMessage("§9●- §7/ToTheMoon oxygen");
          p.sendMessage("§9●- §7/ToTheMoon perms");
          p.sendMessage("§9●- §7/TTM help");
          p.sendMessage("§9●- §7/TTM oxygen");
          p.sendMessage("§9●- §7/TTM perms");
          p.sendMessage("§9●- §7/oxygen");
          p.sendMessage("");
          p.sendMessage("§9§m-]----- §6☪ §9ToTheMoon §6☪§9§m-----[-");
          return true;
        } 
      } 
      if (args.length >= 1)
        if (args[0].equalsIgnoreCase("oxygen")) {
          Player p = (Player)sender;
          if (!p.hasPermission("ToTheMoon.Oxygen")) {
            p.sendMessage(String.valueOf(this.main.getConfig().getString("ToTheMoon.Commands.Prefix").replace("&", "§")) + 
                this.main.getConfig().getString("ToTheMoon.Commands.NoPermissions").replace("&", "§"));
            return true;
          } 
          File file = new File("plugins" + File.separator + "ToTheMoon" + File.separator + "save.yml");
          YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(file);
          p.sendMessage(String.valueOf(this.main.getConfig().getString("ToTheMoon.Commands.Prefix").replace("&", "§")) + 
              this.main.getConfig().getString("ToTheMoon.Commands.Oxygen").replace("&", "§") + 
              yamlConfiguration.getInt(String.valueOf(p.getName()) + "." + p.getUniqueId().toString()) + "%");
          return true;
        }  
      if (args.length >= 1)
        if (args[0].equalsIgnoreCase("perms")) {
          Player p = (Player)sender;
          if (!p.hasPermission("ToTheMoon.permissions")) {
            p.sendMessage(String.valueOf(this.main.getConfig().getString("ToTheMoon.Commands.Prefix").replace("&", "§")) + 
                this.main.getConfig().getString("ToTheMoon.Commands.NoPermissions").replace("&", "§"));
            return true;
          } 
          p.sendMessage("§9§m-]----- §6☪ §9ToTheMoon §6☪§9§m-----[-");
          p.sendMessage("");
          p.sendMessage("§aToTheMoon.help §7for /ttm help");
          p.sendMessage("§aToTheMoon.oxygen §7for /oxygen");
          p.sendMessage("§aToTheMoon.permissions §7for /ttm perms");
          p.sendMessage("");
          p.sendMessage("§9§m-]----- §6☪ §9ToTheMoon §6☪§9§m-----[-");
          return true;
        }  
    } 
    return false;
  }
}


/* Location:              C:\Users\quent.DESKTOP-L0VD1RP\Downloads\ToTheMoon.jar!\ToTheMoon\MoonWorld\Commands\Principal.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */
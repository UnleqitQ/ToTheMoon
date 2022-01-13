package ToTheMoon.Fb;

import be.maximvdw.placeholderapi.PlaceholderAPI;
import be.maximvdw.placeholderapi.PlaceholderReplaceEvent;
import be.maximvdw.placeholderapi.PlaceholderReplacer;
import java.io.File;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class Fb implements PlaceholderReplacer {
  public Fb(Plugin pPlugin) {
    PlaceholderAPI.registerPlaceholder(pPlugin, "oxygen", this);
  }
  
  public String onPlaceholderReplace(PlaceholderReplaceEvent event) {
    Player player = event.getPlayer();
    File file = new File("plugins" + File.separator + "ToTheMoon" + File.separator + "save.yml");
    YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(file);
    if (yamlConfiguration.getInt(String.valueOf(player.getName()) + "." + player.getUniqueId().toString()) == 100)
      return "100%"; 
    if (yamlConfiguration.getInt(String.valueOf(player.getName()) + "." + player.getUniqueId().toString()) == 99)
      return "99%"; 
    if (yamlConfiguration.getInt(String.valueOf(player.getName()) + "." + player.getUniqueId().toString()) == 98)
      return "98%"; 
    if (yamlConfiguration.getInt(String.valueOf(player.getName()) + "." + player.getUniqueId().toString()) == 97)
      return "97%"; 
    if (yamlConfiguration.getInt(String.valueOf(player.getName()) + "." + player.getUniqueId().toString()) == 96)
      return "96%"; 
    if (yamlConfiguration.getInt(String.valueOf(player.getName()) + "." + player.getUniqueId().toString()) == 95)
      return "95%"; 
    if (yamlConfiguration.getInt(String.valueOf(player.getName()) + "." + player.getUniqueId().toString()) == 94)
      return "94%"; 
    if (yamlConfiguration.getInt(String.valueOf(player.getName()) + "." + player.getUniqueId().toString()) == 93)
      return "93%"; 
    if (yamlConfiguration.getInt(String.valueOf(player.getName()) + "." + player.getUniqueId().toString()) == 92)
      return "92%"; 
    if (yamlConfiguration.getInt(String.valueOf(player.getName()) + "." + player.getUniqueId().toString()) == 91)
      return "91%"; 
    if (yamlConfiguration.getInt(String.valueOf(player.getName()) + "." + player.getUniqueId().toString()) == 90)
      return "90%"; 
    if (yamlConfiguration.getInt(String.valueOf(player.getName()) + "." + player.getUniqueId().toString()) == 89)
      return "89%"; 
    if (yamlConfiguration.getInt(String.valueOf(player.getName()) + "." + player.getUniqueId().toString()) == 88)
      return "88%"; 
    if (yamlConfiguration.getInt(String.valueOf(player.getName()) + "." + player.getUniqueId().toString()) == 87)
      return "87%"; 
    if (yamlConfiguration.getInt(String.valueOf(player.getName()) + "." + player.getUniqueId().toString()) == 86)
      return "86%"; 
    if (yamlConfiguration.getInt(String.valueOf(player.getName()) + "." + player.getUniqueId().toString()) == 85)
      return "85%"; 
    if (yamlConfiguration.getInt(String.valueOf(player.getName()) + "." + player.getUniqueId().toString()) == 84)
      return "84%"; 
    if (yamlConfiguration.getInt(String.valueOf(player.getName()) + "." + player.getUniqueId().toString()) == 83)
      return "83%"; 
    if (yamlConfiguration.getInt(String.valueOf(player.getName()) + "." + player.getUniqueId().toString()) == 82)
      return "82%"; 
    if (yamlConfiguration.getInt(String.valueOf(player.getName()) + "." + player.getUniqueId().toString()) == 81)
      return "81%"; 
    if (yamlConfiguration.getInt(String.valueOf(player.getName()) + "." + player.getUniqueId().toString()) == 80)
      return "80%"; 
    if (yamlConfiguration.getInt(String.valueOf(player.getName()) + "." + player.getUniqueId().toString()) == 79)
      return "79%"; 
    if (yamlConfiguration.getInt(String.valueOf(player.getName()) + "." + player.getUniqueId().toString()) == 78)
      return "78%"; 
    if (yamlConfiguration.getInt(String.valueOf(player.getName()) + "." + player.getUniqueId().toString()) == 77)
      return "77%"; 
    if (yamlConfiguration.getInt(String.valueOf(player.getName()) + "." + player.getUniqueId().toString()) == 76)
      return "76%"; 
    if (yamlConfiguration.getInt(String.valueOf(player.getName()) + "." + player.getUniqueId().toString()) == 75)
      return "75%"; 
    if (yamlConfiguration.getInt(String.valueOf(player.getName()) + "." + player.getUniqueId().toString()) == 74)
      return "74%"; 
    if (yamlConfiguration.getInt(String.valueOf(player.getName()) + "." + player.getUniqueId().toString()) == 73)
      return "73%"; 
    if (yamlConfiguration.getInt(String.valueOf(player.getName()) + "." + player.getUniqueId().toString()) == 72)
      return "72%"; 
    if (yamlConfiguration.getInt(String.valueOf(player.getName()) + "." + player.getUniqueId().toString()) == 71)
      return "71%"; 
    if (yamlConfiguration.getInt(String.valueOf(player.getName()) + "." + player.getUniqueId().toString()) == 70)
      return "70%"; 
    if (yamlConfiguration.getInt(String.valueOf(player.getName()) + "." + player.getUniqueId().toString()) == 69)
      return "69%"; 
    if (yamlConfiguration.getInt(String.valueOf(player.getName()) + "." + player.getUniqueId().toString()) == 68)
      return "68%"; 
    if (yamlConfiguration.getInt(String.valueOf(player.getName()) + "." + player.getUniqueId().toString()) == 67)
      return "67%"; 
    if (yamlConfiguration.getInt(String.valueOf(player.getName()) + "." + player.getUniqueId().toString()) == 66)
      return "66%"; 
    if (yamlConfiguration.getInt(String.valueOf(player.getName()) + "." + player.getUniqueId().toString()) == 65)
      return "65%"; 
    if (yamlConfiguration.getInt(String.valueOf(player.getName()) + "." + player.getUniqueId().toString()) == 64)
      return "64%"; 
    if (yamlConfiguration.getInt(String.valueOf(player.getName()) + "." + player.getUniqueId().toString()) == 63)
      return "63%"; 
    if (yamlConfiguration.getInt(String.valueOf(player.getName()) + "." + player.getUniqueId().toString()) == 62)
      return "62%"; 
    if (yamlConfiguration.getInt(String.valueOf(player.getName()) + "." + player.getUniqueId().toString()) == 61)
      return "61%"; 
    if (yamlConfiguration.getInt(String.valueOf(player.getName()) + "." + player.getUniqueId().toString()) == 60)
      return "60%"; 
    if (yamlConfiguration.getInt(String.valueOf(player.getName()) + "." + player.getUniqueId().toString()) == 59)
      return "59%"; 
    if (yamlConfiguration.getInt(String.valueOf(player.getName()) + "." + player.getUniqueId().toString()) == 58)
      return "58%"; 
    if (yamlConfiguration.getInt(String.valueOf(player.getName()) + "." + player.getUniqueId().toString()) == 57)
      return "57%"; 
    if (yamlConfiguration.getInt(String.valueOf(player.getName()) + "." + player.getUniqueId().toString()) == 56)
      return "56%"; 
    if (yamlConfiguration.getInt(String.valueOf(player.getName()) + "." + player.getUniqueId().toString()) == 55)
      return "55%"; 
    if (yamlConfiguration.getInt(String.valueOf(player.getName()) + "." + player.getUniqueId().toString()) == 54)
      return "54%"; 
    if (yamlConfiguration.getInt(String.valueOf(player.getName()) + "." + player.getUniqueId().toString()) == 53)
      return "53%"; 
    if (yamlConfiguration.getInt(String.valueOf(player.getName()) + "." + player.getUniqueId().toString()) == 52)
      return "52%"; 
    if (yamlConfiguration.getInt(String.valueOf(player.getName()) + "." + player.getUniqueId().toString()) == 51)
      return "51%"; 
    if (yamlConfiguration.getInt(String.valueOf(player.getName()) + "." + player.getUniqueId().toString()) == 50)
      return "50%"; 
    if (yamlConfiguration.getInt(String.valueOf(player.getName()) + "." + player.getUniqueId().toString()) == 49)
      return "49%"; 
    if (yamlConfiguration.getInt(String.valueOf(player.getName()) + "." + player.getUniqueId().toString()) == 48)
      return "48%"; 
    if (yamlConfiguration.getInt(String.valueOf(player.getName()) + "." + player.getUniqueId().toString()) == 47)
      return "47%"; 
    if (yamlConfiguration.getInt(String.valueOf(player.getName()) + "." + player.getUniqueId().toString()) == 46)
      return "46%"; 
    if (yamlConfiguration.getInt(String.valueOf(player.getName()) + "." + player.getUniqueId().toString()) == 45)
      return "45%"; 
    if (yamlConfiguration.getInt(String.valueOf(player.getName()) + "." + player.getUniqueId().toString()) == 44)
      return "44%"; 
    if (yamlConfiguration.getInt(String.valueOf(player.getName()) + "." + player.getUniqueId().toString()) == 43)
      return "43%"; 
    if (yamlConfiguration.getInt(String.valueOf(player.getName()) + "." + player.getUniqueId().toString()) == 42)
      return "42%"; 
    if (yamlConfiguration.getInt(String.valueOf(player.getName()) + "." + player.getUniqueId().toString()) == 41)
      return "41%"; 
    if (yamlConfiguration.getInt(String.valueOf(player.getName()) + "." + player.getUniqueId().toString()) == 40)
      return "40%"; 
    if (yamlConfiguration.getInt(String.valueOf(player.getName()) + "." + player.getUniqueId().toString()) == 39)
      return "39%"; 
    if (yamlConfiguration.getInt(String.valueOf(player.getName()) + "." + player.getUniqueId().toString()) == 38)
      return "38%"; 
    if (yamlConfiguration.getInt(String.valueOf(player.getName()) + "." + player.getUniqueId().toString()) == 37)
      return "37%"; 
    if (yamlConfiguration.getInt(String.valueOf(player.getName()) + "." + player.getUniqueId().toString()) == 36)
      return "36%"; 
    if (yamlConfiguration.getInt(String.valueOf(player.getName()) + "." + player.getUniqueId().toString()) == 35)
      return "35%"; 
    if (yamlConfiguration.getInt(String.valueOf(player.getName()) + "." + player.getUniqueId().toString()) == 34)
      return "34%"; 
    if (yamlConfiguration.getInt(String.valueOf(player.getName()) + "." + player.getUniqueId().toString()) == 33)
      return "33%"; 
    if (yamlConfiguration.getInt(String.valueOf(player.getName()) + "." + player.getUniqueId().toString()) == 32)
      return "32%"; 
    if (yamlConfiguration.getInt(String.valueOf(player.getName()) + "." + player.getUniqueId().toString()) == 31)
      return "31%"; 
    if (yamlConfiguration.getInt(String.valueOf(player.getName()) + "." + player.getUniqueId().toString()) == 30)
      return "30%"; 
    if (yamlConfiguration.getInt(String.valueOf(player.getName()) + "." + player.getUniqueId().toString()) == 29)
      return "29%"; 
    if (yamlConfiguration.getInt(String.valueOf(player.getName()) + "." + player.getUniqueId().toString()) == 28)
      return "28%"; 
    if (yamlConfiguration.getInt(String.valueOf(player.getName()) + "." + player.getUniqueId().toString()) == 27)
      return "27%"; 
    if (yamlConfiguration.getInt(String.valueOf(player.getName()) + "." + player.getUniqueId().toString()) == 26)
      return "26%"; 
    if (yamlConfiguration.getInt(String.valueOf(player.getName()) + "." + player.getUniqueId().toString()) == 25)
      return "25%"; 
    if (yamlConfiguration.getInt(String.valueOf(player.getName()) + "." + player.getUniqueId().toString()) == 24)
      return "24%"; 
    if (yamlConfiguration.getInt(String.valueOf(player.getName()) + "." + player.getUniqueId().toString()) == 23)
      return "23%"; 
    if (yamlConfiguration.getInt(String.valueOf(player.getName()) + "." + player.getUniqueId().toString()) == 22)
      return "22%"; 
    if (yamlConfiguration.getInt(String.valueOf(player.getName()) + "." + player.getUniqueId().toString()) == 21)
      return "21%"; 
    if (yamlConfiguration.getInt(String.valueOf(player.getName()) + "." + player.getUniqueId().toString()) == 20)
      return "20%"; 
    if (yamlConfiguration.getInt(String.valueOf(player.getName()) + "." + player.getUniqueId().toString()) == 19)
      return "19%"; 
    if (yamlConfiguration.getInt(String.valueOf(player.getName()) + "." + player.getUniqueId().toString()) == 18)
      return "18%"; 
    if (yamlConfiguration.getInt(String.valueOf(player.getName()) + "." + player.getUniqueId().toString()) == 17)
      return "17%"; 
    if (yamlConfiguration.getInt(String.valueOf(player.getName()) + "." + player.getUniqueId().toString()) == 16)
      return "16%"; 
    if (yamlConfiguration.getInt(String.valueOf(player.getName()) + "." + player.getUniqueId().toString()) == 15)
      return "15%"; 
    if (yamlConfiguration.getInt(String.valueOf(player.getName()) + "." + player.getUniqueId().toString()) == 14)
      return "14%"; 
    if (yamlConfiguration.getInt(String.valueOf(player.getName()) + "." + player.getUniqueId().toString()) == 13)
      return "13%"; 
    if (yamlConfiguration.getInt(String.valueOf(player.getName()) + "." + player.getUniqueId().toString()) == 12)
      return "12%"; 
    if (yamlConfiguration.getInt(String.valueOf(player.getName()) + "." + player.getUniqueId().toString()) == 11)
      return "11%"; 
    if (yamlConfiguration.getInt(String.valueOf(player.getName()) + "." + player.getUniqueId().toString()) == 10)
      return "10%"; 
    if (yamlConfiguration.getInt(String.valueOf(player.getName()) + "." + player.getUniqueId().toString()) == 9)
      return "9%"; 
    if (yamlConfiguration.getInt(String.valueOf(player.getName()) + "." + player.getUniqueId().toString()) == 8)
      return "8%"; 
    if (yamlConfiguration.getInt(String.valueOf(player.getName()) + "." + player.getUniqueId().toString()) == 7)
      return "7%"; 
    if (yamlConfiguration.getInt(String.valueOf(player.getName()) + "." + player.getUniqueId().toString()) == 6)
      return "6%"; 
    if (yamlConfiguration.getInt(String.valueOf(player.getName()) + "." + player.getUniqueId().toString()) == 5)
      return "5%"; 
    if (yamlConfiguration.getInt(String.valueOf(player.getName()) + "." + player.getUniqueId().toString()) == 4)
      return "4%"; 
    if (yamlConfiguration.getInt(String.valueOf(player.getName()) + "." + player.getUniqueId().toString()) == 3)
      return "3%"; 
    if (yamlConfiguration.getInt(String.valueOf(player.getName()) + "." + player.getUniqueId().toString()) == 2)
      return "2%"; 
    if (yamlConfiguration.getInt(String.valueOf(player.getName()) + "." + player.getUniqueId().toString()) == 1)
      return "1%"; 
    if (yamlConfiguration.getInt(String.valueOf(player.getName()) + "." + player.getUniqueId().toString()) == 0)
      return "0%"; 
    return "";
  }
}


/* Location:              C:\Users\quent.DESKTOP-L0VD1RP\Downloads\ToTheMoon.jar!\ToTheMoon\Fb\Fb.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */
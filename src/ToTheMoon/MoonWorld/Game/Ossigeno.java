package ToTheMoon.MoonWorld.Game;

import ToTheMoon.Main;
import ToTheMoon.Utils.ItemUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;


public class Ossigeno implements Listener {
	
	private Main main;
	
	public Ossigeno(Main main) {
		this.main = main;
	}
	
	@EventHandler
	public void consumeitem(PlayerItemConsumeEvent event) {
		Player player = event.getPlayer();
		ItemStack item = event.getItem();
		if (player.getItemInUse().getType() == Material.POTION)
			if (ItemUtils.isNamedItem(event.getItem(),
				this.main.getConfig().getString("ToTheMoon.Items.OxygenBottle.Name").replace("&", "§"))) {
				double pw = this.main.getPlayerWater(player) + this.main.drinkingwater;
				if (pw >= this.main.maxwater)
					pw = this.main.maxwater;
				this.main.setPlayerWater(player, pw);
			}
	}
	
}


/*
 * Location:
 * C:\Users\quent.DESKTOP-L0VD1RP\Downloads\ToTheMoon.jar!\ToTheMoon\MoonWorld\
 * Game\Ossigeno.class Java compiler version: 7 (51.0) JD-Core Version: 1.1.3
 */
package ToTheMoon.MoonWorld.Vines;

import java.io.File;
import java.util.Random;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.generator.BlockPopulator;


public class Diamond extends BlockPopulator {
	
	Random r;
	
	public File file;
	
	public FileConfiguration fileConfig;
	
	public void populate(World world, Random r, Chunk chunk) {
		this.file = new File("plugins" + File.separator + "ToTheMoon" + File.separator + "config.yml");
		this.fileConfig = (FileConfiguration) YamlConfiguration.loadConfiguration(this.file);
		for (int i = 1; i < 15; i++) {
			if (r.nextInt(100) < this.fileConfig.getInt("ToTheMoon.WorldGeneration.DiamondSpawnPercentage")) {
				int X = r.nextInt(15);
				int Z = r.nextInt(15);
				int Y = r.nextInt(40) + 20;
				if (chunk.getBlock((X + 16) % 16, Y, (Z + 16) % 16).getType() == Material.END_STONE) {
					boolean isStone = true;
					while (isStone) {
						try {
							chunk.getBlock((X + 16) % 16, Y, (Z + 16) % 16).setType(Material.DIAMOND_ORE);
							if (r.nextInt(100) < 40) {
								switch (r.nextInt(5)) {
									case 0:
										X++;
										break;
									case 1:
										Y++;
										break;
									case 2:
										Z++;
										break;
									case 3:
										X--;
										break;
									case 4:
										Y--;
										break;
									case 5:
										Z--;
										break;
								}
								isStone =
									(chunk.getBlock((X + 16) % 16, Y, (Z + 16) % 16).getType() == Material.END_STONE
										&& chunk.getBlock((X + 16) % 16, Y, (Z + 16) % 16).getType()
											!= Material.DIAMOND_ORE);
								continue;
							}
							isStone = false;
						}
						catch (Exception ex) {
							break;
						}
					}
				}
			}
		}
	}
	
}


/*
 * Location:
 * C:\Users\quent.DESKTOP-L0VD1RP\Downloads\ToTheMoon.jar!\ToTheMoon\MoonWorld\
 * Vines\Diamond.class Java compiler version: 7 (51.0) JD-Core Version: 1.1.3
 */
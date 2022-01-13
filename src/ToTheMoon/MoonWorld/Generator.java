package ToTheMoon.MoonWorld;

import ToTheMoon.Main;
import ToTheMoon.MoonWorld.Vines.Coal;
import ToTheMoon.MoonWorld.Vines.Diamond;
import ToTheMoon.MoonWorld.Vines.Emerald;
import ToTheMoon.MoonWorld.Vines.Gold;
import ToTheMoon.MoonWorld.Vines.Iron;
import ToTheMoon.MoonWorld.Vines.Lapis;
import ToTheMoon.MoonWorld.Vines.Redstone;
import net.minecraft.core.BlockPosition;
import net.minecraft.core.IRegistry;
import net.minecraft.resources.MinecraftKey;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.WorldServer;
import net.minecraft.world.level.biome.BiomeBase;
import net.minecraft.world.level.biome.BiomeFog;
import net.minecraft.world.level.biome.BiomeSettingsGeneration;
import net.minecraft.world.level.biome.BiomeSettingsMobs;
import net.minecraft.world.level.chunk.BiomeStorage;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.generator.WorldInfo;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.noise.SimplexOctaveGenerator;

import com.mojang.serialization.Lifecycle;


public class Generator extends ChunkGenerator {
	
	Plugin plugin;
	
	Random r;
	
	Logger log;
	
	public Generator(Plugin p, Logger l) {
		this.plugin = p;
		this.log = l;
	}
	
	@Override
	public void generateSurface(WorldInfo worldInfo, Random random, int chunkX, int chunkZ, ChunkData chunkData) {
		SimplexOctaveGenerator octave = new SimplexOctaveGenerator(worldInfo.getSeed(), 24);
		octave.setScale(0.0078125D);
		// Chunk chunk = chunkData.set.getChunkAt(chunkX, chunkZ);
		int x;
		// Block[] blocks = new Block[16 * 16 * 256];
		// Chunk chunk = Bukkit.getWorld(worldInfo.getUID()).getChunkAt(chunkX,
		// chunkZ);
		for (x = 0; x < 16; x++) {
			for (int z = 0; z < 16; z++) {
				chunkData.setBlock(x, 0, z, Material.BEDROCK);
				double noise = octave.noise((x + chunkX * 16), (z + chunkZ * 16), 1D, 0.5D) * 24.0D;
				double dx = octave.noise((x + chunkX * 16 + 1), (z + chunkZ * 16 + 1), 1D, 0.5D) * 24.0D
					- octave.noise((x + chunkX * 16 - 1), (z + chunkZ * 16 + 1), 1D, 0.5D) * 24.0D
					+ octave.noise((x + chunkX * 16 + 1), (z + chunkZ * 16), 1D, 0.5D) * 48.0D
					- octave.noise((x + chunkX * 16 - 1), (z + chunkZ * 16), 1D, 0.5D) * 48.0D
					+ octave.noise((x + chunkX * 16 + 1), (z + chunkZ * 16 - 1), 1D, 0.5D) * 24.0D
					- octave.noise((x + chunkX * 16 - 1), (z + chunkZ * 16 - 1), 1D, 0.5D) * 24.0D;
				double dz = octave.noise((x + chunkX * 16 + 1), (z + chunkZ * 16 + 1), 1D, 0.5D) * 24.0D
					- octave.noise((x + chunkX * 16 + 1), (z + chunkZ * 16 - 1), 1D, 0.5D) * 24.0D
					+ octave.noise((x + chunkX * 16), (z + chunkZ * 16 + 1), 1D, 0.5D) * 48.0D
					- octave.noise((x + chunkX * 16), (z + chunkZ * 16 - 1), 1D, 0.5D) * 48.0D
					+ octave.noise((x + chunkX * 16 - 1), (z + chunkZ * 16 + 1), 1D, 0.5D) * 24.0D
					- octave.noise((x + chunkX * 16 - 1), (z + chunkZ * 16 - 1), 1D, 0.5D) * 24.0D;
				for (int y = 1; y < 32.0D + noise + Math.sqrt(dx * dx + dz * dz) * 4.0D; y++) {
					chunkData.setBlock(x, y, z, Material.END_STONE);
				}
				// for (int y = 0; y < 256; y++) {
				// blocks[(y * 16 + x) * 16 + z] = chunk.getBlock(x, y, z);
				// }
			}
		}
		for (x = 0; x < 16; x++) {
			for (int z = 0; z < 16; z++) {
				for (int y = 1; y < 14; y++) {
					if (chunkData.getType(x, y, z) == Material.AIR)
						chunkData.setBlock(x, y, z, Material.ICE);
				}
			}
		}
		
		// world.setBiome(chunkX * 16, 16, chunkZ * 16, Biome.PLAINS);
		// Bukkit.getWorld(worldInfo.getUID()).setBiome(chunkX * 16, 16, chunkZ
		// * 16, Main.getInstance().customBiome);
		// BiomeKey biomeKey = new BiomeKey("moon");
		// CustomBiomeColors.getInstance().getBiomeManager().changeBiomeColor(blocks,
		// BiomeColorType.SKY, 0x0000ff,
		// biomeKey);
	}
	
	
	public List<BlockPopulator> getDefaultPopulators(World world) {
		ArrayList<BlockPopulator> populators = new ArrayList<>();
		populators.add(new Coal());
		populators.add(new Iron());
		populators.add(new Gold());
		populators.add(new Diamond());
		populators.add(new Emerald());
		populators.add(new Redstone());
		populators.add(new Lapis());
		return populators;
	}
	
	public Location getFixedSpawnLocation(World world, Random random) {
		return new Location(world, 0.0D, 60.0D, 0.0D);
	}
	
	private int coordinateToByte(int x, int y, int z) {
		return (x * 16 + z) * 128 + y;
	}
	
	public void setBlocksBiome(World world, int x, int y, int z, BiomeBase base) {
		BlockPosition blockPosition = new BlockPosition(x, y, z);
		WorldServer worldServer = ((CraftWorld) world).getHandle();
		net.minecraft.world.level.chunk.Chunk chunk = worldServer.getChunkAtWorldCoords(blockPosition);
		if (chunk != null && chunk.getBiomeIndex() != null) {
			List<BiomeBase> oldBiomes = getBiomeTypesInChunk(chunk.getBiomeIndex());
			chunk.getBiomeIndex().setBiome(blockPosition
				.getX() >> 2,
				blockPosition
					.getY() >> 2,
				blockPosition
					.getZ() >> 2,
				base);
			chunk.markDirty();
			List<BiomeBase> list1 = getBiomeTypesInChunk(chunk.getBiomeIndex());
		}
	}
	
	private List<BiomeBase> getBiomeTypesInChunk(BiomeStorage chunksBiomeIndex) {
		List<BiomeBase> biomes = new ArrayList<>();
		for (BiomeBase biomeBase : chunksBiomeIndex.e)
			biomes.add(biomeBase);
		return biomes;
	}
	
	
}


/*
 * Location:
 * C:\Users\quent.DESKTOP-L0VD1RP\Downloads\ToTheMoon.jar!\ToTheMoon\MoonWorld\
 * Generator.class Java compiler version: 7 (51.0) JD-Core Version: 1.1.3
 */
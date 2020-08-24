package fr.ardidex.hammer.Events;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.HashMap;

public class BreakEvent implements Listener {

    private HashMap<Player, BlockFace> face = new HashMap<>();
    @EventHandler
    public void interact(PlayerInteractEvent e){
        if(e.getBlockFace() != null){
            System.out.println(e.getPlayer().getName());
            System.out.println(e.getBlockFace());
            face.put(e.getPlayer(), e.getBlockFace());
        }

    }
    @EventHandler
    public void blockbreak(BlockBreakEvent e){
        Player player = e.getPlayer(); // je crée la variable player pour que ce sois plus simple et que je n'ai pas a appeler e.getPlayer() a chaque fois qui est assez long
        // je check si la pioche est en diams ensuite je check les enchantements puis le nom
        if(player.getItemInHand().getType() == Material.DIAMOND_PICKAXE
                && (player.getItemInHand().getEnchantments().containsKey(Enchantment.DIG_SPEED) && player.getItemInHand().getEnchantments().get(Enchantment.DIG_SPEED) == 5)
                && (player.getItemInHand().getEnchantments().containsKey(Enchantment.DURABILITY) && player.getItemInHand().getEnchantments().get(Enchantment.DURABILITY) == 3)
                && player.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase("Hammer"))
        {


            BlockFace bface = face.get(e.getPlayer());
            System.out.println(bface);
            if(bface == BlockFace.DOWN || bface == BlockFace.UP){
                Block block;
                for(int xOff = -1; xOff <= 1; ++xOff){
                    for(int zOff = -1; zOff <= 1; ++zOff){
                        block = e.getBlock().getRelative(xOff, 0, zOff);
                        block.breakNaturally();
                    }
                }

            }else if(bface == BlockFace.EAST || bface == BlockFace.WEST){
                Block block;
                for(int yOff = -1; yOff <= 1; ++yOff){
                    for(int zOff = -1; zOff <= 1; ++zOff){
                        block = e.getBlock().getRelative(0, yOff, zOff);
                        block.breakNaturally();
                    }
                }
            }else{
                Block block;
                for(int xOff = -1; xOff <= 1; ++xOff){
                    for(int yOff = -1; yOff <= 1; ++yOff){
                        block = e.getBlock().getRelative(xOff, yOff, 0);
                        block.breakNaturally();
                    }
                }
            }
        }
    }
}

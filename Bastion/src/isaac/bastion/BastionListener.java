package isaac.bastion;


import isaac.bastion.manager.BastionBlockManager;
import isaac.bastion.manager.ConfigManager;

import com.untamedears.citadel.entity.PlayerReinforcement;
import com.untamedears.citadel.events.CreateReinforcementEvent;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDispenseEvent;
import org.bukkit.event.block.BlockPistonExtendEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;


public final class BastionListener
implements Listener
{
	private BastionBlockManager bastionManager;
	private ConfigManager config;

	public BastionListener()
	{
		bastionManager = Bastion.getBastionManager();
		config=Bastion.getConfigManager();
	}
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event) {
		if(event.isCancelled()){
			return;
		}
		bastionManager.handleBlockPlace(event);
	}
	
	/*@EventHandler
	public void sandPlaced(EntityChangeBlockEvent  event){
		if(event.isCancelled()){
			return;
		}
		bastionManager.handleSandLanded(event);
	}*/
	
	@EventHandler
	public void pistionPushed(BlockPistonExtendEvent  event){
		if(event.isCancelled()){
			return;
		}
		bastionManager.handlePistonPush(event);
	}
	@EventHandler
	public void bucketPlaced(PlayerBucketEmptyEvent  event){
		if(event.isCancelled()){
			return;
		}
		bastionManager.handleBucketPlace(event);

	}
	@EventHandler
	public void dispensed(BlockDispenseEvent  event){
		if(event.isCancelled()){
			return;
		}
		
		bastionManager.handleDispensed(event);
	}
	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		if(event.isCancelled()){
			return;
		}
		bastionManager.handleBlockBreakEvent(event);
	}
	@EventHandler
	public void onReinforcement(CreateReinforcementEvent event) {

		if (event.getBlock().getType() == config.getBastionBlockMaterial()) {
			bastionManager.addBastion(event.getBlock().getLocation(),(PlayerReinforcement) event.getReinforcement());
		}
	}
	public BastionBlockManager getBastionManager(){
		return bastionManager;

	}
}
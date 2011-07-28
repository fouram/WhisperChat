package com.gmail.deejay4am.whisperchat;

import java.util.List;

import com.herocraftonline.dthielke.herochat.HeroChat;
import com.herocraftonline.dthielke.herochat.channels.Channel;
import com.herocraftonline.dthielke.herochat.channels.ChannelManager;

public class HeroChatManager {
	
	private WhisperChat plugin;
	private HeroChat hcPlugin;
	private ChannelManager hcChanMan;
	private List<Channel> hcChannels;
	
	public HeroChatManager(WhisperChat plugin) {
		
		this.plugin = plugin;
		
		// Find HeroChat
		hcPlugin = (HeroChat) plugin.getServer().getPluginManager().getPlugin("HeroChat");
		if (hcPlugin == null) {
			plugin.getServer().getPluginManager().disablePlugin(plugin);
			plugin.logSevere("This plugin requires HeroChat 4.10.3 which was not found. Disabled.");
			return;
		}
		
		// Find the ChannelManager
		hcChanMan = hcPlugin.getChannelManager();
		if (hcPlugin == null) {
			plugin.getServer().getPluginManager().disablePlugin(plugin);
			plugin.logSevere("Couldn't hook into HeroChat's channel management. Disabled.");
			return;
		}

	}
	
	public void link() {
		//TODO: Iterate through config "channels." branch; when matching channel name
	}
	
}

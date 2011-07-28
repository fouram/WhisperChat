/*
 * (c) 2011 4am
 */

package com.gmail.deejay4am.whisperchat;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.event.Event;
import org.bukkit.event.CustomEventListener;
import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event.Type;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.gmail.deejay4am.whisperchat.WhisperChatCustomListener;
import com.gmail.deejay4am.whisperchat.configuration.WhisperChatConfigurationManager;
import com.herocraftonline.dthielke.herochat.HeroChat;
import com.herocraftonline.dthielke.herochat.HeroChatPlayerListener;
//import com.nijikokun.bukkit.Permissions.Permissions;

public class WhisperChat extends JavaPlugin {
	
	private static Logger log = Logger.getLogger("Minecraft");
	private static PluginDescriptionFile descFile;
	private WhisperChatCustomListener customListener;
	private WhisperChatConfigurationManager cm;
	private HeroChatManager hm;
	private boolean eventsRegistered = false;
	
	public void onEnable() {
		
		PluginManager pm = getServer().getPluginManager();
		
		descFile = this.getDescription();
		
		// Load Configuration
		cm = new WhisperChatConfigurationManager(this);
		if (cm == null) {
			log(Level.SEVERE,"Error initializing configuration management.");
			pm.disablePlugin(this);
			return;
		}
		try {
			cm.load();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logSevere("ERROR LOADING config.yml! -----------------------------");
			e.printStackTrace();
			logSevere("--- ^ PLEASE COPY EVERYTHING BETWEEN THE LINES ^ ------");
			pm.disablePlugin(this);
			return;
		}
		
		//TODO: Hook into Permissions
		
		// Load HeroChat
		
		
		if (!eventsRegistered) {
			
			customListener = new WhisperChatCustomListener(this);
			pm.registerEvent(Event.Type.CUSTOM_EVENT, customListener, Priority.Normal, this);
			
			eventsRegistered = true;
		}
		
		logInfo("Version " + descFile.getVersion() + " has loaded successfuly.");
	}
	
	public void onDisable() {
		log(Level.INFO,"Plugin has been disabled!");
	}
	
	public void log(Level level, String msg) {
		log.log(level,"[" + descFile.getName() + "] " + msg);
	}

    public void logSevere(String msg) {
        log(Level.SEVERE,msg);
    }
    public void logWarn(String msg) {
        log(Level.WARNING,msg);
    }
    public void logInfo(String msg) {
        log(Level.INFO,msg);
    }
}

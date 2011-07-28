package com.gmail.deejay4am.whisperchat.configuration;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;

import org.bukkit.util.config.Configuration;

import com.gmail.deejay4am.whisperchat.WhisperChat;

public class WhisperChatConfigurationManager {
	
	protected WhisperChat plugin;
	protected File configFile;
	private Configuration theConfig;
	
	public WhisperChatConfigurationManager(WhisperChat plugin) {
		this.plugin = plugin;
		this.configFile = new File(plugin.getDataFolder(),"config.yml");
	}
	
	public void load() throws Exception {
		
		if (!configFile.exists()) {
			try {
				configFile.getParentFile().mkdir();
				configFile.createNewFile();
				OutputStream output = new FileOutputStream(configFile,false);
				InputStream input = WhisperChatConfigurationManager.class.getResourceAsStream("config.yml");
				byte[] buffer = new byte[8192];
				while (true)
				{
					int length = input.read(buffer);
					if (length < 0) break;
					output.write(buffer,0,length);
				}
				input.close();
				output.close();
				plugin.log(Level.WARNING,"config.yml not found; installed default config.yml file.");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		theConfig = plugin.getConfiguration();
		
		if (theConfig == null) {
			//TODO This should throw exception of some kind
			plugin.log(Level.SEVERE,"Config returned null");
			return;
		}
		
		plugin.log(Level.INFO,theConfig.getString("balls"));
		
	}
	
	public void save() {
		theConfig.save();
	}
	
	public void set(String node, Object val) {
		theConfig.setProperty(node, val);
	}
	
	public Object get(String node) {
		return theConfig.getProperty(node);
	}
	
}

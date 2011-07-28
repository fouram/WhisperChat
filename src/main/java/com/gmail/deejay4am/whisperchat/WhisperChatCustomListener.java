package com.gmail.deejay4am.whisperchat;

import java.util.logging.Level;

import org.bukkit.event.Event;
import org.bukkit.event.CustomEventListener;

import com.herocraftonline.dthielke.herochat.event.*;
import com.herocraftonline.dthielke.herochat.event.ChannelChatEvent;

public class WhisperChatCustomListener extends CustomEventListener {

	private WhisperChat plugin;
	
	public WhisperChatCustomListener(WhisperChat whisperChat) {
		super();
		this.plugin = whisperChat;
	}
	
	@Override
	public void onCustomEvent(Event event) {
		ChannelChatEvent e;
		
		plugin.log(Level.INFO, "Caught a custom event: " + event.getClass().getSimpleName());
		if (!(event instanceof ChannelChatEvent)) return;
		e = (ChannelChatEvent) event;
		if (e.isCancelled()) return;
		
		plugin.log(Level.INFO, "HeroChat event: " + e.getChannel().getName() );
	}
	
}

package Events;


import Main.Main;
import java.util.*;
import java.io.*;
import java.awt.*;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Uptime extends ListenerAdapter {

	@Override
	public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
		String messageSent = event.getMessage().getContentRaw();

		if (messageSent.equals(Main.PREFIX + "uptime")) {
			Main.log("-> Command \">uptime\" executed by " + event.getAuthor().getName());
			
			long uptime = System.currentTimeMillis() - Main.LAUNCH_TIME, hrs, mins, secs;
			
			hrs = uptime / 3600000;
			uptime -= hrs * 3600000;
			mins = uptime / 60000;
			uptime -= mins * 60000;
			secs = uptime / 1000;
			
			// Build embed
			EmbedBuilder eb = new EmbedBuilder();
			
			// Set embed color
			eb.setColor(new Color((float) Math.random(), (float) Math.random(), (float) Math.random()));
			
			// Set embed title
			eb.setAuthor("Bot Uptime", null);
			
			// Set embed description
			eb.setDescription(String.format("%d hours %d minutes %d seconds", hrs, mins, secs));
			
			// Create embed
			MessageEmbed embed = eb.build();
			
			event.getChannel().sendMessage(embed).queue();
		}
	}

}
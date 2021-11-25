package Events;

import Main.Main;
import java.util.*;
import java.io.*;
import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Version extends ListenerAdapter {

	@Override
	public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
		String messageSent = event.getMessage().getContentRaw();

		if (messageSent.equals(Main.PREFIX + "version")) {
			Main.log("-> Command \">version\" executed by " + event.getAuthor().getName());

			// Build embed
			EmbedBuilder eb = new EmbedBuilder();

			// Set embed color
			eb.setColor(new Color((float) Math.random(), (float) Math.random(), (float) Math.random()));

			// Set embed title
			eb.setAuthor("Bot Version", null);

			// Set embed description
			eb.setDescription(Main.class.getPackage().getImplementationVersion());

			// Create embed
			MessageEmbed embed = eb.build();

			event.getChannel().sendMessage(embed).queue();
		}
	}

}

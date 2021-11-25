package Events;

import Main.Main;
import java.awt.Color;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Host extends ListenerAdapter {

	@Override
	public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
		String messageSent = event.getMessage().getContentRaw();

		if (messageSent.equals(Main.PREFIX + "host")) {
			Main.log("-> Command \">host\" executed by " + event.getAuthor().getName());
			
			// Build embed
			EmbedBuilder eb = new EmbedBuilder();
			
			// Set embed color
			eb.setColor(new Color((float) Math.random(), (float) Math.random(), (float) Math.random()));
			
			// Set description
			eb.setDescription("Current bot host: " + ((System.getProperty("user.name").equalsIgnoreCase("Ibraheem")) ? "Ibraheem" : "Raspberry Pi 4"));			
			
			// Create embed
			MessageEmbed embed = eb.build();
			
			event.getChannel().sendMessage(embed).queue();
		}
	}

}

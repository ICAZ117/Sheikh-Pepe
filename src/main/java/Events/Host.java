package Events;

import Main.Main;
import java.awt.Color;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Host extends ListenerAdapter {

	@Override
	public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
		// Check the command type
		if (event.getName().equals("host") && !event.getUser().isBot()) {
			// Send waiting message
			event.deferReply().queue();

			Main.log("-> Command \">host\" executed by " + event.getUser().getName());
			
			// Build embed
			EmbedBuilder eb = new EmbedBuilder();
			
			// Set embed color
			eb.setColor(new Color((float) Math.random(), (float) Math.random(), (float) Math.random()));
			
			// Set description
			eb.setDescription("Current bot host: " + ((System.getProperty("user.name").equalsIgnoreCase("Ibraheem")) ? "Ibraheem" : "Raspberry Pi 4"));			
			
			// Create embed
			MessageEmbed embed = eb.build();
			
			event.getHook().sendMessageEmbeds(embed).queue();
		}
	}

}

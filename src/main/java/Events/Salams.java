package Events;

import java.awt.Color;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Salams extends ListenerAdapter {

	@Override
	public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
		String messageSent = event.getMessage().getContentRaw();

		if (messageSent.contains("سلام")
				|| messageSent.contains("salam")
				|| messageSent.contains("sallam")
				|| messageSent.contains("Salam")
				|| messageSent.contains("Sallam")) {
			// Build embed
			EmbedBuilder eb = new EmbedBuilder();

			// Set embed color
			eb.setColor(new Color((float) Math.random(), (float) Math.random(), (float) Math.random()));

			// Set embed description
			eb.setDescription("Wa'alaykum Assalam Warahmatullahi Wabarakatuh");

			// Create embed
			MessageEmbed embed = eb.build();

			event.getChannel().sendMessage(embed).queue();
		}
	}
}

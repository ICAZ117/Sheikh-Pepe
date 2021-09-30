package Events;

import Main.Main;
import java.awt.Color;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Ping extends ListenerAdapter {

	@Override
	public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
		String messageSent = event.getMessage().getContentRaw();

		if (messageSent.equals(Main.PREFIX + "ping")) {
			Main.log("-> Command \">ping\" executed by " + event.getAuthor().getName());

			// Build embed
			EmbedBuilder eb = new EmbedBuilder();

			// Set embed color
			eb.setColor(new Color((float) Math.random(), (float) Math.random(), (float) Math.random()));

			event.getJDA().getRestPing().queue((time)
					-> event.getChannel().sendMessage(eb.setDescription("Ping: " + time + "ms").build()).queue()
			);

			// Create embed
			MessageEmbed embed = eb.build();

			event.getChannel().sendMessage(embed).queue();
		}
	}
}

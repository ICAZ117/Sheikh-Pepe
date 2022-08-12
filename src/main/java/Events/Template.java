package Events;

import Main.Main;
import java.awt.Color;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Template extends ListenerAdapter {

	@Override
	public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
		// Check the command type
		if (event.getName().equals("COMMAND") && !event.getUser().isBot()) {
			// Send waiting message
			event.deferReply().queue();

			Main.log("-> Command \">COMMAND\" executed by " + event.getUser().getName());
			
			// Build embed
			EmbedBuilder eb = new EmbedBuilder();
			
			// Set embed color
			eb.setColor(new Color((float) Math.random(), (float) Math.random(), (float) Math.random()));
			
			// Set embed title
			eb.setAuthor("TITLE", null/*event.getJDA().getSelfUser().getAvatarUrl()*/);
			
			// Set embed thumbnail
			//eb.setThumbnail(event.getJDA().getSelfUser().getAvatarUrl()/*event.getGuild().getIconUrl()*/);
			
			// Create fields in embed
			eb.addField("HEADING", "CONTENTS", true);
			
			// Create embed
			MessageEmbed embed = eb.build();
			
			event.getHook().sendMessageEmbeds(embed).queue();
		}
	}

}

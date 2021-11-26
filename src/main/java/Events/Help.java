package Events;

import Main.Main;
import java.awt.Color;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Help extends ListenerAdapter {

	@Override
	public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
		String messageSent = event.getMessage().getContentRaw();

		if (messageSent.equals(Main.PREFIX + "help")) {
			Main.log("-> Command \">help\" executed by " + event.getAuthor().getName());
			
			// Build embed
			EmbedBuilder eb = new EmbedBuilder();
			
			// Set embed color
			eb.setColor(new Color((float) Math.random(), (float) Math.random(), (float) Math.random()));
			
			// Set embed title
			eb.setAuthor("Commands", null/*event.getJDA().getSelfUser().getAvatarUrl()*/);
			
			// Set embed thumbnail
			//eb.setThumbnail(event.getJDA().getSelfUser().getAvatarUrl()/*event.getGuild().getIconUrl()*/);
			
			// Create fields in embed
			eb.addField("help", "Shows a list of all of the commands", true);
			eb.addField("verify", "Registers you as an official member of Muslim Gang!", true);
			eb.addField("arabic", "Shows a list of common arabic phrases for easy copy-pasting", true);
			eb.addField("joke", "Gives you an extremely cringey (most of the time) joke!", true);
			eb.addField("ping", "Returns a RestAction ping between the bot and the Discord servers", true);
			eb.addField("host", "Displayes where the bot is currently being run from", true);
			eb.addField("version", "Displays the current version of the bot", true);
			eb.addField("uptime", "Displays how long the bot has been running for", true);
			eb.addField("systeminfo", "Displays general information about the system", true);
			
			// Set footer
			eb.setFooter("Prefix: >");
			
			// Create embed
			MessageEmbed embed = eb.build();
			
			event.getChannel().sendMessage(embed).queue();
		}
	}

}

package Events;

import Main.Main;
import java.awt.Color;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Arabic extends ListenerAdapter {

	@Override
	public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
		String messageSent = event.getMessage().getContentRaw();

		if (messageSent.equals(Main.PREFIX + "arabic")) {
			Main.log("-> Command \">arabic\" executed by " + event.getAuthor().getName());
			
			// Build embed
			EmbedBuilder eb = new EmbedBuilder();
			
			// Set embed color
			eb.setColor(new Color((float) Math.random(), (float) Math.random(), (float) Math.random()));
			
			// Set embed title
			eb.setAuthor("Common Arabic Phrases", null/*event.getJDA().getSelfUser().getAvatarUrl()*/);
			
			// Set embed thumbnail
			//eb.setThumbnail(event.getJDA().getSelfUser().getAvatarUrl()/*event.getGuild().getIconUrl()*/);
			
			// Create fields in embed
			eb.addField("Assalamu'alaykum Warahmatullahi Wabarakatuh", "السلام عليكم ورحمة الله وبركاته", true);
			eb.addField("Wa'alaykum Assalam Warahmatullahi Wabarakatuh", "وعليكم السلام ورحمة الله وبركاته", true);
			eb.addField("'azza wajal", "عز وجل", true);
			eb.addField("JazakAllah Khair", "جزاك الله خير", true);
			eb.addField("Wa Iyyaak", "وإيك", true);
			eb.addField("BaarakAllahu feek", "بارك الله فيك", true);
			eb.addField("Allahumma Baarik", "اللهم بارك", true);
			eb.addField("Bi’ithnillaahi ta’aalah", "بإذن الله تعلى", true);
			eb.addField("Insha'Allah", "إنشاء الله", true);
			eb.addField("SallAllahu 'Alayhi Wasallam", "صلى الله عليه وسلم", true);
			
			// Create embed
			MessageEmbed embed = eb.build();
			
			event.getChannel().sendMessage(embed).queue();
		}
	}

}

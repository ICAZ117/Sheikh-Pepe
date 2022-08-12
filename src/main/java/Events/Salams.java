package Events;

import java.awt.Color;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Salams extends ListenerAdapter {

	@Override
	public void onMessageReceived(MessageReceivedEvent event) {
		String messageSent = event.getMessage().getContentRaw();

		if ((
				messageSent.contains("سلام")
						|| messageSent.contains("Assalam")
						|| messageSent.contains("assalam")
						|| messageSent.contains("Assallam")
						|| messageSent.contains("assallam")
						|| messageSent.contains("Assalaam")
						|| messageSent.contains("assalaam")
						|| messageSent.contains("Assalaam")
						|| messageSent.contains("assalaam")
						|| messageSent.contains("Asalam")
						|| messageSent.contains("asalam")
						|| messageSent.contains("Asallam")
						|| messageSent.contains("asallam")
						|| messageSent.contains("Asalaam")
						|| messageSent.contains("asalaam")
						|| messageSent.contains("Asalaam")
						|| messageSent.contains("asalaam")
						|| messageSent.contains("As salam")
						|| messageSent.contains("as salam")
						|| messageSent.contains("As sallam")
						|| messageSent.contains("as sallam")
						|| messageSent.contains("As salaam")
						|| messageSent.contains("as salaam")
						|| messageSent.contains("As salaam")
						|| messageSent.contains("as salaam")
						|| messageSent.contains("As Salam")
						|| messageSent.contains("as Salam")
						|| messageSent.contains("As Sallam")
						|| messageSent.contains("as Sallam")
						|| messageSent.contains("As Salaam")
						|| messageSent.contains("as Salaam")
						|| messageSent.contains("As Salaam")
						|| messageSent.contains("as Salaam")
						|| messageSent.contains("As-salam")
						|| messageSent.contains("as-salam")
						|| messageSent.contains("As-sallam")
						|| messageSent.contains("as-sallam")
						|| messageSent.contains("As-salaam")
						|| messageSent.contains("as-salaam")
						|| messageSent.contains("As-salaam")
						|| messageSent.contains("as-salaam")
						|| messageSent.contains("As-Salam")
						|| messageSent.contains("as-Salam")
						|| messageSent.contains("As-Sallam")
						|| messageSent.contains("as-Sallam")
						|| messageSent.contains("As-Salaam")
						|| messageSent.contains("as-Salaam")
						|| messageSent.contains("As-Salaam")
						|| messageSent.contains("as-Salaam"))
				&& !(
				messageSent.contains("Wa'al")
						|| messageSent.contains("wa'al")
						|| messageSent.contains("Waal")
						|| messageSent.contains("waal")
						|| messageSent.contains("W'al")
						|| messageSent.contains("w'al")
						|| messageSent.contains("Wal")
						|| messageSent.contains("wal")
						|| messageSent.contains("WAL")
						|| messageSent.contains("Wa al")
						|| messageSent.contains("wa al")
						|| messageSent.contains("Wa3al")
						|| messageSent.contains("wa3al")
						|| messageSent.contains("Wa 3al")
						|| messageSent.contains("wa 3al")
						|| messageSent.contains("وعل")
						|| messageSent.contains("وال")
						|| messageSent.contains("و عل"))
				&& !event.getAuthor().isBot()) {

			// Build embed
			EmbedBuilder eb = new EmbedBuilder();

			// Set embed color
			eb.setColor(new Color((float) Math.random(), (float) Math.random(), (float) Math.random()));

			// Set embed description
			eb.setDescription("Wa'alaykum Assalam Warahmatullahi Wabarakatuh");

			// Create embed
			MessageEmbed embed = eb.build();

			event.getChannel().sendMessageEmbeds(embed).queue();
		}
	}
}

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
import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;

public class Version extends ListenerAdapter {

	@Override
	public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
		String messageSent = event.getMessage().getContentRaw();

		if (messageSent.equals(Main.PREFIX + "version")) {
			Main.log("-> Command \">version\" executed by " + event.getAuthor().getName());

			MavenXpp3Reader reader = new MavenXpp3Reader();

			try {
				Model model = reader.read(new FileReader("pom.xml"));

				// Build embed
				EmbedBuilder eb = new EmbedBuilder();

				// Set embed color
				eb.setColor(new Color((float) Math.random(), (float) Math.random(), (float) Math.random()));

				// Set embed title
				eb.setAuthor("Bot Version", null);

				// Set embed description
				eb.setDescription(model.getVersion());
				
				// Create embed
				MessageEmbed embed = eb.build();

				event.getChannel().sendMessage(embed).queue();
			} catch (FileNotFoundException ex) {
				Main.log("-> Unknown exception in version read");
			} catch (IOException ex) {
				Main.log("-> IOException in version read");
			} catch (XmlPullParserException ex) {
				Main.log("-> XmlPullParserException in version read");
			}
		}
	}

}

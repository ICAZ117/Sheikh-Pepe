package Events;

import Main.Main;
import java.util.*;
import java.io.*;
import java.awt.*;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class SystemInfo extends ListenerAdapter {

	@Override
	public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
		String messageSent = event.getMessage().getContentRaw();

		if (messageSent.equals(Main.PREFIX + "systeminfo")) {
			Main.log("-> Command \">systeminfo\" executed by " + event.getAuthor().getName());

			Runtime rpi = Runtime.getRuntime();
			String[] commands = {"bash", "-c", "cpu=$(</sys/class/thermal/thermal_zone0/temp);echo \"$((cpu/1000))\";vcgencmd measure_temp"};
			String line, temps = "";
			double cpu, gpu;

			try {
				Process launcher = rpi.exec(commands);

				launcher.waitFor();
				BufferedReader stdoutReader = new BufferedReader(new InputStreamReader(launcher.getInputStream()));
				
				while ((line = stdoutReader.readLine()) != null) {
					temps += line;
				}
				
				stdoutReader.close();
			} catch (IOException | InterruptedException e) {
				System.err.println("Failed to execute bash with command: " + "cpu=$(</sys/class/thermal/thermal_zone0/temp);echo \"$((cpu/1000))\";vcgencmd measure_temp");
			}
			
			cpu = Double.parseDouble(temps.split("temp=")[0]);
			line = temps.split("temp=")[1];
			gpu = Double.parseDouble(line.substring(0, line.length() - 2));

			// Build embed
			EmbedBuilder eb = new EmbedBuilder();

			// Set embed color
			eb.setColor(new Color((float) Math.random(), (float) Math.random(), (float) Math.random()));

			// Set embed title
			eb.setAuthor("System Information", null);

			// Create fields in embed
			eb.addField("CPU Temperature", String.format("%d℃", (int) cpu), true);
			eb.addField("GPU Temperature", String.format("%d℃", (int) gpu), true);

			// Create embed
			MessageEmbed embed = eb.build();

			event.getChannel().sendMessage(embed).queue();
		}
	}

}

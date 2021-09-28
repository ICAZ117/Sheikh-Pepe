package Events;

import Main.Main;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class Terminate extends ListenerAdapter {

	@Override
	public void onMessageReceived(MessageReceivedEvent event) {
		String messageSent = event.getMessage().getContentRaw();

		if (messageSent.equals(Main.PREFIX + "terminate") && event.getAuthor().getId().equals("390633990312427520")) {
			Main.log("-> Terminating program execution\n\n");
			try {
				Thread.sleep(500);
			} catch (InterruptedException ex) {
				System.out.println("Failed to sleep");
			}
			System.exit(0);
		}
	}

}

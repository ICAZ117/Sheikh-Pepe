package Events;

import Main.Main;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class Terminate extends ListenerAdapter {

	@Override
	public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
		// Check the command type
		if (event.getName().equals("terminate") && !event.getUser().isBot()) {
			// Send waiting message
			event.deferReply().queue();

			Main.log("-> Command \">terminate\" executed by " + event.getUser().getName());

			if (Main.admins.contains(event.getUser().getIdLong())) {

				Main.log("-> Terminating program execution\n\n");
				event.getHook().sendMessage("Terminating program execution").queue();

				try {
					Thread.sleep(500);
				} catch (InterruptedException ex) {
					Main.out.println("Failed to sleep");
					Main.out.flush();
				}
				Main.out.close();
				System.exit(0);
			}
			else {
				event.getHook().sendMessage("You can't run that command, you aren't a system admin smh. Stop trying to be a troll and go get a life, scrub -_-").queue();
			}
		}
	}

}

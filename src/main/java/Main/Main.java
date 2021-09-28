package Main; 

import Events.Terminate;
import Events.Arabic;
import Events.Salams;
import Events.Verify;
import Events.Help;
import Events.Joke;
import java.util.*;
import javax.security.auth.login.LoginException;
import net.dv8tion.jda.api.*;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.requests.restaction.*;

public class Main {

	public static final char PREFIX = '>';
	public static final long SERVER_ID = 767404002647343115L;
	public static final long LOG_CHANNEL_ID = 887415187429265448L;
	public static Guild SERVER;
	public static TextChannel LOG_CHANNEL;

	public static void main(String[] args) throws LoginException, InterruptedException {
		// Initialize JDA
		JDA jda = JDABuilder.createDefault("Nzk5Mzg5NTk1NDk3NzkxNTAw.YAC3kw.RzHlpCOG96gR-XjOFwF6vCxH8Hs").setActivity(Activity.playing("Ultimate Thumb Wrestling 2021")).build();

		// Await completion of connection to Discord servers
		jda.awaitReady();

		// Get server object
		SERVER = jda.getGuildById(SERVER_ID);

		// If server is null, print an error message and exit program.
		if (SERVER == null) {
			System.out.println("\n-------------- SERVER NOT FOUND -------------\n");
			System.exit(0);
		}
		// Else, print server name
		else {
			System.out.printf("\n------------------ SUCCESS ------------------\n-> Found Server: %s\n-> Searching for dump channel...\n", SERVER.getName());

			// Get system log channel
			LOG_CHANNEL = SERVER.getTextChannelById(LOG_CHANNEL_ID);

			if (LOG_CHANNEL == null) {
				System.out.println("\n----------- LOG CHANNEL NOT FOUND -----------\n");
				System.exit(0);
			}
			else {
				System.out.printf("-> Found log channel: %s\n", LOG_CHANNEL.getName());
				log("-> Connection status: ONLINE");
				log("-> Loading modules...");
				log("-> Initialization complete");
			}
		}

		// Add event listeners
		jda.addEventListener(new Help());
		jda.addEventListener(new Verify());
		jda.addEventListener(new Arabic());
		jda.addEventListener(new Salams());
		jda.addEventListener(new Joke());
		jda.addEventListener(new Terminate());
	}
	
	public static void log(String message) {
		LOG_CHANNEL.sendMessage(String.format("```%s```", message)).queue();
	}
	
	public static void dm(User user, String message) {
		user.openPrivateChannel().flatMap(channel -> channel.sendMessage(message)).queue();
	}
}

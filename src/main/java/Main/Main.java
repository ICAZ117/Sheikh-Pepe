package Main;

import Events.*;
import java.io.*;
import java.util.*;
import javax.security.auth.login.LoginException;
import net.dv8tion.jda.api.*;
import net.dv8tion.jda.api.entities.*;

public class Main {

	public static final char PREFIX = '>';
	public static final long SERVER_ID = 767404002647343115L;
	public static final long LOG_CHANNEL_ID = 887415187429265448L;
	public static final long LAUNCH_TIME = System.currentTimeMillis();
	public static final int ID_LENGTH = 18;
	public static Guild SERVER;
	public static TextChannel LOG_CHANNEL;
	public static PrintWriter out;
	public static HashSet<String> IBIs = new HashSet<>();
	public static HashSet<Long> admins = new HashSet<>();

	public static void main(String[] args) throws LoginException, InterruptedException, FileNotFoundException {
		// Initialize PrintWriter for event logs
		out = new PrintWriter(new File("EventLogs.out"));
		
		// Initialize JDA
		JDA jda = JDABuilder.createDefault(new Scanner(new File("token.txt")).nextLine()).setActivity(Activity.playing("Ultimate Thumb Wrestling 2021")).build();

		// Await completion of connection to Discord servers
		jda.awaitReady();

		// Get server object
		SERVER = jda.getGuildById(SERVER_ID);

		// If server is null, print an error message and exit program.
		if (SERVER == null) {
			out.println("\n-------------- SERVER NOT FOUND -------------\n");
			out.flush();
			Main.out.close();
			System.exit(0);
		}
		// Else, print server name
		else {
			out.printf("\n------------------ SUCCESS ------------------\n-> Found Server: %s\n-> Searching for log channel...\n", SERVER.getName());
			out.flush();
			
			// Get system log channel
			LOG_CHANNEL = SERVER.getTextChannelById(LOG_CHANNEL_ID);

			// Check if log channel is null
			if (LOG_CHANNEL == null) {
				out.println("\n----------- LOG CHANNEL NOT FOUND -----------\n");
				out.flush();
				Main.out.close();
				System.exit(0);
			}
			else {
				out.printf("-> Found log channel: %s\n", LOG_CHANNEL.getName());
				out.flush();
				log("-> Connection status: ONLINE");
				log("-> Loading modules...");
				
//				Scanner in = new Scanner(new File("ibi.in"));
//				
//				while (in.hasNext()) {
//					IBIs.add(in.next());
//				}
				
				admins.add(390633990312427520L);
				admins.add(384665051333656577L);
				
				log("-> Initialization complete");
			}
		}

		// Add event listeners
		jda.addEventListener(new Help());
		jda.addEventListener(new Verify());
		jda.addEventListener(new Arabic());
		jda.addEventListener(new Salams());
		jda.addEventListener(new Joke());
		jda.addEventListener(new Ping());
		jda.addEventListener(new Host());
		jda.addEventListener(new Version());
		jda.addEventListener(new Uptime());
		jda.addEventListener(new SystemInfo());
		jda.addEventListener(new Leave());
		jda.addEventListener(new Terminate());
	}
	
	public static void log(String message) {
		LOG_CHANNEL.sendMessage(String.format("```%s```", message)).queue();
		out.println(message);
		out.flush();
	}
	
	public static void dm(User user, String message) {
		user.openPrivateChannel().flatMap(channel -> channel.sendMessage(message)).queue();
	}
}

package Main;

import Events.*;
import java.io.*;
import java.util.*;
import javax.security.auth.login.LoginException;
import net.dv8tion.jda.api.*;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.requests.GatewayIntent;

public class Main {

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
		JDA jda = JDABuilder.create(new Scanner(new File("token.txt")).nextLine(),
				GatewayIntent.DIRECT_MESSAGES,
				GatewayIntent.DIRECT_MESSAGE_REACTIONS,
				GatewayIntent.DIRECT_MESSAGE_TYPING,
				GatewayIntent.GUILD_BANS,
				GatewayIntent.GUILD_EMOJIS_AND_STICKERS,
				GatewayIntent.GUILD_INVITES,
				GatewayIntent.GUILD_MEMBERS,
				GatewayIntent.GUILD_MESSAGES,
				GatewayIntent.GUILD_MESSAGE_REACTIONS,
				GatewayIntent.GUILD_MESSAGE_TYPING,
				GatewayIntent.GUILD_PRESENCES,
				GatewayIntent.GUILD_VOICE_STATES,
				GatewayIntent.GUILD_WEBHOOKS,
				GatewayIntent.MESSAGE_CONTENT).setActivity(Activity.playing("Ultimate Thumb Wrestling 2021")).build();

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

		jda.updateCommands().addCommands(
				Commands.slash("arabic", "Shows a list of common arabic phrases for easy copy-pasting"),
				Commands.slash("help", "Shows a list of all of the commands"),
				Commands.slash("host", "Displays where the bot is currently being run from"),
				Commands.slash("joke", "Gives you an extremely cringey (most of the time) joke!")
						.addOption(OptionType.STRING, "id", "(Optional) The ID of the joke you want", false),
				Commands.slash("ping", "Returns a RestAction ping between the bot and the Discord servers"),
				Commands.slash("systeminfo", "Displays general information about the system"),
				Commands.slash("terminate", "Terminates the bot's code execution. Can only be run by system admins."),
				Commands.slash("uptime", "Displays how long the bot has been running for"),
				Commands.slash("verify", "Registers you as an official member of Muslim Gang!"),
				Commands.slash("version", "Displays the current version of the bot")
		).queue();

		// Add event listeners
		jda.addEventListener(new Arabic());
		jda.addEventListener(new Help());
		jda.addEventListener(new Host());
		jda.addEventListener(new Joke());
		jda.addEventListener(new Leave());
		jda.addEventListener(new Ping());
		jda.addEventListener(new Salams());
		jda.addEventListener(new SystemInfo());
		jda.addEventListener(new Terminate());
		jda.addEventListener(new Uptime());
		jda.addEventListener(new Verify());
		jda.addEventListener(new Version());
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

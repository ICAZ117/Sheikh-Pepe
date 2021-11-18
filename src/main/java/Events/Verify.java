package Events;

import Main.Main;

import java.awt.Color;
import java.net.*;
import java.io.*;
import java.security.*;
import java.util.*;
import Dependencies.Submission;
import java.time.Duration;

import net.dv8tion.jda.api.*;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;
import net.dv8tion.jda.api.exceptions.ErrorHandler;
import net.dv8tion.jda.api.requests.ErrorResponse;
import net.dv8tion.jda.api.requests.RestAction;
import net.dv8tion.jda.api.requests.restaction.RoleAction;
import net.dv8tion.jda.api.requests.restaction.pagination.ReactionPaginationAction;
import net.dv8tion.jda.api.utils.concurrent.Task;
import net.dv8tion.jda.internal.entities.UserImpl;
import net.dv8tion.jda.internal.handle.InviteCreateHandler;
import net.dv8tion.jda.internal.managers.GuildManagerImpl;
import net.dv8tion.jda.internal.utils.concurrent.task.GatewayTask;

public class Verify extends ListenerAdapter {

	public HashSet<Long> IDs = new HashSet<>();
	private static final Map<Long, Submission> VERIFICATION_MAP = new HashMap<>();
	private final Timer timer = new Timer();
	private boolean canDM;
	private boolean inverted;

	@Override
	public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
		// Get contents of message
		String messageSent = event.getMessage().getContentRaw();

		// Create temporary reference objects for later use
		User user;
		Member member;
		List<Role> roles;

		// Create verification flags
		boolean isVerified, acceptedRules;

		// IF the message recieved is ">verify"
		if (messageSent.equals(Main.PREFIX + "verify")) {
			// Log the event in the log channel
			Main.log("-> Command \">verify\" executed by " + event.getAuthor().getName());

			// Get the user and member objects of the message author
			user = event.getAuthor();
			member = event.getMember();

			// Check if the member object is for whatever reason null. If it is,
			// print an error to the log channel
			if (member == null) {
				Main.log("-> Member is null");
			}
			else {
				// Get all of the roles assigned to the member
				roles = member.getRoles();

				// Initialize verification flags
				isVerified = false;
				acceptedRules = false;

				// Loop over all of the member's roles to check for rule
				// acceptance and verification status
				for (int i = 0; i < roles.size(); i++) {
					// If the member is already verified, tell them so!
					if (roles.get(i).getName().equalsIgnoreCase("Male") || roles.get(i).getName().equalsIgnoreCase("Female")) {
						event.getChannel().sendMessage("Bruh. You're already verified ._.").queue();
						isVerified = true;
					}

					// If the member has accepted the rules, set the
					// corresponding flag to true
					if (roles.get(i).getIdLong() == 767465681917837332L) {
						acceptedRules = true;
					}
				}

				// If the user is not yet verified, verify them!
				if (!isVerified) {
					try {
						// If the user has not yet accepted the rules, print an
						// error message telling them to first accept them, and
						// then try again
						if (!acceptedRules) {
							event.getChannel().sendMessage("Uh-oh, looks like you haven't accepted the rules yet. Please head over to <#767404002672771128> to accept the rules, and then try again.").queue();
						}
						// Else, the user has accepted the rules, initialize the
						// verification process
						else {
							// If the user has already started verfication, and
							// then attempts to restart it, tell them no and 
							// make fun of them. :)
							if (VERIFICATION_MAP.containsKey(event.getAuthor().getIdLong())) {
								event.getChannel().sendMessage("Ayo big brain, you've already initiated your verification. Check your DMs smh.").queue();
								Main.dm(user, "Oy, over here. Can we continue now pls??");
							}
							else {
								if (!canDM(event.getAuthor(), event)) {
									event.getChannel().sendMessage("Unfortunately, your DMs are closed, meaning that I cannot verify you. Please follow the instructions in <#910678638654029835>, and then try again.").queue();
								}
								else {
									VERIFICATION_MAP.put(event.getAuthor().getIdLong(), new Submission());
									initDMVerification(member);

									new Timer().schedule(new TimerTask() {
										@Override
										public void run() {
											if (VERIFICATION_MAP.get(member.getIdLong()).getProgress() == 6) {
												Submission current = VERIFICATION_MAP.get(member.getIdLong());
												Role male = event.getGuild().getRoleById(816758517305573406L);
												Role female = event.getGuild().getRoleById(818182649612337242L);
												Role age13 = event.getGuild().getRoleById(795390523996897321L);
												Role age14 = event.getGuild().getRoleById(795390677345239071L);
												Role age15 = event.getGuild().getRoleById(795390722638872617L);
												Role age16 = event.getGuild().getRoleById(795390767258402836L);
												Role age17 = event.getGuild().getRoleById(795390809159499796L);
												Role age18 = event.getGuild().getRoleById(795390849357185044L);
												Role kayi = event.getGuild().getRoleById(793956269898596404L);
												Role pasha = event.getGuild().getRoleById(793956273418010634L);
												Role dj = event.getGuild().getRoleById(767404002647343124L);
												Role unidentified = event.getGuild().getRoleById(767720948849311754L);

												if (male == null || female == null || age13 == null || age14 == null || age15 == null || age16 == null || age17 == null || age18 == null || kayi == null || pasha == null || dj == null || unidentified == null) {
													Main.out.println("CANNOT FIND A ROLE, CANCELLING VERIFICATION");
													Main.out.flush();
													Main.dm(user, "My apologies, I seem to be encountering some sort of error. Please ping <@&794030971082375178> in <#767720553145958400> for assistance. Jazakallahu Khair!");
													this.cancel();
												}
												else {
													// First give them their gender role
													if (current.getGender().equalsIgnoreCase("male")) {
														event.getGuild().addRoleToMember(member, male).queue();
													}
													else {
														event.getGuild().addRoleToMember(member, female).queue();
													}

													// Now assign their age
													switch (current.getAge()) {
														case 13:
															event.getGuild().addRoleToMember(member, age13).queue();
															break;
														case 14:
															event.getGuild().addRoleToMember(member, age14).queue();
															break;
														case 15:
															event.getGuild().addRoleToMember(member, age15).queue();
															break;
														case 16:
															event.getGuild().addRoleToMember(member, age16).queue();
															break;
														case 17:
															event.getGuild().addRoleToMember(member, age17).queue();
															break;
														case 18:
															event.getGuild().addRoleToMember(member, age18).queue();
															break;
														default:
															if (current.getAge() < 13) {
																// Underage. Kick them
																Main.dm(user, "Unfortunately, Discord ToS mandates that all users must be over the age of 13. Therefore, you have been removed from the server. Once you turn 13, please apply again. Sorry for the inconveniance. Jazakallahu Khair for your understanding and cooperation.");
																Main.log("-> User " + user.getAsMention() + " has been kicked for: Underage (" + current.getAge() + ")");
																member.getGuild().kick(member, "Underage (" + current.getAge() + ")").queue();
															}
															else {
																// Overage. Kick them
																Main.dm(user, "Unfortunately, you have been removed from the Muslim Gang server. We have an age limit of 18, meaning that I cannot grant you verification. My apologies. Fortunately, I can redirect you to Muslim Gang's partnered server, where there is no such age cap. I have attached the invite for said server below. Jazakallahu Khair for your cooperation.\nhttps://discord.gg/C3CqMm93zP");
																Main.log("-> User " + user.getAsMention() + " has been kicked for: Overage (" + current.getAge() + ")");
																member.getGuild().kick(member, "Overage (" + current.getAge() + ")").queue();
															}
															break;
													}

													// Assign Kayi and Pasha roles
													if (current.getSeenErtugrul()) {
														event.getGuild().addRoleToMember(member, kayi).queue();
													}
													if (current.getSeenPayitaht()) {
														event.getGuild().addRoleToMember(member, pasha).queue();
													}

													// Assign DJ role
													event.getGuild().addRoleToMember(member, dj).queue();

													// Remove unidentified role
													event.getGuild().removeRoleFromMember(member, unidentified).queue();

													// Search if name role already exists
													List<Role> names = event.getGuild().getRolesByName(current.getFirstName() + " " + current.getLastName().charAt(0) + ".", true);

													// If it does, give it to the member
													if (names.size() > 0) {
														event.getGuild().addRoleToMember(member, names.get(0)).queue();
													}
													// If it doesn't, create a new name role
													else {
														event.getGuild().createRole().setName(current.getFirstName() + " " + current.getLastName().charAt(0) + ".").queue();

														while (event.getGuild().getRolesByName(current.getFirstName() + " " + current.getLastName().charAt(0) + ".", true).size() <= 0) {
														}

														List<Role> newNameList = event.getGuild().getRolesByName(current.getFirstName() + " " + current.getLastName().charAt(0) + ".", true);

														Main.out.println("New role: " + newNameList.get(0).getName());
														Main.out.flush();

														Role newName = newNameList.get(0);
														event.getGuild().modifyRolePositions().selectPosition(newName).moveTo(event.getGuild().modifyRolePositions().selectPosition(age18).getSelectedPosition()).queue();
														event.getGuild().addRoleToMember(member, newName).queue();
													}
												}

												this.cancel();
											}
										}
									}, 100, 100);
								}
							}
						}
					} catch (Exception e) {
						Main.dm(user, "My apologies, I seem to be encountering some sort of error. Please ping <@&794030971082375178> in <#767720553145958400> for assistance. Jazakallahu Khair!");
					}
				}

			}
		}
	}

	public boolean canDM(User user, GuildMessageReceivedEvent event) {
		canDM = true;
		inverted = false;
		user.openPrivateChannel()
				.flatMap(channel -> channel.sendMessage("Testing"))
				.delay(Duration.ofSeconds(30))
				.flatMap(Message::delete) // delete after 30 seconds
				.queue(null, new ErrorHandler()
						.ignore(ErrorResponse.UNKNOWN_MESSAGE) // if delete fails that's fine
						.handle(ErrorResponse.CANNOT_SEND_TO_USER, // Fallback handling for blocked messages
								(e) -> invert()));

		long time = System.currentTimeMillis();
		
		while (System.currentTimeMillis() <= time + 1000) {			
			
		}
		
//		System.out.printf("AFTER LOOP\ncanDM: %b\ntime: %d\n", canDM, System.currentTimeMillis());

		return canDM;
	}

	public void invert() {
//		System.out.println("BEFORE: " + System.currentTimeMillis());
		canDM = !canDM;
		inverted = true;
//		System.out.println("AFTER: " + System.currentTimeMillis());
	}

	public void initDMVerification(Member member) {
		User user = member.getUser();

		// First Name
		Main.dm(user, "Welcome to Muslim Gang!\nI am the official Muslim Gang bot, and I will be assisting you with registration and verification today insha'Allah\nTo begin, please enter your first name:");

		// Last Name
		new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				if (VERIFICATION_MAP.get(member.getIdLong()).getProgress() == 1) {
					Main.dm(user, "Alright, now please enter your last name:");
					this.cancel();
				}
			}
		}, 100, 100);

		// Age
		new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				if (VERIFICATION_MAP.get(member.getIdLong()).getProgress() == 2) {
					Main.dm(user, "Jazakallahu Khair. How old are you?");
					this.cancel();
				}
			}
		}, 100, 100);

		// Gender
		new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				if (VERIFICATION_MAP.get(member.getIdLong()).getProgress() == 3) {
					Main.dm(user, "Cool. Are you male or female?");
					this.cancel();
				}
			}
		}, 100, 100);

		// Kayi
		new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				if (VERIFICATION_MAP.get(member.getIdLong()).getProgress() == 4) {
					Main.dm(user, "Awesome, have you seen Dirilis Ertugrul and/or Kurulus Osman?");
					this.cancel();
				}
			}
		}, 100, 100);

		// Pasha
		new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				if (VERIFICATION_MAP.get(member.getIdLong()).getProgress() == 5) {
					Main.dm(user, "Perfect, last question, I promise. Have you seen Payitaht Abdulhamid?");
					this.cancel();
				}
			}
		}, 100, 100);

		// Complete
		new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				if (VERIFICATION_MAP.get(member.getIdLong()).getProgress() == 6) {
					Main.dm(user, "Alright, that's it! Jazakallahu Khair for your cooperation! Welcome to Muslim Gang!!");

					Submission current = VERIFICATION_MAP.get(member.getIdLong());
					Main.log(String.format("-> Verification complete for user: %s\n   First Name: %s\n   Last name: %s\n   Age: %d\n   Gender: %s\n   Seen Ertugrul/Osman: %b\n   Seen Payitaht: %b", member.getUser().getName(), current.getFirstName(), current.getLastName(), current.getAge(), current.getGender(), current.getSeenErtugrul(), current.getSeenPayitaht()));

					this.cancel();
				}
			}
		}, 100, 100);

	}

	@Override
	public void onPrivateMessageReceived(final PrivateMessageReceivedEvent event) {
		if (VERIFICATION_MAP.containsKey(event.getAuthor().getIdLong())) {
			String content = event.getMessage().getContentRaw();
			if (content.length() != 0) {

				switch (VERIFICATION_MAP.get(event.getAuthor().getIdLong()).getProgress()) {
					case 0:
						VERIFICATION_MAP.get(event.getAuthor().getIdLong()).setFirstName(content);
						VERIFICATION_MAP.get(event.getAuthor().getIdLong()).incrementProgress();
						break;
					case 1:
						VERIFICATION_MAP.get(event.getAuthor().getIdLong()).setLastName(content);
						VERIFICATION_MAP.get(event.getAuthor().getIdLong()).incrementProgress();
						break;
					case 2:
						int age;
						try {
							age = Integer.parseInt(content);
						} catch (Exception e) {
							Main.dm(event.getAuthor(), "Invalid reponse. Please enter an integer number.");
							break;
						}
						VERIFICATION_MAP.get(event.getAuthor().getIdLong()).setAge(age);
						VERIFICATION_MAP.get(event.getAuthor().getIdLong()).incrementProgress();
						break;
					case 3:
						if (content.equalsIgnoreCase("male")) {
							content = "male";
						}
						else if (content.equalsIgnoreCase("female")) {
							content = "female";
						}
						else {
							Main.dm(event.getAuthor(), "Invalid reponse. Please select either male or female.");
							break;
						}
						VERIFICATION_MAP.get(event.getAuthor().getIdLong()).setGender(content);
						VERIFICATION_MAP.get(event.getAuthor().getIdLong()).incrementProgress();
						break;
					case 4:
						if (!content.equalsIgnoreCase("yes") && !content.equalsIgnoreCase("no")) {
							Main.dm(event.getAuthor(), "Invalid reponse. Please respond with either yes or no.");
							break;
						}
						VERIFICATION_MAP.get(event.getAuthor().getIdLong()).setSeenErtugrul(content.equalsIgnoreCase("yes"));
						VERIFICATION_MAP.get(event.getAuthor().getIdLong()).incrementProgress();
						break;
					case 5:
						if (!content.equalsIgnoreCase("yes") && !content.equalsIgnoreCase("no")) {
							Main.dm(event.getAuthor(), "Invalid reponse. Please respond with either yes or no.");
							break;
						}
						VERIFICATION_MAP.get(event.getAuthor().getIdLong()).setSeenPayitaht(content.equalsIgnoreCase("yes"));
						VERIFICATION_MAP.get(event.getAuthor().getIdLong()).incrementProgress();
						break;
					default:
						Main.dm(event.getAuthor(), "My dude. I'm a bot. Why are you DMing me?? smh <:coolthonk:791626203273756673>");
						Main.dm(event.getAuthor(), "https://tenor.com/view/discord-ping-gif-20120886");
				}
			}
		}
		else {
			try {
				Main.dm(event.getAuthor(), "Smh. My dude. I'm a bot. Why are you DMing me?? <:coolthonk:791626203273756673>");
				Main.dm(event.getAuthor(), "https://tenor.com/view/discord-ping-gif-20120886");
			} catch (Exception e) {
			}
		}
	}
}

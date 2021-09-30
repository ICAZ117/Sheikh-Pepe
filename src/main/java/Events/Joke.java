package Events;

import Main.Main;
import java.awt.Color;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Joke extends ListenerAdapter {

	public String[] jokes = {"Passenger: Excuse me. How high is this plane?\nFlight Attendant: About 30,000 feet.\nPassenger: And how wide is it?",
		"The loudspeaker of the big jet clicked on and the captain’s voice announced in a clear, even tone: “Now there’s no cause for alarm but we felt you should know that for the last three hours we’ve been flying without the benefit of radio, compass, radar, or navigational beam due to the breakdown of certain key components. This means that we are, in the broad sense of the word, lost and not quite sure in which direction we are heading. I’m sure you’ll be glad to know however, that we’re making excellent time!\"",
		"An airliner flew into a violent thunderstorm and was soon swaying and bumping in the sky. One very nervous lady happened to be sitting next to a clergyman and turned to him for comfort. \"Can’t you do something?\" she demanded. \"I’m sorry, ma’am,\" said the reverend gently. \"I’m in sales, not management.\"",
		"A man is now able to go across the United States in eight hours . . . four hours for flying, and the other four to get to the airport.",
		"The airline company was disturbed over a high percentage of accidents and decided to eliminate human errors by building a completely mechanical plane. \"Ladies and gentlemen,\" came a voice over a loudspeaker on the plane’s maiden voyage, \"it may interest you to know that you are now traveling in the world’s first completely automated plane. Now just sit back and relax because nothing can possibly go wrong . . . go wrong . . . go wrong . . . go wrong . . .\"",
		"Officer: Soldier, do you have change for a dollar?\nSoldier: Sure, buddy.\nOfficer: That’s no way to address an officer. Now, let’s try that again. Soldier, do you have change for a dollar?\nSoldier: No, sir!",
		"An Army base staff that was planning war games didn’t want to use live ammunition. Instead they informed the soldiers: \"In place of a rifle, you go, ‘Bang, bang.’ In place of a knife, you go, \'Stab, stab.\' In place of a hand grenade, you go, \'Lob, lob.\'\" The game was in progress when one of the soldiers saw one of the enemy. He said, \"Bang, bang,\" but nothing happened. He ran forward and shouted, \"Stab, stab,\" but nothing  happened. He ran back and went, \"Lob, lob,\" but nothing happened. Finally he walked up to the enemy and said, \"You\'re not playing fair. I went \'Bang, bang\' and \'Stab, stab\' and \'Lob, lob\' and you haven\'t fallen dead yet!\" The enemy responded, \"Rumble, rumble, I\'m a tank.\"",
		"A very new soldier was on sentry duty at the main gate of a military outpost. His orders were clear: No car was to enter unless it had a special sticker on the windshield. A big Army car drove up with a general seated in the back. The sentry said, \"Halt, who goes there?\" The chauffeur, a corporal, said, \"General Wheeler.\" \"I\'m sorry, I can\'t let you through. You\'ve got to have a sticker on the windshield.\" The general said, \"Drive on.\" The sentry said, \"Hold it. You really can\'t come through. I have orders to shoot if you try driving in without a sticker.\" The general repeated, \"I\'m telling you, son, drive on.\" The sentry walked up to the rear window and said, \"General, I\'m new at this. Do I shoot you or the driver?\"",
		"If a man is bald in front, he\'s a thinker. If he\'s bald in the back, he\'s a lover. If he\'s bald in front and back, he thinks he\'s a lover.",
		"\"Papa, are you growing taller all the time?\"\n\"No, my child. Why do you ask?\"\n\"\'Cause the top of your head is poking up through your hair.\"",
		"He has wavy hair—it\'s waving goodbye.",
		"He\'s not bald . . . he just has flesh-colored hair.",
		"He\'s a man of polish . . . mostly around his head.",
		"There\'s one proverb that really depresses him: \"Hair today, gone tomorrow.\"",
		"He has less hair to comb, but more face to wash.",
		"It\'s not that he\'s bald . . . he just has a tall face.",
		"There\'s one thing about baldness . . . it\'s neat.",
		"There\'s a new remedy on the market for baldness. It\'s made of alum and persimmon juice. It doesn\'t grow hair, but it shrinks your head to fit what hair you have.",
		"I couldn\'t stand my boy\'s long hair any longer, so I dragged him with me and ordered, \"Give him a crew cut.\" The barber did just that, and so help me, I found I\'d been bringing up somebody else\'s son!",
		"I\'ve got a 16-year-old son who was 6' 3'' until he got a haircut. Now he is 5' 8''.",
		"The customer settled himself and let the barber put the towel around him. Then he told the barber, \"Before we start, I know the weather\'s awful. I don\'t care who wins the next big fight, and I don\'t bet on the horse races. I know I\'m getting thin on top, but I don\'t mind. Now get on with it.\"",
		"\"Well, sir, if you don\'t mind,\" said the barber, \"I\'ll be able to concentrate better if you don\'t talk so much!\"",
		"A man entered a barber shop and said, \"I am tired of looking like everyone else! I want a change! Part my hair from ear to ear!\" \"Are you sure?\" \"Yes!\" said the man. The barber did as he was told and a satisfied customer left the shop. Three hours passed and the man reentered the shop. \"Put it back the way it was,\" he said. \"What\'s the matter?\" asked the barber. \"Are you tired of being a nonconformist already?\" \"No,\" he replied, \"I\'m tired of people whispering in my nose!\"",
		"Customer (twice nicked by the barber\'s razor): Hey, barber, gimme a glass of water.\nBarber: What\'s wrong, sir? Hair in your mouth?\nCustomer: No, I want to see if my neck leaks",
		"Q: Why did the coffee file a police report?\nA: It got mugged.",
		"Opener: A man says to a werewolf, \"You\'re a werewolf.\"\nPunchline: The werewolf says, \"I\'m awere.\"",
		"My wife is so negative. I remembered the car seat, the stroller, AND the diaper bag.\nYet all she can talk about is how I forgot the baby.",
		"Q: Dad, did you get a haircut?\nA: No, I got them all cut.",
		"Opener: My aunt\'s astrological sign was cancer, funny to consider how she died.\nPunchline: She was killed by a giant crab.",
		"Opener: Did you know that\'s a popular cemetery?\nPunchline: People are just dying to get in there!",
		"If a child refuses to sleep during nap time, are they guilty of resisting a rest?",
		"Q: What\'s faster, hot or cold?\nA: Hot, because you can catch a cold!",
		"Q: What time did the man go to the dentist?\nA: Tooth hurt-y.",
		"Opener: I recently bumped into the guy that sold me an antique globe.\nPunchline: It\'s a small world.",
		"Q: Dad, can you put my shoes on?\nA: No, I don\'t think they\'ll fit me.",
		"Opener: My wife and I have decided not to have kids.\nPunchline: The kids are taking it pretty badly.",
		"If you see a robbery at an Apple Store does that make you an iWitness?",
		"Q: What\'s the difference between a poorly dressed man on a tricycle and a well-dressed man on a bicycle?\nA: Attire!",
		"The Big List of the Funniest Dad Jokes\nQ: Why don\'t eggs tell jokes?\nA: They\'d crack each other up.",
		"I don\'t trust stairs. They\'re always up to something!",
		"Q: Why can\'t a leopard hide?\nA: Because he\'s always spotted",
		"Q: How many apples grow on a tree?\nA: All of them.",
		"It\'s important to keep some candy in your pocket at all times. It could be a lifesaver.",
		"Q: Did you hear about the kidnapping at school?\nA: He woke up.",
		"Opener: I dreamed about drowning in an ocean made out of orange soda last night.\nPunchline: It took me a while to work out it was just a Fanta sea.",
		"Opener: At work, we have a printer we\'ve nicknamed Bob Marley.\nPunchline: It\'s always Jammin\'.",
		"Opener: I went to the store to pick up 8 cans of Sprite.\nPunchline:  But when I got home I realized I\'d only picked 7up.",
		"Opener: My daughter screeched, \"Daaaad, you haven\'t listened to one word I\'ve said, have you!?\"\nPunchline: What a strange way to start a conversation with me…",
		"I couldn\'t get a reservation at the library… They were fully booked.",
		"Q: What do you call someone with no body and no nose?\nA: Nobody knows.",
		"My wife: He\'s always trying to jeopardize our relationship.\nTherapist: And how do you respond to that? \nMe: I\'ll take \"My wife is being a big baby\" for $500, Alex.",
		"Q: What is the least spoken language in the world?\nA: Sign language.",
		"Opener: I bought some shoes from a drug dealer.\nPunchline: I don\'t know what he laced them with, but I was tripping all day!",
		"Q: How did the dog stop the music?\nA: Paws.",
		"The problem with Nearly Headless Nick is that he\'s a poorly executed character.",
		"Opener: I accidentally got rice in my headphone jack.\nPunchline: Now all my music sounds grainy.",
		"Opener: My wife tried to unlatch our daughter\'s car seat with one hand and said, \"How do one-armed mothers do it?\"\nPunchline: Without missing a beat I replied, \"Single handedly.\"",
		"Q: Why don\'t skeletons ever go trick or treating?\nA: Because they have no body to go with.",
		"Q: I just watched a documentary about beavers.\nA: It was the best dam show I ever saw!",
		"Q: I ordered a chicken and an egg from Amazon.\nA: I\'ll let you know.",
		"Q: This graveyard looks overcrowded.\nA: People must be dying to get in there!",
		"Reversing the car ‘Ahh, this takes me back.\'",
		"Q: What did the buffalo say when his son left?\nA: Bison!",
		"Q: What do you call a dinosaur that is sleeping?\nA: A dino-snore!",
		"Don\'t trust atoms. They make up everything!",
		"Q: Did you know that humans eat more bananas than monkeys?\nA: It\'s true. I mean when was the last time you ate a monkey?",
		"Q: What\'s the funniest city in Louisiana?\nA:  Laugh-ayette.",
		"Q: Why did the cookie go to the hospital?\nA: Because he felt crummy.",
		"Well, we were having dinner, and Dad had spilled his peas on the table… He looks right at me and said, \"Oh no, I have just peed on the table.\"",
		"Opener: I\'m reading a book about anti-gravity.\nPunchline: It\'s impossible to put down!",
		"Opener: I thought about going on an all-almond diet….\nPunchline: But that\'s just nuts!",
		"Q: What do you call a Mexican who has lost his car?\nA: Carlos.",
		"Q: Is your refrigerator running?\nA: Because I might vote for it…",
		"Chris Hemsworth is Australian and Thor is from space does that make him an Australien?",
		"Opener: Spring is here!\nPunchline: I got so excited I wet my plants!",
		"Q: Can February March?\nA: No, but April May!",
		"Q: What did the drummer call his twin baby daughters?\nA: Anna1 Anna2",
		"Q: What\'s orange and sounds like a parrot.\nA: A carrot!",
		"Opener: I never wanted to believe that my Dad was stealing from his job as a road worker.\nPunchline: But when I got home, all the signs were there.",
		"Doctor: [handing me my newborn baby] I\'m sorry but your wife didn\'t make it\nMe: [handing baby back to him] bring me the one my wife made.",
		"Q: You\'re American when you go into the bathroom, and you\'re American when you come out, but do you know what you are while you\'re in there?\nA: European.",
		"Q: Did you hear about the cheese factory that exploded in France?\nAnswer: There was nothing left but de Brie.",
		"Q: What\'s brown and sticky?\nAnswer: A stick.",
		"Opener: The secret service isn\'t allowed to yell \"Get down!\" anymore when the president is about to be attacked.\nPunchline: Now they have to yell \"Donald, duck!\"",
		"Dad asks me, \"Have you heard about the new movie constipation?\" I was like, \"What? No.\" And he said, \"It never came out.\"",
		"Opener: I am suspicious that my wife is secretly adding glue to my weapons collection.\nPunchline: She denies it, but I\'m sticking to my guns.",
		"Q: What do you call a hippie\'s wife?\nA: Mississippi.",
		"Is a rivalry between two vegetarians still called a beef?",
		"Opener: I start a new job in Seoul next week.\nPunchline: I hope it is going to be a good Korea move.",
		"Opener: Do I enjoy making courthouse puns?\nPunchline: Guilty.",
		"Opener: I can\'t decide if I want to pursue a career as a writer or a grifter.\nPunchline: I\'m still weighing the prose and cons",
		"I don\'t often tell dad jokes, but when I do, he laughs.",
		"Q: Dad walks into a bookstore and says, \" Can I have a book by Shakespeare?\" \"Of course, sir, which one?\"\nA: William.",
		"Q: What\'s better than Ted Danson?\nA: Ted singing and Danson!"};

	@Override
	public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
		String messageSent = event.getMessage().getContentRaw();

		if (messageSent.startsWith(Main.PREFIX + "joke")) {
			Main.log("-> Command \">joke\" executed by " + event.getAuthor().getName());

			int jokeIndex = (int) ((Math.random() * 100000) % jokes.length);

			if (messageSent.split(" ").length == 2) {
				try {
					jokeIndex = (Integer.parseInt(messageSent.split(" ")[1]) < jokes.length) ? Integer.parseInt(messageSent.split(" ")[1]) : jokeIndex;
				} catch (Exception e) {
				}
			}
			

			// Build embed
			EmbedBuilder eb = new EmbedBuilder();

			// Set embed color
			eb.setColor(new Color((float) Math.random(), (float) Math.random(), (float) Math.random()));

			// Set embed title
			eb.setAuthor("Joke", null/*event.getJDA().getSelfUser().getAvatarUrl()*/);

			// Tell the actual joke
			eb.setDescription(jokes[jokeIndex]);

			// Print joke index in embed footer
			eb.setFooter("Joke index: " + jokeIndex);

			// Create embed
			MessageEmbed embed = eb.build();

			// Queue the action for execution
			event.getChannel().sendMessage(embed).queue();
		}
	}

}

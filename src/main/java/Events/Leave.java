package Events;

import Main.Main;
import java.util.*;
import javax.annotation.Nonnull;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Leave extends ListenerAdapter {

	@Override
	public void onGuildMemberRemove​(@Nonnull GuildMemberRemoveEvent event) {
		Member member = event.getMember();
		User user = event.getUser();
		Guild guild = event.getGuild();

		Main.log("-> User " + user.getAsTag() + " left the server");

		if (member == null) {
			Main.log("-> ERROR: MEMBER OBJECT NOT FOUND!");
			return;
		}
		
		Verify.VERIFICATION_MAP.remove(user.getIdLong());

		List<Role> roles = member.getRoles();
		
		for (Role next : roles) {
			String name = next.getName();
			
			if (name.charAt(name.length() - 1) == '.' && name.charAt(name.length() - 3) == ' ' && next.getGuild().getMembersWithRoles(next).size() <= 1) {
				Main.log("-> Deleted role " + name);
				next.delete().queue();
				break;
			}
		}
	}
}

package pl.bmstefanski.tools.command;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.bmstefanski.tools.command.basic.CommandContext;
import pl.bmstefanski.tools.command.basic.CommandInfo;
import pl.bmstefanski.tools.impl.CommandImpl;
import pl.bmstefanski.tools.impl.configuration.Messages;
import pl.bmstefanski.tools.object.User;
import pl.bmstefanski.tools.object.util.UserUtils;
import pl.bmstefanski.tools.util.MessageUtils;

import java.util.*;

public class ListCommand {

    @CommandInfo(name = "list", description = "list command", usage = "[full/basic]", userOnly = true, completer = "listCompleter", min = 1)
    public void list(CommandSender commandSender, CommandContext context) {
        Player player = (Player) commandSender;
        Collection<? extends Player> playersOnline = Bukkit.getOnlinePlayers();

        int playersOnlineSize = playersOnline.size();
        int maxPlayers = Bukkit.getMaxPlayers();

        List<String> onlinePlayers = new ArrayList<>();

        if (context.getArgs().length == 1) {
            if (context.getParam(0).equalsIgnoreCase("full")) {

                for (User user : UserUtils.getUsers()) {
                    if (!user.isOnline()) return;
                    if (onlinePlayers.contains(user.getName())) return;

                    onlinePlayers.add(user.getName());
                }

                MessageUtils.sendMessage(player, StringUtils.replace(Messages.LIST_FULL, "%online%", onlinePlayers.toString()));
            } else if (context.getParam(0).equalsIgnoreCase("basic")) {
                MessageUtils.sendMessage(player, StringUtils.replaceEach(Messages.LIST_BASIC,
                        new String[] {"%online%", "%max%"},
                        new String[] {playersOnlineSize + "", maxPlayers + ""}));
            }
        }
    }

    public List<String> listCompleter(CommandSender commandSender, CommandContext context) {
        if (context.getArgs().length == 1) {
            final List<String> availableList = Arrays.asList("basic", "full");

            Collections.sort(availableList);
            return availableList;
        }

        return null;
    }
}
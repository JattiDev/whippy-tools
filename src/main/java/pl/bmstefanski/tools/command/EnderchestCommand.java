/*
 MIT License

 Copyright (c) 2018 Whippy Tools

 Permission is hereby granted, free of charge, to any person obtaining a copy
 of this software and associated documentation files (the "Software"), to deal
 in the Software without restriction, including without limitation the rights
 to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 copies of the Software, and to permit persons to whom the Software is
 furnished to do so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in all
 copies or substantial portions of the Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 SOFTWARE.
 */

package pl.bmstefanski.tools.command;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import pl.bmstefanski.commands.Arguments;
import pl.bmstefanski.commands.Messageable;
import pl.bmstefanski.commands.annotation.Command;
import pl.bmstefanski.commands.annotation.Completer;
import pl.bmstefanski.commands.annotation.GameOnly;
import pl.bmstefanski.commands.annotation.Permission;
import pl.bmstefanski.tools.Tools;
import pl.bmstefanski.tools.storage.configuration.Messages;
import pl.bmstefanski.tools.util.TabCompleterUtils;

import java.util.List;

public class EnderchestCommand implements Messageable {

    private final Tools plugin;
    private final Messages messages;

    public EnderchestCommand(Tools plugin) {
        this.plugin = plugin;
        this.messages = plugin.getMessages();
    }

    @Command(name = "enderchest", usage = "[player]", aliases = {"ender", "ec"}, max = 1)
    @Permission("tools.command.enderchest")
    @GameOnly
    public void command(Arguments arguments) {

        Player player = (Player) arguments.getSender();

        if (arguments.getArgs().length == 0) {
            player.openInventory(player.getEnderChest());
            return;
        }

        if (player.hasPermission("tools.command.enderchest.other")) {

            if (Bukkit.getPlayer(arguments.getArgs(0)) == null) {
                sendMessage(player, StringUtils.replace(messages.getPlayerNotFound(), "%player%", arguments.getArgs(0)));
                return;
            }

            Player target = Bukkit.getPlayer(arguments.getArgs(0));
            player.openInventory(target.getEnderChest());

        }

    }

    @Completer("enderchest")
    public List<String> completer(Arguments arguments) {
        List<String> availableList = TabCompleterUtils.getAvailableList(arguments);
        if (availableList != null) return availableList;

        return null;
    }
}

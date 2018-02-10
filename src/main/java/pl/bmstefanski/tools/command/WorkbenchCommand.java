package pl.bmstefanski.tools.command;

import org.bukkit.entity.Player;
import pl.bmstefanski.commands.Arguments;
import pl.bmstefanski.commands.Messageable;
import pl.bmstefanski.commands.annotation.Command;
import pl.bmstefanski.commands.annotation.GameOnly;
import pl.bmstefanski.commands.annotation.Permission;
import pl.bmstefanski.tools.Tools;
import pl.bmstefanski.tools.storage.configuration.Messages;

public class WorkbenchCommand implements Messageable {

    private final Tools plugin;
    private final Messages messages;

    public WorkbenchCommand(Tools plugin) {
        this.plugin = plugin;
        this.messages = plugin.getMessages();
    }

    @Command(name = "workbench", aliases = {"crafting", "wb"})
    @Permission("tools.command.workbench")
    @GameOnly
    public void command(Arguments arguments) {

        Player player = arguments.getPlayer();

        player.openWorkbench(player.getLocation(), true);

    }
}

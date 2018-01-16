package pl.bmstefanski.tools.command;

import org.bukkit.command.CommandSender;
import pl.bmstefanski.tools.Tools;
import pl.bmstefanski.tools.command.basic.CommandContext;
import pl.bmstefanski.tools.command.basic.CommandInfo;
import pl.bmstefanski.tools.storage.configuration.Messages;
import pl.bmstefanski.tools.util.MessageUtils;

public class DisableCommand {

    private final Tools plugin;

    public DisableCommand(Tools plugin) {
        this.plugin = plugin;
    }

    @CommandInfo (
            name = "tools-disable",
            description = "disable command",
            permission = "disable"
    )

    public void disable(CommandSender commandSender, CommandContext context) {
        plugin.getServer().getPluginManager().disablePlugin(plugin);
        MessageUtils.sendMessage(commandSender, Messages.SUCCESSFULLY_DISABLED);
    }
}

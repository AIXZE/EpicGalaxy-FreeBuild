package de.aixze.freebuild.manager;

import de.aixze.epicgalaxy.api.SystemAPI;
import de.aixze.freebuild.main.Main;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * JavaDoc this file!
 * Created: 01.11.2022
 *
 * @author AIXZE
 */

public class TPAManager {

    //Player
    private final Player target;

    //List<UUID>
    private final List<UUID> requesterList = new ArrayList<>();

    //HashMap<Player, TPAManager>
    private static final HashMap<Player, TPAManager> managerMap = new HashMap<>();

    public TPAManager(final Player p) {
        this.target = p;

        managerMap.put(p, this);
    }

    public void sendTPARequest(final Player requester) {
        if (!(SystemAPI.isVanished(this.target) || this.target.isOnline())) {
            requester.sendMessage(Main.getInstance().prefix + "§cDieser Spieler ist nicht online.");

            return;
        }

        UUID requesterUUID = requester.getUniqueId();

        if (this.requesterList.contains(requesterUUID)) {
            requester.sendMessage(Main.getInstance().prefix + "§cDu hast diesem Spieler bereits eine TPA-Anfrage gesendet.");

            return;
        }

        // Requester management
        this.requesterList.add(requesterUUID);
        requester.sendMessage(Main.getInstance().prefix + "§7Deine §eTPA-Anfrage §7wurde erfolgreich an §a" + this.target.getDisplayName() + " §7versandt.");

        // Target management
        this.target.sendMessage(Main.getInstance().prefix + requester.getDisplayName() + " §7hat dir eine §eTPA-Anfrage §7gesendet.");

        TextComponent tpaComponent = new TextComponent();
        tpaComponent.setText(Main.getInstance().prefix);

        TextComponent acceptComponent = new TextComponent();
        acceptComponent.setText("§7[§aAnnehmen§7]");
        acceptComponent.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tpa accept " + requester.getName()));
        tpaComponent.addExtra(acceptComponent);

        tpaComponent.addExtra(" ");

        TextComponent declineComponent = new TextComponent();
        declineComponent.setText("§7[§cAblehnen§7]");
        declineComponent.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tpa decline " + requester.getName()));
        tpaComponent.addExtra(declineComponent);

        this.target.spigot().sendMessage(tpaComponent);
        this.target.playSound(this.target.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 1.0F, 2.0F);
    }

    public void accept(final String requesterName) {
        Player requester = Bukkit.getPlayer(requesterName);

        if(requester == null) {
            this.target.sendMessage(Main.getInstance().prefix + "§cDieser Spieler ist nicht online.");

            return;
        }

        if (!(SystemAPI.isVanished(requester) || requester.isOnline())) {
            requester.sendMessage(Main.getInstance().prefix + "§cDieser Spieler ist nicht online.");

            return;
        }

        UUID requesterUUID = requester.getUniqueId();

        if (!this.requesterList.contains(requesterUUID)) {
            this.target.sendMessage(Main.getInstance().prefix + "§cDu hast keine TPA-Anfrage von diesem Spieler erhalten.");

            return;
        }

        // Target management
        this.target.sendMessage(Main.getInstance().prefix + "§7Du hast die §eTPA-Anfrage §7von §a" + requester.getDisplayName() + " §7angenommen.");

        // Requester management
        requester.sendMessage(Main.getInstance().prefix + this.target.getDisplayName() + " §7hat deine §eTPA-Anfrage §7angenommen.");
        requester.playSound(requester.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0F, 2.0F);
        requester.teleport(this.target.getLocation());

        this.requesterList.remove(requesterUUID);
    }

    public void decline(final String requesterName) {
        Player requester = Bukkit.getPlayer(requesterName);

        if (!(SystemAPI.isVanished(requester) || requester == null || requester.isOnline())) {
            requester.sendMessage(Main.getInstance().prefix + "§cDieser Spieler ist nicht online.");

            return;
        }

        UUID requesterUUID = requester.getUniqueId();

        if (!this.requesterList.contains(requesterUUID)) {
            requester.sendMessage(Main.getInstance().prefix + "§cDu hast keine TPA-Anfrage von diesem Spieler erhalten.");

            return;
        }

        // Target management
        this.target.sendMessage(Main.getInstance().prefix + "§7Du hast die §eTPA-Anfrage §7von §a" + requester.getDisplayName() + " §7abgelehnt.");

        // Requester management
        requester.sendMessage(Main.getInstance().prefix + this.target.getDisplayName() + " §7hat deine §eTPA-Anfrage §7abgelehnt.");
        requester.playSound(requester.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1.0F, 0.0F);

        this.requesterList.remove(requesterUUID);
    }

    public static TPAManager getTPAManager(final Player p) {
        if (managerMap.containsKey(p)) {
            return managerMap.get(p);
        }

        return new TPAManager(p);
    }

    public void remove() {
        managerMap.remove(this.target);
    }
}
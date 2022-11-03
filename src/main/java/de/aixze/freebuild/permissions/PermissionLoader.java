package de.aixze.freebuild.permissions;

import de.aixze.epicgalaxy.permissions.PermissionManager;
import de.aixze.epicgalaxy.permissions.PermissionsAPI;

/**
 * JavaDoc this file!
 * Created: 01.11.2022
 *
 * @author AIXZE
 */

public class PermissionLoader {

    public static void loadPermissions() {
        // Administrator
        PermissionsAPI.addPermission(PermissionManager.Groups.ADMINISTRATOR, "lwc.*");

        // Developer
        PermissionsAPI.addPermission(PermissionManager.Groups.DEVELOPER, "lwc.mod");

        // Content
        PermissionsAPI.addPermission(PermissionManager.Groups.CONTENT, "lwc.mod");

        // Senior-Moderator
        PermissionsAPI.addPermission(PermissionManager.Groups.SENIOR_MODERATOR, "lwc.mod");

        // Moderator
        PermissionsAPI.addPermission(PermissionManager.Groups.MODERATOR, "lwc.mod");

        // Supporter
        PermissionsAPI.addPermission(PermissionManager.Groups.SUPPORTER, "lwc.mod");

        // Builder
        PermissionsAPI.addPermission(PermissionManager.Groups.BUILDER, "lwc.mod");

        // VIP
        PermissionsAPI.addPermission(PermissionManager.Groups.VIP, "lwc.protect");

        // Famous
        PermissionsAPI.addPermission(PermissionManager.Groups.FAMOUS, "lwc.protect");

        // Diamond
        PermissionsAPI.addPermission(PermissionManager.Groups.DIAMOND, "lwc.protect");

        // Gold
        PermissionsAPI.addPermission(PermissionManager.Groups.GOLD, "lwc.protect");

        // Spieler
        PermissionsAPI.addPermission(PermissionManager.Groups.SPIELER, "lwc.protect");
    }
}

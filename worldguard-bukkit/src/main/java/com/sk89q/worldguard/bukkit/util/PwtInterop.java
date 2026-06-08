/*
 * WorldGuard, a suite of tools for Minecraft
 * Copyright (C) sk89q <http://www.sk89q.com>
 * Copyright (C) WorldGuard team and contributors
 *
 * This program is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License
 * for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package com.sk89q.worldguard.bukkit.util;

import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class PwtInterop {
    private PwtInterop() {
    }

    public static boolean supportsParallelWorldTicking(JavaPlugin plugin) {
        try {
            Server server = Bukkit.getServer();
            Method method = server.getClass().getMethod("isParallelWorldTickingEnabled");
            boolean enabled = (Boolean) method.invoke(server);
            if (!enabled) {
                plugin.getLogger().info("Parallel World Ticking supported but not enabled");
                return false;
            }

            plugin.getLogger().info("Enabled Parallel World Ticking support!");
            return true;
        } catch (NoSuchMethodException | IllegalAccessException ignored) {
        } catch (InvocationTargetException exception) {
            throw new IllegalStateException(exception);
        }

        return false;
    }

}

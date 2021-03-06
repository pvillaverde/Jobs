package com.gamingmesh.jobs.Signs;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import com.gamingmesh.jobs.container.Job;
import com.gamingmesh.jobs.stuff.Util;

public class jobsSign {

    private String worldName;

    private Integer x;
    private Integer y;
    private Integer z;
    private World world;
    private Location loc;

    private Integer number;
    private String jobName;
    private boolean special = false;
    private SignTopType type;

    public void setSpecial(boolean special) {
	this.special = special;
    }

    public boolean isSpecial() {
	return special;
    }

    public void setJobName(String JobName) {
	this.jobName = JobName;
    }

    public String getJobName() {
	return jobName;
    }

    public void setX(int x) {
	this.x = x;
    }

    public void setY(int y) {
	this.y = y;
    }

    public void setZ(int z) {
	this.z = z;
    }

    public void setWorldName(String World) {
	this.worldName = World;
    }

    public String getWorldName() {
	return worldName;
    }

    public Location getLocation() {
	if (loc != null)
	    return loc;
	if (worldName == null)
	    return null;
	world = Bukkit.getWorld(worldName);
	if (world == null)
	    return null;
	loc = new Location(world, x, y, z);
	return loc;
    }

    public void setNumber(int Number) {
	this.number = Number;
    }

    public int getNumber() {
	return number;
    }

    public String locToBlockString() {
	return worldName + ";" + x + ";" + y + ";" + z;
    }

    public static String locToBlockString(Location loc) {
	if (loc == null)
	    return "";
	return loc.getWorld().getName() + ";" + loc.getBlockX() + ";" + loc.getBlockY() + ";" + loc.getBlockZ();
    }

    public void setLoc(Location loc) {
	if (loc == null)
	    return;
	this.worldName = loc.getWorld().getName();
	this.x = loc.getBlockX();
	this.y = loc.getBlockY();
	this.z = loc.getBlockZ();
	this.world = loc.getWorld();
	this.loc = loc;
    }

    public void setLoc(String string) {
	if (!string.contains(";"))
	    return;

	String[] split = string.replace(",", ".").split(";");

	Integer x = 0;
	Integer y = 0;
	Integer z = 0;

	if (split.length > 0)
	    try {
		x = Integer.parseInt(split[1]);
	    } catch (NumberFormatException e) {
		return;
	    }

	if (split.length > 1)
	    try {
		y = Integer.parseInt(split[2]);
	    } catch (NumberFormatException e) {
		return;
	    }

	if (split.length > 2)
	    try {
		z = Integer.parseInt(split[3]);
	    } catch (NumberFormatException e) {
		return;
	    }

	World world = Util.getWorld(split[0]);
	if (world == null)
	    return;

	this.setLoc(new Location(world, x, y, z));
    }

    public SignTopType getType() {
	return type == null ? SignTopType.toplist : type;
    }

    public void setType(SignTopType type) {
	this.type = type;
    }

    public String getIdentifier() {
	if (this.getType() !=  SignTopType.toplist)
	    return this.getType().toString();
	return this.jobName != null ? this.jobName + ":" + this.getType().toString() : this.getType().toString();
    }

    public static String getIdentifier(Job job, SignTopType type) {
	if (type !=  SignTopType.toplist)
	    return type.toString();
	return job != null ? job.getName() + ":" + type.toString() : type.toString();
    }
}

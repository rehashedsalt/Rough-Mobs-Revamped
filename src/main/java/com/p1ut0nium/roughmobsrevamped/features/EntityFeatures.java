package com.p1ut0nium.roughmobsrevamped.features;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import com.p1ut0nium.roughmobsrevamped.config.RoughConfig;
import com.p1ut0nium.roughmobsrevamped.misc.Constants;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;

public abstract class EntityFeatures {
	
	public static final Random RND = new Random();
	public static final int MAX = Short.MAX_VALUE;
	
	//Default
	public String name;
	protected List<Class<? extends Entity>> entityClasses;
	//Config
	protected boolean featuresEnabled;
	protected List<String> entityNames;
	
	@SuppressWarnings("unchecked")
	public EntityFeatures(String name, Class<? extends Entity>... entityClasses) {
		this.name = name;
		this.entityClasses = Arrays.asList(entityClasses);
	}
	
	public boolean isEntity(Entity creature) {
		ResourceLocation loc = EntityList.getKey(creature);
		return featuresEnabled && loc != null && entityNames.contains(loc.toString());
	}
	
	public void initConfig() {
		
		if (!hasDefaultConfig())
			return;
	
		RoughConfig.getConfig().addCustomCategoryComment(name, "Configuration options which affect " + name + " features");
		
		featuresEnabled = RoughConfig.getBoolean(name, "FeaturesEnabled", true, "Set to false to disable ALL %s features", true);
		entityNames = Arrays.asList(RoughConfig.getStringArray(name, "Entities", Constants.getRegNames(entityClasses).toArray(new String[0]), "Entities which count as %s entities"/*, EntityList.getEntityNameList().toArray(new String[0])*/));
	}
	
	public boolean hasDefaultConfig() {
		return true;
	}
	
	public void addFeatures(EntityJoinWorldEvent event, Entity entity, Boolean bossesEnabled) {
	}
	
	public void addAI(EntityJoinWorldEvent event, Entity entity, EntityAITasks tasks, EntityAITasks targetTasks) {
	}

	public void onAttack(Entity attacker, Entity immediateAttacker, Entity target, LivingAttackEvent event) {
	}

	public void onDefend(Entity target, Entity attacker, Entity immediateAttacker, LivingAttackEvent event) {
	}

	public void onDeath(Entity deadEntity, DamageSource source) {
		// If the mob was killed by a player, update the player's kill count.
		if (source.getTrueSource() instanceof EntityPlayer) {
			//TODO PlayerHelper.setPlayerMobKills();
		}
	}
	
	public void onFall(Entity entity, LivingFallEvent event) {
	}
	
	public void onBlockBreak(EntityPlayer player, BreakEvent event) {
	}
	
	public void preInit() {
	}

	public void postInit() {
	}
}

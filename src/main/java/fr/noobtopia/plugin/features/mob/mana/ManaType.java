package fr.noobtopia.plugin.features.mob.mana;

import org.bukkit.entity.LivingEntity;

import java.util.List;

public enum ManaType {
    WAR("Guerre"),
    FIRE("Feu"),
    EARTH("Terre"),
    WATER("Eau"),
    WIND("Vent"),
    ARTISAN("Artisant"),
    CURSED_WAR("Guerre Maudit"),
    CURSED_FIRE("Feu Maudit"),
    CURSED_EARTH("Terre Maudit"),
    CURSED_WATER("Eau Maudit"),
    CURSED_WIND("Vent Maudit"),
    CURSED_ARTISAN("Artisant Maudit");

    private String display;

    ManaType(String display){ this.display = display; }

    public String getDisplay(){ return display; }

    public static ManaType[] fromEntity(LivingEntity entity){
        return switch (entity.getType()){
            case BEE, PHANTOM, SHULKER, VEX
                    -> array(WIND);
            case BLAZE, CREEPER, GHAST, MAGMA_CUBE
                    -> array(FIRE);
            case CAVE_SPIDER, ENDERMAN, EVOKER, GIANT, ILLUSIONER, RAVAGER, SKELETON, SPIDER, VILLAGER, VINDICATOR
                    -> array(WAR);
            case ELDER_GUARDIAN, GUARDIAN, POLAR_BEAR
                    -> array(WATER);
            case ENDERMITE, SILVERFISH
                    -> array(EARTH);

            case DROWNED, STRAY
                    -> array(WAR, WATER);
            case HOGLIN, HUSK, PIGLIN, PIGLIN_BRUTE, PILLAGER, WITHER_SKELETON
                    -> array(WAR, FIRE);
            case SLIME, WITCH
                    -> array(WAR, EARTH, WATER);
            case ZOMBIE, ZOMBIE_VILLAGER, WOLF
                -> array(WAR, EARTH);
            case ZOGLIN
                -> array(WAR, FIRE, EARTH);
            default -> null;
        };
    }

    private static ManaType[] array(ManaType... types){ return List.of(types).toArray(types); }
}
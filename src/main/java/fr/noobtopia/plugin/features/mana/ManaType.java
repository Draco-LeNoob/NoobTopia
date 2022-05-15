package fr.noobtopia.plugin.features.mana;

import org.bukkit.entity.LivingEntity;

public enum ManaType {
    WAR("§4Guerre"),
    FIRE("§cFeu"),
    EARTH("§2Terre"),
    WATER("§1Eau"),
    WIND("§aVent"),
    ARTISAN("§7Artisant"),
    CURSED_WAR("§4Guerre §8Maudit"),
    CURSED_FIRE("§cFeu §8Maudit"),
    CURSED_EARTH("§2Terre §8Maudit"),
    CURSED_WATER("§1Eau §8Maudit"),
    CURSED_WIND("§aVent §8Maudit"),
    CURSED_ARTISAN("§7Artisant §8Maudit");

    private String display;

    ManaType(String display){ this.display = display; }

    public String getDisplay(){ return display; }

    public static ManaType[] fromEntity(LivingEntity entity){
        return switch (entity.getType()){
            case BEE, PHANTOM, SHULKER, VEX
                    -> array(WIND);
            case BLAZE, CREEPER, GHAST, MAGMA_CUBE
                    -> array(FIRE);
            case CAVE_SPIDER, ENDERMAN, EVOKER, GIANT, ILLUSIONER, RAVAGER, SKELETON, SPIDER, VINDICATOR
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

    private static ManaType[] array(ManaType... types){ return types; }
}
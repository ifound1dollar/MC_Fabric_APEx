package net.dollar.simplegear.world.gen;

public class ModWorldGeneration {
    /**
     * Consolidator function that calls all world generation functions in sequence (ex. ore generation).
     */
    public static void generateModWorldGen() {
        ModOreGeneration.generateOres();
    }
}

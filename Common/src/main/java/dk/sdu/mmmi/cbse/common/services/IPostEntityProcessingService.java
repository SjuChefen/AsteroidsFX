package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

/**
 * This interface provides the methods necessary for a post entity processing service.
 * A post entity processing service is responsible for processing game entities after the main game loop.
 */
public interface IPostEntityProcessingService {
    /**
     * Processes game entities after the main game loop.
     *
     * Pre-condition: gameData and world must not be null.
     * Post-condition: The game entities are updated (entities are added, removed etc.).
     *
     * @param gameData The game data.
     * @param world The game world.
     */
    void process(GameData gameData, World world);
}

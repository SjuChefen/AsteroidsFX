package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
/**
 * This interface provides the methods necessary for an entity processing service.
 * An entity processing service is responsible for processing game entities.
 */
public interface IEntityProcessingService {

    /**
     * Processes game entities.
     *
     * Pre-condition: gameData and world must not be null.
     * Post-condition: The game entities are processed.
     *
     * @param gameData The game data.
     * @param world The game world.
     */
    void process(GameData gameData, World world);
}

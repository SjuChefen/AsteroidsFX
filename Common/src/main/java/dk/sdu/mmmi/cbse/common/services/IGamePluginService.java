package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
/**
 * This interface provides the methods necessary for a game plugin service.
 * A game plugin service is responsible for starting and stopping game plugins.
 */
public interface IGamePluginService {

    /**
     * Starts a game plugin.
     *
     * Pre-condition: gameData and world must not be null.
     * Post-condition: A new game plugin is started and added to the world.
     *
     * @param gameData The game data.
     * @param world The game world.
     */
    void start(GameData gameData, World world);


    /**
     * Stops a game plugin.
     *
     * Pre-condition: gameData and world must not be null.
     * Post-condition: The game plugin is stopped and removed from the world.
     *
     * @param gameData The game data.
     * @param world The game world.
     */
    void stop(GameData gameData, World world);
}

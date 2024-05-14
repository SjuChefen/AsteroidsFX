package dk.sdu.mmmi.cbse.playersystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
/**
 * This class is responsible for managing the player in the game.
 * It implements the IGamePluginService interface.
 */
public class PlayerPlugin implements IGamePluginService {

    private Entity player;

    public PlayerPlugin() {
    }
    /**
     * This method is called when the game starts.
     * It adds the player entity to the world.
     *
     * @param gameData The current state of the game.
     * @param world The current state of the world.
     */
    @Override
    public void start(GameData gameData, World world) {

        // Add entities to the world
        player = createPlayerShip(gameData);
        world.addEntity(player);
    }
    /**
     * This method is responsible for creating a new player entity.
     * It sets the polygon coordinates, x and y coordinates of the player.
     *
     * @param gameData The current state of the game.
     * @return The created player entity.
     */
    private Entity createPlayerShip(GameData gameData) {

        Entity playerShip = new Player();
        playerShip.setPolygonCoordinates(-5,-5,10,0,-5,5);
        playerShip.setX(gameData.getDisplayHeight()/2);
        playerShip.setY(gameData.getDisplayWidth()/2);
        return playerShip;
    }
    /**
     * This method is called when the game stops.
     * It removes the player entity from the world.
     *
     * @param gameData The current state of the game.
     * @param world The current state of the world.
     */
    @Override
    public void stop(GameData gameData, World world) {
        // Remove entities
        world.removeEntity(player);
    }

}

package dk.sdu.mmmi.cbse.asteroidsystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
/**
 * This class is responsible for creating and managing asteroids in the game.
 * It implements the IGamePluginService interface.
 */
public class AsteroidPlugin implements IGamePluginService {
    public AsteroidPlugin() {
    }
    /**
     * This method is responsible for creating a new asteroid entity.
     * It sets the polygon coordinates, radius, x and y coordinates, and rotation of the asteroid.
     *
     * @param size The size of the asteroid.
     * @param xCoord The x coordinate of the asteroid.
     * @param yCoord The y coordinate of the asteroid.
     * @return The created asteroid entity.
     */
    public Entity createAsteroid(int size, double xCoord, double yCoord){
        Entity asteroid = new Asteroid();
        asteroid.setPolygonCoordinates(size, -size, -size, -size, -size, size, size, size);
        asteroid.setRadius(size);
        asteroid.setX(xCoord);
        asteroid.setY(yCoord);
        asteroid.setRotation(180);
        return asteroid;
    }
    /**
     * This method is called when the game starts.
     * It doesn't do anything in the current implementation.
     *
     * @param gameData The current state of the game.
     * @param world The current state of the world.
     */
    @Override
    public void start(GameData gameData, World world) {
    }
    /**
     * This method is called when the game stops.
     * It removes all asteroid entities from the world.
     *
     * @param gameData The current state of the game.
     * @param world The current state of the world.
     */
    @Override
    public void stop(GameData gameData, World world) {
        // Remove entities
        for (Entity e : world.getEntities()) {
            if (e.getClass() == Asteroid.class) {
                world.removeEntity(e);
            }
        }
    }

}

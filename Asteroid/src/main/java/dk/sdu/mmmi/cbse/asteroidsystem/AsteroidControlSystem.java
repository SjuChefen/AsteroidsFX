package dk.sdu.mmmi.cbse.asteroidsystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

/**
 * This class is responsible for controlling the behavior of asteroids in the game.
 * It implements the IEntityProcessingService interface.
 *
 * Operation Contract:
 * - process(GameData, World): This operation is called every frame to update the asteroid's state. It is responsible for handling asteroid movement, updating the asteroid's position and rotation accordingly, and managing asteroid collisions.
 *   Precondition: The gameData and world parameters must not be null.
 *   Postcondition: The asteroid's state is updated based on game rules.
 */
public class AsteroidControlSystem implements IEntityProcessingService {

    AsteroidPlugin plugin = new AsteroidPlugin();

    /**
     * This method is responsible for processing the game data and world state for asteroids.
     * It checks the number of asteroids in the world and creates new ones if needed.
     * It also updates the position and rotation of each asteroid based on its current state.
     * If an asteroid has collided with something, it is removed from the world and two new smaller asteroids are created in its place.
     *
     * @param gameData The current state of the game.
     * @param world The current state of the world.
     */

    @Override
    public void process(GameData gameData, World world) {
        if (world.getEntities(Asteroid.class).size() < 4) {
            if (Math.random() * 100 > 95) {
                world.addEntity(plugin.createAsteroid(20, gameData.getDisplayWidth(), gameData.getDisplayHeight() * Math.random()));
            }
        }
        for (Entity asteroid : world.getEntities(Asteroid.class)) {

            double changeX = Math.cos(Math.toRadians(asteroid.getRotation()));
            double changeY = Math.sin(Math.toRadians(asteroid.getRotation()));
            asteroid.setX(asteroid.getX() + changeX);
            asteroid.setY(asteroid.getY() + changeY);

            if (asteroid.getX() < 0) {
                asteroid.setRotation(Math.random() * 20);
            }

            if (asteroid.getX() > gameData.getDisplayWidth()) {
                asteroid.setRotation(Math.random() * 20 + 180);
            }

            if (asteroid.getY() < 0) {
                asteroid.setRotation(Math.random() * 20 + 90);
            }

            if (asteroid.getY() > gameData.getDisplayHeight()) {
                asteroid.setRotation(Math.random() * 20 + 270);
            }
            if (asteroid.isCollided()) {
                world.removeEntity(asteroid);
                if (asteroid.getRadius() > 10) {
                    Entity asteroid1 = plugin.createAsteroid(asteroid.getRadius() - 5, asteroid.getX(), asteroid.getY() - 50);
                    asteroid1.setRotation(asteroid.getRotation() + 35);

                    Entity asteroid2 = plugin.createAsteroid(asteroid.getRadius() - 5, asteroid.getX(), asteroid.getY() + 50);
                    asteroid2.setRotation(asteroid.getRotation() - 35);

                    world.addEntity(asteroid1);
                    world.addEntity(asteroid2);
                }
            }
        }
    }
}

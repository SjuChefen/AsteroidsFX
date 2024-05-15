package dk.sdu.mmmi.cbse.playersystem;

import dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.GameKeys;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

import java.util.Collection;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;
/**
 * This class is responsible for controlling the behavior of the player in the game.
 * It implements the IEntityProcessingService interface.
 *
 * Operation Contract:
 * - process(GameData, World): This operation is called every frame to update the player's state. It is responsible for handling player input and updating the player's position and rotation accordingly.
 *   Precondition: The gameData and world parameters must not be null.
 *   Postcondition: The player's state is updated based on player input and game rules.
 */
public class PlayerControlSystem implements IEntityProcessingService {
    /**
     * This method is responsible for processing the game data and world state for the player.
     * It updates the position of the player based on the current game keys state.
     * If the player has collided with something, it is removed from the world.
     * If the space key is pressed, it creates a bullet entity.
     *
     * @param gameData The current state of the game.
     * @param world The current state of the world.
     */
    @Override
    public void process(GameData gameData, World world) {

        for (Entity player : world.getEntities(Player.class)) {
            if (gameData.getKeys().isDown(GameKeys.LEFT)) {
                player.setRotation(player.getRotation() - 5);
            }
            if (gameData.getKeys().isDown(GameKeys.RIGHT)) {
                player.setRotation(player.getRotation() + 5);
            }
            if (gameData.getKeys().isDown(GameKeys.UP)) {
                double changeX = Math.cos(Math.toRadians(player.getRotation()))*2;
                double changeY = Math.sin(Math.toRadians(player.getRotation()))*2;
                player.setX(player.getX() + changeX);
                player.setY(player.getY() + changeY);
            }
            if (gameData.getKeys().isPressed(GameKeys.SPACE)) {
                for (BulletSPI bullet : getBulletSPIs()) {
                    world.addEntity(bullet.createBullet(player, gameData,true));
                }
            }

            if (player.getX() < 0) {
                player.setX(2);
            }

            if (player.getX() > gameData.getDisplayWidth()) {
                player.setX(gameData.getDisplayWidth() - 2);
            }

            if (player.getY() < 0) {
                player.setY(2);
            }

            if (player.getY() > gameData.getDisplayHeight()) {
                player.setY(gameData.getDisplayHeight() - 2);
            }
            if (player.isCollided()){
                world.removeEntity(player);
            }

        }
    }
    /**
     * This method retrieves all the BulletSPI services available.
     * It uses the ServiceLoader to load all the BulletSPI implementations.
     *
     * @return A collection of BulletSPI services.
     */
    private Collection<? extends BulletSPI> getBulletSPIs() {
        return ServiceLoader.load(BulletSPI.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }
}

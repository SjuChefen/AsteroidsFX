package dk.sdu.mmmi.cbse.bulletsystem;

import dk.sdu.mmmi.cbse.common.bullet.Bullet;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
/**
 * This class is responsible for managing bullets in the game.
 * It implements the IGamePluginService interface.
 *
 * Operation Contract:
 * - start(GameData, World): This operation is called when the game starts. It is responsible for initializing the bullet entity and adding it to the world.
 *   Precondition: The gameData and world parameters must not be null.
 *   Postcondition: A new bullet entity is added to the world.
 *
 * - stop(GameData, World): This operation is called when the game stops. It is responsible for removing the bullet entity from the world.
 *   Precondition: The gameData and world parameters must not be null.
 *   Postcondition: The bullet entity is removed from the world.
 */
public class BulletPlugin implements IGamePluginService {

    private Entity bullet;
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
     * It removes all bullet entities from the world.
     *
     * @param gameData The current state of the game.
     * @param world The current state of the world.
     */
    @Override
    public void stop(GameData gameData, World world) {
        for (Entity e : world.getEntities()) {
            if (e.getClass() == Bullet.class) {
                world.removeEntity(e);
            }
        }
    }

}

package dk.sdu.mmmi.cbse.enemysystem;

import dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

import java.util.Collection;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;
/**
 * This class is responsible for controlling the behavior of the enemy in the game.
 * It implements the IEntityProcessingService interface.
 *
 * Operation Contract:
 * - process(GameData, World): This operation is called every frame to update the enemy's state. It is responsible for handling enemy movement and updating the enemy's position and rotation accordingly.
 *   Precondition: The gameData and world parameters must not be null.
 *   Postcondition: The enemy's state is updated based on game rules.
 */
public class EnemyControlSystem implements IEntityProcessingService{
    /**
     * This method is responsible for processing the game data and world state for enemies.
     * It updates the position of each enemy based on its current state.
     * If an enemy has collided with something, it is removed from the world.
     * If an enemy decides to shoot, it creates a bullet entity.
     *
     * @param gameData The current state of the game.
     * @param world The current state of the world.
     */
    @Override
    public void process(GameData gameData, World world) {
        for (Entity enemy : world.getEntities(Enemy.class)) {

            double changeX = Math.cos(Math.toRadians(enemy.getRotation())) + 1 * Math.random();
            double changeY = Math.sin(Math.toRadians(enemy.getRotation())) + 1 * Math.random();
            enemy.setX(enemy.getX() + changeX);
            enemy.setY(enemy.getY() + changeY);
            enemy.setRotation(enemy.getRotation() + 1 * Math.random());

            if (100 * Math.random() > 98) {
                for (BulletSPI bullet : getBulletSPIs()) {
                    world.addEntity(bullet.createBullet(enemy, gameData,false));
                }
            }

            if (enemy.getX() < -2) {
                enemy.setX(gameData.getDisplayWidth() + 1);
            }

            if (enemy.getX() > gameData.getDisplayWidth() + 2) {
                enemy.setX(-1);
            }

            if (enemy.getY() < -2) {
                enemy.setY(gameData.getDisplayHeight() + 1);
            }

            if (enemy.getY() > gameData.getDisplayHeight() + 2) {
                enemy.setY(-1);
            }
            if(enemy.isCollided()){
                world.removeEntity(enemy);
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

package dk.sdu.mmmi.cbse.enemysystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
/**
 * This class is responsible for managing enemies in the game.
 * It implements the IGamePluginService interface.
 */
public class EnemyPlugin implements IGamePluginService {

    private Entity enemy;
    public EnemyPlugin() {
    }
    /**
     * This method is called when the game starts.
     * It adds enemy entities to the world.
     *
     * @param gameData The current state of the game.
     * @param world The current state of the world.
     */
    @Override
    public void start(GameData gameData, World world) {

        // Add entities to the world
        enemy = spawnEnemy(gameData);
        world.addEntity(enemy);
    }
    /**
     * This method is responsible for creating a new enemy entity.
     * It sets the polygon coordinates, radius, x and y coordinates of the enemy.
     *
     * @param gameData The current state of the game.
     * @return The created enemy entity.
     */
    private Entity spawnEnemy(GameData gameData) {

        Entity enemy = new Enemy();
        enemy.setPolygonCoordinates(-5,-5,10,0,-5,5);
        enemy.setX(gameData.getDisplayWidth()+1);
        enemy.setY(Math.random()*gameData.getDisplayHeight());
        enemy.setRadius(8);
        return enemy;
    }
    /**
     * This method is called when the game stops.
     * It removes all enemy entities from the world.
     *
     * @param gameData The current state of the game.
     * @param world The current state of the world.
     */
    @Override
    public void stop(GameData gameData, World world) {
        // Remove entities
        for (Entity e : world.getEntities()) {
            if (e.getClass() == Enemy.class) {
                world.removeEntity(e);
            }
        }
    }

}

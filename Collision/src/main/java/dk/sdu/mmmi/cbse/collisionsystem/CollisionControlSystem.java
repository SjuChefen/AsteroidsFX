package dk.sdu.mmmi.cbse.collisionsystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;
/**
 * This class is responsible for controlling the collision behavior in the game.
 * It implements the IPostEntityProcessingService interface.
 */
public class CollisionControlSystem implements IPostEntityProcessingService {
    /**
     * This method is responsible for processing the game data and world state for collisions.
     * It checks for collisions between all pairs of entities in the world.
     * If a collision is detected between two entities, it sets their collided status to true.
     *
     * @param gameData The current state of the game.
     * @param world The current state of the world.
     */
    @Override
    public void process(GameData gameData, World world) {
        for (Entity entity1 : world.getEntities()) {
            for (Entity entity2 : world.getEntities()) {
                if (entity1.getID().equals(entity2.getID())) {
                    continue;
                }

                if (this.collides(entity1, entity2)) {
                    entity1.setCollided(true);
                    entity2.setCollided(true);
                }
            }
        }
    }
    /**
     * This method checks if two entities collide.
     * It calculates the distance between the entities and compares it to the sum of their radii.
     * If the distance is less than the sum of the radii, the entities are colliding.
     *
     * @param entity1 The first entity.
     * @param entity2 The second entity.
     * @return True if the entities are colliding, false otherwise.
     */
    public Boolean collides(Entity entity1, Entity entity2) {
        float dx = (float) entity1.getX() - (float) entity2.getX();
        float dy = (float) entity1.getY() - (float) entity2.getY();
        float distance = (float) Math.sqrt(dx * dx + dy * dy);
        return distance < (entity1.getRadius() + entity2.getRadius());
    }
}

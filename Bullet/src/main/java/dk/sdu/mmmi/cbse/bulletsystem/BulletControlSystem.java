package dk.sdu.mmmi.cbse.bulletsystem;

import dk.sdu.mmmi.cbse.common.bullet.Bullet;
import dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * This class is responsible for controlling the behavior of bullets in the game.
 * It implements the IEntityProcessingService and BulletSPI interfaces.
 * <p>
 * Operation Contract:
 * - process(GameData, World): This operation is called every frame to update the bullet's state. It is responsible for handling bullet movement and removing the bullet if it is out of the game display or has collided with something.
 * Precondition: The gameData and world parameters must not be null.
 * Postcondition: The bullet's state is updated based on game rules.
 * <p>
 * - createBullet(Entity, GameData): This operation is responsible for creating a new bullet entity. It sets the polygon coordinates, radius, x and y coordinates, and rotation of the bullet.
 * Precondition: The shooter and gameData parameters must not be null.
 * Postcondition: A new bullet entity is created and returned.
 */
public class BulletControlSystem implements IEntityProcessingService, BulletSPI {
    /**
     * This method is responsible for processing the game data and world state for bullets.
     * It updates the position of each bullet based on its current state.
     * If a bullet has collided with something or is out of the game display, it is removed from the world.
     *
     * @param gameData The current state of the game.
     * @param world    The current state of the world.
     */
    @Override
    public void process(GameData gameData, World world) {

        for (Entity bullet : world.getEntities(Bullet.class)) {
            double changeX = Math.cos(Math.toRadians(bullet.getRotation())) * 4;
            double changeY = Math.sin(Math.toRadians(bullet.getRotation())) * 4;
            bullet.setX(bullet.getX() + changeX);
            bullet.setY(bullet.getY() + changeY);

            if (bullet.getX() < 0) {
                world.removeEntity(bullet);
            }

            if (bullet.getX() > gameData.getDisplayWidth()) {
                world.removeEntity(bullet);
            }

            if (bullet.getY() < 0) {
                world.removeEntity(bullet);
            }

            if (bullet.getY() > gameData.getDisplayHeight()) {
                world.removeEntity(bullet);
            }
            if (bullet.isCollided()) {
                if ((((Bullet) bullet).getPointGiving())) {
                    addToScore();
                }
                world.removeEntity(bullet);
            }
        }
    }

    /**
     * This method is responsible for creating a new bullet entity.
     * It sets the polygon coordinates, radius, x and y coordinates, and rotation of the bullet.
     *
     * @param shooter  The entity that shoots the bullet.
     * @param gameData The current state of the game.
     * @return The created bullet entity.
     */
    @Override
    public Entity createBullet(Entity shooter, GameData gameData, Boolean pointGiving) {
        Entity bullet = new Bullet();
        bullet.setX(shooter.getX() + (Math.cos(Math.toRadians(shooter.getRotation())) * 10));
        bullet.setY(shooter.getY() + (Math.sin(Math.toRadians(shooter.getRotation())) * 10));
        bullet.setRotation(shooter.getRotation());
        bullet.setPolygonCoordinates(-2, -2, 2, -2, 2, 2, -2, 2);
        bullet.setRadius(1);
        if(pointGiving){
            ((Bullet) bullet).setPointGiving(true);
        }
        else {
            ((Bullet) bullet).setPointGiving(false);
        }
        return bullet;
    }

    private void addToScore(){
        URL url = null;
        try {
            url = new URL("http://localhost:8080/AddToScore");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.getResponseCode();
            connection.disconnect();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

package dk.sdu.mmmi.cbse.bulletsystem;

import dk.sdu.mmmi.cbse.common.bullet.Bullet;
import dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

public class BulletControlSystem implements IEntityProcessingService, BulletSPI {

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
            if(bullet.isCollided()){
                world.removeEntity(bullet);
            }
        }
    }

    @Override
    public Entity createBullet(Entity shooter, GameData gameData) {
        Entity bullet = new Bullet();
        bullet.setX(shooter.getX() + (Math.cos(Math.toRadians(shooter.getRotation())) * 10));
        bullet.setY(shooter.getY() + (Math.sin(Math.toRadians(shooter.getRotation())) * 10));
        bullet.setRotation(shooter.getRotation());
        bullet.setPolygonCoordinates(-2, -2, 2, -2, 2, 2, -2, 2);
        bullet.setRadius(1);
        return bullet;
    }
}

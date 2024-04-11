package dk.sdu.mmmi.cbse.enemysystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

public class EnemyControl implements IEntityProcessingService {


    @Override
    public void process(GameData gameData, World world) {
        for (Entity enemy : world.getEntities(Enemy.class)) {
            double numberRandom;

            numberRandom = Math.random() * 10;

            if (false) {
                enemy.setRotation(enemy.getRotation() - 5);
            }
            if (false) {
                enemy.setRotation(enemy.getRotation() + 5);
            }
            if (false) {
                double changeX = Math.cos(Math.toRadians(enemy.getRotation()));
                double changeY = Math.cos(Math.toRadians(enemy.getRotation()));
                enemy.setX(enemy.getX() + changeX);
                enemy.setX(enemy.getX() + changeY);
            }
            if (enemy.getX() < 2) {
                enemy.setX(gameData.getDisplayWidth() + 1);
            }
            if (enemy.getY() < 2) {
                enemy.setY(gameData.getDisplayHeight() + 1);
            }
            if (enemy.getX() < 2) {
                enemy.setX(gameData.getDisplayWidth() + 1);
            }
            if (enemy.getX() > gameData.getDisplayWidth()+2) {
                enemy.setY(-1);
            }
            if (enemy.getY() > gameData.getDisplayHeight()+2) {
                enemy.setY(-1);
            }
        }
    }
}
package Interface;

import Enum.Direction;

public interface Iplayer {

   void setSpawnPoint(int spawnPoint);
   int getSpawnPoint();
   int getCurrentLocation();
   Direction getDirection();
   void setDirection(Direction direction);
   void setCurrentSpawn(int currentSpawn);
   void startGame();
   String colorReturn();
   void setSpeed(int speed);
   int getPlayerNumber();
   void playerDies();
    int getMovementSpeed();

}

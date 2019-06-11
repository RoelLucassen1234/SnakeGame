package Interface;

import enums.Direction;

public interface Iplayer {

   int getCurrentLocation();
   Direction getDirection();
   void setDirection(Direction direction);
   void setCurrentPoint(int currentSpawn);
   void startGame();
   String colorReturn();
   void setSpeed(int speed);
   int getPlayerNumber();
   void playerDies();
    int getMovementSpeed();

   boolean isPlayerAlive();

}

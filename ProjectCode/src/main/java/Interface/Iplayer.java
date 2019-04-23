package Interface;

import Enum.*;
public interface Iplayer {

   void setSpawnPoint(int spawnPoint);
   int getSpawnPoint();
   int getCurrentLocation();
   Direction getDirection();
   void setCurrentSpawn(int currentSpawn);
}

package core;

/**
 * Bot Class
 */
public class Bot{

  /**
   * Private data set
   */
  private String name     = "";
  private int    location = 0;
  private int    time     = 0;

  /**
   * Bot construct
   * @param  name
   */
  public Bot(String name){
    this.name = name;
    this.reset();
  }

  /**
   * Set Bot Location in hallway. If it's out or range then throw Exception
   * @param  location
   * @return void
   */
  public void setLocation(int location) throws Exception{
    if(location<Config.MIN_LOCATION || location>Config.MAX_LOCATION){
      throw new Exception("Location is out of range ("+location+").");
    }else{
      this.location = location;
    }
  }

  /**
   * return the current location of bot
   * @return int
   */
  public int getLocation(){
    return this.location;
  }

  /**
   * Set a time it took for bot to get to new location.
   * @param  time
   * @return void
   */
  public void setTime(int time) throws Exception{
    if(time<0){
      throw new Exception("Time cannot be less then zero ("+time+").");
    }else{
      this.time = time;
    }
  }

  /**
   * return the latest time
   * @return int
   */
  public int getTime(){
    return this.time;
  }

  /**
   * Reset the time and location data
   * @return void
   */
  public void reset(){
    this.location = Config.MIN_LOCATION;
    this.time     = 0;
  }
}

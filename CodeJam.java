package core;

/**
 * import Java Libraries
 */
import java.util.*;
import java.lang.Math.*;

/**
 * CodeJem class
 */
public class CodeJam{

  /**
   * Private dataset
   */
  private Bot    B    = new Bot("Blue");
  private Bot    O    = new Bot("Orange");
  private int    move = 0;
  private String bot  = "";
  private Gui    gui;

  /**
   * CodeJam construct
   * @param  g  Gui class
   */
  public CodeJam(Gui g){
    this.gui = g;
  }

  /**
   * Start Code Jam
   * @return void
   */
  public void start(){
    // display gui
    this.gui.display();
    // read the file that's passed from terminal
    Scanner input = new Scanner(System.in);
    // get test case number
    int T = input.nextInt();
    // loop through all the test case
    for (int caseNum = 1; caseNum <= T; caseNum++) {
      // number of buttons that need to be pressed by Bot
      int N = input.nextInt();
      // loop through numbers of button Bot will press
      for (int i = 0; i < N; i++) {
        String bot = input.next();    // get Bot name
        int    btn = input.nextInt(); // get button Bot will press
        // If Bot is Orange so calculate time for Orange Bot
        if(bot.equals("O")){
          this.calculate(O, B, btn);
        }
        // else Bot is Blue so calculate time for Blue Bot
        else{
          this.calculate(B, O, btn);
        }
        // Update the value on GUI
        this.gui.changeValue(bot, btn);
        // Put Thread to sleep for 1 seconds or change the sleep time base on speed set on GUI
        try {
          Thread.sleep(1000 / this.gui.getSpeed());
        } catch (InterruptedException e) {
          // We don't care.
        }
      }
      // print case info on GUI and on Terminal
      this.printCase(caseNum, Math.max(B.getTime(), O.getTime()));
      // Reset Both Bot's info
      B.reset();
      O.reset();
    }
  }

  /**
   * Calculate time it take for current Bot.
   * @param int current current bot object
   * @param int other   other bot object
   * @param int button  button current bot need to move
   * @return void
   */
  public void calculate(Bot current, Bot other, int button){
    // time took to press the button
    int moveTime = Math.abs(current.getLocation() - button) + current.getTime();
    // get bigger time from bot
    int time = Math.max(moveTime, other.getTime()) + 1;
    // set the time and location of the Orange Bot
    try{
      current.setTime(time);       // save the time current bot took
      current.setLocation(button); // save the bot new location
    } catch (Exception e){
      System.out.println("Exception thrown  :" + e);
    }
  }

  /**
   * Print the outout or the current case
   * @param int x case number
   * @param int y minimum number of seconds required for the robots to push the given buttons
   * @return void
   */
  public void printCase(int x, int y){
    String output = String.format("Case %d: %d\n", x, y);
    System.out.format(output);    // print the output to terminal
    this.gui.displayCase(output); // print the output to GUI
  }
}

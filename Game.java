import java.util.*;

public class Game {
  private static HashMap<String, Integer> leaderboard;
  private static Room[][] rooms = new Room[5][5];
  private ArrayList<String> descriptions = new ArrayList<String>(Arrays.asList("a kitchen", "a living room", "a dungeon", "a trap", "a treasury", "a restroom", "a library", "a game room", "a laboratory", "a bedroom", "a basement", "a forest", "a wine cellar", "a study", "an attic", "a conservatory", "a billiard room", "a dining room", "a tree house", "a greenhouse", "a daycare", "a pantry", "a ballroom", "a museum", "a dojo"));
  
  private static int x = Utils.randInt(0, rooms[0].length-1);
  private static int y = Utils.randInt(0, rooms.length-1);
  private static int numMoves = 0;
  private static Room currentRoom;
  private static boolean noQuit = true;
  private static boolean gameOn = true;

  public Game() {
    if (leaderboard == null) {
      State state = State.restore();
      if (state == null)
        leaderboard = new HashMap<String, Integer>();
      else
        leaderboard = state.scores;
    }
    while (descriptions.size() > 0) {
      for (int i = 0; i < rooms.length; i++) {
        for (int j = 0; j < rooms[0].length; j++) {
          int index = Utils.randInt(0, descriptions.size()-1);
          rooms[i][j] = new Room(descriptions.remove(index));
        }
      }
    }
    rooms[x][y] = new Room ("the origin");
  }
  public static void play() {
    Scanner scan = new Scanner(System.in);     
    while (noQuit) {
      System.out.print("Do you want to play ('p'), check the leaderboard ('l') or quit ('q')? ");
      gameOn = true;
      String option = scan.nextLine();
      switch (option) {
        case "p":
          Game g = new Game();
          numMoves = 0;
          System.out.println("Welcome to a 5x5 maze. Your objective is to move around the maze to find the treasure and avoid a trap. \nYou will be spawned in a random position, and the treasury and trap will also be in random rooms. \nYour score is the number of moves taken. You want as low of a score as possible. Good luck!\nType 'u', 'd' 'l', 'r' or 'up', 'down', 'left', 'right', to move.");
          while (gameOn) {
          currentRoom = rooms[x][y];
          System.out.print("You are in " + currentRoom.printDescription());
          if (currentRoom.getCondition() != null && currentRoom.getCondition() == false) {
            System.out.println("The trap triggers and a thousand knifes pierce your throat.");
            gameOn = false;
            break;
          }
          if (currentRoom.getCondition() != null && currentRoom.getCondition() == true) {
            System.out.println("You collect all the gold and escape the game!");
            gameOn = false;
            System.out.print("What are your initials? ");
            String name = scan.nextLine();
            while (name.length() != 2) {
              System.out.print("Please input a 2-digit initial! ");
              try {
                name = scan.nextLine();
              }
              catch (Exception e) {
              }
            }
            if (leaderboard.get(name) != null && numMoves < leaderboard.get(name)) {
              System.out.println("New highscore!");
              leaderboard.put(name, numMoves);
              numMoves = 0;
              break;
            }
            leaderboard.put(name, numMoves);
            numMoves = 0;
            break;
          }
          if ((x < rooms[0].length - 1 && rooms[x+1][y].getCondition() != null && rooms[x+1][y].getCondition() == false)  || (x > 0 && rooms[x-1][y].getCondition() != null && rooms[x-1][y].getCondition() == false) || (y < rooms.length - 1 && rooms[x][y+1].getCondition() != null && rooms[x][y+1].getCondition() == false) || (y > 0 && rooms[x][y-1].getCondition() != null && rooms[x][y-1].getCondition() == false)) {
            System.out.println("You hear the snapping of a pressure plate nearby. Be careful!");
          }
          String response = scan.nextLine().toLowerCase().trim();
          switch (response) {
            case "up", "u":
              if (y > 0) {
                numMoves++;
                y--;
              }
              else 
                System.out.println("Can't go up");
              break;
            case "down", "d":
              if (y < rooms.length - 1) {
                numMoves++;
                y++;
              }
              else
                System.out.println("Can't go down");
              break;
            case "right", "r":
              if (x < rooms[0].length - 1) {
                numMoves++;
                x++;
              }
              else
                System.out.println("Can't go right");
              break;
            case "left", "l":
              if (x > 0) {
                numMoves++;
                x--;
              }
              else
                System.out.println("Can't go left");
              break;
            default:
              System.out.println("Please type 'u', 'd' 'l', 'r' or 'up', 'down', 'left', 'right'.");
          }
        }
          break;
        case "l":
          if (leaderboard.size() == 0) {
            System.out.println("No winning scores have been recorded");
            break;
          }
          System.out.println("  Name  |  Score");
          for (String thing : leaderboard.keySet()) {
            Integer score = leaderboard.get(thing);
            System.out.println("    " + thing + "  |  " + score);
          }
          break;
        case "q":
          System.out.println("Goodbye!");
          State s = new State();
          s.scores = leaderboard;
          s.save();
          noQuit = false;
          break;
        case "win":
          gameOn = false;
          numMoves = 0;
          System.out.print("What are your initials? ");
          String name = scan.nextLine();
          s = new State();
          while (name.length() != 2) {
            System.out.print("Please input a 2-digit initial! ");
            try {
              name = scan.nextLine();
            }
            catch (Exception e) {
            }
          }
          if (leaderboard.get(name) != null && numMoves < leaderboard.get(name)) {
            System.out.println("New highscore!");
            leaderboard.put(name, numMoves);
            s.save();
            numMoves = 0;
            break;
          }
          leaderboard.put(name, numMoves);
          s.save();
          numMoves = 0;
          break;
        default:
          System.out.println("Please type 'p', 'l', or 'q'");
          break;
      }
    }
    
  }
  
}

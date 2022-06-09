/*
Room class:
  (local) private String description;
  (instance) private Boolean condition;

Method Info:
Game class:
  Game() put known descriptions randomly into   rooms[][], establish which rooms are win/lose
  static move() update x,y coordinates
  static check() check if currentRoom is win/lose
  static play() move() player and calls check() each move
Room class:
  Room() prints description and checks condition 
  Boolean getCondition() return win/lose condition
  String printDescription() print on entry
*/

public class Room {
  private String description;
  private Boolean condition;

  public Room(String d) {
    description = d;
    if (d.equals("a treasury")) {
      condition = true;
    }
    else if (d.equals("a trap")) {
      condition = false;
    }
  }
  public Boolean getCondition() {
    return condition;
  }
  public String printDescription() {
    return description;
  }  
}
Game class - Text Adventure

In this game, one person is randomly spawned into a 5x5 grid and moves from room to room in a dungeon using commands until arriving upon the winning room or dying in a death room. There are warnings when the death room is one square away in the four cardinal directions, and a message when the user tries to move beyond the borders of the grid.

Serialization: scores by players are stored into a leaderboard hashmap which is saved across multiple playthroughs using serialization in "ScoreState.ser" and even after the user types 'q' to quit the program.

To check serialization, you can quickly win the game with the command 'win' during the "Do you want to play" dialogue. You can then input as many initials as needed with each win, and quit the program with 'q'. When starting the game again and typing 'l', the leaderboard will be saved after quitting.

Author - Isaac Zhang

State Info:
Game class:
  (static) HashMap<String, Integer> leaderboard;
  (static instance) Room[][] rooms;
  (local) ArrayList<String> descriptions; 
  (static) private static int x;
  (static) private static int y;
  (static) Room currentRoom;
  (static) boolean noQuit;
  (static) boolean gameOn;
  (static) numMoves;
Room class:
  (local) private String description;
  (instance) private Boolean condition;

Method Info:
Game class:
  Game() put known descriptions randomly into rooms[][], establish which rooms are        win/lose, sets a random spawn point and sets its description to "the origin"
  static play() move player and checks each location, prints a warning if you are 1       square away in all directions from a trap (while also avoiding index exceptions)
Room class:
  Room() prints description and checks condition 
  String getCondition() return win/lose condition
  String printDescription() print on entry

Change history:
5/12: Created
5/12: Updated State and Method info
5/16: implemented constructor, room boundaries, switch movement, updated variable types
5/17: implemented win condition, hashmap for leaderboard, trap border warning
5/18: implemented highscore checker, switch for play, leaderboard, or quit
5/19: implemented try/catch for player name on leaderboard
5/23: fixed respawn location on new game
6/9: added serialization for leaderboard
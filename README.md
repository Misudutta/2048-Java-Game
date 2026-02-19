The game takes place on an grid, initially with 2 randomly placed blocks (which are randomly selected from either a 2 or 4 with equal chance). The parameter n should be retrieved from command line arguments. If it is invalid or not provided, use the default which is n=4.

Each turn, the player may make one of four moves with the arrow keys: UP, DOWN, LEFT or RIGHT. This shifts all blocks in that given direction, and also merges adjacent blocks with the same number into a higher value (eg, 4 and 4 makes 8). You should ensure that block movement is smoothly transitioning from one cell to the next (as in the example game linked above), OR that only one block moves one space per frame.

After the player’s move is finished, a new random block will spawn in one of the empty spaces. This can be either a 2 or a 4. In addition, the player may also spawn any amount of new blocks into empty cells themselves by clicking on an empty space, and a 2 or 4 will then spawn there (randomly chosen with 50% chance). If the player is unable to make any moves that change the position of blocks, the game is over.

A timer in the top right corner of the screen should keep track of the number of seconds since the game began. When the game ends, this timer stops, and the text “GAME OVER” is displayed in the centre of the screen. The player can press ‘r’ to restart the game. 

package model;

public class Maze {
	
	// Maze attributes
    private int rows;
    private int columns;
    private Tile[][] maze;
    private Tile tileToInsert;
    private int nextTokenToCapture;
    
    // Constructors
    public Maze() {
        maze = new Tile[7][7];
    }

    public Maze(int rows, int columns) {
        maze = new Tile[rows][columns];
    }

    // Getters and Setters
    public Tile getTile(int x, int y) { return maze[x][y]; }
    public void setTile(Tile tile, int x, int y) { maze[x][y] = tile; }
    
    public int getRows() { return rows; }
    public void setRows(int rows) { this.rows = rows; }
    
    public int getColumns() { return columns; }
    public void setColumns(int columns) { this.columns = columns; }

    
    public Tile[][] getMaze() { return maze; }
	public void setMaze(Tile[][] maze) { this.maze = maze; }
	
	public Tile getTileToInsert() { return tileToInsert; }
	public void setTileToInsert(Tile tileToInsert) { this.tileToInsert = tileToInsert; }
	
	public int getNextTokenToCapture() { return nextTokenToCapture; }
	public void setNextTokenToCapture(int nextTokenToCapture) { this.nextTokenToCapture = nextTokenToCapture; }

	
	// Maze Logic
	
	// Generate Maze Method (with numbering for tiles 1-49)
    public Tile[][] generateMaze() {
        int numbering = 1;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                // Assuming Image image is provided
                Tile tile = new Tile(numbering++, i, j, null, new boolean[13]);
                maze[i][j] = tile;
            }
        }
        return maze;
    }

    // Insert Methods
    public Tile insertLeft(Tile tile, int row) {
        tileToInsert = maze[row][columns - 1];  // Capture the rightmost tile before shifting

        // Move player or coin from the sticking out tile if applicable
        if (tileToInsert.hasCharacter() && tileToInsert.hasCoin()) {
            tile.setHasCharacter(tileToInsert.hasCharacter());  			// Move the player to the new tile
            tile.setHasCoin(tileToInsert.hasCoin());  						// Move the coin to the new tile
            tileToInsert.unsetCharacter();  								// Remove player from the sticking out tile
            tileToInsert.unsetCoin();  										// Remove coin from the sticking out tile
        } else if (tileToInsert.hasCharacter()) {
            tile.setHasCharacter(tileToInsert.hasCharacter());  			// Move the player to the new tile
            tileToInsert.unsetCharacter();  								// Remove player from the sticking out tile
        } else if (tileToInsert.hasCoin()) {
            tile.setContainsCoin(tileToInsert.getContainsCoin());
            tile.setHasCoin(tileToInsert.hasCoin());  						// Move the coin to the new tile
            tileToInsert.unsetCoin();  										// Remove coin from the sticking out tile
        }

        // Shift all tiles to the right (from the second-last to the first)
        for (int i = columns - 2; i >= 0; i--) {
            maze[row][i + 1] = maze[row][i];
        }

        maze[row][0] = tile;  										// Insert the new tile at the leftmost position
        return tileToInsert;  										// The tile that "sticks out" is the rightmost one
    }

    public Tile insertUp(Tile tile, int column) {
        tileToInsert = maze[rows - 1][column];  					// Capture the bottommost tile before shifting

        // Move player or coin from the sticking out tile if applicable
        if (tileToInsert.hasCharacter() && tileToInsert.hasCoin()) {
            tile.setHasCharacter(tileToInsert.hasCharacter());  			// Move the player to the new tile
            tile.setHasCoin(tileToInsert.hasCoin());  						// Move the coin to the new tile
            tileToInsert.unsetCharacter();  								// Remove player from the sticking out tile
            tileToInsert.unsetCoin();  										// Remove coin from the sticking out tile
        } else if (tileToInsert.hasCharacter()) {
            tile.setHasCharacter(tileToInsert.hasCharacter());  	// Move the player to the new tile
            tileToInsert.unsetCharacter();  						// Remove player from the sticking out tile
        } else if (tileToInsert.hasCoin()) {
            tile.setContainsCoin(tileToInsert.getContainsCoin());
            tile.setHasCoin(tileToInsert.hasCoin());  				// Move the coin to the new tile
            tileToInsert.unsetCoin();  								// Remove coin from the sticking out tile
        }

        // Shift all tiles down (from the second-last to the first)
        for (int i = rows - 2; i >= 0; i--) {
            maze[i + 1][column] = maze[i][column];
        }

        maze[0][column] = tile;  									// Insert the new tile at the topmost position
        return tileToInsert;  										// The tile that "sticks out" is the bottommost one
    }

    public Tile insertRight(Tile tile, int row) {
        tileToInsert = maze[row][0];  								// Capture the leftmost tile before shifting

        // Move player or coin from the sticking out tile if applicable
        if (tileToInsert.hasCharacter() && tileToInsert.hasCoin()) {
            tile.setHasCharacter(tileToInsert.hasCharacter());  			// Move the player to the new tile
            tile.setHasCoin(tileToInsert.hasCoin());  						// Move the coin to the new tile
            tileToInsert.unsetCharacter();  								// Remove player from the sticking out tile
            tileToInsert.unsetCoin();  										// Remove coin from the sticking out tile
        } else if (tileToInsert.hasCharacter()) {
            tile.setHasCharacter(tileToInsert.hasCharacter());  	// Move the player to the new tile
            tileToInsert.unsetCharacter();  						// Remove player from the sticking out tile
        } else if (tileToInsert.hasCoin()) {
            tile.setContainsCoin(tileToInsert.getContainsCoin());
            tile.setHasCoin(tileToInsert.hasCoin());  				// Move the coin to the new tile
            tileToInsert.unsetCoin();  								// Remove coin from the sticking out tile
        }

        // Shift all tiles to the left (from the second to the last)
        for (int i = 1; i < columns; i++) {
            maze[row][i - 1] = maze[row][i];
        }

        maze[row][columns - 1] = tile;  							// Insert the new tile at the rightmost position
        return tileToInsert;  										// The tile that "sticks out" is the leftmost one
    }

    public Tile insertDown(Tile tile, int column) {
        tileToInsert = maze[0][column];  							// Capture the topmost tile before shifting

        // Move player or coin from the sticking out tile if applicable
        if (tileToInsert.hasCharacter() && tileToInsert.hasCoin()) {
            tile.setHasCharacter(tileToInsert.hasCharacter());  			// Move the player to the new tile
            tile.setHasCoin(tileToInsert.hasCoin());  						// Move the coin to the new tile
            tileToInsert.unsetCharacter();  								// Remove player from the sticking out tile
            tileToInsert.unsetCoin();  										// Remove coin from the sticking out tile
        } else if (tileToInsert.hasCharacter()) {
            tile.setHasCharacter(tileToInsert.hasCharacter());  	// Move the player to the new tile
            tileToInsert.unsetCharacter();  						// Remove player from the sticking out tile
        } else if (tileToInsert.hasCoin()) {
            tile.setContainsCoin(tileToInsert.getContainsCoin());
            tile.setHasCoin(tileToInsert.hasCoin());  				// Move the coin to the new tile
            tileToInsert.unsetCoin();  								// Remove coin from the sticking out tile
        }

        // Shift all tiles up (from the second to the last)
        for (int i = 1; i < rows; i++) {
            maze[i - 1][column] = maze[i][column];
        }

        maze[rows - 1][column] = tile;  							// Insert the new tile at the bottommost position
        return tileToInsert;  										// The tile that "sticks out" is the topmost one
    }
}

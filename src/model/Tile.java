package model;

import java.awt.Image;
import java.util.HashMap;
import java.util.Map;

public class Tile {
    
    // Tile numbering
    private int numbering;
    
    // Tile position on the maze
    private int xPos;
    private int yPos;
    
    // Tile status indicators
    private boolean hasCharacter;  // Has character on the tile
    private boolean hasCoin;       // Has coin on the tile
    private int containsCoin;      // Coin type or number

    // Tile types
    private boolean isUpperLeftCorner;  // Upper Left Corner type
    private boolean isLowerLeftCorner;  // Lower Left Corner type
    private boolean isUpperRightCorner; // Upper Right Corner type
    private boolean isLowerRightCorner; // Lower Right Corner type
    
    private boolean isVerticalPassage;  // Vertical Passage type
    private boolean isHorizontalPassage; // Horizontal Passage type
    
    private boolean leftT;             // Left T-type passage
    private boolean rightT;            // Right T-type passage
    private boolean upT;               // Up T-type passage
    private boolean downT;             // Down T-type passage
    
    private boolean isCrossPassage;    // Cross Passage type
    
    private boolean isWall;            // Wall type
    private boolean isEmpty;           // Empty space type
    
    private boolean[] tileType = new boolean[13];
    
    // Movable direction of the Tile in the order: LEFT, UP, RIGHT, DOWN
    private Map<String, Boolean> movableDirection = new HashMap<>();
    
    // Images of the tile for UI rendering
    private Image image;      		// Image for the tile
    private Image coinImage;  		// Image for the coin on the tile
    
    // Constructors
    public Tile(int numbering, int x, int y) {
    	this.numbering = numbering;
    	this.xPos = x;
    	this.yPos = y;
    }

    public Tile(int numbering, int x, int y, Image image, boolean[] type) {
        this.numbering = numbering;
        this.xPos = x;
        this.yPos = y;
        this.image = image;
        
        // Identify which element in the `type` array is `true`
        int trueIndex = -1;
        for (int i = 0; i < type.length; i++) {
            if (type[i]) {
                trueIndex = i;
                break;
            }
        }

        // Ensure only one value is `true` in the array
        if (trueIndex != -1) {
            switch (trueIndex) {
                case 0:  // Upper Left Corner
                    isUpperLeftCorner = true;
                    setMovableDirection(false, false, true, true); // Can move right and down
                    break;
                case 1:  // Lower Left Corner
                    isLowerLeftCorner = true;
                    setMovableDirection(false, true, true, false); // Can move up and right
                    break;
                case 2:  // Upper Right Corner
                    isUpperRightCorner = true;
                    setMovableDirection(true, false, false, true); // Can move left and down
                    break;
                case 3:  // Lower Right Corner
                    isLowerRightCorner = true;
                    setMovableDirection(true, true, false, false); // Can move left and up
                    break;
                case 4:  // Vertical Passage
                    isVerticalPassage = true;
                    setMovableDirection(false, true, false, true); // Can move up and down
                    break;
                case 5:  // Horizontal Passage
                    isHorizontalPassage = true;
                    setMovableDirection(true, false, true, false); // Can move left and right
                    break;
                case 6:  // Left T
                    leftT = true;
                    setMovableDirection(false, true, true, true); // Can move right, up, and down
                    break;
                case 7:  // Right T
                    rightT = true;
                    setMovableDirection(true, true, false, true); // Can move left, up, and down
                    break;
                case 8:  // Up T
                    upT = true;
                    setMovableDirection(true, false, true, true); // Can move left, right, and down
                    break;
                case 9:  // Down T
                    downT = true;
                    setMovableDirection(true, true, true, false); // Can move left, right, and up
                    break;
                case 10:  // Cross Passage
                    isCrossPassage = true;
                    setMovableDirection(true, true, true, true); // Can move in all directions
                    break;
                case 11:  // Wall
                    isWall = true;
                    setMovableDirection(false, false, false, false); // No movement allowed
                    break;
                case 12:  // Empty
                    isEmpty = true;
                    setMovableDirection(false, false, false, false); // No movement allowed
                    break;
                default:
                    throw new IllegalArgumentException("Invalid type array provided.");
            }
        } else {
            throw new IllegalArgumentException("No true value found in type array.");
        }
    }
    
    // Getters and Setters
    
    // Get and set tile numbering
    public int getNumbering() { return numbering; }
    public void setNumbering(int numbering) { this.numbering = numbering; }
    
    // Get and set tile position
    public int getxPos() { return xPos; }
    public void setxPos(int xPos) { this.xPos = xPos; }
    
    public int getyPos() { return yPos; }
    public void setyPos(int yPos) { this.yPos = yPos; }
    
    // Get and set character presence
    public boolean hasCharacter() { return hasCharacter; }
    public void setHasCharacter(boolean hasCharacter) { this.hasCharacter = hasCharacter; }
    
    // Get and set coin presence
    public boolean hasCoin() { return hasCoin; }
    public void setHasCoin(boolean hasCoin) { this.hasCoin = hasCoin; }
    
    public int getContainsCoin() { return containsCoin; }
    public void setContainsCoin(int containsCoin) { this.containsCoin = containsCoin; }
    
    // Get and set tile types
    public boolean isUpperLeftCorner() { return isUpperLeftCorner; }
    public void setUpperLeftCorner(boolean isUpperLeftCorner) { this.isUpperLeftCorner = isUpperLeftCorner; }
    
    public boolean isLowerLeftCorner() { return isLowerLeftCorner; }
    public void setLowerLeftCorner(boolean isLowerLeftCorner) { this.isLowerLeftCorner = isLowerLeftCorner; }
    
    public boolean isUpperRightCorner() { return isUpperRightCorner; }
    public void setUpperRightCorner(boolean isUpperRightCorner) { this.isUpperRightCorner = isUpperRightCorner; }
    
    public boolean isLowerRightCorner() { return isLowerRightCorner; }
    public void setLowerRightCorner(boolean isLowerRightCorner) { this.isLowerRightCorner = isLowerRightCorner; }
    
    public boolean isVerticalPassage() { return isVerticalPassage; }
    public void setVerticalPassage(boolean isVerticalPassage) { this.isVerticalPassage = isVerticalPassage; }
    
    public boolean isHorizontalPassage() { return isHorizontalPassage; }
    public void setHorizontalPassage(boolean isHorizontalPassage) { this.isHorizontalPassage = isHorizontalPassage; }
    
    public boolean isLeftT() { return leftT; }
    public void setLeftT(boolean leftT) { this.leftT = leftT; }
    
    public boolean isRightT() { return rightT; }
    public void setRightT(boolean rightT) { this.rightT = rightT; }
    
    public boolean isUpT() { return upT; }
    public void setUpT(boolean upT) { this.upT = upT; }
    
    public boolean isDownT() { return downT; }
    public void setDownT(boolean downT) { this.downT = downT; }
    
    public boolean isCrossPassage() { return isCrossPassage; }
    public void setCrossPassage(boolean isCrossPassage) { this.isCrossPassage = isCrossPassage; }
    
    public boolean isWall() { return isWall; }
    public void setWall(boolean isWall) { this.isWall = isWall; }
    
    public boolean isEmpty() { return isEmpty; }
    public void setEmpty(boolean isEmpty) { this.isEmpty = isEmpty; }
    
    // Get and set tile type array
    public boolean[] getTileType() { return tileType; }
    public void setTileType(boolean[] tileType) {
        this.tileType = tileType;

        // Reset all tile type fields to false before setting new values
        isUpperLeftCorner = false;
        isLowerLeftCorner = false;
        isUpperRightCorner = false;
        isLowerRightCorner = false;
        isVerticalPassage = false;
        isHorizontalPassage = false;
        leftT = false;
        rightT = false;
        upT = false;
        downT = false;
        isCrossPassage = false;
        isWall = false;
        isEmpty = false;

        // Set the appropriate tile type fields based on the 'tileType' array
        for (int i = 0; i < tileType.length; i++) {
            if (tileType[i]) {
                switch (i) {
                    case 0:  // Upper Left Corner
                        isUpperLeftCorner = true;
                        break;
                    case 1:  // Lower Left Corner
                        isLowerLeftCorner = true;
                        break;
                    case 2:  // Upper Right Corner
                        isUpperRightCorner = true;
                        break;
                    case 3:  // Lower Right Corner
                        isLowerRightCorner = true;
                        break;
                    case 4:  // Vertical Passage
                        isVerticalPassage = true;
                        break;
                    case 5:  // Horizontal Passage
                        isHorizontalPassage = true;
                        break;
                    case 6:  // Left T
                        leftT = true;
                        break;
                    case 7:  // Right T
                        rightT = true;
                        break;
                    case 8:  // Up T
                        upT = true;
                        break;
                    case 9:  // Down T
                        downT = true;
                        break;
                    case 10: // Cross Passage
                        isCrossPassage = true;
                        break;
                    case 11: // Wall
                        isWall = true;
                        break;
                    case 12: // Empty
                        isEmpty = true;
                        break;
                    default:
                        break;
                }
            }
        }
    }
    
    // Get and set movable direction
    public Map<String, Boolean> getMovableDirection() { return movableDirection; }
    public void setMovableDirection(boolean canMoveLeft, boolean canMoveUp, boolean canMoveRight, boolean canMoveDown) {
        movableDirection.put("left", canMoveLeft);
        movableDirection.put("up", canMoveUp);
        movableDirection.put("right", canMoveRight);
        movableDirection.put("down", canMoveDown);
    }
    
    // Get and set tile images
    public Image getImage() { return image; }
    public void setImage(Image image) { this.image = image; }
    
    public Image getCoinImage() { return coinImage; }
    public void setCoinImage(Image coinImage) { this.coinImage = coinImage; }
    
    // Additional Tile logic
    
    // Compare movable direction between this tile and another tile
    public Map<String, Boolean> compareMovableDirection(Tile other) {
        // Create a map to hold the result of the comparison
        Map<String, Boolean> result = new HashMap<>();

        // Compare 'left' movement direction
        boolean canMoveLeft = this.movableDirection.get("left") && other.movableDirection.get("right");
        result.put("left", canMoveLeft);

        // Compare 'up' movement direction
        boolean canMoveUp = this.movableDirection.get("up") && other.movableDirection.get("down");
        result.put("up", canMoveUp);

        // Compare 'right' movement direction
        boolean canMoveRight = this.movableDirection.get("right") && other.movableDirection.get("left");
        result.put("right", canMoveRight);

        // Compare 'down' movement direction
        boolean canMoveDown = this.movableDirection.get("down") && other.movableDirection.get("up");
        result.put("down", canMoveDown);

        // Return the result map
        return result;
    }
    
    // Unset character from the tile (remove the character)
    public void unsetCharacter() {
        this.hasCharacter = false; // Set hasCharacter to false, indicating no character on the tile
    }

    // Unset coin from the tile (remove the coin)
    public void unsetCoin() {
        this.hasCoin = false;       // Set hasCoin to false, indicating no coin on the tile
        this.containsCoin = 0;      // Reset containsCoin value to 0, indicating no coin on the tile
    }
    
    public void rotate90Left(Image image) {
        // Update the image with the new rotated image
        this.image = image;

        // Check tile type and apply rotation logic accordingly
        if (isUpperLeftCorner) {
            isUpperLeftCorner = false;
            isLowerLeftCorner = true;
            setMovableDirection(false, true, true, false); // Can move up and right
        } else if (isLowerLeftCorner) {
            isLowerLeftCorner = false;
            isLowerRightCorner = true;
            setMovableDirection(true, true, false, false); // Can move left and up
        } else if (isLowerRightCorner) {
            isLowerRightCorner = false;
            isUpperRightCorner = true;
            setMovableDirection(true, false, false, true); // Can move left and down
        } else if (isUpperRightCorner) {
            isUpperRightCorner = false;
            isUpperLeftCorner = true;
            setMovableDirection(false, false, true, true); // Can move right and down
        } else if (isVerticalPassage) {
            isVerticalPassage = false;
            isHorizontalPassage = true;
            setMovableDirection(true, false, true, false); // Can move left and right
        } else if (isHorizontalPassage) {
            isHorizontalPassage = false;
            isVerticalPassage = true;
            setMovableDirection(false, true, false, true); // Can move up and down
        } else if (leftT) {
            leftT = false;
            downT = true;
            setMovableDirection(true, true, true, false); // Can move left, right, and up
        } else if (downT) {
            downT = false;
            rightT = true;
            setMovableDirection(true, true, false, true); // Can move left, up, and down
        } else if (rightT) {
            rightT = false;
            upT = true;
            setMovableDirection(true, false, true, true); // Can move left, right, and down
        } else if (upT) {
            upT = false;
            leftT = true;
            setMovableDirection(false, true, true, true); // Can move right, up, and down
        }
    }

    public void rotate90Right(Image image) {
        // Update the image with the new rotated image
        this.image = image;

        // Check tile type and apply rotation logic accordingly
        if (isUpperLeftCorner) {
            isUpperLeftCorner = false;
            isUpperRightCorner = true;
            setMovableDirection(true, false, false, true); // Can move left and down
        } else if (isUpperRightCorner) {
            isUpperRightCorner = false;
            isLowerRightCorner = true;
            setMovableDirection(true, true, false, false); // Can move left and up
        } else if (isLowerRightCorner) {
            isLowerRightCorner = false;
            isLowerLeftCorner = true;
            setMovableDirection(false, true, true, false); // Can move up and right
        } else if (isLowerLeftCorner) {
            isLowerLeftCorner = false;
            isUpperLeftCorner = true;
            setMovableDirection(false, false, true, true); // Can move right and down
        } else if (isVerticalPassage) {
            isVerticalPassage = false;
            isHorizontalPassage = true;
            setMovableDirection(true, false, true, false); // Can move left and right
        } else if (isHorizontalPassage) {
            isHorizontalPassage = false;
            isVerticalPassage = true;
            setMovableDirection(false, true, false, true); // Can move up and down
        } else if (leftT) {
            leftT = false;
            upT = true;
            setMovableDirection(true, false, true, true); // Can move left, right, and down
        } else if (upT) {
            upT = false;
            rightT = true;
            setMovableDirection(true, true, false, true); // Can move left, up, and down
        } else if (rightT) {
            rightT = false;
            downT = true;
            setMovableDirection(true, true, true, false); // Can move left, right, and up
        } else if (downT) {
            downT = false;
            leftT = true;
            setMovableDirection(false, true, true, true); // Can move right, up, and down
        }
    }
}

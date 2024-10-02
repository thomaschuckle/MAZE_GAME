package ui;

public class UIPrototype {
	
	// Full area.
    private final int[] maxDim = {1000, 700};
    
    // Split the frame into 30:70.
    // 30 part will be info panels.
    private final int[] topPanelDim = {1000, 30};
    private final int[] pInfoDim = {300, 300};
    private final int[] chatDim = {300, 300};
    private final int[] buttonsPanelDim = {300, 70};
    
    // 70 part will be maze.
    private final int[] mazeAreaDim = {700, 700};

    
    // Inside maze area.
    private final int[] logoDim = {300, 50};
    private final int[] mazeDim = {500, 500};
    private final int[] posDim = {55, 55};
    private final int[] arrowButtonDim = {15, 15};
    private final int[] actionButtonDim = {40, 40};

    // Getters for the dimensions
    public int[] getMaxDim() {
        return maxDim;
    }

    public int[] getTopPanelDim() {
        return topPanelDim;
    }

    public int[] getPInfoDim() {
        return pInfoDim;
    }

    public int[] getChatDim() {
        return chatDim;
    }

    public int[] getButtonsPanelDim() {
        return buttonsPanelDim;
    }

    public int[] getMazeAreaDim() {
        return mazeAreaDim;
    }

    public int[] getLogoDim() {
        return logoDim;
    }

    public int[] getMazeDim() {
        return mazeDim;
    }

    public int[] getPosDim() {
        return posDim;
    }

    public int[] getArrowButtonDim() {
        return arrowButtonDim;
    }

    public int[] getActionButtonDim() {
        return actionButtonDim;
    }
}
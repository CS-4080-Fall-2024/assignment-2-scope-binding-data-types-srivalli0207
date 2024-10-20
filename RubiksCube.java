public class RubiksCube {
    // Define the 6 faces of the cube (U, D, L, R, F, B)
    private char[][][] cube; // 3D array where cube[face][row][col]
    private int size; // Size of the cube (e.g., 3 for 3x3, 4 for 4x4)

    // Constructor for initializing a cube of any size
    public RubiksCube(int size) {
        this.size = size;
        this.cube = new char[6][size][size]; // 6 faces, each face is a size x size grid
        initializeCube();
    }

    // Initialize each face of the cube with default colors
    private void initializeCube() {
        char[] colors = {'W', 'Y', 'G', 'B', 'R', 'O'}; // Up, Down, Left, Right, Front, Back
        for (int i = 0; i < 6; i++) {
            for (int row = 0; row < size; row++) {
                for (int col = 0; col < size; col++) {
                    cube[i][row][col] = colors[i];
                }
            }
        }
    }

    // Rotate a face 90 degrees clockwise
    private void rotateFaceClockwise(int face) {
        char[][] temp = new char[size][size];
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                temp[col][size - row - 1] = cube[face][row][col];
            }
        }
        cube[face] = temp;
    }

    // Rotate a face 90 degrees counter-clockwise
    private void rotateFaceCounterClockwise(int face) {
        char[][] temp = new char[size][size];
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                temp[size - col - 1][row] = cube[face][row][col];
            }
        }
        cube[face] = temp;
    }

    // General method to rotate a row (horizontal)
    public void rotateRow(int row, int faceIndex) {
        // Rotate the specified row on the specified face
        rotateFaceClockwise(faceIndex);
        
        // Rotate adjacent rows
        int[] adjacentFaces = {2, 4, 3, 5}; // L, F, R, B for the top row (U)
        char[] temp = new char[4];

        // Store the tiles from the adjacent faces
        for (int i = 0; i < 4; i++) {
            temp[i] = cube[adjacentFaces[i]][row][0]; // Get the first column of each adjacent face
        }

        // Rotate the tiles from adjacent faces
        for (int i = 0; i < 4; i++) {
            cube[adjacentFaces[i]][row][0] = cube[adjacentFaces[(i + 1) % 4]][row][0];
        }

        // Put the stored tiles back into the adjacent faces
        cube[adjacentFaces[3]][row][0] = temp[0]; // Last back to first
    }

    // General method to rotate a column (vertical)
    public void rotateColumn(int col, int faceIndex) {
        // Rotate the specified column on the specified face
        rotateFaceClockwise(faceIndex);

        // Rotate adjacent columns
        int[] adjacentFaces = {2, 4, 3, 5}; // L, F, R, B for the left column
        char[] temp = new char[4];

        // Store the tiles from the adjacent faces
        for (int i = 0; i < 4; i++) {
            temp[i] = cube[adjacentFaces[i]][0][col]; // Get the first row of each adjacent face
        }

        // Rotate the tiles from adjacent faces
        for (int i = 0; i < 4; i++) {
            cube[adjacentFaces[i]][0][col] = cube[adjacentFaces[(i + 1) % 4]][0][col];
        }

        // Put the stored tiles back into the adjacent faces
        cube[adjacentFaces[3]][0][col] = temp[0]; // Last back to first
    }

    // Rotate the middle layer (horizontal)
    public void rotateMiddleLayerClockwise() {
        int middleRow = size / 2; // Middle row for odd sizes
        rotateRow(middleRow, 0); // Rotate U face
        rotateRow(middleRow, 1); // Rotate D face
    }

    // Rotate the middle column (vertical)
    public void rotateMiddleColumnClockwise() {
        int middleCol = size / 2; // Middle column for odd sizes
        rotateColumn(middleCol, 0); // Rotate U face
        rotateColumn(middleCol, 1); // Rotate D face
    }

    // Rotate the specified face (0 to 5) clockwise
    public void rotateFace(int face) {
        rotateFaceClockwise(face);
    }

    // Display the cube's current state
    public void displayCube() {
        for (int face = 0; face < 6; face++) {
            System.out.println("Face " + face + ":");
            for (int row = 0; row < size; row++) {
                for (int col = 0; col < size; col++) {
                    System.out.print(cube[face][row][col] + " ");
                }
                System.out.println();
            }
            System.out.println();
        }
    }

    // Main method for testing
    public static void main(String[] args) {
        RubiksCube rubiksCube = new RubiksCube(3);
        System.out.println("Initial Rubiks Cube:");
        rubiksCube.displayCube();
    
        System.out.println("After layers and columns are rotated:");
        // Rotate the top layer
        rubiksCube.rotateRow(0, 0);
        
        // Rotate the bottom layer
        rubiksCube.rotateRow(2, 1);
        
        // Rotate the left column
        rubiksCube.rotateColumn(0, 2);
        
        // Rotate the middle layer
        rubiksCube.rotateMiddleLayerClockwise();
        
        // Rotate the middle column
        rubiksCube.rotateMiddleColumnClockwise();
        
        rubiksCube.displayCube();
    }
}

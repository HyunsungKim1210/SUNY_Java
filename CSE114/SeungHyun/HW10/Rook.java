public class Rook extends ChessPiece {

    public Rook(char color, String location) {
        super('R',color, location);
    }

    @Override
    public boolean isValidMove(String target) {
        if (target.equals(location)) {
            return false;
        }

        char currentFile = location.charAt(0);
        int currentRank = location.charAt(1) - '0';

        char targetFile = target.charAt(0);
        int targetRank = target.charAt(1) - '0';

        // Rook moves horizontally or vertically
        if (currentFile == targetFile || currentRank == targetRank) {
            // Check if the move is within the bounds of the board
            return targetFile >= 'a' && targetFile <= 'h' && targetRank >= 1 && targetRank <= 8;
        }

        return false;
    }

    @Override
    public String toString() {
        return "R_" + color;
    }
}

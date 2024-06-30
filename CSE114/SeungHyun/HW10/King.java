public class King extends ChessPiece {

    public King(char color, String location) {
        super('K',color, location);
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

        // King moves one square in any direction
        int fileDifference = Math.abs(targetFile - currentFile);
        int rankDifference = Math.abs(targetRank - currentRank);

        if ((fileDifference <= 1 && rankDifference <= 1) &&
                (targetFile >= 'a' && targetFile <= 'h') &&
                (targetRank >= 1 && targetRank <= 8)) {
            return true;
        }

        return false;
    }

    @Override
    public String toString() {
        return "K_" + color;
    }
}

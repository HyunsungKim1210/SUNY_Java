public class Bishop extends ChessPiece {

    public Bishop(char color, String location) {
        super('B',color, location);
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


        int fileDifference = Math.abs(targetFile - currentFile);
        int rankDifference = Math.abs(targetRank - currentRank);

        if (fileDifference == rankDifference) {

            return targetFile >= 'a' && targetFile <= 'h' && targetRank >= 1 && targetRank <= 8;
        }

        return false;
    }

    @Override
    public String toString() {
        return "B_" + color;
    }
}


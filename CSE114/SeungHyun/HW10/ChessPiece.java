// ChessBoard.java - track pieces on a chessboard
//
//
// Tony Mione
//

public abstract class ChessPiece {

    protected char piece;
    protected char color;
    protected String location; // Should be a-h, 1-8 like e2

    public ChessPiece(char piece, char color, String location) {
        this.piece = piece;
        this.color = color;
        if (isValidLocation(location)) {
            this.location = location;
        } else {
            this.location = null;
        }
    }

    public boolean isValidLocation(String location) {
        char file = location.charAt(0);
        char rank = location.charAt(1);

        String files = "abcdefgh";
        String ranks = "12345678";

        int fileoffset = files.indexOf(file);
        int rankoffset = ranks.indexOf(rank);
        if ((fileoffset < 0) || (rankoffset < 0)) {
            return false; // Bad location or bad target
        } else {
            return true;
        }
    }

    public String whereAmI() {
        return this.location;
    }

    public char getPiece() {
        return piece;
    }

    public char getColor() {
        return color;
    }

    public String toString() {
        return piece + "_" + color;
    }

    public abstract boolean isValidMove(String target);

}


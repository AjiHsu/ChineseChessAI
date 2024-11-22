import java.util.ArrayList;
import java.util.Arrays;

public class ChineseChessAI {
    // ----------type define-----------
    enum Group {
        RED, BLACK, EMPTY
    }

    enum Piece {
        KING, ADVISOR, ELEPHANT, CHARIOT, HORSE, CANNON, SOLDIER, EMPTY
    }
    // ----------end type define----------

    // ----------evaluate arrays : static for main test----------
    static int[] pieceEvaluate = { 0, 250, 250, 500, 300, 300, 80};
    static int[] mobilityEvaluate = { 0, 1, 1, 6, 12, 6, 15 };

    static int[][][] kingPositionEvaluate = { { { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                                                { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                                                { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                                                { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                                                { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                                                { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                                                { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                                                { 0, 0, 0,-9,-9,-9, 0, 0, 0 },
                                                { 0, 0, 0,-8,-8,-8, 0, 0, 0 },
                                                { 0, 0, 0, 1, 5, 1, 0, 0, 0 } },

                                              { { 0, 0, 0, 1, 5, 1, 0, 0, 0 },
                                                { 0, 0, 0,-8,-8,-8, 0, 0, 0 },
                                                { 0, 0, 0,-9,-9,-9, 0, 0, 0 },
                                                { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                                                { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                                                { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                                                { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                                                { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                                                { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                                                { 0, 0, 0, 0, 0, 0, 0, 0, 0 } } };

    static int[][][] advisorPositionEvaluate = { { { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                                                   { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                                                   { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                                                   { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                                                   { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                                                   { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                                                   { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                                                   { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                                                   { 0, 0, 0, 0, 3, 0, 0, 0, 0 },
                                                   { 0, 0, 0, 0, 0, 0, 0, 0, 0 } },

                                                 { { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                                                   { 0, 0, 0, 0, 3, 0, 0, 0, 0 },
                                                   { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                                                   { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                                                   { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                                                   { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                                                   { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                                                   { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                                                   { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                                                   { 0, 0, 0, 0, 0, 0, 0, 0, 0 } } };

    static int[][][] elephantPositionEvaluate = { { { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                                                    { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                                                    { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                                                    { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                                                    { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                                                    { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                                                    { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                                                    {-2, 0, 0, 0, 3, 0, 0, 0,-2 },
                                                    { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                                                    { 0, 0, 0, 0, 0, 0, 0, 0, 0 } },

                                                  { { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                                                    { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                                                    {-2, 0, 0, 0, 3, 0, 0, 0,-2 },
                                                    { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                                                    { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                                                    { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                                                    { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                                                    { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                                                    { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                                                    { 0, 0, 0, 0, 0, 0, 0, 0, 0 } } };

    static int[][][] chariotPositionEvaluate = { { { 6, 8, 7,13,14,13, 7, 8, 6 },
                                                   { 6,12, 9,16,33,16, 9,12, 6 },
                                                   { 6, 8, 7,14,16,14, 7, 8, 6 },
                                                   { 6,13,13,16,16,16,13,13, 6 },
                                                   { 8,11,11,14,15,14,11,11, 8 },
                                                   { 8,12,12,14,15,14,12,12, 8 },
                                                   { 4, 9, 4,12,14,12, 4, 9, 4 },
                                                   {-2, 8, 4,12,12,12, 4, 8,-2 },
                                                   { 5, 8, 6,12, 0,12, 6, 8, 5 },
                                                   {-6, 6, 4,12, 0,12, 4, 6,-6 } },

                                                 { {-6, 6, 4,12, 0,12, 4, 6,-6 },
                                                   { 5, 8, 6,12, 0,12, 6, 8, 5 },
                                                   {-2, 8, 4,12,12,12, 4, 8,-2 },
                                                   { 4, 9, 4,12,14,12, 4, 9, 4 },
                                                   { 8,12,12,14,15,14,12,12, 8 },
                                                   { 8,11,11,14,15,14,11,11, 8 },
                                                   { 6,13,13,16,16,16,13,13, 6 },
                                                   { 6, 8, 7,14,16,14, 7, 8, 6 },
                                                   { 6,12, 9,16,33,16, 9,12, 6 },
                                                   { 6, 8, 7,13,14,13, 7, 8, 6 } } };

    static int[][][] horsePositionEvaluate = { { { 2, 2, 2, 8, 2, 8, 2, 2, 2 },
                                                 { 2, 8,15, 9, 6, 9,15, 8, 2 },
                                                 { 4,10,11,15,11,15,11,10, 4 },
                                                 { 5,20,12,19,12,19,12,20, 5 },
                                                 { 2,12,11,15,16,15,11,12, 2 },
                                                 { 2,10,13,14,15,14,13,10, 2 },
                                                 { 4, 6,10, 7,10, 7,10, 6, 4 },
                                                 { 5, 4, 6, 7, 4, 7, 6, 4, 5 },
                                                 {-3, 2, 4, 5,-10,5, 4, 2,-3 },
                                                 { 0,-3, 2, 0, 2, 0, 2,-3, 0 } },

                                               { { 0,-3, 2, 0, 2, 0, 2,-3, 0 },
                                                 {-3, 2, 4, 5,-10,5, 4, 2,-3 },
                                                 { 5, 4, 6, 7, 4, 7, 6, 4, 5 },
                                                 { 4, 6,10, 7,10, 7,10, 6, 4 },
                                                 { 2,10,13,14,15,14,13,10, 2 },
                                                 { 2,12,11,15,16,15,11,12, 2 },
                                                 { 5,20,12,19,12,19,12,20, 5 },
                                                 { 4,10,11,15,11,15,11,10, 4 },
                                                 { 2, 8,15, 9, 6, 9,15, 8, 2 },
                                                 { 2, 2, 2, 8, 2, 8, 2, 2, 2 } } };

    static int[][][] cannonPositionEvaluate = { { { 4, 4, 0,-5,-6,-5, 0, 4, 4 },
                                                  { 2, 2, 0,-4,-7,-4, 0, 2, 2 },
                                                  { 1, 1, 0,-5,-4,-5, 0, 1, 1 },
                                                  { 0, 3, 3, 2, 4, 2, 3, 3, 0 },
                                                  { 0, 0, 0, 0, 4, 0, 0, 0, 0 },
                                                  {-1, 0, 3, 0, 4, 0, 3, 0,-1 },
                                                  { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                                                  { 1, 0, 4, 3, 5, 3, 4, 0, 1 },
                                                  { 0, 1, 2, 2, 2, 2, 2, 1, 0 },
                                                  { 0, 0, 1, 3, 3, 3, 1, 0, 0 } },

                                                { { 0, 0, 1, 3, 3, 3, 1, 0, 0 },
                                                  { 0, 1, 2, 2, 2, 2, 2, 1, 0 },
                                                  { 1, 0, 4, 3, 5, 3, 4, 0, 1 },
                                                  { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                                                  {-1, 0, 3, 0, 4, 0, 3, 0,-1 },
                                                  { 0, 0, 0, 0, 4, 0, 0, 0, 0 },
                                                  { 0, 3, 3, 2, 4, 2, 3, 3, 0 },
                                                  { 1, 1, 0,-5,-4,-5, 0, 1, 1 },
                                                  { 2, 2, 0,-4,-7,-4, 0, 2, 2 },
                                                  { 4, 4, 0,-5,-6,-5, 0, 4, 4 } } };

    static int[][][] soldierPositionEvaluate = { { { 0, 0, 0, 2, 4, 2, 0, 0, 0 },
                                                   {20,30,50,65,70,65,50,30,20 },
                                                   {20,30,45,55,55,55,45,30,20 },
                                                   {20,27,30,40,42,40,30,27,20 },
                                                   {10,18,22,35,40,35,22,18,10 },
                                                   { 3, 0, 4, 0, 7, 0, 4, 0, 3 },
                                                   {-2, 0,-2, 0, 6, 0,-2, 0,-2 },
                                                   { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                                                   { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                                                   { 0, 0, 0, 0, 0, 0, 0, 0, 0 } },

                                                 { { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                                                   { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                                                   { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                                                   {-2, 0,-2, 0, 6, 0,-2, 0,-2 },
                                                   { 3, 0, 4, 0, 7, 0, 4, 0, 3 },
                                                   {10,18,22,35,40,35,22,18,10 },
                                                   {20,27,30,40,42,40,30,27,20 },
                                                   {20,30,45,55,55,55,45,30,20 },
                                                   {20,30,50,65,70,65,50,30,20 },
                                                   { 0, 0, 0, 2, 4, 2, 0, 0, 0 } } };

    static int[][][][] positionEvaluate = { kingPositionEvaluate, advisorPositionEvaluate, elephantPositionEvaluate, chariotPositionEvaluate, horsePositionEvaluate, cannonPositionEvaluate, soldierPositionEvaluate };
    // ----------end evaluate arrays----------

    // ----------
    private static boolean inBound(int i, int j) {
        return (0 <= i && i < 10 && 0 <= j && j < 9);
    }
    // ----------

    // ----------generate move // only for method "generateMove"----------
    static int[] kingMoveI = { 0, 0, 1, -1 };
    static int[] kingMoveJ = { 1, -1, 0, 0 };
    static int[] advisorMoveI = { 1, 1, -1, -1 };
    static int[] advisorMoveJ = { 1, -1, 1, -1 };
    static int[] elephantMoveI = { 2, 2, -2, -2 };
    static int[] elephantMoveJ = { 2, -2, 2, -2 };
    // special method for chariot move
    static int[] horseMoveI = { 1, 1, -1, -1, 2, 2, -2, -2 };
    static int[] horseMoveJ = { 2, -2, 2, -2, 1, -1, 1, -1 };
    // special method for cannon move // special method for cannon eat
    // special method for horse move

    private static ArrayList<int[]> generateSingleMoves(Piece piece, int i, int j, int[][] boardPiece, Group turn) { // todo
        ArrayList<int[]> moves = new ArrayList<>();
        int[] dirI = { 0, 0, 1, -1 };
        int[] dirJ = { 1, -1, 0, 0 };
        switch (piece) {
            case KING:
                for (int k = 0; k < 4; k++) {
                    int ii = i + kingMoveI[k];
                    int jj = j + kingMoveJ[k];
                    if (inBound(ii, jj)) {
                        moves.add(new int[] {ii, jj});
                    }
                }
                break;
            case ADVISOR:
                for (int k = 0; k < 4; k++) {
                    int ii = i + advisorMoveI[k];
                    int jj = j + advisorMoveJ[k];
                    if (inBound(ii, jj)) {
                        moves.add(new int[] {ii, jj});
                    }
                }
                break;
            case ELEPHANT:
                for (int k = 0; k < 4; k++) {
                    int ii = i + elephantMoveI[k];
                    int jj = j + elephantMoveJ[k];
                    if (inBound(ii, jj) && boardPiece[i + elephantMoveI[k] / 2][j +elephantMoveJ[k] / 2] == Piece.EMPTY.ordinal()) {
                        moves.add(new int[] {ii, jj});
                    }
                }
                break;
            case CHARIOT:
                for (int k = 0; k < 4; k++) {
                    int ti = i, tj = j;
                    while (inBound(ti += dirI[k], tj += dirJ[k]) && boardPiece[ti][tj] == Piece.EMPTY.ordinal()) {
                        moves.add(new int[] {ti, tj});
                    }
                    if (inBound(ti, tj)) moves.add(new int[] {ti, tj}); // under protect or attack
                }
                break;
            case HORSE:
                for (int k = 0; k < 4; k++) {
                    int ii = i + horseMoveI[k];
                    int jj = j + horseMoveJ[k];
                    if (inBound(ii, jj) && boardPiece[i][j + horseMoveJ[k] / 2] == Piece.EMPTY.ordinal()) {
                        moves.add(new int[] {ii, jj});
                    }
                }
                for (int k = 4; k < 8; k++) {
                    int ii = i + horseMoveI[k];
                    int jj = j + horseMoveJ[k];
                    if (inBound(ii, jj) && boardPiece[i + horseMoveI[k] / 2][j] == Piece.EMPTY.ordinal()) {
                        moves.add(new int[] {ii, jj});
                    }
                }
                break;
            case CANNON:
                for (int k = 0; k < 4; k++) {
                    int ti = i, tj = j;
                    while (inBound(ti += dirI[k], tj += dirJ[k]) && boardPiece[ti][tj] == Piece.EMPTY.ordinal()) {
                        moves.add(new int[] {ti, tj});
                    }
                    if (inBound(ti, tj)) {
                        while (inBound(ti += dirI[k], tj += dirJ[k]) && boardPiece[ti][tj] == Piece.EMPTY.ordinal());
                        if (inBound(ti, tj)) moves.add(new int[] {ti, tj}); // under attack or protect
                    }
                }
                break;
            case SOLDIER:
                if (turn == Group.RED) {
                    if (i >= 5) moves.add(new int[] { i - 1, j });
                    else {
                        if (inBound(i - 1, j)) moves.add(new int[] { i - 1, j });
                        if (inBound(i, j + 1)) moves.add(new int[] { i, j + 1 });
                        if (inBound(i, j - 1)) moves.add(new int[] { i, j - 1 });
                    }
                } else if (turn == Group.BLACK) {
                    if (i <= 4) moves.add(new int[] { i + 1, j });
                    else {
                        if (inBound(i + 1, j)) moves.add(new int[] { i + 1, j });
                        if (inBound(i, j + 1)) moves.add(new int[] { i, j + 1 });
                        if (inBound(i, j - 1)) moves.add(new int[] { i, j - 1 });
                    }
                } else {
                    System.err.println("Wrong turn at generateSingleMoves");
                    System.exit(1);
                }
                break;
            default:
                break;
        }
        return moves;
    }

    // for all move (including self group : to evaluate protect score)
    private static ArrayList<int[]> generateMove(int[][] boardPiece, int[][] boardGroup, Group turn) {
        return null; // todo
    }

    // for only valid move
    private static ArrayList<int[]> generateAvailableMove(int[][] boardPiece, int[][] boardGroup, Group turn) {
        ArrayList<int[]> allMoves = new ArrayList<>();
        // todo

        return allMoves; // generate all move { i1, j1, i2, j2 };
    }
    // ----------end generate move---------

    // ----------check if under dKing----------
    // todo can be used in generateMove...
    private static boolean checkUnderDKing(int[][] boardPiece, int[][] boardGroup, Group turn) {
        // find king position
        int ki = -1, kj = -1;
        for (int i = 0; i < boardPiece.length; i++) {
            for (int j = 0; j < boardPiece[0].length; j++) {
                if (boardGroup[i][j] == Piece.KING.ordinal() && boardPiece[i][j] == turn.ordinal()) {
                    ki = i;
                    kj = j;
                    break;
                }
            }
        }

        if (ki == -1 && kj == -1) { // error occur
            System.err.println("Cannot find king position");
            System.exit(1);
        }
        Group enemyTurn = Group.EMPTY;
        if (turn == Group.RED) enemyTurn = Group.BLACK;
        else if (turn == Group.BLACK) enemyTurn = Group.RED;
        else {
            System.err.println("Wrong turn at checkUnderDKing");
            System.exit(1);
        }

        for (int i = 0; i < boardPiece.length; i++) {
            for (int j = 0; j < boardPiece[0].length; j++) {
                if (boardPiece[i][j] != Piece.EMPTY.ordinal()) {
                    ArrayList<int[]> moves = generateSingleMoves(Piece.values()[boardPiece[i][j]], i, j, boardPiece, enemyTurn);
                    for (int[] move : moves) {
                        if (move[0] == ki && move[1] == kj) return true;
                    }
                }
            }
        }

        return false;
    }
    // ----------end check----------

    private static int evaluate(int[][] boardPiece, int[][] boardGroup, Group turn) {

        int maxValue = 10000;
        double attackWeight = 0.5;
        double protectWeight = 0.2;

        // find opposite group----------
        Group oppositeTurn = Group.EMPTY;
        if (turn == Group.RED) oppositeTurn = Group.BLACK;
        else if (turn == Group.BLACK) oppositeTurn = Group.RED;
        else {
            System.err.println("Wrong turn at evaluate");
            System.exit(1);
        }
        // end find opposite group----------

        // position of king----------
        int[] king = new int[2];
        int[] oppositeKing = new int[2];
        for (int i = 0; i < boardPiece.length; i++) {
            for (int j = 0; j < boardPiece[0].length; j++) {
                if (boardGroup[i][j] == turn.ordinal()) {
                    if (boardPiece[i][j] == Piece.KING.ordinal()) {
                        king[0] = i;
                        king[1] = j;
                    }
                } else if (boardGroup[i][j] == oppositeTurn.ordinal()) {
                    if (boardPiece[i][j] == Piece.KING.ordinal()) {
                        oppositeKing[0] = i;
                        oppositeKing[1] = j;
                    }
                }
            }
        }
        // end position of king----------

        // piece position & type evaluate----------
        int turnTotal = 0, oppositeTurnTotal = 0;
        for (int i = 0; i < boardPiece.length; i++) {
            for (int j = 0; j < boardPiece[i].length; j++) {
                if (boardGroup[i][j] == turn.ordinal() && boardPiece[i][j] != Piece.EMPTY.ordinal()) {
                    turnTotal += positionEvaluate[boardPiece[i][j]][boardGroup[i][j]][i][j];
                    turnTotal += pieceEvaluate[boardPiece[i][j]];
                } else if (boardGroup[i][j] == oppositeTurn.ordinal() && boardPiece[i][j] != Piece.EMPTY.ordinal()) {
                    oppositeTurnTotal += positionEvaluate[boardPiece[i][j]][boardGroup[i][j]][i][j];
                    oppositeTurnTotal += pieceEvaluate[boardPiece[i][j]];
                }
            }
        }
        // end piece position & type evaluate----------

        // piece mobility evaluate | attack & protect evaluate----------
        boolean underDKing = checkUnderDKing(boardPiece, boardGroup, turn);
        ArrayList<int[]> moves = generateMove(boardPiece, boardGroup, turn);
        ArrayList<int[]> oppositeMoves = generateMove(boardPiece, boardGroup, oppositeTurn);

        for (int[] move : moves) {
            if (boardGroup[move[2]][move[3]] == Group.EMPTY.ordinal()) { // mobile
                turnTotal += mobilityEvaluate[boardPiece[move[0]][move[1]]];
            } else if (boardGroup[move[2]][move[3]] == oppositeTurn.ordinal()) { // attack
                if (boardPiece[move[2]][move[3]] == Piece.KING.ordinal()) return maxValue;
                else turnTotal += pieceEvaluate[boardPiece[move[2]][move[3]]] * attackWeight;
            } else if (boardGroup[move[2]][move[3]] == turn.ordinal()) { // protect
                turnTotal += pieceEvaluate[boardPiece[move[2]][move[3]]] * protectWeight;
            }
        }

        for (int[] oppositeMove : oppositeMoves) {
            if (boardGroup[oppositeMove[2]][oppositeMove[3]] == Group.EMPTY.ordinal()) { // mobile
                oppositeTurnTotal += mobilityEvaluate[boardPiece[oppositeMove[0]][oppositeMove[1]]];
            } else if (boardGroup[oppositeMove[2]][oppositeMove[3]] == turn.ordinal()) { // attack
                if (boardPiece[oppositeMove[2]][oppositeMove[3]] == Piece.KING.ordinal()) oppositeTurnTotal += maxValue;
                else oppositeTurnTotal += pieceEvaluate[boardPiece[oppositeMove[2]][oppositeMove[3]]] * attackWeight;
            } else if (boardGroup[oppositeMove[2]][oppositeMove[3]] == oppositeTurn.ordinal()) { // protect
                oppositeTurnTotal += pieceEvaluate[boardPiece[oppositeMove[2]][oppositeMove[3]]] * protectWeight;
            }
        }
        // end piece mobility evaluate | attack & protect evaluate----------

        // calculate sum
        int totalScore = turnTotal - oppositeTurnTotal;

        return totalScore;
    }

    // find the deep-th score----------
    public static int bestScore(int[][] boardPiece, int[][] boardGroup, Group turn, int deep) { // recursion return { i1, i2, j1, j2 }
        // end of deep search
        if (deep <= 0) {
            return evaluate(boardPiece, boardGroup, turn);
        }

        ArrayList<int[]> moves = generateAvailableMove(boardPiece, boardGroup, turn);
        int max = Integer.MIN_VALUE;

        for (int[] move : moves) {
            // copy board
            int[][] newBoardPiece = new int[boardPiece.length][boardPiece[0].length];
            int[][] newBoardGroup = new int[boardGroup.length][boardGroup[0].length];
            Group newTurn = turn;
            for (int i = 0; i < boardPiece.length; i++) {
                for (int j = 0; j < boardPiece[i].length; j++) {
                    newBoardPiece[i][j] = boardPiece[i][j];
                    newBoardGroup[i][j] = boardGroup[i][j];
                }
            }

            newBoardPiece[move[2]][move[3]] = newBoardPiece[move[0]][move[1]];
            newBoardPiece[move[0]][move[1]] = Piece.EMPTY.ordinal();
            newBoardGroup[move[2]][move[3]] = boardGroup[move[0]][move[1]];
            newBoardGroup[move[0]][move[1]] = Group.EMPTY.ordinal();

            if (turn == Group.BLACK) {
                newTurn = Group.RED;
            } else if (turn == Group.RED) {
                newTurn = Group.BLACK;
            } else {
                System.err.println("illegal input \"Group\"---at method : bestMove");
                System.exit(1);
            }

            // the opposite choose the best move
            int score = bestScore(newBoardPiece, newBoardGroup, newTurn, deep - 1);

            if (score > max) {
                max = score;
            }
        }
        return max;
    }
    // end of bestScore----------

    // can be called by main----------
    public static int[] bestMove(int[][] boardPiece, int[][] boardGroup, Group turn, int deep) { // recursion return { i1, i2, j1, j2 }
        ArrayList<int[]> moves = generateAvailableMove(boardPiece, boardGroup, turn);
        int max = Integer.MIN_VALUE;
        int[] ansMove = null;

        for (int[] move : moves) {
            // copy board
            int[][] newBoardPiece = new int[boardPiece.length][boardPiece[0].length];
            int[][] newBoardGroup = new int[boardGroup.length][boardGroup[0].length];
            Group newTurn = turn;
            for (int i = 0; i < boardPiece.length; i++) {
                for (int j = 0; j < boardPiece[i].length; j++) {
                    newBoardPiece[i][j] = boardPiece[i][j];
                    newBoardGroup[i][j] = boardGroup[i][j];
                }
            }

            newBoardPiece[move[2]][move[3]] = newBoardPiece[move[0]][move[1]];
            newBoardPiece[move[0]][move[1]] = Piece.EMPTY.ordinal();
            newBoardGroup[move[2]][move[3]] = boardGroup[move[0]][move[1]];
            newBoardGroup[move[0]][move[1]] = Group.EMPTY.ordinal();

            if (turn == Group.BLACK) {
                newTurn = Group.RED;
            } else if (turn == Group.RED) {
                newTurn = Group.BLACK;
            } else {
                System.err.println("illegal input \"Group\"---at method : bestMove");
                System.exit(1);
            }

            // the opposite choose the best move
            int score = bestScore(newBoardPiece, newBoardGroup, newTurn, deep - 1);

            if (score > max) {
                max = score;
                ansMove = move;
            }
        }
        return ansMove;
    }
    //end of bestMove----------

    // main for testing
    public static void main(String[] args) {
        Piece piece = Piece.valueOf("SOLDIER");
        System.out.println(pieceEvaluate[piece.ordinal()]);

        int[][] pieceBoard = null;
        int[][] groupBoard = null;
        Group turn = null;
        int deep = -1;

        int[] nextMove = bestMove(pieceBoard, groupBoard, turn, deep);
        System.out.println("the next move is : " + Arrays.asList(nextMove));
    }
}
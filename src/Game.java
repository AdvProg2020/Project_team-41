import java.util.ArrayList;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class moves{
    public moves(char whoMoved, int[] movedPiece, int[] destroyedPiece, String destroyedPieceName, String movedPieceName) {
        this.whoMoved = whoMoved;
        this.movedPiece[0] = movedPiece[1]+1;
        this.movedPiece[1] = movedPiece[0]+1;
        this.destroyedPiece[0] = destroyedPiece[1]+1;
        this.destroyedPiece[1] = destroyedPiece[0]+1;
        this.destroyedPieceName = destroyedPieceName;
        this.movedPieceName = movedPieceName;


    }
    char whoMoved;
    int[] movedPiece = new int[2];
    int[] destroyedPiece = new int[2];
    String destroyedPieceName;
    String movedPieceName;

}



public class Game {
    private String whitePlayer;
    private String blackPlayer;
    private int limit;
    private char turn;
    String destroyedPiece;
    String movedPiece;
    private int[] movedTo = new int[2];
    private int[] movedFrom = new int[2];
    //shows whos' turn is it
    //w or b
    private boolean turnUndo = false;
    //index 0 is white and index 1 is black
    private int[] totalUndo = new int[2];
    //index 0 is white and index 1 is black
    private boolean changedBoard = false;
    private ArrayList<moves> totalMoves = new ArrayList<>();
    private int[] selectedPiece = new int[2];
    private String[] undoSelectedPieceInside = new String[2];
    //first index is selected piece
    //second index is target place
    private int[][] undoSelectedPiece = new int[2][2];
    // {{x,y},{target x, target y}}
    private String[][] board = new String[8][8];
    public Game(String whitePlayer,String blackPlayer,int limit) {
        this.whitePlayer = whitePlayer;
        this.blackPlayer = blackPlayer;
        this.limit = limit;
        selectedPiece[0] = -1;
        selectedPiece[1] = -1;
        movedTo[0] = -1;
        movedTo[1] = -1;
        movedFrom[0] = -1;
        movedFrom[1] = -1;
        turn = 'w';


    }
    public void main(){
        String command;
        Matcher matcher;
        while (true) {
            command = Main.scanner.nextLine();
            if((matcher = getMatcher(command, "select ((-?\\d+),(-?\\d+))")).matches()){
                select(matcher.group(1));
            }
            else if (command.equals("deselect"))
                deselect();
            else if((matcher = getMatcher(command, "move ((-?\\d+),(-?\\d+))")).matches()){
                move(matcher.group(1));
            }
            else if (command.equals("next_turn"))
                nextTurn();
            else if (command.equals("undo"))
                undo();
            else if (command.equals("undo_number"))
                undoNumber();
            else if((matcher = getMatcher(command, "show_moves( -all)?")).matches()){
                showMoves(matcher.group());
            }
            else if (command.equals("show_turn"))
                showTurn();
            else if((matcher = getMatcher(command, "show_killed( -all)?")).matches()){
                showKilled(matcher.group());
            }
            else if (command.equals("show_board"))
                showBoard();
            else if (command.equals("help"))
                help();
            else if (command.equals("forfeit"))
                forfeit();
            else {
                System.out.println("invalid command");
            }

        }

    }
    private void select(String coordinates){
        if(coordinates.length() > 3){
            System.out.println("wrong coordination");
            this.main();
        }
        String[] coordinatesArray = coordinates.split(",");
        int column = Integer.parseInt(coordinatesArray[0]) - 1;
        int row = Integer.parseInt(coordinatesArray[1]) - 1;
        if((row > 7) || (row < 0) || (column > 7) || (column < 0)) {
            System.out.println("wrong coordination");
            this.main();
        }
        if(board[row][column] .equals("  ")) {
            System.out.println("no piece on this spot");
            main();
        }
        if(board[row][column].charAt(1) != turn) {
            System.out.println("you can only select one of your pieces");
            main();
        }
        selectedPiece[0] = row;
        selectedPiece[1] = column;
        System.out.println("selected");
        main();

    }
    private void deselect(){
        if((selectedPiece[0] == -1) &&(selectedPiece[1] == -1)) {
            System.out.println("no piece is selected");
        }
        else{
            selectedPiece[0] = -1;
            selectedPiece[1] = -1;
            System.out.println("deselected");
        }
        main();

    }
    private void move(String coordinates){

        if(changedBoard) {
            System.out.println("already moved");
            main();
        }
        if(coordinates.length() > 3){
            System.out.println("wrong coordination");
            this.main();
        }
        String[] coordinatesArray = coordinates.split(",");
        int column = Integer.parseInt(coordinatesArray[0]) - 1;
        int row = Integer.parseInt(coordinatesArray[1]) - 1;
        if((row > 7) || (row < 0) || (column > 7) || (column < 0)) {
            System.out.println("wrong coordination");
            this.main();
        }
        if(selectedPiece[0] == -1){
            System.out.println("do not have any selected piece");
            main();
        }
        if(!legalMove(row,column)){
            System.out.println("cannot move to the spot");
            main();
        }
        if((board[row][column].charAt(1) != board[selectedPiece[0]][selectedPiece[1]].charAt(1)) && (!board[row][column] .equals("  ")))
            System.out.println("rival piece destroyed");
        else
            System.out.println("moved");
        movedPiece = board[selectedPiece[0]][selectedPiece[1]];
        destroyedPiece = board[row][column];

        movedTo[0] = row;
        movedTo[1] = column;
        movedFrom[0] = selectedPiece[0];
        movedFrom[1] = selectedPiece[1];
        undoSelectedPiece[0][0] = selectedPiece[0];
        undoSelectedPiece[0][1] = selectedPiece[1];
        undoSelectedPiece[1][0] = row;
        undoSelectedPiece[1][1] = column;
        undoSelectedPieceInside[0] = board[selectedPiece[0]][selectedPiece[1]];
        undoSelectedPieceInside[1] = board[row][column];
        board[row][column] = board[selectedPiece[0]][selectedPiece[1]];
        board[selectedPiece[0]][selectedPiece[1]] = "  ";
        changedBoard = true;
        main();
    }
    private void nextTurn(){
        if(changedBoard) {
            System.out.println("turn completed");
            if(destroyedPiece .equals("Kw")){
                System.out.println("player "+blackPlayer+" with color black won");
                Objects.requireNonNull(User.allUsersContainsUsername(whitePlayer)).addLosses();
                Objects.requireNonNull(User.allUsersContainsUsername(blackPlayer)).addWins();
                Menu.menu();
            }
            if(destroyedPiece .equals("Kb")){
                System.out.println("player "+whitePlayer+" with color white won");
                Objects.requireNonNull(User.allUsersContainsUsername(whitePlayer)).addWins();
                Objects.requireNonNull(User.allUsersContainsUsername(blackPlayer)).addLosses();
                Menu.menu();
            }
           totalMoves.add(new moves(turn,movedFrom.clone(),movedTo.clone(),destroyedPiece,movedPiece));
            changedBoard = false;
            turnUndo = false;
            selectedPiece[0] = -1;
            selectedPiece[1] = -1;
            destroyedPiece = "";
            movedPiece = "";
            movedTo[0] = -1;
            movedTo[1] = -1;
            movedFrom[0] = -1;
            movedFrom[1] = -1;
            if(turn == 'w')
                turn = 'b';
            else
                turn = 'w';

            limit--;
            if(limit == 0){
                System.out.println("draw");
                Objects.requireNonNull(User.allUsersContainsUsername(whitePlayer)).addDraws();
                Objects.requireNonNull(User.allUsersContainsUsername(blackPlayer)).addDraws();
                Menu.menu();
            }

        }
        else {
            System.out.println("you must move then proceed to next turn");
        }
        main();
    }
    private void showTurn(){
        if(turn == 'w') {
            System.out.println("it is player "+whitePlayer+" turn with color white");
        }
        else {
            System.out.println("it is player "+blackPlayer+" turn with color black");
        }
            main();
    }
    private void undo(){
        int w0b1;
        if(turn == 'w')
            w0b1 = 0;
        else
            w0b1 = 1;

        if(totalUndo[w0b1] > 1){
            System.out.println("you cannot undo anymore");
            main();
        }
        if(!changedBoard) {
            System.out.println("you must move before undo");
            main();
        }
        if(turnUndo) {
            System.out.println("you have used your undo for this turn");
            main();
        }
        System.out.println("undo completed");
        totalUndo[w0b1]++;
        turnUndo = true;
        changedBoard = false;
        destroyedPiece = null;
        movedPiece = null;
        board[undoSelectedPiece[0][0]][undoSelectedPiece[0][1]] = undoSelectedPieceInside[0];
        board[undoSelectedPiece[1][0]][undoSelectedPiece[1][1]] = undoSelectedPieceInside[1];
        main();

    }
    private void undoNumber(){
        if(turn == 'w')
            System.out.println("you have "+(2-totalUndo[0])+" undo moves");
        else
            System.out.println("you have "+(2-totalUndo[1])+" undo moves");
        main();
    }
    private void showMoves(String hasAllString){
        boolean hasAll = false;
        if(hasAllString.contains("-all")) {
            hasAll = true;
        }
        if(hasAll){
            for (moves move : totalMoves) {
                if(move.destroyedPieceName .equals("  ")){
                    System.out.println(move.movedPieceName+" "+move.movedPiece[0]+","+move.movedPiece[1]+" to "+move.destroyedPiece[0]+","+move.destroyedPiece[1]);
                }
                else{
                    System.out.println(move.movedPieceName+" "+move.movedPiece[0]+","+move.movedPiece[1]+" to "+move.destroyedPiece[0]+","+move.destroyedPiece[1]+" destroyed "+move.destroyedPieceName);
                }
            }
        }
        else{
            for (moves move : totalMoves) {
                if(move.movedPieceName.charAt(1) == turn) {
                    if (move.destroyedPieceName .equals ("  ")) {
                        System.out.println(move.movedPieceName+" "+move.movedPiece[0]+","+move.movedPiece[1]+" to "+move.destroyedPiece[0]+","+move.destroyedPiece[1]);
                    }
                    else {
                        System.out.println(move.movedPieceName+" "+move.movedPiece[0]+","+move.movedPiece[1]+" to "+move.destroyedPiece[0]+","+move.destroyedPiece[1]+" destroyed "+move.destroyedPieceName);
                    }
                }
            }

        }
        if(changedBoard) {
            if ((destroyedPiece.charAt(1) == turn)||(destroyedPiece.charAt(1) == ' '))
                System.out.println(movedPiece+" "+(movedFrom[1] + 1)+","+(movedFrom[0] + 1)+" to "+(movedTo[1] + 1)+","+(movedTo[0] + 1));
            else
                System.out.println(movedPiece+" "+(movedFrom[1] + 1)+","+(movedFrom[0] + 1)+" to "+(movedTo[1] + 1)+","+(movedTo[0] + 1)+" destroyed "+destroyedPiece);
        }
        main();
    }
    private void showKilled(String hasAllString){
        boolean hasAll = false;
        if(hasAllString.contains("-all")) {
            hasAll = true;
        }
        if(hasAll){
            for (moves move : totalMoves) {
                if(!move.destroyedPieceName .equals("  ")){
                    System.out.println(move.destroyedPieceName+" killed in spot "+move.destroyedPiece[0]+","+move.destroyedPiece[1]);
                }
            }
            if(changedBoard)
                if(!destroyedPiece .equals("  "))
                    System.out.println(destroyedPiece+" killed in spot "+(movedTo[1]+1)+","+(movedTo[0]+1));

        }
        else{
            for (moves move : totalMoves) {
                if(move.movedPieceName.charAt(1) != turn) {
                    if (!move.destroyedPieceName .equals("  ")) {
                        System.out.println(move.destroyedPieceName+" killed in spot "+move.destroyedPiece[0]+","+move.destroyedPiece[1]);
                    }
                }
            }
        }
        main();


    }
    private void showBoard(){
        for (int column = 7; column >= 0 ; column--) {
            for (int row = 0; row < 8 ; row++) {
                System.out.print(board[row][column]+"|");
            }
            System.out.println();

        }
    }
    private void help(){
        System.out.println("select [x],[y]\n" +
                "deselect\n" +
                "move [x],[y]\n" +
                "next_turn\n" +
                "show_turn\n" +
                "undo\n" +
                "undo_number\n" +
                "show_moves [-all]\n" +
                "show_killed [-all]\n" +
                "show_board\n" +
                "help\n" +
                "forfeit");
        main();
    }
    private void forfeit(){
        if(turn == 'w') {
            Objects.requireNonNull(User.allUsersContainsUsername(whitePlayer)).addSurrenderLosses();
            Objects.requireNonNull(User.allUsersContainsUsername(blackPlayer)).addSurrenderWins();
            System.out.println("you have forfeited");
            System.out.println("player " + blackPlayer + " with color black won");
        }
        else{
            Objects.requireNonNull(User.allUsersContainsUsername(blackPlayer)).addSurrenderLosses();
            Objects.requireNonNull(User.allUsersContainsUsername(whitePlayer)).addSurrenderWins();
            System.out.println("you have forfeited");
            System.out.println("player " + whitePlayer + " with color white won");
        }
        Menu.menu();
    }
    private boolean legalMove(int x,int y){
        if(board[x][y].charAt(1) == board[selectedPiece[0]][selectedPiece[1]].charAt(1))
            return false;
        else if(board[selectedPiece[0]][selectedPiece[1]].charAt(0) == 'K')
            return legalKingMove(x,y);
        else if(board[selectedPiece[0]][selectedPiece[1]].charAt(0) == 'Q')
            return legalQueenMove(x,y);
        else if(board[selectedPiece[0]][selectedPiece[1]].charAt(0) == 'B')
            return legalBishopMove(x,y);
        else if(board[selectedPiece[0]][selectedPiece[1]].charAt(0) == 'N')
            return legalKnightMove(x,y);
        else if(board[selectedPiece[0]][selectedPiece[1]].charAt(0) == 'R')
            return legalRookMove(x,y);
        else if(board[selectedPiece[0]][selectedPiece[1]].charAt(0) == 'P')
            return legalPawnMove(x,y);
        else
            return false;

    }
    private boolean legalKingMove(int x,int y){
        return (Math.abs(x - selectedPiece[0]) < 2) && (Math.abs(y - selectedPiece[1]) < 2);
    }
    private boolean legalQueenMove(int x,int y){
        return legalRookMove(x, y) || legalBishopMove(x, y);
    }
    private boolean legalBishopMove(int x,int y){
        if((x-y) == (selectedPiece[0]-selectedPiece[1])){
            if(x < selectedPiece[0]){
                for (int row = x+1; row < selectedPiece[0]; row++) {
                    int column = -x+y+row;
                    if(!board[row][column] .equals("  "))
                        return false;

                }
            }
            else{
                for (int row = x-1; row > selectedPiece[0]; row--) {
                    int column = -x+y+row;
                    if(!board[row][column] .equals("  "))
                        return false;

                }
            }
            return true;
        }

        else if((x+y) == (selectedPiece[0]+selectedPiece[1])){
            if(x < selectedPiece[0]){
                for (int row = x+1; row < selectedPiece[0]; row++) {
                    int column = x+y-row;
                    if(!board[row][column].equals("  "))
                        return false;

                }
            }
            else{
                for (int row = x-1; row > selectedPiece[0]; row--) {
                    int column = x+y-row;
                    if(!board[row][column].equals("  "))
                        return false;

                }
            }
            return true;

        }
        else
            return false;

    }
    private boolean legalKnightMove(int x,int y){
        return ((x - selectedPiece[0]) * (x - selectedPiece[0])) + ((y - selectedPiece[1]) * (y - selectedPiece[1])) == 5;

    }
    private boolean legalRookMove(int x,int y){
        if(y == selectedPiece[1]){
            if(x < selectedPiece[0]){
                for (int row = x+1; row < selectedPiece[0]; row++) {
                    if(!board[row][y].equals("  "))
                        return false;

                }
            }
            else{
                for (int row = x-1; row > selectedPiece[0]; row--) {
                    if(!board[row][y].equals("  "))
                        return false;

                }
            }
            return true;
        }

        else if((x == selectedPiece[0])){
            if(y < selectedPiece[1]){
                for (int column = y+1; column < selectedPiece[1]; column++) {
                    if(!board[x][column].equals("  "))
                        return false;

                }
            }
            else {
                for (int column = y - 1; column > selectedPiece[1]; column--) {
                    if (!board[x][column].equals("  "))
                        return false;

                }
            }
            return true;
        }
        else
            return false;


    }
    private boolean legalPawnMove(int x,int y){
        if((selectedPiece[1] == 1) && (board[selectedPiece[0]][selectedPiece[1]].charAt(1) == 'w') && ((y - selectedPiece[1]) < 3) && ((y - selectedPiece[1]) > 0) && (x == selectedPiece[0])){
            if((y - selectedPiece[1]) == 2){
                return (board[x][y - 1].equals("  ")) && (board[x][y].equals("  "));

            }
            else
                return board[x][y].equals("  ");

        }

        else if((selectedPiece[1] == 6) && (board[selectedPiece[0]][selectedPiece[1]].charAt(1) == 'b') && ((selectedPiece[1] - y) < 3) && ((selectedPiece[1] - y) > 0) && (x == selectedPiece[0])){
            if((selectedPiece[1] - y) == 2){
                return (board[x][y + 1].equals("  ")) && (board[x][y].equals("  "));
            }
            else
                return board[x][y].equals("  ");

        }
        else if((selectedPiece[1] != 1) && (selectedPiece[1] != 6) && (board[selectedPiece[0]][selectedPiece[1]].charAt(1) == 'w')  && ((y - selectedPiece[1]) == 1) && (x == selectedPiece[0]) && (board[x][y].equals("  ")))
            return true;
        else if((selectedPiece[1] != 1) && (selectedPiece[1] != 6) && (board[selectedPiece[0]][selectedPiece[1]].charAt(1) == 'b')  && ((selectedPiece[1] - y) == 1) && (x == selectedPiece[0]) && (board[x][y].equals("  ")))
            return true;
        else if((board[selectedPiece[0]][selectedPiece[1]].charAt(1) == 'w') && (board[x][y].charAt(1) == 'b') && ((y - selectedPiece[1]) == 1) && (Math.abs(x - selectedPiece[0]) == 1))
            return true;
        else
            return (board[selectedPiece[0]][selectedPiece[1]].charAt(1) == 'b') && (board[x][y].charAt(1) == 'w') && ((selectedPiece[1] - y) == 1) && (Math.abs(x - selectedPiece[0]) == 1);

    }



    private static Matcher getMatcher(String input, String regex){
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(input);
    }
    public void initializeBoard(){
        int row;
        int column;
        row = 0;
        board[0][row] = "Rw";
        board[1][row] = "Nw";
        board[2][row] = "Bw";
        board[3][row] = "Qw";
        board[4][row] = "Kw";
        board[5][row] = "Bw";
        board[6][row] = "Nw";
        board[7][row] = "Rw";
        row = 1;
        board[0][row] = "Pw";
        board[1][row] = "Pw";
        board[2][row] = "Pw";
        board[3][row] = "Pw";
        board[4][row] = "Pw";
        board[5][row] = "Pw";
        board[6][row] = "Pw";
        board[7][row] = "Pw";
        row = 7;
        board[0][row] = "Rb";
        board[1][row] = "Nb";
        board[2][row] = "Bb";
        board[3][row] = "Qb";
        board[4][row] = "Kb";
        board[5][row] = "Bb";
        board[6][row] = "Nb";
        board[7][row] = "Rb";
        row = 6;
        board[0][row] = "Pb";
        board[1][row] = "Pb";
        board[2][row] = "Pb";
        board[3][row] = "Pb";
        board[4][row] = "Pb";
        board[5][row] = "Pb";
        board[6][row] = "Pb";
        board[7][row] = "Pb";

        for (row = 0; row < 8; row++) {
            for (column = 0; column < 8 ; column++) {
                if((column < 6)&&(column > 1))
                    board[row][column] = "  ";
            }
        }
        main();

    }
}


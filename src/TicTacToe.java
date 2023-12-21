import java.util.*;
public class TicTacToe {
    char player;
    char computer;
    Scanner scanner = new Scanner(System.in);
    char[][] board;
    char[][] exBoard = {{'1','2','3'}, {'4','5','6'}, {'7','8','9'}};
    public TicTacToe(){
        board = new char[][]   {{' ',' ',' '},
                                {' ',' ',' '},
                                {' ',' ',' '}};
    }

    public void printBoard(char[][] b){
        System.out.println(b[0][0] + "|"+ b[0][1]+"|"+ b[0][2]);
        System.out.println("-+-+-");
        System.out.println(b[1][0] + "|"+ b[1][1]+"|"+ b[1][2]);
        System.out.println("-+-+-");
        System.out.println(b[2][0] + "|" + b[2][1]+"|"+ b[2][2]);
    }

    public void playerHandler(){
        String who;
        System.out.println("Hello and welcome to TicTacToe!");
        System.out.println("Here some ground rules:");
        System.out.println("This is the board:");
        printBoard(exBoard);
        System.out.println("Every turn you will choose the next move by typing the corresponding number");
        System.out.println("Now you get to pick, do you want X/O?");
        who = scanner.nextLine();

//        boolean n = who.equals("X");
//        boolean p = who.equals("O");

        while (who.equals("X") && who.equals("O")){
            System.out.println("Oh no! please write the letters X or O");
            who = scanner.nextLine();
        }

        System.out.println("Excellent!");
        System.out.println("Now X gets to play first so...");
        if(who.equals("O")){
            player = 'O';
            computer= 'X';
            computerMove();
        }
        else {
            player = 'X';
            computer= 'O';
            playerMove();
        }
    }

    public void computerMove(){
        Random rand = new Random();
        Integer r = rand.nextInt(9)+1;
        while (!validMove(r.toString())){
            r = rand.nextInt(9)+1;
        }

        placeMove(r.toString(), computer);
        if(didWin(computer)){
            computerWon();
        }
        else if(isTie()){
            tie();
        }
        else playerMove();
    }

    public void playerMove(){
        printBoard(board);
        System.out.println("Enter a number to place your move:");
        String m = scanner.nextLine();
        while(!validMove(m)){
            System.out.println("Oh no! this move isn't valid, enter another number:");
            m = scanner.nextLine();
        }

        placeMove(m,player);
        if(didWin(player)){
            playerWon();
        }
        else if(isTie()){
            tie();
        }
        else computerMove();
    }

    public boolean isTie(){
        for(int i=0;i<3;i++){
            for (int j=0;j<3;j++){
                if(board[i][j] == ' ')
                    return false;
            }
        }
        return true;
    }

    public void tie(){
        System.out.println("Its a tie :)");
        System.out.println("Do you want another game?(1-Yes,2-No)");
        String s = scanner.nextLine();
        while (!s.equals("1") && !s.equals("2") ){
            System.out.println("choose a number: (1-Yes,2-No)");
            s = scanner.nextLine();
        }
        if(s.equals("1")){
            board = new char[][] {{' ', ' ', ' '},
                    {' ', ' ', ' '},
                    {' ', ' ', ' '}};
            playerHandler();
        }
        if(s.equals("2")){
            System.out.println("GoodBye! :)");
        }
    }

    public boolean didWin(char x){
        //rows
        if(board[0][0] == board[0][1] && board[0][1] == board[0][2] && board[0][2] == x){
            return true;
        }
        if(board[1][0] == board[1][1] && board[1][1] == board[1][2]&& board[1][2] == x){
            return true;
        }
        if(board[2][0] == board[2][1] && board[2][1] == board[2][2]&& board[2][2] == x){
            return true;
        }
        //columns
        if(board[0][0] == board[1][0] && board[1][0] == board[2][0]&& board[2][0] == x){
            return true;
        }
        if(board[0][1] == board[1][1] && board[1][1] == board[2][1]&& board[2][1] == x){
            return true;
        }
        if(board[0][2] == board[1][2] && board[1][2] == board[2][2]&& board[2][2] == x){
            return true;
        }
        //diagonals
        if(board[0][0] == board[1][1] && board[1][1] == board[2][2]&& board[2][2] == x){
            return true;
        }
        if(board[0][2] == board[1][1] && board[1][1] == board[2][0]&& board[2][0] == x){
            return true;
        }

        //not a win
        return false;
    }

    public void playerWon(){
        printBoard(board);
        System.out.println("Oh yeahhh you won!!");
        System.out.println("Do you want another game?(1-Yes,2-No)");
        String s = scanner.nextLine();
        while (!s.equals("1") && !s.equals("2") ){
            System.out.println("choose a number: (1-Yes,2-No)");
            s = scanner.nextLine();
        }
        if(s.equals("1")){
            board = new char[][] {{' ', ' ', ' '},
                    {' ', ' ', ' '},
                    {' ', ' ', ' '}};
            playerHandler();
        }
        if(s.equals("2")){
            System.out.println("GoodBye! :)");
        }
    }

    public void computerWon(){
        printBoard(board);
        System.out.println("Bummer... you lost :(");
        System.out.println("Do you want another game?(1-Yes,2-No)");
        String s = scanner.nextLine();
        while (!s.equals("1") && !s.equals("2") ){
            System.out.println("choose a number: (1-Yes,2-No)");
            s = scanner.nextLine();
        }
        if(s.equals("1")){
            board = new char[][]{{' ', ' ', ' '},
                    {' ', ' ', ' '},
                    {' ', ' ', ' '}};
            playerHandler();
        }
        if(s.equals("2")){
            System.out.println("GoodBye! :)");
        }
    }

    public boolean validMove(String sm) {
        Integer m = Integer.parseInt(sm);
        if(m<1 || m>9){
            return false;
        }
        switch (sm){
            case "1":
                if(board[0][0] != ' ')
                    return false;
                break;
            case "2":
                if(board[0][1] != ' ')
                    return false;
                break;
            case "3":
                if(board[0][2] != ' ')
                    return false;
                break;
            case "4":
                if(board[1][0] != ' ')
                    return false;
                break;
            case "5":
                if(board[1][1] != ' ')
                    return false;
                break;
            case "6":
                if(board[1][2] != ' ')
                    return false;
                break;
            case "7":
                if(board[2][0] != ' ')
                    return false;
                break;
            case "8":
                if(board[2][1] != ' ')
                    return false;
                break;
            case "9":
                if(board[2][2] != ' ')
                    return false;
                break;
        }

        return true;
    }

    public void placeMove(String m, char who){
        switch (m){
            case "1":
                board[0][0] = who;
                break;
            case "2":
                board[0][1] = who;
                break;
            case "3":
                board[0][2] = who;
                break;
            case "4":
                board[1][0] = who;
                break;
            case "5":
                board[1][1] = who;
                break;
            case "6":
                board[1][2] = who;
                break;
            case "7":
                board[2][0] = who;
                break;
            case "8":
                board[2][1] = who;
                break;
            case "9":
                board[2][2] = who;
                break;
        }


    }

}

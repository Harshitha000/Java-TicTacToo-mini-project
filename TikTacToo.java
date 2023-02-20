import java.util.*;
public class TikTacToo{
    static ArrayList<Integer> playerPositions = new ArrayList<Integer>();
    static ArrayList<Integer> cpuPositions = new ArrayList<Integer>();
    static Scanner sc=new Scanner(System.in);
    //Main Method
    public static void main(String[] args){
        char[][] gameBoard={{' ','|',' ','|',' '},{'-','+','-','+','-'},{' ','|',' ','|',' '},{'-','+','-','+','-'},{' ','|',' ','|',' '}};
        printBoard(gameBoard);
        System.out.println("Choose \n1:2 Player\n2:Player and CPU");
        int opt=sc.nextInt();
        switch(opt){
            case 1:player1AndPlayer2(gameBoard);
                   break;
            case 2:playerAndCpu(gameBoard);
                   break;
            default:System.out.println("Invalid Option");
                    break;
        }        
    }
    //player1AndPlayer2 Function
    public static void player1AndPlayer2(char[][] gameBoard){
        while(true){
            System.out.println("Player1 turn--------");
            System.out.print("Enter Player1 Position(1-9):");
            playerMove(gameBoard,"player");
            String result=checkWinner("player1");
            if(result.length()>0){
                System.out.println(result);
                break;
            }

            System.out.println("Player2 turn--------");
            System.out.print("Enter Player2 Position(1-9):");
            playerMove(gameBoard,"player2");
            result =checkWinner("player2");
            if(result.length()>0){
                System.out.println(result);
                break;
            }
        }
    }
    //playerAndCPU Function
    public static void playerAndCpu(char[][] gameBoard){
        while(true){
            System.out.println("Player turn--------");
            System.out.print("Enter Player Position(1-9):");
            playerMove(gameBoard,"player");
            String result=checkWinner("player");
            if(result.length()>0){
                System.out.println(result);
                break;
            }

            System.out.println("CPU turn--------");
            cpuMove(gameBoard);

            result=checkWinner("cpu");
            if(result.length()>0){
                System.out.println(result);
                break;
            }     
        }
    }
    //playerMove Function
    public static void playerMove(char[][] gameBoard,String user){
        int playerPos=sc.nextInt();
            while(playerPositions.contains(playerPos)||cpuPositions.contains(playerPos)){
                System.out.println("Position Taken!Enter a correct Position");
                System.out.print("Enter Correct Position(1-9):");
                playerPos=sc.nextInt();
            }
            place(gameBoard, playerPos,user);
    }
    //cpuMove function
    public static void cpuMove(char[][] gameBoard){
        Random rand=new Random();
            int cpuPos=rand.nextInt(9) + 1;
            while(playerPositions.contains(cpuPos)||cpuPositions.contains(cpuPos)){
                cpuPos=rand.nextInt(9) + 1;
            }
            place(gameBoard, cpuPos, "cpu");
    }
    //printBoard Function
    public static void printBoard(char[][] gameBoard){
        for(char[] row:gameBoard){
            for(char c:row){
                System.out.print(c);
            }
            System.out.println();
        }
    }
    //Place Function(Placing into the gameboard)
    public static void place(char[][] gameBoard,int pos,String user){
        char symbol=' ';
        if(user.equals("player")){
            symbol='X';
            playerPositions.add(pos);
        }
        else if(user.equals("cpu")||user.equals("player2")){
            symbol='O';
            cpuPositions.add(pos);
        }
        switch(pos){
            case 1:gameBoard[0][0]=symbol;
                   break;
            case 2:gameBoard[0][2]=symbol;
                   break;
            case 3:gameBoard[0][4]=symbol;
                   break;
            case 4:gameBoard[2][0]=symbol;
                   break;
            case 5:gameBoard[2][2]=symbol;
                   break;
            case 6:gameBoard[2][4]=symbol;
                   break;
            case 7:gameBoard[4][0]=symbol;
                   break;
            case 8:gameBoard[4][2]=symbol;
                   break;
            case 9:gameBoard[4][4]=symbol;
                   break;  
            default:
                   break;     
        }
        printBoard(gameBoard);
    }
    //checkWinner Functiom
    public static String checkWinner(String user){
        List toprow= Arrays.asList(1, 2, 3);
        List midrow= Arrays.asList(4, 5, 6);
        List botrow= Arrays.asList(7, 8, 9);
        List leftcol= Arrays.asList(1, 4, 7);
        List midcol= Arrays.asList(2, 5, 8);
        List rightcol= Arrays.asList(3, 6, 9);
        List cross1= Arrays.asList(1, 5, 9); 
        List cross2= Arrays.asList(3, 5, 7);

        List<List> winning= new ArrayList<List>();
        winning.add(toprow);
        winning.add(midrow);
        winning.add(botrow);
        winning.add(leftcol);
        winning.add(midcol);
        winning.add(rightcol);
        winning.add(cross1);
        winning.add(cross2);

        for(List l:winning){
            if(playerPositions.containsAll(l)){
                return "Congratulations "+user+" won!";
            }
            else if(cpuPositions.containsAll(l)){
                return "CPU won!";
                        }
            else if(playerPositions.size() + cpuPositions.size() == 9){
                return "Match Draw!Better luck next time";
            }       
        }
        return "";
    }
}
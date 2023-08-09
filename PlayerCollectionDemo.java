/*
* Name : Azizbek Muminjonov
* ID : U2110207
*/

import  java.util.*;


class Player implements Comparable <Player>
{
    private String  PlayerName;
    private int PlayerID, NoOfGoalScored;


    Player(){}
    Player(int PlayerID, String PlayerName, int NoOfGoalScored)
    {
        this.PlayerID = PlayerID;
        this.PlayerName = PlayerName;
        this.NoOfGoalScored = NoOfGoalScored;
    }

    public int getPlayerID() {
        return PlayerID;
    }

    public void setPlayerID(int playerID) {
        PlayerID = playerID;
    }

    public String getPlayerName() {
        return PlayerName;
    }

    public void setPlayerName(String playerName) {
        PlayerName = playerName;
    }

    public int getNoOfGoalScored() {
        return NoOfGoalScored;
    }

    public void setNoOfGoalScored(int NoOfGoalScored) {
        this.NoOfGoalScored = NoOfGoalScored;
    }

    @Override
    public int compareTo(Player player) {
        if (this.NoOfGoalScored == player.getNoOfGoalScored())
        {
            return 0;
        }
        else if(this.NoOfGoalScored < player.getNoOfGoalScored())
        {
            return 1;
        }
        else
        {
            return -1;
        }
    }
}


public class PlayerCollectionDemo {

    public static void main(String[] args)
    {
        int index, numberOfGoals,ID;
        String Name, enter;
        boolean menu = true;

        Scanner in = new Scanner(System.in);
        ArrayList <Player> players = new ArrayList<Player>();


        while(menu) {
            System.out.println("1. Add a user");
            System.out.println("2. Delete a user");
            System.out.println("3. Display");
            System.out.println("4. Sort");
            System.out.println("0. Exit");
            System.out.println("\n\nChoose an option : ");
            index = in.nextInt();
            switch (index) {
                case 0: {
                    System.out.println("Exiting. . .");
                    menu = false;
                    break;
                }
                case 1: {
                    System.out.print("Input the ID : ");
                    ID = in.nextInt();
                    System.out.print("Input the Name : ");
                    Name = in.next();
                    System.out.print("Input numbers of goals : ");
                    numberOfGoals = in.nextInt();
                    players.add(new Player(ID, Name, numberOfGoals));
                    System.out.println("Done . . .");
                    break;
                }
                case 2: {
                    if (players.isEmpty())
                    {
                        System.out.println("Please input players first . . .");
                        break;
                    }
                    else {
                        System.out.print("Input the ID of Player to delete : ");
                        ID = in.nextInt();
                        players.remove(ID);
                        break;
                    }
                }
                case 3: {
                    if(players.isEmpty())
                    {
                        System.out.println("Please input players first . . .");
                    }
                    else {
                        for (Player player : players) {
                            System.out.println("*******************");
                            System.out.println("ID : " + player.getPlayerID());
                            System.out.println("Name : " + player.getPlayerName());
                            System.out.println("Number of Goals : " + player.getNoOfGoalScored());
                            System.out.println("*******************");
                        }
                    }
                    break;
                }
                case 4:
                {
                    if (players.size() != 0 ) {
                        Collections.sort(players);
                        System.out.println("Sorted . . .");
                    }
                    else
                    {
                        System.out.println("Please input players first . . .");
                    }

                    break;
                }
                default:
                {
                    System.out.println("Wrong input try again T_T . . .");
                }


            }
            System.out.println("");
            System.out.println("");
            System.out.println("Enter anything to continue . . . ");
            enter = in.next();

        }




    }



}

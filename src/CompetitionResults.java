import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.IntStream;

/**
 * Name and ID: Iymen Abdella 40218280
 * Due Date: November 23 2023
 * Assignment: 2
 * Question: Part IV
 * Written by: Iymen Abdella 40218280
 * 
 * Main method to execute from
 */
public class CompetitionResults {
    public static void main (String[] args){
        
        // Part IVa)
        ParticipantList pL = new ParticipantList();
        ParticipantList pL_topThree = new ParticipantList(pL);

        Scanner scan = new Scanner(System.in);

        System.out.println("Input initial Participant information including their name, and list of candies: ");

        // Part IVb) get input information
        while(scan.hasNextLine()){
            String[] inputs = scan.nextLine().trim().split(" ");
            
            //check if scanner reads an empty string, signalling an end of line and exit
            if (inputs[0].equals("")){
                break;
            }
            
            String ParticipantName = inputs[0];
            int[] candy_amounts = new int[10];
            IntStream.range(1, inputs.length).forEach(i -> candy_amounts[i-1] = Integer.parseInt(inputs[i]));
            
            //handle duplicates
            if (pL.contains(ParticipantName)){
                System.out.println("Duplicate entry detected. Adding their candy amounts to existing entry.");
                Participant p = pL.find(ParticipantName);
                int[] amounts = p.getCandyCollection().getamounts();

                IntStream.range(0, amounts.length).forEach(i -> amounts[i] = amounts[i] + candy_amounts[i]);
            }else{ //insert at the front
                pL.addToStart(new Participant(ParticipantName, new Candy_Collection(candy_amounts)));
            }
        }


        // Part IVc)
        System.out.println("Input requests: ");
        ArrayList<String> requests = new ArrayList<String>();

        while(scan.hasNextLine()){
            String req = scan.nextLine().trim();

            //checks if its the last line in the input to exit
            if (req.equals("")){
                break;
            }
            requests.add(req);
        }

        //Process each of the participants and print the outcome whether the participant will be in top three or not.
        for (String s: requests){
            Participant p = pL.find(s);
            String podium="";

            //get podium placement based on order of top 3
            if (Participant.getTopThree().get(0).equals(p.getParticipantName())){
                podium = "GOLD";
            } else if (Participant.getTopThree().get(1).equals(p.getParticipantName())){
                podium = "SILVER";
            } else{
                podium = "BRONZE";
            }

            if (Participant.getTopThree().contains(p.getParticipantName())){

                System.out.println(String.format("Participant %s earned place %s.", s, podium));
            } else{
                System.out.println(String.format("Participant %s is not in the top 3.", s));
            }
        }

        //Part IVd) Accept input of IDs
        while (true){
            System.out.println("Input a participant ID to look up.");
            int input_id = Integer.parseInt(scan.nextLine().trim());

            if (pL.contains(input_id)){
                System.out.println(pL.find(input_id).toString());
            }else{
                System.out.println(String.format("Could not find participant with ID: %d.", input_id));
            }

            System.out.println("Would you like to input another participant ID? (y/n)");
            if (scan.nextLine().trim().toLowerCase().startsWith("n")){
                break;
            }
        }

        // Part IVe)

        // test the insert at index and error catching
        System.out.println(String.format("Input a index insert between [%d, %d]: ", 0, pL.getSize()));
        int index = Integer.parseInt(scan.nextLine().trim());

        System.out.println("Input participant information to create: ");

        String[] inputs = scan.nextLine().trim().split(" ");
        String ParticipantName = inputs[0];
        int[] candy_amounts = new int[10];
        IntStream.range(1, inputs.length).forEach(i -> candy_amounts[i-1] = Integer.parseInt(inputs[i]));

        pL.insertAtIndex(new Participant(ParticipantName, new Candy_Collection(candy_amounts)), index);

        //print to show
        System.out.println(pL.find(ParticipantName).toString());

        System.out.println("Testing delete from start");
        System.out.println(pL.find("EerieEldritch").toString());
        pL.deleteFromStart();
        System.out.println(pL.find("SoulboundSpecter").toString());

        // test isintopthree
        String name1 = "EnchantressEclipse";
        String name2 = "PhantomHex";
        if (pL.find(name1).isInTheTopThree(pL.find(name2))){
            if (Participant.getTopThree().contains(name1)){
                System.out.println(String.format("Both %s and %s are in the top 3", name1, name2));
            } else{
                System.out.println(String.format("Both %s and %s are NOT in the top 3", name1, name2));
            }
        }else {
            if (Participant.getTopThree().contains(name1)){
                System.out.println(String.format("ONLY participant %s is in the top 3", name1));
            } else if (Participant.getTopThree().contains(name2)) {
                System.out.println(String.format("ONLY participant %s is in the top 3", name2));
            }
        }

        // test delete from index
        int index_to_Delete = 1;
        System.out.println("Testing delete at index " + index_to_Delete);
        pL.deleteFromIndex(index_to_Delete);
        System.out.println(String.format("Size after delete %d", pL.getSize()));

        //replace at index
        pL.replaceAtIndex(new Participant(ParticipantName, new Candy_Collection(candy_amounts)), index_to_Delete );

        System.out.println(pL.find("test_participant").toString());

        scan.close();
    }
}

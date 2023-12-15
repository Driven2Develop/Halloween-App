import java.util.ArrayList;

/**
 * Name and ID: Iymen Abdella 40218280
 * Due Date: November 23 2023
 * Assignment: 2
 * Question: Part II
 * Written by: Iymen Abdella 40218280
 * 
 * Participant ID is unique to each participant and enforced with the total participant count. 
 * Names are unqiue and enforced through the @ParticipantList  
 * Implements the Winable interface to determine if a participant is in the top 3 or not.
 */
public class Participant implements Winable {

    private int participantID; 
    private String participantName; 
    private Candy_Collection candyCollection;
    private static int participantCount=0;
    private static ArrayList<String> topThree = new ArrayList<String>();

    /**
     * Part IIa)
     * ID is managed is based off total participant count to enforce uniqueness. 
     * @param name a unique name for participant
     * @param collection number of collected candies
     */
    public Participant(String name, Candy_Collection collection){
        this.participantID = participantCount;
        this.participantName = name;
        this.candyCollection = collection;
        participantCount++;

    }

    /**
     * Part IIb)
     * A new ID is generated for the new object based on total participant count.
     * @param p the participant to copy. 
     */
    public Participant(Participant p){
        this.candyCollection = p.candyCollection;
        this.participantName = p.participantName;
        this.participantID = participantCount;
        participantCount ++;
    }

    /**
     * @return arraylist of top three participants based on monetary value
     */
    public static ArrayList<String> getTopThree(){return topThree;}

    /**
     * Sets the top three names
     * @param new_topThree ArrayList of names of the top 3 participants
     */
    public static void setTopThree(ArrayList<String> new_topThree){topThree = new_topThree; }

    /**
     * 
     * @return ID of participant
     */
    public int getParticipantId(){ return this.participantID; }

    /**
     * 
     * @return participant Name
     */
    public String getParticipantName(){ return this.participantName; }

    /**
     * 
     * @return candy collection object, from which to check amounts
     */
    public Candy_Collection getCandyCollection(){ return this.candyCollection; }

    /**
     * 
     * @param new_Name new name of the participant
     */
    public void setParticipantName(String new_Name){ this.participantName = new_Name; }

    /**
     * 
     * @param collection new candy collection, and their amounts
     */
    public void setCandyCollection(Candy_Collection collection){ this.candyCollection = collection; }

    /**
     * Creates a deep copy of the Participant using the copy constructor
     * Part IIc)
     */
    public Participant clone(){ return new Participant(this); }

    /**
     * that takes in another Participant object P and should return true if P is from the
     * same group as the current participant object, or vise versa; otherwise it returns
     * false. There will be two groups; one with top three and the other with the rest.
     * Part IIe)
     * @param P other participant to check if this and the parameter are in the top three
     */
    @Override
    public Boolean isInTheTopThree(Participant P) {
        if (topThree.contains(P.getParticipantName()) && topThree.contains(this.participantName)){
            return true;
        }else if( (!(topThree.contains(P.getParticipantName()))) && (!(topThree.contains(this.participantName)))){
            return true;
        }else{
            return false;
        }
    }
    
    /**
     * Part IId
     * returns a string representation of the objects ID, name, and collection, returned in the format
     * Participant ID: [ID]
     * ParticipantName: [name]
     * Candy Collection: [array of amounts]
     */
    public String toString(){
        return String.format(
            "Participant ID: %s%nParticipant Name: %s%nCandy Collection: %s%n",
            this.getParticipantId(), this.getParticipantName(), this.getCandyCollection());
    }

    /**
     * Part IId
     * assuming order of Candy collection doesnt matter.
     * Compares the name and the collection.
     */ 
    public boolean equals(Participant p){
        Boolean flag = false;
        if (p == null){
            return flag;
        }
        
        if (this.getParticipantName() == p.getParticipantName() && this.candyCollection.equals(p.candyCollection)){
                flag = true;
            }
        
        return flag;
    }

}

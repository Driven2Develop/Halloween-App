import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * Name and ID: Iymen Abdella 40218280
 * Due Date: November 23 2023
 * Assignment: 2
 * Question: Part III
 * Written by: Iymen Abdella 40218280
 * 
 * Participant ID is unique to each participant and enforced with the total participant count. 
 * Names are unqiue and enforced through the @ParticipantList  
 * Implements the Winable interface to determine if a participant is in the top 3 or not.
 */
public class ParticipantList {

    /**
     * Inner class to encapsulate the participant, and pointer to the next participant in list
     * Part IIIai)
     */
    private class ParticipantNode{
        private Participant participant;
        private ParticipantNode nextParticipantNode;

        /**
         * default constructor 
         * Part IIIaii)
         */
        public ParticipantNode(){
            participant = null;
            nextParticipantNode = null;
        }

        /**
         * parameterized constructor
         * @param p Participant of current node.
         * @param pN Pointer to next node in list
         * Part IIIaiii)
         */
        public ParticipantNode(Participant p, ParticipantNode pN){
            participant = p;
            nextParticipantNode = pN;
        }

        /**
         * copy constructor, copies the attributes of parameter, and returns a deep copy
         * The deep copy has a new ID
         * @param pN node to copy
         * Part IIIaiv)
         */
        public ParticipantNode(ParticipantNode pN){
            this.participant = pN.participant.clone();
            this.nextParticipantNode = pN.nextParticipantNode.clone();
        }

        /**
         * Returns a deep clone of this node using the copy constructor.
         * Part IIIav)
         */
        @Override
        public ParticipantNode clone(){
            return new ParticipantNode(this);
        }

        /**
         * @return this nodes participant
         */
        public Participant getParticipant(){ return this.participant; }

        /** 
         * @return linked node of this participant node
         */
        public ParticipantNode getNextParticipantNode() { return this.nextParticipantNode; }

        /**
         * sets participant of current node
         * @param p participant object to set
         */
        public void setParticipant(Participant p){ this.participant = p;}

        /**
         * sets participant of next node
         * @param p participant object to set
         */
        public void setNextParticipantNode(ParticipantNode p){this.nextParticipantNode = p;}
    }

    private ParticipantNode head; //Part IIIb)
    private int size; // Part IIIc)

    /**
     * default constructor, creates an empty list.
     * Part IIId)
     */ 
    public ParticipantList(){
        head = null;
        size = 0;
    }


    public ParticipantNode getParticipantNode() { return this.head; }
    public int getSize() { return this.size; }

    public void setHead(ParticipantNode p){ this.head = p;}
    public void setSize(int s){this.size = s;}

    /**
     * A copy constructor, which accepts a ParticipantList object and creates a copy of it.
     * Part IIIe)
     */ 
    public ParticipantList(ParticipantList pL){
        this.head = null;
        this.size = pL.size;

        ParticipantNode current = this.head;
        ParticipantNode p = pL.head;
        
        //traverse list and attach a clone
        while(current != null){
            current = p.clone();

            current = current.nextParticipantNode;
            p = p.nextParticipantNode;
        }
    }
    
    /**
     * which accepts only one parameter, an object from
     * Participant class, creates a node with that passed object, and inserts this node at
     * the head of the list.
     * @param p
     * Part IIIf)
     */
    public void addToStart(Participant p){
        ParticipantNode new_head = new ParticipantNode(p, this.head);
        this.head = new_head;
        size++;

        setTopThree();
    }

    /**
     * which accepts two parameters, an object from the
     * Participant class, and an integer representing an index. If the index is not valid (a
     * valid index must have a value between zero and size-1), then the method must throw
     * a NoSuchElementException and terminates the program. If index is valid, then
     * the method creates a node with passed Participant object and inserts this node at
     * the given index. The method must properly handle all special cases.
     * @param p Participant object to insert
     * @param i index where to insert participant, checks if the index is between 0 and the size
     * Part IIIg)
     */
    public void insertAtIndex (Participant p, int i){

        if (i > size || i < 0 ){
            String error_msg = "Invalid Entry: index is out of bounds";
            throw new NoSuchElementException(error_msg);
        }

        ParticipantNode current = head;
        int index = 0;
        
        while (current != null){

            if (index == i){
                break;
            }else{
                current = current.nextParticipantNode;
                index ++;
            }
        }

        ParticipantNode new_node = new ParticipantNode(p, current.nextParticipantNode);
        current.setNextParticipantNode(new_node);
        
        size++;
        setTopThree();
    }

    /**
     * which accepts one int parameter representing 
     * an index. If index is not valid, method must throw a NoSuchElementException
     * and terminate the program. Otherwise, node pointed by that index is deleted from
     * the list. The method must properly handle all special cases.
     * @param i index where to insert participant, checks if the index is between 0 and the size
     * Part IIIh)
     */
    public void deleteFromIndex(int i){
        
        if (i > size || i < 0 ){
            String error_msg = "Invalid Entry: index is out of bounds";
            throw new NoSuchElementException(error_msg);
        }
        
        ParticipantNode current = head;

        int index = 0;
        while (current != null){
            if (index == i-1){
                break;
            }else{
                current = current.nextParticipantNode;
                index ++;
            }
        }

        ParticipantNode to_delete = current.nextParticipantNode;

        current.setNextParticipantNode(to_delete.nextParticipantNode);

        to_delete.setParticipant(null);
        to_delete.setNextParticipantNode(null);

        size--;
        setTopThree();
    }

    /**
     * A method called deleteFromStart(), which deletes the first node in the list (the one
     * pointed by head). All special cases must be properly handled.
     * Exceptional case when the had is null, there is nothing to delete. 
     * Part IIIi)
     */
    public void deleteFromStart(){
        if (head == null){
            return;
        }

        ParticipantNode new_head = head.getNextParticipantNode();
        head.setNextParticipantNode(null);
        head.setParticipant(null);

        head = new_head;
        size--;
        setTopThree();
    }

    /**
     * which accepts two parameters, an object from
     * Participant class, and an integer representing an index. If index is not valid, the
     * method simply returns; otherwise, object in list at passed index must be replaced
     * with the object passed.
     * @param p Participant object to replace
     * @param i index where to replace participant, checks if the index is between 0 and the size
     * Part IIIj)
     */
    public void replaceAtIndex(Participant p, int i){
                
        if (i > size || i < 0 ){
            String error_msg = "Invalid Entry: index is out of bounds";
            throw new NoSuchElementException(error_msg);
        }

        ParticipantNode current = head;
        int index = 0;

        while(current != null){
            if (index == i){
                break;
            }else{
                current = current.nextParticipantNode;
                index ++;
            }
        }
        current.setParticipant(p);
        setTopThree();
    }

    /**
     * Finds the top three participants in the list and updates the static list for Participant
     * uses the uniqueness of the IDs and strings to set the the top 3 participants based on its score. 
     */
    private void setTopThree() {
        ArrayList<String> topThreeNames = new ArrayList<String>();

        ParticipantNode max;
        ParticipantNode current;
        int[] default_candies = {0,0,0,0,0,0,0,0,0,0};

        // traverse the list three times
        for (int j=0; j<3; j++){
            max = new ParticipantNode(new Participant("", new Candy_Collection(default_candies)), null); // max is being initialized to the largest in the list
            current = head;

            // at each traversal look for the max that is not already in the topThreeIDs list
            while(current != null){

                // if the current node is already part of the top three, avoid comparing it to the others. 
                if (topThreeNames.contains(current.getParticipant().getParticipantName())){
                    current = current.nextParticipantNode;
                    continue;
                }

                //check the monetary value of max and current node, if larger, becomes new max
                if (max.getParticipant().getCandyCollection().getMonetaryValue() < current.getParticipant().getCandyCollection().getMonetaryValue()){
                
                    max = current;

                // if the monetary values are equal check their utility values
                }else if (max.getParticipant().getCandyCollection().getMonetaryValue() == current.getParticipant().getCandyCollection().getMonetaryValue()){
                    
                    if (max.getParticipant().getCandyCollection().getTotalUtilityValue() < current.getParticipant().getCandyCollection().getTotalUtilityValue()){
                        max = current;
                    }
                }
                current = current.nextParticipantNode;
            }
            topThreeNames.add(max.getParticipant().getParticipantName());
        }
        Participant.setTopThree(topThreeNames);
    }

    /**
     * which accepts one parameter of type String representing a
     * participantID. This method then searches the list for a participantNode with that
     * participantID. If such an object is found, then method returns a deep copy of that
     * participantNode; otherwise, method returns null. The method must keep track of
     * how many iterations were made before the search finally finds the participant or
     * concludes that it is not in the list.
     * @param id_toFind index where to replace participant, checks if the index is between 0 and the size
     */
    public Participant find(int id_toFind){
        
        if (id_toFind > size || id_toFind < 0 ){
            String error_msg = "Invalid Entry: index is out of bounds";
            throw new NoSuchElementException(error_msg);
        }
        
        ParticipantNode current = head;
        while(current != null){

            if (current.getParticipant().getParticipantId() == id_toFind ){
                return new Participant(current.getParticipant());
            }else{
                //System.out.println(current.participant.toString());
                current = current.getNextParticipantNode();
            }
        }
        return null;
    }

    /**
     * helper method to find participant with a certain name instead of ID
     * if the name doesnt exist, null is returned.
     * Same behavior of find(int)
     * @param name_toFind is the name to search for
     */
    public Participant find(String name_toFind){
        
        ParticipantNode current = head;

        while(current != null){
            if (current.getParticipant().getParticipantName().equals(name_toFind)){
                return new Participant(current.getParticipant());
            }else{
                current = current.nextParticipantNode;
            }
        }
        return null;
    }

    /**
     * Part IIIl)
     * checks if the ID is part of the list
     * @param id_toFind participant ID to search for
     * @return Method returns true if a participant with that participantID is in the list; otherwise, the method returns false.
     */
    public Boolean contains(int id_toFind){
        ParticipantNode current = head;

        while(current != null){

            if (current.getParticipant().getParticipantId() == id_toFind ){
                return true;
            }else{
                current = current.getNextParticipantNode();
            }
        }

        return false;
    }

    /**
     * Part IIIm)
     * Recall that two Participant objects are equal if they have the same values except for the participantID, which is expected to be, different.
     * @param pL participant list to compare to
     * @return true if the two lists contain similar participants; otherwise, the method returns false
     */
    public Boolean equals(ParticipantList pL){
        ParticipantNode current = head;
        ParticipantNode compareToNode = pL.head;

        if (this.size != pL.size){
            return false;
        }
        
        while(current != null){

            if (!(current.equals(compareToNode))){
                return false;
            }

            current = current.nextParticipantNode;
            compareToNode = compareToNode.nextParticipantNode;
        }

        return true;
    }

    /**
     * Same behavior of other contains method except with name
     * helper method checks the participant names to ensure no duplicates
     * @param name_toFind to search through in list
     */ 
    public Boolean contains(String name_toFind){
        ParticipantNode current = head;

        while (current != null){

            if (current.getParticipant().getParticipantName() == name_toFind ){
                return true;
            }else{
                current = current.nextParticipantNode;
            } 
        }
        return false;
    }
}

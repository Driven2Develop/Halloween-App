/**
 * Name and ID: Iymen Abdella 40218280
 * Due Date: November 23 2023
 * Assignment: 2
 * Question: Some general information section
 * Written by: Iymen Abdella 40218280
 * 
 * This candy collection class is responsible for keeping track of the candies collected by the participants.
 * Assumes the names, utilities, and cost are all fixed.
 * Assumed the indexing of each of the candies is fixed as well. 
 */

public class Candy_Collection {

    /**
     * Candies encoded as ac constant. 
     * Constant is intentionally private to avoid privacy leaks, with some simple accessibility methods.   
     */
    private enum Candy{
        FIZZLEBERRY_FIRECRUNCH("Fizzleberry Firecrunch", 8, 2.00),
        GOBLIN_GUMMY_GRINS("Goblin Gummy Grins", 7, 1.50),
        MOONLIGHT_SWIRLS("Moonlight Swirls", 9, 1.75),
        WITCHS_BREW_BONBONS("Witch's Brew Bonbons", 6, 2.50),
        STARLIGHT_SUGAR_SPRINKLES("Starlight Sugar Sprinkles", 7, 1.25),
        DRAGON_SCALE_DELIGHTS("Dragon Scale Delights", 8, 2.25),
        UNICORN_RAINBOW_DROPS("Unicorn Rainbow Drops", 9, 1.75),
        MYSTIC_MARSHMALLOWS("Mystic Marshmallows", 7, 1.50),
        ENCHANTED_CHOCOLATE_TRUFFLES("Enchanted Chocolate Truffles", 9, 2.00),
        FAIRY_FEATHERS("Fairy Feathers", 9, 1.25);

        /**
         * All constructor parameters are fixed
         */
        private Candy(String name, int utility_value, double cost){
            this.name = name;
            this.utility_value = utility_value;
            this.cost = cost;
        }
        
        private String name;
        private int utility_value;
        private double cost;

        /**
         * Simple getters returning the values of each candy
         * @return name of candy
         */
        public String getName() { return name; }

        /**
         * Simple getters returning the values of each candy
         * @return utility value of candy
         */
        public int getUtilityValue() { return utility_value; }
        
        /**
         * Simple getters returning the values of each candy
         * @return cost of candy
         */
        public double getCost() {return cost; }
    }

    /**
     * specifies the indexed value of each candy constant.
    */
    private int[] amounts;
    private double monetary_value=0;
    private int total_utility_value=0;

    /**
     * @param amounts is initialized as an attributes.
     * Monetary value and total utility are calculated immediately based on amounts.
     */
    public Candy_Collection (int[] amounts){
        this.amounts = amounts;
        for (int i=0; i < amounts.length; i++){
            this.monetary_value += amounts[i]*Candy.values()[i].cost;
            this.total_utility_value += amounts[i]*Candy.values()[i].utility_value;
        }
        
    }

    /**
     * @return list of amounts.
     */
    public int[] getamounts() {return this.amounts; }

    /**
     * @return monetary score precalculated
     */
    public double getMonetaryValue(){ return this.monetary_value;}
    /**
     * @return total utility value precalculated
     */
    public int getTotalUtilityValue() { return this.total_utility_value;}


    /**
     * 
     * @param new_amount amounts to set.
     */
    public void setAmounts(int[] new_amount) {this.amounts = new_amount; }

    /**
     * 
     * @param monetary_value new monetary value to set
     */
    public void setMonetaryValue(int monetary_value){this.monetary_value = monetary_value;}

    /**
     * 
     * @param utility_value new utility value to set
     */
    public void setTotalUtilityValue(int utility_value){this.total_utility_value = utility_value;}
    
    /**
     * Does not compare the candy collections because they are constant.
     * @param collection_toCompare candy collection to compare this object to.
     * @return true if the two arrays are equal or false otherwise.
     */
    public boolean equals(Candy_Collection collection_toCompare){
        if (this.amounts.equals(collection_toCompare.getamounts())){
            return true;
        }else{
            return false;
        }
    }

    /**
     * @return a string representation of the Candy_Collection object in the form:
     * Candy 1: amount
     * Candy 2: amount
     * ...
     */
    public String toString(){
        StringBuilder sb = new StringBuilder();

        for (int i=0; i < amounts.length; i++){
            sb.append(String.format("%s: %d", Candy.values()[i].name, amounts[i]));
            sb.append("\n");
        }
        return sb.toString();
    }
}

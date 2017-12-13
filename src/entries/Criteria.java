package entries;

/**
 * Class Criteria
 * Describes a criteria
 * 
 * @author remy
 *
 */
public class Criteria
{
        static int total_criteria;
        String name;
        int place;
        Double weight;
        boolean maximize;

        /**
         * Constructor
         */
        public Criteria()
        {
                this.name = "Constructor";
                this.place = 1000;
                this.weight = (double) 1000;
                this.maximize = true;
                Criteria.total_criteria ++;
        }

        /**
         * Constructor with 4 params
         * @param name
         * @param place
         * @param weight
         * @param maximize
         */
        public Criteria(String name, int place, double weight, boolean maximize)
        {
                this.name = name;
                this.place = place;
                this.weight = weight;
                this.maximize = maximize;
                Criteria.total_criteria ++;
        }

        /**
         * Constructor
         * @param crit
         */
        public Criteria(Criteria crit)
        {
                this.name = crit.name;
                this.place = crit.place;
                this.weight = crit.weight;
                this.maximize = crit.maximize;
                Criteria.total_criteria ++;
        }


        /**
         * getters and setters
         */
        public static int getTotal_criteria() {
                return total_criteria;
        }
        public static void setTotal_criteria(int total_criteria) {
                Criteria.total_criteria = total_criteria;
        }
        public String getName() {
                return name;
        }
        public void setName(String name) {
                this.name = name;
        }
        public int getPlace() {
                return place;
        }
        public void setPlace(int place) {
                this.place = place;
        }
        public Double getWeight() {
                return weight;
        }
        public void setWeight(Double weight) {
                this.weight = weight;
        }
        public boolean getMaximize() {
                return maximize;
        }
        public void setMaximize(boolean maximize) {
                this.maximize = maximize;
        }
}

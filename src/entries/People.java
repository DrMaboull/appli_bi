package entries;

/**
 * Class People
 * Describes a people
 * 
 * @author remy
 *
 */
public class People
{
        static int total_people;
        String name;
        int place;

        /**
         * Constructor
         */
        public People()
        {
                this.name = "Constructor";
                this.place = 1000;
                People.total_people ++;
        }

        /**
         * Constructor with 2 params
         * @param name
         * @param place
         */
        public People(String name, int place)
        {
                this.name = name;
                this.place = place;
                People.total_people ++;
        }

        /**
         * Constructor
         * @param people
         */
        public People(People people)
        {
                this.name = people.name;
                this.place = people.place;
                People.total_people ++;
        }


        /**
         * getters ans setters
         */
        public static int getTotal_people() {
                return total_people;
        }
        public static void setTotal_people(int total_people) {
                People.total_people = total_people;
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
}

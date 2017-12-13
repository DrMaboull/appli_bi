package entries;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.crypto.Data;

/**
 * 
 * it's a table of table we have a list of People, and for each
 * people we list a table of criteria
 * 
 * @author remy 
 * 
 */
public class DataTable {
        List<List<Double>> weight_table;
        List<People> people_list;
        List<Criteria> criteria_list;;

        /**
         * Constructor
         */
        public DataTable() {
                this.weight_table = new ArrayList<List<Double>>();
                this.people_list = new ArrayList<People>();
                this.criteria_list = new ArrayList<Criteria>();
        }


        /**
         * Constructor used in main
         * @param list
         * @param list_people
         * @param list_criteria
         */
        public DataTable(List<List<Double>> list, List<People> list_people, List<Criteria> list_criteria) {
                this.weight_table = list;
                this.people_list = list_people;
                this.criteria_list = list_criteria;
        }

        /**
         * Set cells of the weight table
         * @param index
         * @param list
         */
        public void addInfoLine(int index, List<Double> list) {
                for (int i=0; i<list.size(); i++) {
                        this.weight_table.get(index).add(new Double(list.get(i)));
                }
        }

        /**
         * getters and setters
         */
        public List<List<Double>> getWeight_table() {
                return weight_table;
        }

        public void setWeight_table(List<List<Double>> weight_table) {
                this.weight_table = weight_table;
        }

        public List<People> getPeople_list() {
                return people_list;
        }

        public void setPeople_list(List<People> people_list) {
                this.people_list = people_list;
        }

        public List<Criteria> getCriteria_list() {
                return criteria_list;
        }

        public void setCriteria_list(List<Criteria> criteria_list) {
                this.criteria_list = criteria_list;
        }
}

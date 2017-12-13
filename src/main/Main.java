package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import electre.Electre;
import entries.*;
import promethee.*;

/**
 * Main méthod
 * To lunch Promethée, ElectreIv ans ElectreIs
 * 
 * @author remy
 *
 */
public class Main {

        public static void main(String[] args) {
                
                /**
                 * DESCRIPTION OF THE FILE :
                 * 
                 * 2 PROMETHEE CALL
                 *      - 1 without preference
                 *      - 1 with preference valur
                 *      
                 * 1 ELECTREIV CALL (concordance : 0.65; veto : 2)
                 * 
                 * 1 ELECTREIS CALL (concordance : 0.65; veto : 2, indifference limit : 1.5)
                 */

                // creation of criteria list
                List<Criteria> criteria = new ArrayList<Criteria>();
                criteria.add(new Criteria("safety", 0, 0.15, true));
                criteria.add(new Criteria("political stability", 1, 0.14, true));
                criteria.add(new Criteria("geographical position", 2, 0.11, true));
                criteria.add(new Criteria("inflation", 3, 0.08, false));
                criteria.add(new Criteria("trade barriers", 4, 0.11, false));
                criteria.add(new Criteria("road infrastructure", 5, 0.08, true));
                criteria.add(new Criteria("bank credit conditions", 6, 0.06, true));
                criteria.add(new Criteria("corruption", 7, 0.06, false));
                criteria.add(new Criteria("harbor infrastructure", 8, 0.04, true));
                criteria.add(new Criteria("complete infrastructure", 9, 0.03, true));
                criteria.add(new Criteria("custom controls", 10, 0.03, true));
                criteria.add(new Criteria("railways", 11, 0.03, true));
                criteria.add(new Criteria("air trafic", 12, 0.02, true));
                criteria.add(new Criteria("local suppliers", 13, 0.02, true));
                criteria.add(new Criteria("taxes", 14, 0.02, false));
                criteria.add(new Criteria("country productivity", 15, 0.01, true));
                criteria.add(new Criteria("court effectiveness", 16, 0.01, true));
                criteria.add(new Criteria("anti-monopoly politics", 17, 0.01, true));
                criteria.add(new Criteria("local competition", 18, 0.01, true));
                criteria.add(new Criteria("supply chains", 19, 0.01, true));

                // creation of people list
                List<People> people = new ArrayList<People>();
                people.add(new People("Serbia", 0));
                people.add(new People("Bulgaria", 1));
                people.add(new People("Macedonia", 2));
                people.add(new People("Romania", 3));
                people.add(new People("Greece", 4));
                people.add(new People("Montenegro", 5));
                people.add(new People("Albania", 6));
                people.add(new People("B&H", 7));
                people.add(new People("Croatia", 8));
                people.add(new People("Slovenia", 9));

                // creation and initialization of datatable data with hard numbers
                List<List<Double>> table = new ArrayList<List<Double>>();
                for (int i = 0; i < people.size(); i++)
                        table.add(new ArrayList<Double>(20));

                DataTable data = new DataTable(table, people, criteria);

                data.addInfoLine(0, Arrays.asList(4.0, 2.0, 5.0, 1.0, 4.5, 2.5, 2.5, 3.5, 2.8, 3.0, 3.5, 2.0, 3.0, 4.5,
                                3.0, 3.5, 2.5, 3.0, 4.0, 3.0));
                data.addInfoLine(1, Arrays.asList(3.5, 2.0, 3.0, 4.0, 4.0, 2.0, 3.0, 3.5, 3.8, 3.0, 3.5, 3.0, 4.0, 4.5,
                                3.0, 4.0, 3.0, 3.5, 4.5, 3.0));
                data.addInfoLine(2, Arrays.asList(4.0, 3.0, 2.0, 5.0, 4.5, 3.0, 2.0, 4.5, 3.7, 4.0, 4.5, 2.5, 3.0, 5.0,
                                5.0, 4.0, 3.0, 4.0, 4.5, 3.5));
                data.addInfoLine(3, Arrays.asList(4.0, 2.0, 2.0, 3.0, 5.0, 2.0, 2.5, 4.5, 3.0, 2.5, 4.0, 2.5, 4.0, 4.5,
                                2.0, 4.5, 2.9, 4.0, 5.0, 3.0));
                data.addInfoLine(4, Arrays.asList(4.0, 2.0, 4.0, 4.0, 5.0, 4.0, 2.5, 3.5, 4.0, 4.5, 4.0, 3.0, 5.0, 4.5,
                                2.0, 3.0, 3.0, 4.0, 5.0, 3.5));
                data.addInfoLine(5, Arrays.asList(5.0, 3.5, 3.0, 3.0, 5.0, 3.0, 3.5, 4.0, 3.4, 3.0, 4.5, 3.0, 4.1, 4.5,
                                3.0, 4.0, 4.0, 4.0, 4.0, 4.0));
                data.addInfoLine(6, Arrays.asList(4.5, 3.0, 3.0, 4.0, 5.0, 3.5, 2.5, 4.0, 3.5, 3.5, 4.0, 1.5, 5.0, 4.0,
                                2.0, 5.0, 4.0, 3.5, 4.0, 3.0));
                data.addInfoLine(7, Arrays.asList(4.0, 2.0, 3.0, 5.0, 4.0, 1.5, 2.0, 3.5, 1.6, 2.0, 3.5, 2.0, 2.5, 4.0,
                                3.0, 3.0, 2.0, 3.0, 3.5, 3.0));
                data.addInfoLine(8, Arrays.asList(4.5, 2.0, 4.0, 4.0, 5.0, 5.0, 2.5, 4.0, 4.0, 5.0, 4.0, 3.5, 4.5, 4.5,
                                3.0, 3.5, 3.0, 4.0, 4.0, 3.0));
                data.addInfoLine(9, Arrays.asList(5.0, 3.0, 2.0, 5.0, 5.0, 4.5, 3.0, 5.0, 5.3, 5.0, 5.0, 3.0, 5.0, 5.0,
                                3.0, 4.0, 4.0, 5.0, 5.0, 4.5));

                /**
                 * TESTS PROMETHEE
                 * 
                 * declare a promethee instance
                 * lunch calculs and catch the winner to display him
                 */
                
                 Promethee prom = new Promethee(data, null);
                 prom.calculPromethee();
                 prom.display();
                 
                 System.out.println();System.out.println("/// ------------------------ ///");System.out.println();
                 
                 Promethee prom2 = new Promethee(data, 1.5);
                 prom2.calculPromethee();
                 prom2.display();
                 
                 System.out.println();System.out.println("/// ------------------------ ///");System.out.println();
                 

                /**
                 * TESTS ELECTRE IV
                 * 
                 * declare a electre instance with 0 for indifference limit
                 * lunch calculs and catch the winner to display him
                 */

                Electre electre = new Electre(data, 0.65, Arrays.asList(2.0, 2.0, 2.0, 2.0, 2.0, 2.0, 2.0, 2.0, 2.0,
                                2.0, 2.0, 2.0, 2.0, 2.0, 2.0, 2.0, 2.0, 2.0, 2.0, 2.0), null);
                electre.calculElectre();
                electre.dispay();
                
                System.out.println();System.out.println();System.out.println("/// ------------------------ ///");System.out.println();
                
                
                /**
                 * TESTS ELECTRE IS
                 * 
                 * declare a electre instance with 0 for indifference limit
                 * lunch calculs and catch the winner to display him
                 */

                Electre electre2 = new Electre(data, 0.65, Arrays.asList(2.0, 2.0, 2.0, 2.0, 2.0, 2.0, 2.0, 2.0, 2.0,
                                2.0, 2.0, 2.0, 2.0, 2.0, 2.0, 2.0, 2.0, 2.0, 2.0, 2.0), 1.5);
                electre2.calculElectre();
                electre2.dispay();
        }
}

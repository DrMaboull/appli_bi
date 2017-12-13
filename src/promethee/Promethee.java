package promethee;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import entries.*;

/**
 * Class Promethee
 * used to realize all calculs and displays for promethee method
 * 
 * @author remy
 *
 */
public class Promethee {
        int size;
        List<List<Double>> promethee_table;
        List<Double> phi_plus;
        List<Double> phi_moins;
        List<Double> phi;
        DataTable data;
        Double preference;

        /**
         * Constructor with DataTable and preference value
         * @param data
         * @param preference
         */
        public Promethee(DataTable data, Double preference) {
                this.size = data.getPeople_list().size();
                this.promethee_table = new ArrayList<List<Double>>(this.size);

                List<Double> temp = new ArrayList<Double>();
                for (int j = 0; j < this.size; j++)
                        temp.add(-1.0);

                for (int i = 0; i < this.size; i++)
                        this.promethee_table.add(new ArrayList<Double>(temp));

                this.phi_plus = new ArrayList<Double>();
                this.phi_moins = new ArrayList<Double>();
                this.phi = new ArrayList<Double>();
                this.data = data;
                this.preference = preference;
        }

        
        /**
         * Display fonction
         * display all informations of promethee method
         * call orderByPhi() to display phi(s) informations
         */
        public void display() {
                System.out.println("Affichage de la matrice de promethee");
                Iterator<List<Double>> it = this.promethee_table.iterator();
                List<Double> temp;
                while (it.hasNext()) {
                        temp = it.next();
                        for (Double d : temp) {
                                if (d == null)
                                        System.out.print("----");
                                else if (d == (double) 0)
                                        System.out.print("0.00");
                                else  {
                                        NumberFormat nf = new DecimalFormat("0.##");
                                        System.out.print(nf.format(d));
                                }
                                System.out.print("\t\t");
                        }
                        System.out.println();
                }
                System.out.println("Matrice Phi +");
                System.out.println(this.phi_plus);
                System.out.println("Matrice Phi -");
                System.out.println(this.phi_moins);
                this.orderByPhis();
        }

        
        /**
         * calculPromethee method
         * lunched by main to throw calculs
         */
        public void calculPromethee() {

                // set cell of promethee table
                this.prometheeTableCalcul();

                // phi(s) calculs
                this.phiPlusCalcul();
                this.phiMoinsCalcul();
                this.phiCalcul();
        }

        /**
         *  Promethee loop for all combinaisons of peoples
         */
        public void prometheeTableCalcul() {
                int i = 0, j = 0;

                // each people is compared with all other peoples
                for (i = 0; i < this.size; i++) {
                        for (j = 0; j < this.size; j++) {
                                // if people isn't compared with himself
                                if (i != j) {
                                        this.setValue(i, j,
                                                        this.calculTwoRows(this.data.getWeight_table().get(i),
                                                                        this.data.getWeight_table().get(j),
                                                                        this.data.getCriteria_list()));
                                } else
                                        this.setValue(i, j, null);
                        }
                }
        }

        
        /**
         * the method get
         * @param first
         * @param second
         * @param criterias
         * @return
         */
        public Double calculTwoRows(List<Double> first, List<Double> second, List<Criteria> criterias) {
                Double result = (double) 0;
                int i = 0;

                if (this.preference == null || this.preference == 0.0) {
                        for (i = 0; i < criterias.size(); i++) {
                                if ((criterias.get(i).getMaximize() == true) && (first.get(i) > second.get(i)))
                                        result += criterias.get(i).getWeight();
                                else if ((criterias.get(i).getMaximize() == false) && (first.get(i) < second.get(i)))
                                        result += criterias.get(i).getWeight();
                        }
                        return result;
                }

                for (i = 0; i < criterias.size(); i++) {
                        if ((criterias.get(i).getMaximize() == true) && (first.get(i) > second.get(i))) {
                                if ((first.get(i) - second.get(i)) < this.preference)
                                        result += criterias.get(i).getWeight() * (first.get(i) - second.get(i))
                                        / this.preference;
                                else
                                        result += criterias.get(i).getWeight();

                        } else if ((criterias.get(i).getMaximize() == false) && (first.get(i) < second.get(i))) {
                                if ((second.get(i) - first.get(i)) < this.preference)
                                        result += criterias.get(i).getWeight() * (second.get(i) - first.get(i))
                                        / this.preference;
                                else
                                        result += criterias.get(i).getWeight();
                        }
                        
                        if (result < 0)
                                System.out.println(result);
                                
                }

                return result;
        }

        /**
         * Set value of a square with a double
         * 
         * @param i
         * @param j
         * @param value
         */
        public void setValue(int i, int j, Double value) {
                this.promethee_table.get(i).set(j, value);
        }

        public void phiPlusCalcul() {
                Iterator<List<Double>> it = this.promethee_table.iterator();
                List<Double> current_list;
                Double temp = (double) 0;
                while (it.hasNext()) {
                        temp = (double) 0;
                        current_list = it.next();
                        for (Double d : current_list)
                                if (d != null)
                                        temp += d;
                        this.phi_plus.add(temp);
                }
        }

        public void phiMoinsCalcul() {
                int column = 0, row = 0;
                Double temp = (double) 0;

                for (column = 0; column < this.size; column++) {
                        temp = (double) 0;
                        for (row = 0; row < this.size; row++) {
                                if (row != column)
                                        temp += this.promethee_table.get(row).get(column);
                        }
                        this.phi_moins.add(temp);
                }
        }

        public void phiCalcul() {
                for (int i = 0; i < this.size; i++) {
                        this.phi.add(this.phi_plus.get(i) - this.phi_moins.get(i));
                }
        }

        public void orderByPhis() {
                System.out.print("Best of Phi + : ");
                System.out.println("Individu " + this.phi_plus.indexOf(Collections.max(this.phi_plus)));

                System.out.print("Best of Phi - : ");
                System.out.println("Individu " + this.phi_moins.indexOf(Collections.min(this.phi_moins)));
                
                System.out.println("Le gagnant par promethée est : " + this.data.getPeople_list().get(this.phi.indexOf(Collections.max(this.phi))).getName());
        }

}

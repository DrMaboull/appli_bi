package electre;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import entries.*;

/**
 * Class Electre used to realize all calculs and displays for both electre
 * methods
 * 
 * @author remy
 *
 */
public class Electre {
        int size;
        List<List<Double>> electre_table;
        List<List<Double>> non_mismatch_table;
        List<Double> veto_table;
        List<Integer> core;
        DataTable data;
        Double matching_value;
        Double indifference_limit;

        /**
         * Constructor with DataTable and matching value
         * 
         * @param data
         */
        public Electre(DataTable data, Double matching_value, List<Double> veto_table, Double indifference_limit) {
                this.size = data.getPeople_list().size();
                this.electre_table = new ArrayList<List<Double>>(this.size);
                this.non_mismatch_table = new ArrayList<List<Double>>(this.size);
                this.veto_table = new ArrayList<Double>(veto_table);

                List<Double> temp = new ArrayList<Double>();
                List<Integer> temp2 = new ArrayList<Integer>();
                for (int j = 0; j < this.size; j++) {
                        temp.add(-1.0);
                        temp2.add(1);
                }

                for (int i = 0; i < this.size; i++) {
                        // initialise tables with -1 (null)
                        this.electre_table.add(new ArrayList<Double>(temp));
                        this.non_mismatch_table.add(new ArrayList<Double>(temp));
                }

                this.core = new ArrayList<Integer>(temp2);
                this.data = data;
                this.matching_value = matching_value;
                this.indifference_limit = indifference_limit;
        }

        /**
         * fonction lunched by main throws electre and core calculs
         */
        public void calculElectre() {
                this.electreTableCalcul();
                this.coreCalcul();
        }

        /**
         * electre loop for all combinaisons of peoples call functions to set
         * electre_table and non_mismatch_table
         */
        public void electreTableCalcul() {
                int i = 0, j = 0;

                for (i = 0; i < this.size; i++) {
                        for (j = 0; j < this.size; j++) {
                                if (i != j) {
                                        this.setValue(i, j,
                                                        this.calculTwoRows(this.data.getWeight_table().get(i),
                                                                        this.data.getWeight_table().get(j),
                                                                        this.data.getCriteria_list()));
                                        this.setNonMismatchTableValue(i, j,
                                                        this.calculNonMismatch(this.data.getWeight_table().get(i),
                                                                        this.data.getWeight_table().get(j),
                                                                        this.data.getCriteria_list()));
                                } else {
                                        this.setValue(i, j, null);
                                        this.setNonMismatchTableValue(i, j, null);
                                }
                        }
                }
        }

        /**
         * the method gets 2 peoples list with their weight for each criteria and the
         * ciiteria list the method has to calculate the domination value of first over
         * second different process according to the indifference limit
         * 
         * @param first
         * @param second
         * @param criterias
         * @return
         */
        public Double calculTwoRows(List<Double> first, List<Double> second, List<Criteria> criterias) {
                Double result = (double) 0;
                int i = 0;

                if (this.indifference_limit == null || this.indifference_limit == 0.0) {
                        for (i = 0; i < criterias.size(); i++) {
                                if ((criterias.get(i).getMaximize() == true) && (first.get(i) >= second.get(i)))
                                        result += criterias.get(i).getWeight();
                                else if ((criterias.get(i).getMaximize() == false) && (first.get(i) <= second.get(i)))
                                        result += criterias.get(i).getWeight();
                        }
                } else {
                        for (i = 0; i < criterias.size(); i++) {
                                if (criterias.get(i).getMaximize() == true) {
                                        if (first.get(i) >= second.get(i))
                                                result += criterias.get(i).getWeight();
                                        else if ((second.get(i) - first.get(i)) < this.indifference_limit)
                                                result += criterias.get(i).getWeight()
                                                                * (this.indifference_limit
                                                                                - (first.get(i) - second.get(i)))
                                                                / this.indifference_limit;
                                } else if (criterias.get(i).getMaximize() == false) {
                                        if (first.get(i) <= second.get(i))
                                                result += criterias.get(i).getWeight();
                                        else if ((first.get(i) - second.get(i)) < this.indifference_limit)
                                                result += criterias.get(i).getWeight()
                                                                * (this.indifference_limit
                                                                                - (second.get(i) - first.get(i)))
                                                                / this.indifference_limit;
                                }
                        }
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
                this.electre_table.get(i).set(j, value);
        }

        /**
         * Set value of a square with a double
         * 
         * @param i
         * @param j
         * @param value
         */
        public void setNonMismatchTableValue(int i, int j, Double value) {
                this.non_mismatch_table.get(i).set(j, value);
        }

        /**
         * calcul of non mismatch value for 2 peoples
         * 
         * @param first
         * @param second
         * @param criterias
         * @return
         */
        public Double calculNonMismatch(List<Double> first, List<Double> second, List<Criteria> criterias) {
                int i = 0;

                for (i = 0; i < criterias.size(); i++) {
                        if (criterias.get(i).getMaximize() == true) {
                                if (first.get(i) + this.veto_table.get(i) <= second.get(i))
                                        return (double) 0;
                        } else {
                                if (first.get(i) >= second.get(i) + this.veto_table.get(i))
                                        return (double) 0;
                        }
                }
                return (double) 1;
        }

        /**
         * displaying electre results
         */
        public void dispay() {
                System.out.println("Affichage de la matrice Electre");
                Iterator<List<Double>> it = this.electre_table.iterator();
                List<Double> temp;
                while (it.hasNext()) {
                        temp = it.next();
                        for (Double d : temp) {
                                if (d == null)
                                        System.out.print("----");
                                else if (d == (double) 0)
                                        System.out.print("0.00");
                                else {
                                        NumberFormat nf = new DecimalFormat("0.##");
                                        System.out.print(nf.format(d));
                                }
                                System.out.print("\t\t\t");
                        }

                        System.out.println();
                }

                System.out.println("Affichage de la matrice de non discordance");

                Iterator<List<Double>> it2 = this.non_mismatch_table.iterator();
                List<Double> temp2;
                while (it2.hasNext()) {
                        temp2 = it2.next();
                        for (Double d : temp2)
                                System.out.print(d + "\t");
                        System.out.println();
                }

                System.out.print("Le(s) gagnant(s) par electreIv est(sont) :");
                for (int i = 0; i < this.core.size(); i++) {
                        if (this.core.get(i) == 1) {
                                System.out.print("'" + this.data.getPeople_list().get(i).getName() + "'   ");
                        }
                }
        }

        /**
         * core calcul for all people combinaisons according to electre table and non mismatch table
         * the people is in the core if he's not dominated
         */
        public void coreCalcul() {
                int i = 0, j = 0;

                for (i = 0; i < this.size; i++) {
                        for (j = 0; j < this.size; j++) {
                                if (i != j) {
                                        if (this.electre_table.get(i).get(j) >= this.matching_value
                                                        && this.non_mismatch_table.get(i).get(j) == (double) 1)
                                                this.core.set(j, 0);
                                }
                        }
                }
        }
}

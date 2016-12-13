/**
 * Created by arashparnia on 28/11/2016.
 */



import com.sun.tools.javac.jvm.Gen;
import org.vu.contest.ContestSubmission;
import org.vu.contest.ContestEvaluation;

import java.util.Random;
import java.util.Properties;
import java.util.Vector;
import java.util.Iterator;

public class player1 implements ContestSubmission
{

    Random rnd_;
    ContestEvaluation evaluation_;
    private int evaluations_limit_;

    public player1()
    {
        rnd_ = new Random();
    }

    public void setSeed(long seed)
    {
        // Set seed of algortihms random process
        rnd_.setSeed(seed);
    }

    public void setEvaluation(ContestEvaluation evaluation)
    {
        // Set evaluation problem used in the run
        evaluation_ = evaluation;

        // Get evaluation properties
        Properties props = evaluation.getProperties();
        // Get evaluation limit
        evaluations_limit_ = Integer.parseInt(props.getProperty("Evaluations"));
        // Property keys depend on specific evaluation
        // E.g. double param = Double.parseDouble(props.getProperty("property_name"));
        boolean isMultimodal = Boolean.parseBoolean(props.getProperty("Multimodal"));
        boolean hasStructure = Boolean.parseBoolean(props.getProperty("Regular"));
        boolean isSeparable = Boolean.parseBoolean(props.getProperty("Separable"));

        // Do sth with property values, e.g. specify relevant settings of your algorithm
        if(isMultimodal){
            // Do sth
        }else{
            // Do sth else
        }
    }

    public void run()
    {
        // Run your algorithm here


        int evals = 0;

        // init Population
        Population pop = new Population();

        pop.setSeed(1);
        evaluations_limit_ =20;


        // calculate fitness
        while(evals < evaluations_limit_ || evaluation_.getFinalResult() < 9  ){
            int c = 1;
            pop.setPopulation_vector(pop.selection_tournament(pop.getPopulation_vector(), true));
            pop.setPopulation_vector(pop.crossover(pop.getPopulation_vector()));
            pop.setPopulation_vector(pop.mutate(pop.getPopulation_vector()));

            Genome[] population =  pop.getPopulation_array();

            for (Genome g : population){

                    System.out.println("GENE " + g.toString());

                    Double fittness = (double) evaluation_.evaluate(g.getGenome());
                    System.out.println(c+" "  +fittness);
                    c++;
                    g.setFitness(fittness);
                    g.setEvaluated(true);
                    evals++;

                if (evaluation_.getFinalResult() > 9 || evals > evaluations_limit_) break;
            }

            pop.setPopulation_array(population);

            System.out.println("--------------------------------------------------------------------------");
            System.out.println("--------------------------------------------------------------------------");
            System.out.println("--------------------------------------------------------------------------");
            System.out.println("--------------------------------------------------------------------------");


            System.out.println(" SIZE OF VECOTR " + pop.getPopulation_vector().size());
            Iterator<Genome> itr = pop.getPopulation_vector().iterator();
            while(itr.hasNext()){
                System.out.println(itr.next().toString());

            }

        }
        System.out.println(evals + " population final result " + evaluation_.getFinalResult());
    }
}



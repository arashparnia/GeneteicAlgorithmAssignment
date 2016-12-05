/**
 * Created by arashparnia on 28/11/2016.
 */



import org.vu.contest.ContestSubmission;
import org.vu.contest.ContestEvaluation;

import java.util.Random;
import java.util.Properties;

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
        // calculate fitness
        while(evals < evaluations_limit_){

            pop.mutate();

            for (Genome g : pop.getPopulation_vector()){
                if (!g.isEvaluated()) {
                    Double fittness = (double) evaluation_.evaluate(g.getGenome());
                    g.setFitness(fittness);
                    g.setEvaluated(true);
                    evals++;
                }
                if (evaluation_.getFinalResult() > 9.9 || evals > evaluations_limit_) break;

            }
            pop.selection_tournament();

            System.out.println(evals + " evaluation with  highest fittness at "+ pop.get_champion().getFitness() );
//
            // Select parents
            // Apply crossover / mutation operators
            //double child[] = {0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0};
            // Check fitness of unknown fuction
            //Double fitness = (double) evaluation_.evaluate(child);
            //evals++;
            // Select survivors
        }
        System.out.println(evals + " population final result "+evaluation_.getFinalResult());
    }
}


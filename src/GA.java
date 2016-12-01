/**
 * Created by arashparnia on 28/11/2016.
 */



import com.sun.org.apache.bcel.internal.generic.POP;
import org.vu.contest.ContestSubmission;
import org.vu.contest.ContestEvaluation;

import java.util.Random;
import java.util.Properties;
import java.util.Vector;

public class GA implements ContestSubmission
{

    Random rnd_;
    ContestEvaluation evaluation_;
    private int evaluations_limit_;

    public GA()
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
        System.out.println(evals);
        // init Population
        Population pop = new Population();
        double champion[] =  {0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0};
        double candidate[] = {0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0};
        double highestFittness = -999999 ;
        // calculate fitness
        while(evals<evaluations_limit_){

            pop.mutate();

            for (Genome g : pop.getPopulation()){
                evals++;
//                System.out.print(g.getGene(0));
                System.arraycopy(g.getGenome(),0,candidate,0,10);
                Double fittness = (double) evaluation_.evaluate(candidate);
                g.setFitness(fittness);
                if (fittness > highestFittness) {
                    highestFittness = fittness;
                    System.arraycopy(g.getGenome(),0,champion,0,10);
                }
            }
            System.out.println("population evaluated with fittness :  " + highestFittness );
            System.out.println("Champion :  " + champion.toString());
            pop.selection_tournament();


            // Select parents
            // Apply crossover / mutation operators
            //double child[] = {0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0};
            // Check fitness of unknown fuction
            //Double fitness = (double) evaluation_.evaluate(child);
            //evals++;
            // Select survivors
        }

    }
}
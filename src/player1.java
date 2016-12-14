/**
 * Created by arashparnia on 28/11/2016.
 */



import com.sun.tools.javac.jvm.Gen;
import org.vu.contest.ContestSubmission;
import org.vu.contest.ContestEvaluation;

import java.util.*;

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
//        evaluations_limit_ =20;


        // calculate fitness
        while(evals < evaluations_limit_ || evaluation_.getFinalResult() < 9  ){
            int c = 1;
//            pop.selection_tournament();
            pop.mutate();

            Vector<Genome> population =  new Vector<Genome>();
            population.addAll((Collection<? extends Genome>) pop.getPopulation_vector().clone());

            if (evaluation_.getFinalResult() > 9.9 || evals > evaluations_limit_-GAParam.population_size) break;
            for (int i = 0 ;i < 10;i++) {
                Genome g  = population.elementAt(i);

                System.out.println(g.toString());

                Double fittness = (double) evaluation_.evaluate(g.getGenome());
                System.out.println("evaluation : " + evals+ " Genome " + c + "  fittness " + fittness);
                c++;
                g.setFitness(fittness);
                g.setEvaluated(true);
                evals++;


            }

            System.out.println("--------------------------------------------------------------------------");
            System.out.println("--------------------------------------------------------------------------");
            System.out.println("--------------------------------------------------------------------------");
            System.out.println("--------------------------------------------------------------------------");


//            System.out.println(" SIZE OF VECOTR " + pop.getPopulation_vector().size());
//            itr = pop.getPopulation_vector().iterator();
//            while(itr.hasNext()){
//                System.out.println(itr.next().toString());
//
//            }

        }
        System.out.println(evals + " population final result " + evaluation_.getFinalResult());
    }
}



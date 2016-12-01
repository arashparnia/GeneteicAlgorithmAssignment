import java.util.Arrays;
import java.util.Random;

/**
 * Created by arashparnia on 01/12/2016.
 */
public class Genome {
    private static final int SIZE = 10;
    private double[] genome = {0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0};
    private double fitness;
    private double mutation_rate = 0;
    private double mutation_pobability = 0;



    //default constructor
    public Genome() {

    }

    public Genome(double fitness, double mutation_rate, double mutation_pobability) {
        this.fitness = fitness;
        this.mutation_rate = mutation_rate;
        this.mutation_pobability = mutation_pobability;
    }
    //clone constructor
    public Genome(double[] genome) {
        this.genome = genome;
    }




    public double[] getGenome() {
        return genome;
    }

    public void setGenome(double[] genome) {
        this.genome = genome;
    }



    public double getMutationRate() {
        return mutation_rate;
    }

    public void setMutationRate(double mutationRate) {
        this.mutation_rate = mutationRate;
    }

    public double getMutationr_pobability() {
        return mutation_pobability;
    }

    public void setMutationr_pobability(double mutationr_pobability) {
        this.mutation_pobability = mutationr_pobability;
    }

    public double getFitness() {
        return fitness;
    }

    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

    public void mutate(){

        Random rand = new Random();

        for (int index = 0 ; index <10 ; index++) {
            double probibility = Math.abs (rand.nextDouble());
            if (mutation_pobability > probibility) {
                double random_mutation_value = rand.nextDouble();
                double actual_mutation_value =  random_mutation_value *  (mutation_rate);
                this.setGene(index, this.getGene(index)  +actual_mutation_value);
            }
        }
   }


   // private  methods
    private double getGene(int index) {
        return genome[index];
    }

    private void setGene(int index,double gene) {
        this.genome[index] = gene;
    }

    @Override
    public String toString() {
        return "Genome{" +
                "genome=" + Arrays.toString(genome) +
                ", fitness=" + fitness +
                ", mutation_rate=" + mutation_rate +
                ", mutation_pobability=" + mutation_pobability +
                '}';
    }
}

import java.util.Arrays;
import java.util.Random;

/**
 * Created by arashparnia on 01/12/2016.
 */
public class Genome implements Comparable<Genome> , GAParam{
    private static final int genome_size = GAParam.genome_size;
    private double[] genome = GAParam.genome;
    private double fitness  = GAParam.fitness;
    private double mutation_rate = GAParam.mutation_rate;
    private double mutation_probability_genome = GAParam.mutation_probability_genome;
    private boolean evaluated = false;




    //default constructor
    public Genome() {

    }

    public Genome(double fitness, double mutation_rate, double mutation_probability) {
        this.fitness = fitness;
        this.mutation_rate = mutation_rate;
        this.mutation_probability_genome = mutation_probability;
    }
    public Genome(double fitness, double mutation_rate, double mutation_probability, boolean mutate) {
        this.fitness = fitness;
        this.mutation_rate = mutation_rate;
        this.mutation_probability_genome = mutation_probability;
        if (mutate) mutate();
    }

    //clone constructor
    public Genome(Genome g) {
        this.setGenome(g.getGenome());
        this.setFitness(g.getFitness());
        this.setMutationRate( g.getMutationRate());
        this.setMutationr_pobability(g.getMutationr_pobability());
    }




    public double[] getGenome() {
        return genome;
    }

    public void setGenome(double[] genome) {
        this.genome = genome;
    }

    public double getGene(int index) {
        return genome[index];
    }

    public void setGene(int index,double gene) {
        this.genome[index] = gene;
    }

    public double getMutationRate() {
        return mutation_rate;
    }

    public void setMutationRate(double mutationRate) {
        this.mutation_rate = mutationRate;
    }

    public double getMutationr_pobability() {
        return mutation_probability_genome;
    }

    public void setMutationr_pobability(double mutation_pobability_genome) {
        this.mutation_probability_genome = mutation_pobability_genome;
    }

    public double getFitness() {
        return fitness;
    }

    public void setFitness(double fitness) {

        this.fitness = fitness;
    }


    public boolean isEvaluated() {
        return evaluated;
    }

    public void setEvaluated(boolean evaluated) {
        this.evaluated = evaluated;
    }

    public void mutate(){
        Random rand = new Random();

        for (int index = 0 ; index <GAParam.genome_size ; index++) {
            if (Math.abs(rand.nextDouble()) < mutation_probability_genome ) {
                double actual_mutation_value =  rand.nextDouble() *  (mutation_rate);
                this.setGene(index, this.getGene(index)  + actual_mutation_value);

            }
        }
   }




    @Override
    public String toString() {
        return "Genome{" +
                "genome=" + Arrays.toString(genome) +
                ", fitness=" + fitness +
                ", mutation_rate=" + mutation_rate +
                ", mutation_pobability=" + mutation_probability_genome +
                '}';
    }

    @Override
    public int compareTo(Genome o) {
        return Double.compare(this.getFitness(),o.getFitness()) ;
    }
}

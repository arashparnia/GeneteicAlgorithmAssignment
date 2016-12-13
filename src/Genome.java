import java.util.Arrays;
import java.util.Random;

/**
 * Created by arashparnia on 01/12/2016.
 */
public class Genome implements Comparable<Genome> , GAParam{


    private static final int genome_size = GAParam.genome_size;
    private double[] genome ;
    private double fitness  = GAParam.fitness;
    private double mutation_rate = GAParam.mutation_rate;
    private double mutation_probability_genome = GAParam.mutation_probability_genome;
    private boolean evaluated = false;


    public void setSeed(long seed)
    {
        // Set seed of algortihms random process
        rnd_.setSeed(seed);
    }

    //default constructor
    public Genome() {
        this.genome = new double[10];
        this.genome = GAParam.genome;
        this.fitness = Double.MIN_VALUE;
        this.mutation_rate = GAParam.mutation_rate;
        this.mutation_probability_genome = GAParam.mutation_probability_genome;
        this.evaluated = false;
    }

    public Genome(double fitness, double mutation_rate, double mutation_probability) {
        this.genome = new double[10];
        this.genome = GAParam.genome;
        this.fitness = fitness;
        this.mutation_rate = mutation_rate;
        this.mutation_probability_genome = mutation_probability;
    }
    public Genome(double fitness, double mutation_rate, double mutation_probability, boolean random) {
        this.genome = new double[10];
        this.fitness = fitness;
        this.mutation_rate = mutation_rate;
        this.mutation_probability_genome = mutation_probability;
        if (random)
            for(int i = 0 ; i < GAParam.genome_size;i++) {
                this.setGene(i, Math.abs(rnd_.nextGaussian()));
//                this.setGene(i, 0);
//                System.out.print(this.getGene(i) + " ");
            }
//        System.out.println();
    }

    //clone constructor
    public Genome(Genome g) {
        this.setGenome(g.getGenome());
        this.setFitness(g.getFitness());
        this.setMutationRate( g.getMutationRate());
        this.setMutationr_pobability(g.getMutationr_pobability());
        this.setEvaluated(g.evaluated);
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

        for (int index = 0 ; index <GAParam.genome_size ; index++) {
            if (Math.abs(rnd_.nextDouble()) < mutation_probability_genome ) {
                double actual_mutation_value =  rnd_.nextDouble() *  (mutation_rate);
                this.setGene(index, this.getGene(index)  + actual_mutation_value);
                this.setEvaluated(false);

            }
        }
   }


    @Override
    public String toString() {
        String s = "";
        s = "Genome{" +
                "genome=" + Arrays.toString(genome) +
                ", fitness=" + fitness +
                ", mutation_rate=" + mutation_rate +
                ", mutation_pobability=" + mutation_probability_genome +
                '}';

        return s;
    }

    @Override
    public int compareTo(Genome o) {
        return Double.compare(fitness , o.getFitness()) ;
    }
}

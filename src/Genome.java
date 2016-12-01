import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

/**
 * Created by arashparnia on 01/12/2016.
 */
public class Genome implements Comparable<Genome> , GAParam{
    private static final int genome_size = GAParam.genome_size;
    private double[] genome = GAParam.genome;
    private double fitness  = GAParam.fitness;
    private int age = GAParam.age;
    private double mutation_rate = GAParam.mutation_rate;
    private double mutation_pobability_genome = GAParam.mutation_pobability_genome;




    //default constructor
    public Genome() {

    }

    public Genome(double fitness, double mutation_rate, double mutation_pobability) {
        this.fitness = fitness;
        this.mutation_rate = mutation_rate;
        this.mutation_pobability_genome = mutation_pobability;
    }
    public Genome(double fitness, double mutation_rate, double mutation_pobability, boolean mutate) {
        this.fitness = fitness;
        this.mutation_rate = mutation_rate;
        this.mutation_pobability_genome = mutation_pobability;
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
        return mutation_pobability_genome;
    }

    public void setMutationr_pobability(double mutationr_pobability_genome) {
        this.mutation_pobability_genome = mutationr_pobability_genome;
    }

    public double getFitness() {
        return fitness;
    }

    public void setFitness(double fitness) {
        setAge(getAge()+1);
        this.fitness = fitness;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void mutate(){
        Random rand = new Random();

        for (int index = 0 ; index <10 ; index++) {
            double probibility = Math.abs (rand.nextDouble());
            if (mutation_pobability_genome > probibility) {
                double random_mutation_value = rand.nextDouble();
                double actual_mutation_value =  random_mutation_value *  (mutation_rate);
                if (rand.nextBoolean())
                    this.setGene(index, this.getGene(index)  + actual_mutation_value);
                else
                    this.setGene(index, this.getGene(index)  - actual_mutation_value);
            }
        }
   }




    @Override
    public String toString() {
        return "Genome{" +
                "genome=" + Arrays.toString(genome) +
                ", fitness=" + fitness +
                ", mutation_rate=" + mutation_rate +
                ", mutation_pobability=" + mutation_pobability_genome +
                '}';
    }

    @Override
    public int compareTo(Genome o) {
        return Double.compare(this.getFitness(),o.getFitness()) ;
    }
}

import java.util.Random;
import java.util.Vector;

/**
 * Created by arashparnia on 01/12/2016.
 */
public class population {
    private Vector<Genome> population = new Vector<Genome>();
    private int size  = 0;
    private double crossover_uniform_probibility = 0.5;



    public population() {
    }


    public population(Vector<Genome> population) {
        this.population = population;
    }

    //spawn population with size
    public population(int size,double mutation_rate, double mutation_probibility){
        this.size = size;
        for (int  i =0; i < size;i++){
            population.add(new Genome(0, mutation_rate, mutation_probibility));
        }
    }

    //online spawn
    //spawn population with the mutation of a specifit genome
    public population(Genome g,int size,double mutation_rate, double mutation_probibility){
        this.size = size;
        for (int  i =0; i < size;i++){
            g.setMutationRate(mutation_rate);
            g.setMutationr_pobability(mutation_probibility);
            if (i > 0) g.mutate(); // skips mutation for the elite
            population.add(g);
        }
    }




    public double getCrossover_uniform_probibility() {
        return crossover_uniform_probibility;
    }

    public void setCrossover_uniform_probibility(double crossover_uniform_probibility) {
        this.crossover_uniform_probibility = crossover_uniform_probibility;
    }




    public Genome crossover_uniform(Genome parent1 , Genome parent2){
        Random rand = new Random();
        double mutaion_rate;
        double mutation_probibility;
        if (rand.nextBoolean()) mutaion_rate = parent1.getMutationRate(); else mutaion_rate = parent2.getMutationRate();
        if (rand.nextBoolean()) mutation_probibility = parent1.getMutationr_pobability(); else mutation_probibility = parent2.getMutationr_pobability();
        Genome child = new Genome(0,mutaion_rate,mutation_probibility);

        for (int i = 0 ; i <10 ; i++){
            if (rand.nextDouble() > crossover_uniform_probibility){
                if (rand.nextBoolean()) child.setGene(i,parent1.getGene(i)); else child.setGene(i,parent2.getGene(i));
            }
        }
        return child;
    }

    public Genome crossover_single_point(Genome parent1 , Genome parent2){
        Random rand = new Random();
        double mutaion_rate;
        double mutation_probibility;
        if (rand.nextBoolean()) mutaion_rate = parent1.getMutationRate(); else mutaion_rate = parent2.getMutationRate();
        if (rand.nextBoolean()) mutation_probibility = parent1.getMutationr_pobability(); else mutation_probibility = parent2.getMutationr_pobability();
        Genome child = new Genome(0,mutaion_rate,mutation_probibility);

        int crossing_point = rand.nextInt(10);

        for (int i = 0 ; i <10 ; i++){
                if (i < crossing_point) child.setGene(i,parent1.getGene(i)); else child.setGene(i,parent2.getGene(i));
        }
        return child;
    }



}

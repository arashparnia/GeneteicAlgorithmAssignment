import java.util.Collections;
import java.util.Random;
import java.util.Vector;

import static oracle.jrockit.jfr.events.Bits.intValue;

/**
 * Created by arashparnia on 01/12/2016.
 */
public class Population implements GAParam{
    private Vector<Genome> population_vector = new Vector<Genome>();
    private int population_size = 0;
    private double crossover_uniform_probibility = GAParam.crossover_uniform_probability;
    private double tournoment_winner_percentage = GAParam.tournament_winner_percentage;
    private double mutation_pobability_population = GAParam.mutation_probability_population;


    //CONSTRUCTORS
    public Population() {
        for (int i = 0; i < GAParam.population_size; i++){
            population_vector.add(new Genome(-999999, mutation_rate, mutation_pobability_population,true));
            this.population_size++;
        }
    }


    public Population(Vector<Genome> population) {
        this.population_vector = population;
    }

    //spawn Population with population_size
    public Population(int population_size, double mutation_rate, double mutation_probibility_genome){
        for (int i = 0; i < population_size; i++){
            population_vector.add(new Genome(-999999, mutation_rate, mutation_probibility_genome,true));
            this.population_size++;
        }
    }

    //online spawn
    //spawn Population with the mutation of a specific genome
    public Population(Genome g, int population_size, double mutation_rate, double mutation_probibility){
        for (int i = 0; i < population_size; i++){
            g.setMutationRate(mutation_rate);
            g.setMutationr_pobability(mutation_probibility);
            if (i > 0) g.mutate(); // skips mutation for the elite
            population_vector.add(g);
            this.population_size++;
        }
    }



    //SETTER AND GETTERS

    public Vector<Genome> getPopulation_vector() {
        return population_vector;
    }

    public void setPopulation_vector(Vector<Genome> population_vector) {
        this.population_vector = population_vector;
    }

    public double getCrossover_uniform_probibility() {
        return crossover_uniform_probibility;
    }

    public void setCrossover_uniform_probibility(double crossover_uniform_probibility) {
        this.crossover_uniform_probibility = crossover_uniform_probibility;
    }
    public double getTournoment_winner_percentage() {
        return tournoment_winner_percentage;
    }

    public void setTournoment_winner_percentage(double tournoment_winner_percentage) {
        this.tournoment_winner_percentage = tournoment_winner_percentage;
    }

    public int getPopulation_size() {
        return population_vector.size();
    }



    public void mutate(){
        Random rand = new Random();
        for (Genome g : population_vector){
            if (rand.nextDouble() < mutation_pobability_population) {
                g.mutate();
                g.setEvaluated(false);
            }
        }
    }

    public Genome[] crossover_uniform(Genome parent1 , Genome parent2){
        Random rand = new Random();
        double mutation_rate;
        double mutation_probability;
        if (rand.nextBoolean()) mutation_rate = parent1.getMutationRate(); else mutation_rate = parent2.getMutationRate();
        if (rand.nextBoolean()) mutation_probability = parent1.getMutationr_pobability(); else mutation_probability = parent2.getMutationr_pobability();
        Genome child1 = new Genome(-999999,mutation_rate,mutation_probability);
        Genome child2 = new Genome(-999999,mutation_rate,mutation_probability);

        for (int i = 0 ; i <GAParam.genome_size ; i++){
            if (rand.nextDouble() < crossover_uniform_probibility){
                if (rand.nextBoolean()) {
                    child1.setGene(i,parent1.getGene(i));
                    child2.setGene(i,parent2.getGene(i));
                }
                else {
                    child1.setGene(i,parent2.getGene(i));
                    child2.setGene(i,parent1.getGene(i));
                }

            }
        }
        Genome[] children =  {child1,child2};
        return children;
    }

    public Genome crossover_single_point(Genome parent1 , Genome parent2){
        Random rand = new Random();
        double mutation_rate;
        double mutation_probability;
        if (rand.nextBoolean()) mutation_rate = parent1.getMutationRate(); else mutation_rate = parent2.getMutationRate();
        if (rand.nextBoolean()) mutation_probability = parent1.getMutationr_pobability(); else mutation_probability = parent2.getMutationr_pobability();
        Genome child = new Genome(-999999,mutation_rate,mutation_probability);

        int crossing_point = rand.nextInt(10);

        for (int i = 0 ; i <10 ; i++){
                if (i < crossing_point) child.setGene(i,parent1.getGene(i)); else child.setGene(i,parent2.getGene(i));
        }
        return child;
    }


    public void selection_tournament(){
        //tournament selection
        Collections.sort(population_vector);

        int c = 1;
        for (Genome ggg : population_vector){

            System.out.println(c + "     " +ggg.getFitness());
            c++;
        }


        int top = intValue (population_size * tournoment_winner_percentage);


        Vector<Genome> children = new Vector<Genome>();


        for (int i = population_size-1 ; i > population_size-top-1; i-=2){
            Genome[] ch = crossover_uniform(population_vector.elementAt(i), population_vector.elementAt(i-1));
            children.addElement(ch[0]);
            children.addElement(ch[1]);
        }

        for (int i = 0 ; i < top;i++) {
            population_vector.setElementAt(children.get(i),i);
//            population_vector.addElement(children.get(i));
        }

//        for (int i = 0 ; i < children.size();i++) {
//        }

    }


    public Genome get_champion(){
        Genome champ = new Genome(-999999,0,0);
        for (Genome g: population_vector)
        {
            if (g.getFitness() > champ.getFitness()) {
                champ.setGenome(g.getGenome());
                champ.setFitness(g.getFitness());
            }
        }
        return champ;
    }


    @Override
    public String toString() {
        return "Population{" +
                "Population=" + population_vector +
                '}';
    }
}

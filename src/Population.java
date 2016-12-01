import java.util.Collections;
import java.util.Random;
import java.util.Vector;

import static oracle.jrockit.jfr.events.Bits.intValue;

/**
 * Created by arashparnia on 01/12/2016.
 */
public class Population implements GAParam{
    private Vector<Genome> population_vector = new Vector<Genome>();
    private int population_size = GAParam.population_size;
    private double crossover_uniform_probibility = GAParam.crossover_uniform_probibility;
    private double tournoment_winner_percentage = GAParam.tournoment_winner_percentage;
    private double mutation_pobability_population = GAParam.mutation_pobability_population;


    //CONSTRUCTORS
    public Population() {
        for (int i = 0; i < population_size; i++){
            population_vector.add(new Genome(0, mutation_rate, mutation_pobability_population,true));
        }
    }


    public Population(Vector<Genome> population) {
        this.population_vector = population;
    }

    //spawn Population with population_size
    public Population(int population_size, double mutation_rate, double mutation_probibility_genome){
        this.population_size = population_size;
        for (int i = 0; i < population_size; i++){
            population_vector.add(new Genome(0, mutation_rate, mutation_probibility_genome,true));
        }
    }

    //online spawn
    //spawn Population with the mutation of a specific genome
    public Population(Genome g, int population_size, double mutation_rate, double mutation_probibility){
        this.population_size = population_size;
        for (int i = 0; i < population_size; i++){
            g.setMutationRate(mutation_rate);
            g.setMutationr_pobability(mutation_probibility);
            if (i > 0) g.mutate(); // skips mutation for the elite
            population_vector.add(g);
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
            if (rand.nextDouble() > mutation_pobability_population) {
                g.mutate();
            }
        }
    }

    public Genome crossover_uniform(Genome parent1 , Genome parent2){
        Random rand = new Random();
        double mutaion_rate;
        double mutation_probibility;
        if (rand.nextBoolean()) mutaion_rate = parent1.getMutationRate(); else mutaion_rate = parent2.getMutationRate();
        if (rand.nextBoolean()) mutation_probibility = parent1.getMutationr_pobability(); else mutation_probibility = parent2.getMutationr_pobability();
        Genome child = new Genome(0,mutaion_rate,mutation_probibility);

        for (int i = 0 ; i <GAParam.genome_size ; i++){
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


    public void selection_tournament(){
        //tournament selection
        Collections.sort(population_vector);
        int top = intValue (population_vector.size() * tournoment_winner_percentage);


        Vector<Genome> children = new Vector<Genome>();

        for (int i = population_vector.size()-1 ; i > population_vector.size()-1-top ; i-=2){
            children.addElement(crossover_uniform(population_vector.elementAt(i), population_vector.elementAt(i-1)));
        }

        for (int i = 0 ; i < children.size();i++) {
            population_vector.setElementAt(children.get(i),i);
//            population_vector.addElement(children.get(i));
        }
//        for (int i = 0 ; i < children.size();i++) {
//        }


    }


    public Genome get_champion(){
        Collections.sort(population_vector);
        return population_vector.firstElement();
    }


    @Override
    public String toString() {
        return "Population{" +
                "Population=" + population_vector +
                '}';
    }
}

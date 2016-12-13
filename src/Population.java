import com.sun.tools.javac.jvm.Gen;

import java.util.Iterator;
import java.util.Vector;

import static oracle.jrockit.jfr.events.Bits.intValue;

/**
 * Created by arashparnia on 01/12/2016.
 */
public class Population implements GAParam {
    private Vector<Genome> population_vector = new Vector<>(GAParam.population_size);
    private int population_size = 0;
    private double crossover_uniform_probibility = GAParam.crossover_uniform_probability;
    private double tournament_size = GAParam.tournament_size;
    private double mutation_pobability_population = GAParam.mutation_probability_population;


    //CONSTRUCTORS
    public Population() {
        Genome[] g = new Genome[GAParam.population_size];
        for (int i = 0; i < GAParam.population_size; i++) {
            g[i] = new Genome(GAParam.fitness, mutation_rate, mutation_pobability_population, true);
            population_vector.add(g[i]);
            this.population_size++;
        }
    }

    public Population(Vector<Genome> population) {

        this.population_vector = (Vector<Genome>) population.clone();
    }

    //spawn Population with population_size
    public Population(int population_size, double mutation_rate, double mutation_probibility_genome) {
        for (int i = 0; i < population_size; i++) {
            population_vector.add(new Genome(GAParam.fitness, mutation_rate, mutation_probibility_genome, true));
            this.population_size++;
        }
    }

    //online spawn
    //spawn Population with the mutation of a specific genome
    public Population(Genome g, int population_size, double mutation_rate, double mutation_probibility) {
        for (int i = 0; i < population_size; i++) {
            g.setMutationRate(mutation_rate);
            g.setMutationr_pobability(mutation_probibility);
            if (i > 0) g.mutate(); // skips mutation for the elite
            population_vector.add(g);
            this.population_size++;
        }
    }


    // GA methods


    public void mutate() {
        for (Genome g : population_vector) {
            if (Math.abs(rnd_.nextDouble()) < mutation_pobability_population) {
                g.mutate();
            }
        }
    }

    public Genome[] crossover_uniform(Genome parent1, Genome parent2) {
        double mutation_rate;
        double mutation_probability;
        if (rnd_.nextBoolean()) mutation_rate = parent1.getMutationRate();
        else mutation_rate = parent2.getMutationRate();
        if (rnd_.nextBoolean()) mutation_probability = parent1.getMutationr_pobability();
        else mutation_probability = parent2.getMutationr_pobability();
        Genome child1 = new Genome(GAParam.fitness, mutation_rate, mutation_probability);
        Genome child2 = new Genome(GAParam.fitness, mutation_rate, mutation_probability);

        for (int i = 0; i < GAParam.genome_size; i++) {
            if (Math.abs(rnd_.nextDouble()) < crossover_uniform_probibility) {
                if (rnd_.nextBoolean()) {
                    child1.setGene(i, parent1.getGene(i));
                    child2.setGene(i, parent2.getGene(i));
                } else {
                    child1.setGene(i, parent2.getGene(i));
                    child2.setGene(i, parent1.getGene(i));
                }

            }
        }
        Genome[] children = {child1, child2};
        return children;
    }

    public Genome[] crossover_single_point(Genome parent1, Genome parent2) {
        double mutation_rate;
        double mutation_probability;
        if (rnd_.nextBoolean()) mutation_rate = parent1.getMutationRate();
        else mutation_rate = parent2.getMutationRate();
        if (rnd_.nextBoolean()) mutation_probability = parent1.getMutationr_pobability();
        else mutation_probability = parent2.getMutationr_pobability();
        Genome child1 = new Genome(GAParam.fitness, mutation_rate, mutation_probability);
        Genome child2 = new Genome(GAParam.fitness, mutation_rate, mutation_probability);
        int crossing_point = Math.abs(rnd_.nextInt(GAParam.genome_size));

        for (int i = 0; i < 10; i++) {
            if (i < crossing_point) {
                child1.setGene(i, parent1.getGene(i));
                child2.setGene(i, parent2.getGene(i));
            } else {
                child1.setGene(i, parent2.getGene(i));
                child2.setGene(i, parent2.getGene(i));
            }
        }
        Genome[] children = {child1, child2};
        return children;
    }


    public void selection_tournament() {
        int selection_size = 0;

        Vector<Genome> offspring = new Vector<Genome>();


        double sum = 0;
        Genome the_elite = new Genome();
        for (Genome g : population_vector) {
            sum += g.getFitness();
            if (g.getFitness() > the_elite.getFitness()) the_elite = g;

        }


        offspring.add(new Genome(the_elite));
        selection_size++;


        while (selection_size < population_size) {

            Vector<Genome> tournament_pool = new Vector<Genome>();

            for (int i = 0; i < tournament_size; i++)
                tournament_pool.add(select_random_genome(population_vector));

            Genome tournaument_winner = new Genome();
            for (Genome g : tournament_pool) {
                if (g.getFitness() > tournaument_winner.getFitness()) tournaument_winner = g;
            }
            offspring.add(tournaument_winner);
            selection_size++;
        }

        population_vector.clear();
        population_vector.add(the_elite);

        for (int i = 1; i < selection_size - 1; i += 2) {
            Genome parent1 = offspring.elementAt(i);
            Genome parent2 = offspring.elementAt(i + 1);

//            Genome[] children  = crossover_single_point(parent1,parent2);
            Genome[] children = crossover_uniform(parent1, parent2);

            population_vector.add(children[0]);
            population_vector.add(children[1]);
        }


    }


    private Genome select_random_genome(Vector<Genome> population) {
        return population.elementAt(Math.abs(rnd_.nextInt(population_size - 1)));
    }


    @Override
    public String toString() {
        return "Population{" +
                "Population=" + population_vector +
                '}';
    }


    //SETTER AND GETTERS

    public Vector<Genome> getPopulation_vector() {

        return (Vector<Genome>) population_vector.clone();
    }

    public void setPopulation_vector(Vector<Genome> population_vector) {
        this.population_vector.clear();
        this.population_vector.addAll(population_vector);
    }

    public void setPopulation_array(Genome[] population) {
        this.population_vector.clear();
        for (Genome g : population) {
            this.population_vector.add(g);
        }
    }

    public Genome[] getPopulation_array() {
        return population_vector.toArray(new Genome[population_size]);
    }


    public double getCrossover_uniform_probibility() {
        return crossover_uniform_probibility;
    }

    public void setCrossover_uniform_probibility(double crossover_uniform_probibility) {
        this.crossover_uniform_probibility = crossover_uniform_probibility;
    }

    public double getTournoment_winner_percentage() {
        return tournament_size;
    }

    public void setTournoment_winner_percentage(double tournament_size) {
        this.tournament_size = tournament_size;
    }

    public int getPopulation_size() {
        return population_vector.size();
    }


    public void setSeed(long seed) {
        // Set seed of algortihms random process
        rnd_.setSeed(seed);
    }


}

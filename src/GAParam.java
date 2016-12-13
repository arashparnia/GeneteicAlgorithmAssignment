import java.util.Random;

/**
 * Created by arashparnia on 01/12/2016.
 */
public interface GAParam {
    int population_size = 10;
    double crossover_uniform_probability = 0.2;
    double tournament_size = 2;
    int genome_size = 10;
    double[] genome = {0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0};
    double fitness = Double.MIN_VALUE;
    double mutation_rate = 0.1;
    double mutation_probability_genome = 0.2;
    double mutation_probability_population = 0.5;
//    double survival_threshold = 0.6;

    Random rnd_ = new Random();

    public void setSeed(long seed);


}

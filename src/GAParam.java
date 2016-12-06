/**
 * Created by arashparnia on 01/12/2016.
 */
public interface GAParam {
    int population_size = 5;
    double crossover_uniform_probability = 0.4;
    double tournament_winner_percentage = 0.4;
    int genome_size = 10;
    double[] genome = {0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0};
    double fitness = -999999;
    double mutation_rate = 0.5;
    double mutation_probability_genome = 0.2;
    double mutation_probability_population = 0.5;
    double survival_threshold = 1.5;


}

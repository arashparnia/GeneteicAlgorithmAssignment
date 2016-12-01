/**
 * Created by arashparnia on 01/12/2016.
 */
public interface GAParam {
    int population_size = 50;
    double crossover_uniform_probibility = 0.2;
    double tournoment_winner_percentage = 0.2;
    int genome_size = 10;
    double[] genome = {0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0};
    double fitness = 0;
    int age=1;
    double mutation_rate = 0.5;
    double mutation_pobability_genome = 0.1;
    double mutation_pobability_population = 0.01;
    int  max_survival_age = 5;


}

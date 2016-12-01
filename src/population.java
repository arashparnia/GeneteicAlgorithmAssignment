import java.util.Vector;

/**
 * Created by arashparnia on 01/12/2016.
 */
public class population {
    private Vector<Genome> population = new Vector<Genome>();
    private int size  = 0;


    public population() {
    }


    public population(Vector<Genome> population) {
        this.population = population;
    }

    public population(int size){
        this.size = size;
        population.add(new Genome());


    }


}

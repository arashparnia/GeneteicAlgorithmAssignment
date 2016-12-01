import org.vu.contest.ContestEvaluation;

public class Main {

    public static void main(String[] args) {
	// write your code here
        GA g= new GA();
//        ContestEvaluation eval = new FletcherPowellEvaluation();
        ContestEvaluation eval = new RastriginEvaluation();
        g.setEvaluation(eval);
        g.run();



//        Population p = new Population(10,0.5,0.5);
//        System.out.println(p.toString());

    }
}

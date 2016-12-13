import org.vu.contest.ContestEvaluation;

public class Main {

    public static void main(String[] args) {
	// write your code here
        player1 g= new player1();
        g.setSeed((long) 1);
//        ContestEvaluation eval = new FletcherPowellEvaluation();
//        ContestEvaluation eval = new RastriginEvaluation();
        ContestEvaluation eval = new SphereEvaluation();
        g.setEvaluation(eval);

        g.run();


    }
}

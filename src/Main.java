import org.vu.contest.ContestEvaluation;

public class Main {

    public static void main(String[] args) {
	// write your code here
        player1 g= new player1();
        g.setSeed((long) 0x1.2c91e9f5p32);
        ContestEvaluation eval = new FletcherPowellEvaluation();
//        ContestEvaluation eval = new RastriginEvaluation();
//        ContestEvaluation eval = new SphereEvaluation();
        g.setEvaluation(eval);

        g.run();



//        Population p = new Population(10,0.5,0.5);
//        System.out.println(p.toString());

    }
}

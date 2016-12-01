import org.vu.contest.ContestEvaluation;

public class Main {

    public static void main(String[] args) {
	// write your code here
        GA g= new GA();
        ContestEvaluation eval = new SphereEvaluation();
        g.setEvaluation(eval);
//        g.run();

        Genome gg = new Genome(0,0.1,0.01);
        for (int i = 0 ; i <10 ; i ++ ) {
            gg.mutate();
            System.out.println(gg.toString());
        }

    }
}

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class PascalsTriangle extends RecursiveTask<Integer> {

    public int n, k;


    public PascalsTriangle(int n, int k) {

        this.n = n;
        this.k = k;


    }

    public Integer compute() {

        if   (n == 0 || k == 0 || n == k) {
            return   1;
        }

        PascalsTriangle left = new PascalsTriangle(this.n - 1, this.k - 1);
        PascalsTriangle right = new PascalsTriangle(this.n - 1, this.k);
        left.fork();
        return right.compute() + left.join();
    }

    public static void main(String []  args) {


        ForkJoinPool pool = new ForkJoinPool(1);
        PascalsTriangle task = new PascalsTriangle(34,20);
        long startTime = System.currentTimeMillis();
        int result = pool.invoke(task);
        long endTime   = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println(result + " " + totalTime);

    }

}



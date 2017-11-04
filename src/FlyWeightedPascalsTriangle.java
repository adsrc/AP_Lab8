import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class FlyWeightedPascalsTriangle extends RecursiveTask<Integer> {


    private static Map<String, FlyWeightedPascalsTriangle> instances = new HashMap<String, FlyWeightedPascalsTriangle>();

    private int n, k, answer;

    private FlyWeightedPascalsTriangle(int n, int k) {

        this.n = n;
        this.k = k;

    }



    public static FlyWeightedPascalsTriangle getInstance(int n, int k) {

        String key = n + "," + k;
        if(!instances.containsKey(key)) {

            FlyWeightedPascalsTriangle val = new FlyWeightedPascalsTriangle(n, k);
            val.answer = val.compute();
            instances.put(key, val);

        }

        return instances.get(key);

    }

    public Integer compute() {

        if   (n == 0 || k == 0 || n == k) {
            this.answer = 1;
            return   1;
        }

        FlyWeightedPascalsTriangle left = getInstance(n-1, k-1);
        FlyWeightedPascalsTriangle right = getInstance(n-1, k);


        left.fork();
        return right.compute() + left.join();
    }

    public static void main(String []  args) {


        ForkJoinPool pool = new ForkJoinPool(1);
        FlyWeightedPascalsTriangle task = new FlyWeightedPascalsTriangle(16,8);
        long startTime = System.currentTimeMillis();
        int result = pool.invoke(task);
        long endTime   = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println(result + " " + totalTime);

    }

}

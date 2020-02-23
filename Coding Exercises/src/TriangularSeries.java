public class TriangularSeries {

    public static void main(String[] args) {
        int n = 6;
        System.out.println("The iterative method result is: " + iterativeTriangle(n));
        System.out.println("The recursive method result is: " + recursiveTriangle(n));
    }

    public static int iterativeTriangle(int n) {
        int result = 1;
        for(int i=1; i<n; i++) {
            result = result + (i+1);
        }
        return result;
    }

    public static int recursiveTriangle(int n) {
        int result = 1;
        if (n == 1) {
            return result;
        } else {
            return n + recursiveTriangle(n-1);
        }
    }
}

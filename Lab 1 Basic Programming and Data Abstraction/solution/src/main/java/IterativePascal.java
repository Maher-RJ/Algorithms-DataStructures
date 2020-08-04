
public class IterativePascal extends ErrorPascal {

    public static void main(String[] args) {
        IterativePascal instance = new IterativePascal();

        instance.printPascal(5);
        System.out.println();
        System.out.println(instance.binom(3, 2));
    }

    public void doPrintPascal(int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                System.out.print(binom(i, j) + " ");
            }
            System.out.println();
        }

    }

    public int doBinom(int n, int k) {
        int[][] array = new int[n + 1][k + 1];
        array[0][0] = 1; //hårdkodar första raden annars försöker programmet undersöka raden -1.
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= k; j++) { // k refererar till mål kolumnen som vi vill räkna ut, j är den vi räknar på just nu
                if (i == j || j == 0) {     // n är målraden som vi vill räkna ut, i är den vi räknar på just nu
                    array[i][j] = 1;
                } else {
                    array[i][j] = array[i - 1][j - 1] + array[i - 1][j];
                }
            }
        }
        return array[n][k];
    }

}





public class RecursivePascal extends ErrorPascal {

    int[][] store = new int[0][0];


    public static void main(String args[]) {
        RecursivePascal instance = new RecursivePascal(); // instansierar


        System.out.println(instance.binom(3, 2));
        System.out.println();
        instance.printPascal(10);
    }


    public void doPrintPascal(int n) { //n är vilken rad vi är på just nu
        boolean reverse = false;
        if (n >= 0) { //basfallet : vi printar ej ut något. Normalfallet är när vi printar ut.
            if (!reverse && n > 0) {
                printPascal(n - 1);
            }
            for (int i = 0; i <= n; i++) { // går igenom kolumnerna
                System.out.print(binom(n, i)); //printar ut siffrorna
                System.out.print(" ");
            }
            System.out.println(); //radbrytning

            if (reverse && n > 0) {
                printPascal(n - 1); // nästa rad (n + 1, fortsätter förevigt)

            }
        }
    }


    public int doBinom(int n, int k) {
        if (store.length <= n) { // <= annars exception
            store = new int[n + 1][n+1];
        }
        if (n == k || k == 0) { //basfall
            return 1;
        } else if (k > n) { //basfall
            return 0;
        } else if (store[n][k] != 0) { // om den vet vad som kommer innan den förgående raden.
            return store[n][k];
        } else {    // om den ej vet tidigare raders värde, bara första gången.
            return store[n][k] = (binom(n - 1, k - 1) + binom(n - 1, k));
        }


    }


}

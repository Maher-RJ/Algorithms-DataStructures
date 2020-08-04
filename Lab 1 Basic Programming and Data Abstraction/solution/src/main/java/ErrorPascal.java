public abstract class ErrorPascal implements Pascal { // kan ej skapa klasser av abstrakta klasser
    public void printPascal(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("You cannot print negative rows");
        } else {
            doPrintPascal(n);
        }
    }

    protected abstract void doPrintPascal(int n); //definierar att metoden måste finnas, i barnen bestämmer vi vad som händer

    protected abstract int doBinom(int n, int k);

    public int binom(int n, int k) { //sköter felkontroll
        if (k > n) {
            throw new IllegalArgumentException("There cannot be more columns than rows in Pascal's Triangle");
        } else if (k < 0) {
            throw new IllegalArgumentException("There cannot be a negative amount of columns in Pascal's Triangle");
        } else if (n < 0) {
            throw new IllegalArgumentException("There cannot be a negative amount of rows in Pascal's Triangle");
        } else {
            return doBinom(n, k);
        }
    }

}

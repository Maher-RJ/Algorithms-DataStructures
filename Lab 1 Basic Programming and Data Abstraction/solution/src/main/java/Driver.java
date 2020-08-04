
public class Driver {
    public static void main (String[] args){
        Pascal instanceOfRecursive = new RecursivePascal();
        Pascal instanceOfIterative = new IterativePascal();

        System.out.println(instanceOfRecursive.binom(6,5)); //doBinom har ej en felkontroll 4,5 ger undantag pga fler kolumner än rader
        System.out.println();
        instanceOfIterative.printPascal(10); //doPrint har ej felkontroll

    }
}

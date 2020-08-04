public class BubbleSort {

    public static void main(String args[]) {
        /*int [] array = {1,4,2,9,5,3,8};
        bubbleSort(array);
        System.out.println(Arrays.toString(array));
        */

        Node list = new Node(1, new Node(4, new Node(2, new Node(9,
                new Node(5, new Node(3, new Node(8, null))))))); // null är sista värdet som det pekar på


        int inversions = bubbleInversionCount(list);
        int swaps = llBubbleSort(list);

        System.out.println(list);
        System.out.println("The amount of inversions: " + inversions);
        System.out.println("The amount of swaps: " + swaps);
    }

    public static void bubbleSort(int[] a) { //Array version
        int size = a.length;
        int R = size - 2;
        boolean swapped = true;
        while (R >= 0 && swapped) {
            swapped = false;
            for (int i = 0; i <= R; i++) {
                if (a[i] > a[i + 1]) {
                    swapped = true;
                    int fluffig = a[i];
                    a[i] = a[i + 1];
                    a[i + 1] = fluffig;
                }
            }
            R--;
        }
    }

    public static int llBubbleSort(Node head) { //head är första värdet i den länkade listan / linkedlist version
        int size = 0; //räknare för storleken
        int counter = 0; //räknare för antal byten
        for (Node node = head; node != null; node = node.next) {
            size++;
        }
        int R = size - 2;
        boolean swapped = true;
        while (R >= 0 && swapped) {
            swapped = false;
            Node node = head;
            for (int i = 0; i <= R; i++) {
                if (node.value > node.next.value) {
                    swapped = true;
                    counter++;
                    int temporary = node.value;
                    node.value = node.next.value;
                    node.next.value = temporary;
                }
                node = node.next;
            }
            R--;
        }
        return counter; // returnerar antal swaps
    }

    public static int bubbleInversionCount(Node list) { //
        int counter = 0;
        for (Node head = list; head != null; head = head.next) {
            for (Node node = head.next; node != null; node = node.next) {
                if (head.value > node.value) {
                    counter++;
                }
            }
        }

        return counter; //returnerar antal inversions
    }

    /*public static int mergeSortInversionCount(Node list) {
        int counter = 0;
        for (Node head = list; head != null; head = head.next) {

        }

        return counter;
    }*/
}
package EP.structures;

import java.util.Arrays;

public class Utilities {
    public static < E >void append(E[] array, E element) {
        if (array.length <= 0) {
            array[0] = element;
        } else {
            E[] temp = Arrays.copyOf(array, array.length + 1);
            temp[array.length] = element;
            array = temp;
        }
    }

    public static < E > int indexOf(E element, E[] array){
        E[] temp = Arrays.copyOf(array, (array.length + 1));
        temp[array.length] = element;

        int i = 0;
        while(!temp[i].equals(element)) i++;

        if (i != array.length) return i;
        else return -1;
    }
}
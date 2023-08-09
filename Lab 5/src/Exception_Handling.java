/*
 * NAME : Azizbek Muminjonov
 * ID : U2110207
 */

import org.codehaus.groovy.classgen.asm.BinaryDoubleExpressionHelper;

import java.lang.*;
import java.util.Scanner;








class NumberNotFoundException extends Exception
{
    NumberNotFoundException(){
        super("Value can not be found");
    }
    NumberNotFoundException(String Message){
        super(Message);
    }
}

class ArrayIndexOutOfBoundsException extends Exception
{
    ArrayIndexOutOfBoundsException()
    {
        super("Invalid index");
    }
    ArrayIndexOutOfBoundsException(String Message)
    {
        super(Message);
    }


}



public class Exception_Handling {

    public static void byIndex(int[] arr, int index, int size) throws ArrayIndexOutOfBoundsException {
    try {
        if (index > (size - 1)) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }catch (ArrayIndexOutOfBoundsException exc)
    {
        System.out.println(exc);
    }
    }

    public static int byValue(int[] arr, int value, int size) throws NumberNotFoundException {
        for (int i = 0; i < size; i++) {
            if (arr[i] == value) {
                return i;

            }
        }
        throw new NumberNotFoundException();

    }

    public static void main(String[] args) throws ArrayIndexOutOfBoundsException, NumberNotFoundException{

        int[] array = new int[10];
        int value, size;

        int index = 0;
        Scanner in = new Scanner(System.in);

        System.out.println("Input values into array for indexes 0 to 9 \n");
        System.out.print("Input how many values do you want to input : ");
        size = in.nextInt();

        try {
            for (int i = 0; i < size; i++) {
                System.out.print("Input value for index " + i + " : ");
                value = in.nextInt();
                array[i] = value;

            }
        }catch (IndexOutOfBoundsException exceptions)
        {
            System.out.println(exceptions);
        }
        System.out.println("Choose an option : ");
        System.out.println(" 1 . Get value by index ");
        System.out.println(" 2 . Get index by value ");
        System.out.println(" 0 . Exit ");
        index = in.nextInt();

        switch(index)
        {
            case 0:
            {
                break;
            }
            case 1:
            {
                System.out.print("Enter the index : ");
                value = in.nextInt();
                byIndex(array, value, size);
                break;
            }
            case 2:
            {
                try {
                    System.out.print("Enter the value : ");
                    value = in.nextInt();
                    System.out.print("Located : " + byValue(array, value, size));
                }catch (NumberNotFoundException exce) {
                    System.out.println(exce);
                }

                break;
            }
            default:{
                System.out.println("Wrong index T_T");
                main(args);
            }

        }
    }
}

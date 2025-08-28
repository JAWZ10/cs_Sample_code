package quick_sort_pack;

import java.util.Random;
import java.util.Scanner;

public class quick_sort {

    public static int[] numbers;

    public static void main(String[] args){

        
        Scanner scan = new Scanner(System.in);
        //gets the minimum random number
        System.out.println("Please enter the minimum number.");
        int min = get_user_input(scan);
        //gets the maximum random number
        System.out.println("Please enter the maximum number.");
        int max = get_user_input(scan);
        //gets the maximum random number
        System.out.println("Please enter the number of random numbers to be generated.");
        int array_length = get_user_input(scan);
        int[] numbers = new int[array_length];

        boolean show_unsorted = false, show_sorted = false;

        //determines whether or not to show the unsorted list
        System.out.println("Would you like to see the unsorted list? Enter 1 for yes and 0 for no");
        boolean keep_going = true;
        while (keep_going == true){
            int temp = get_user_input(scan);
            if (temp == 1 || temp == 0){
                if(temp == 1){
                    show_unsorted = true;
                }
                keep_going = false;
            }else{
                System.out.println("invalid input");
            }
        }

        //determines whether or not to show the sorted list
        System.out.println("Would you like to see the sorted list? Enter 1 for yes and 0 for no");
        keep_going = true;
        while (keep_going == true){
            int temp = get_user_input(scan);
            if (temp == 1 || temp == 0){
                if(temp == 1){
                    show_sorted = true;
                }
                keep_going = false;
            }else{
                System.out.println("invalid input");
            }
        }
        //calls a method to fill the array
        fill_array(numbers, min, max);
        //prints the unsorted list if directed to
        if (show_unsorted == true){
            print_array(numbers);
            System.out.println("");
        }
        //sorts the array
        partition_sort(numbers, 0, array_length - 1);
        //prints the sorted list if directed to
        if (show_sorted == true){
            print_array(numbers);
        }
    }

    //gets a number from the user
    public static int get_user_input(Scanner scan) {
        int input = 0;
        boolean keep_asking = true;
        while(keep_asking == true) {
            //try catch statement to catch invalid inputs
            try {
                input = scan.nextInt();
                keep_asking = false;
            } catch (Exception e) {
                System.out.println ("Invalid input.");
            } finally {
                scan.nextLine();
            }
        }
        return input;
    }
    //prints the array
    public static void print_array(int[] aray) {
        int size = aray.length;
        for (int i = 0, j = 1; i < size; i++) {
            //System.out.print(aray[i] + " ");
            System.out.printf("%4d \t", aray[i]);
            //System.out.printf("%4d /t /n", aray[i]);
            if(j % 20 == 0){
                System.out.println();
            }
            j++;
        }
    }
    //fills the aray
    public static void fill_array(int[] aray,int min,int max) {
        int size = aray.length;
        for (int i = 0; i < size; i++) {
            aray[i] = gen_random_range(min, max);
        }
    }
    //method to generate a single random number within a range
    public static int gen_random_range(int min, int max)  {
        Random rand = new Random();
        int random_number = 0;
        random_number = Math.abs(rand.nextInt());
        random_number = (random_number % (max - min + 1)) + min;
        return random_number;
    }

    public static void partition_sort(int[] numbers, int low, int high)  {
        if (low >= high){
            return;
        }
        int pivot = numbers[high];
        int left_pointer = low;
        int right_pointer = high;
        while (left_pointer < right_pointer){
            //moves the left pointer untill it is greater than the pivot
            while(numbers[left_pointer] <= pivot && left_pointer < right_pointer){
                left_pointer++;
            }
            //moves the right pointer untill it is less than the pivot
            while(numbers[right_pointer] >= pivot && left_pointer < right_pointer){
                right_pointer--;
            }
            //swaps the right and left indexes
            int temp = numbers[left_pointer];
            numbers[left_pointer] = numbers[right_pointer];
            numbers[right_pointer] = temp;
        }
        //swaps the left index and the pivot
        int temp = numbers[left_pointer];
        numbers[left_pointer] = numbers[high];
        numbers[high] = temp;
        //recursively calls this method for the different partitions
        partition_sort(numbers, low, left_pointer - 1);
        partition_sort(numbers, left_pointer + 1 , high);
    }
}
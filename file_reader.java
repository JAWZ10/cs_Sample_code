package file_package;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class file_reader {
    
    public static int[] numbers;


    public static void main(String[] args) {

        String file_name = ("randomNumbers.txt");

        read_file(file_name);
        bubble_sort(numbers);
        print_array(numbers);
        write_file();
    }

    public static void write_file() {
        BufferedWriter bw = null;
        FileWriter fw = null;
        try {
            fw = new FileWriter("NumbersOut.txt");
            bw = new BufferedWriter(fw);
            int size = numbers.length;
            for (int i = 0, j = 1; i < size; i++, j++) {
                bw.write(numbers[i] + " ");
                if(j % 20 == 0){
                    bw.write("\n");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if(bw != null) {
                    bw.close();
                }
                if(fw != null) {
                    fw.close();
                }
            } catch (Exception e) {
            }
        }
    }

    public static void read_file(String file_name) {
        File file = new File(file_name);
        
        Scanner reader = null;
        try {

            reader = new Scanner(file);
            int size = reader.nextInt();//reads the first int of the file, assumes that the first int is set to the total number of ints in the file
            numbers = new int[size];//sets the size of the array
            
            int counter = 0;
            while(reader.hasNextInt()){
                numbers[counter] = reader.nextInt();
                counter++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            reader.close();
        }
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
    //sorts the array
    public static void bubble_sort (int[] aray) {
        int size = aray.length;
        int temp = 0;
        for(int i = 0; i < size; i++){
            for(int n = 1; n < size; n++){
                if(aray[n - 1] > aray[n]){
                    temp = aray[n - 1];
                    aray[n-1] = aray[n];
                    aray[n] = temp;
                }
            }
        }
    }


}

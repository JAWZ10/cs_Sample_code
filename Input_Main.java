//Josten McEachran's code

package input_package;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Input_Main {

    public static void main(String[] args)  {
        //creates a new Scanner
        Scanner scan = new Scanner(System.in);
        
        //chooses which door has a prize
        int prize_door = gen_random_range(1, 3);

        //calls a method to get the user's input within a specific range
        int input = get_user_input(scan, 1, 3);

        //list of possible garbage doors
        ArrayList<String> bad_doors_1 = new ArrayList<>();
        bad_doors_1.add(0," is five used sticks of licorish.");
        bad_doors_1.add(1," is the nefarious leech.");
        bad_doors_1.add(2," is a door to door solar panel salesman.");
        bad_doors_1.add(3," is an empty dill pickle flavored Wendys burger wrapper.");

        //2nd list of possible garbage doors
        ArrayList<String> bad_doors_2 = new ArrayList<>();
        bad_doors_2.add(0,"The door opens up to the void.");
        bad_doors_2.add(1,"The door opens up, and the nefarious angler fish lures you to your doom.");
        bad_doors_2.add(2,"The door opens, and a sandal traveling a mach 7 hits your face, instantly ending you.");
        bad_doors_2.add(3,"The door opens up to a brand new Cybertruck. You instantly disintigrate to avoid the shame of owning such a horrendously desighned vehicle.");

        //list of possible good doors
        ArrayList<String> good_doors = new ArrayList<>();
        good_doors.add(0,"The door opens up to 578 pounds of peanut brittle as well as a bath tub.");
        good_doors.add(1,"The door opens to your very own FLDSMDFR!!! (Flint Lockwood Diatonic Super Mutating Dynamic Food Replicator).");
        good_doors.add(2,"The door opens and fills you with the unmatched power of the sun.");
        good_doors.add(3,"The door opens to a benevolent slug.");

        //Array list containing the three doors
        ArrayList<String> available_doors = new ArrayList<>(3);
        available_doors.add(0,"door 1");
        available_doors.add(1,"door 2");
        available_doors.add(2,"door 3");

        //removes the prize door from the list of available doors
        available_doors.remove(prize_door - 1);

        //removes the door coresponding to the input, so long as the the input does not equal the prize door
        if (prize_door != input){
            if(input < prize_door){
                available_doors.remove(input - 1);
            }else{
                available_doors.remove(input - 2);
            }
        }

        //prints the chosen trap door that is not the same as the input or the prize door
        int garbage_doors = available_doors.size();
        if (garbage_doors == 1){
            System.out.println("Behind " + available_doors.get (0) + bad_doors_1.get(gen_random_range(0,3)));
        }else{
            System.out.println("Behind " + available_doors.get (gen_random_range(0, 1)) + bad_doors_1.get(gen_random_range(0,3)));
        }
        
        //asks if the user wants to switch doors
        System.out.println("Would you like to switch doors?");
        System.out.println("Enter 1 if you would like to switch, otherwise enter 0.");
        int switch_doors = get_user_input(scan, 0, 1);

        //based off of if the user switches or not, this determines whether or not they win
        if (switch_doors == 0 && input == prize_door || switch_doors == 1 && input != prize_door){
            System.out.println("You win. " + good_doors.get(gen_random_range(0,3)));
            System.exit(0);
        }else{
            System.out.println("You lost. " + bad_doors_2.get(gen_random_range(0,3)));
            System.exit(0);
        }
        
        
        

    scan.close();
}

    //method to generate a single random number within a range
    public static int gen_random_range(int min, int max)  {
        Random rand = new Random();
        int random_number = 0;
        random_number = Math.abs(rand.nextInt());
        random_number = (random_number % (max - min + 1)) + min;
        return random_number;
    }

    //ask the user for a number beween a minimum and a maximum
    public static int get_user_input(Scanner scan, int min, int max) {
        int input = min - 1;
        boolean keep_asking = true;
        while(keep_asking == true) {
            //try catch statement to catch invalid inputs
            try {
                //ensures that it asks you to enter a number within the indicated range only if the range is not equal to [0,1]
                if (min != 0 || max != 1){
                    System.out.println("Enter a number between " + min + " and " + max);
                }
                input = scan.nextInt();
                //ensures that the int is within the specified range
                if(input < 0 || input > 3){
                    throw new Exception();
                }
                keep_asking = false;
            } catch (Exception e) {
                System.out.println ("Invalid input.");
            } finally {
                scan.nextLine();
            }
        }
        return input;
    }
}




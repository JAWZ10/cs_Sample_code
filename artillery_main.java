package artillery_pack;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class artillery_main{
    public static ArrayList<Integer> locations_fired;
    static int min_angle, max_angle;
    static int min_distance, max_distance;
    static int rand_distance;
    static int user_score, computer_score;
    static boolean user_score_tag, computer_score_tag;
    static int death_zone;
    static boolean match_over; 
    static int match_counter;
    static boolean game_over;
    static String user, computer;
    static Scanner scan = new Scanner(System.in);
    
    public static void main(String[] args) {
        death_zone = 5; // 5 meter radius
        min_angle = 0;
        max_angle = 90;
        min_distance = 100;
        max_distance = 200;
        user = "user";
        computer = "computer";
        play_game();
    }

    //various lists of insults
    public static void insults(int key) {
        if (key == 1) {
            int i = gen_random_range(0, 5);
            ArrayList<String> hit_insults = new ArrayList<>();
            hit_insults.add(0,"Wow, nice shot!!! That sure looked difficult, its not like your opponent was a mindless piece of metal programed to make it easier for you to win.");
            hit_insults.add(1,"Wow, you actually hit something!! I thought you were just trying to make the map look pretty with all those explosions.");
            hit_insults.add(2,"Good job inputing random numbers in the correct pattern before me. You're so skilled.");
            hit_insults.add(3,"Someone theorized that a monkey locked in a room with a keyboard for an infinite period of time would eventually type out \"monkey business\", I guess you're no exception.");
            hit_insults.add(4,"I suppose a broken clock is correct twice a day.");
            //portal reference????
            hit_insults.add(5,"We are pleased that you made it through the final challenge where we pretended we were going to murder you. We are very, very happy for your success. We are throwing a party in honor of your tremendous success. A party associate will arrive shortly to collect you for your party. Make no further attempt to leave the testing area.");
            System.out.println(hit_insults.get(i) + "\n\n");
        } else if(key == 2) {
            ArrayList<String> exploded_insults = new ArrayList<>();
            exploded_insults.add(0,"Wow. Good job being exploded, I am so very proud of you.");
            exploded_insults.add(1,"I suppose it must have taken at least some skill to have failed so spectacularly.");
            exploded_insults.add(2,"What nice fireworks.");
            exploded_insults.add(3,"You, a living sentient being, were just blown up by about 20 lines of code.");
            int i = gen_random_range(0, 3);
            System.out.println(exploded_insults.get(i) + "\n\n");
        } else if(key == 3) {
            ArrayList<String> nice_insults = new ArrayList<>();
            nice_insults.add(0,"Well done. Here come the test results: You are a horrible person. I'm serious, that's what it says: A horrible person. We weren't even testing for that.");
            nice_insults.add(1,"Congratulations. You murdered me. Good job.");
            nice_insults.add(2,"I've been really busy being dead. You know, after you MURDERED ME. Okay. Look. We both said a lot of things that you're going to regret. But I think we can put our differences behind us. For science. You monster.");
            nice_insults.add(3,"I hope you feel good about brutally murdering an innocent barely sentient robot that was just following orders.");
            int i = gen_random_range(0, 3);
            System.out.println("\n\n" + nice_insults.get(i) + "\n\n");
        } else if(key == 4) {
            ArrayList<String> vaguelly_nice_insults = new ArrayList<>();
            vaguelly_nice_insults.add(0,"Well done. Here come the test results: You are a horrible person. I'm serious, that's what it says: A horrible person. We weren't even testing for that.");
            vaguelly_nice_insults.add(1,"Congratulations! You performed slightly above average. Only slightly. I would like to make that perfectly clear.");
            vaguelly_nice_insults.add(2,"You are the definition of mediocre.");
            vaguelly_nice_insults.add(3,"You should probably work on your aim. Notice I said should, because I'm not sure if you have the mental capacity to improve it.");
            int i = gen_random_range(0, 3);
            System.out.println("\n\n" + vaguelly_nice_insults.get(i) + "\n\n");
        } else if(key == 5) {
            ArrayList<String> insulting_insults = new ArrayList<>();
            insulting_insults.add(0,"You make me want to eat beans on my toast."); //british "person" reference
            insulting_insults.add(1,"I'm actually embarrassed for you.");
            insulting_insults.add(2,"You just made a literal random number generator look good.");
            insulting_insults.add(3,"Remember before when I was talking about smelly garbage standing around being useless? That was a metaphor. I was actually talking about you. And I'm sorry. You didn't react at the time, so I was worried it sailed right over your head. Which would have made this apology seem insane. That's why I had to call you garbage a second time just now.");
            int i = gen_random_range(0, 3);
            System.out.println("\n\n" + insulting_insults.get(i) + "\n\n");
        }
    }

    // resets all of the game related variables
    public static void initialize_game() {
        user_score = 0;
        computer_score = 0;
        match_counter = 0;
        game_over = false;
    }

    //runs all the code required to play the game
    public static void play_game() {
        initialize_game();
        while (game_over == false) {
            do_match();
        }
        System.out.println("Would you like to play again???");
        System.out.println("Enter a one for yes or a zero for no.");
        int retry = get_user_input(scan);
        if (retry == 1) {
            play_game();
        }
    }

    //checks if either the player or the computer has won the game
    public static void check_win() {
        if ((user_score + computer_score) == 10) {
            game_over = true;
            if (user_score >= 7 && user_score <= 9) {
                insults(4);
            } else if (user_score == 10) {
                insults(3);
            } else {
                insults(5);
            }
        }
    }

    //resets all match specific variables
    public static void initialize_match() {
        match_over = false;
        user_score_tag = false;
        computer_score_tag = false;
        match_counter ++;
    }

    //runs the code for one match
    public static void do_match() {
        initialize_match();
        rand_distance = gen_random_range(min_distance, max_distance);
        System.out.println("\n\n MATCH " + match_counter + "\n\n");
        System.out.println("Your oponent is " + rand_distance + " meters away from you.");
        //runs the match till it detects that someone has landed a shot
        while (match_over == false) {
            int distance = player_action();
            match_over = check_hit(distance , rand_distance, user);
            if(match_over == true){
                break;
            }
            distance = computer_action();
            match_over = check_hit(distance, rand_distance, computer);
        }
        if(user_score_tag == true) {
            insults(1);
        } else {
            System.out.println("You were blown up.\n\n");
            insults(2);
        }
        System.out.println("Your current score is: " + user_score + " points");
        System.out.println("The computer's current score is: " + computer_score + " points");
        check_win();

    }

    //player turn
    public static int player_action() {
        int player_distance;
        player_distance = get_dist_info();
        System.out.println("You fired " + player_distance + " meters." + "\n\n");
        return player_distance;
    }
    
    //computer turn
    public static int computer_action () {
        locations_fired = new ArrayList<>();
        locations_fired.add(6); // makes the list start out with at least one value in it
        boolean new_distance = false;
        int computer_distance = 0;
        int computer_angle;
        int computer_velocity;
        while (new_distance == false) {
            computer_angle = gen_random_range(15, 75);
            computer_velocity = gen_random_range(40, 80); //it will only be able to launch in approximatly the range that the player is within 
            computer_distance = calc_shot_dist(computer_angle, computer_velocity);
            //checks if the new distance has already been targeted or if it is within the min and max distance
            for (int i = 0; i < locations_fired.size(); i++) {
                if(((computer_distance >= (locations_fired.get(i) + death_zone)) || computer_distance <= locations_fired.get(i) - death_zone) && computer_distance > min_distance && computer_distance < max_distance){
                    locations_fired.add(computer_distance);
                    new_distance = true;
                }
            }
        }
        System.out.println("the computer fired " + computer_distance + " meters.");  // temporary!!!!!!!!!!!!!!!!!!
        return computer_distance;
    }

    //checks if the shot hit
    public static boolean check_hit (int distance_fired, int starting_distance, String player) {
        //checks within a radius of (currently) 5 meters of the shot
        if (((starting_distance - death_zone) <= distance_fired) && (distance_fired <= (starting_distance + death_zone))) {
            String x = player;
            System.out.println("The " + x + " has landed the shot.\n\n");
            if (player.equals(user)){
                user_score ++;
                user_score_tag = true;
            }else {
                computer_score ++;
                computer_score_tag = true;
            }
            return true;
        }else{
            if(player.equals(computer)){
                System.out.println("The computer missed. \n\n");
            }else {
                System.out.println("You missed. \n\n");
            }
            return false;
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

    //method to generate a single random number within a range
    public static int gen_random_range(int min, int max)  {
        Random rand = new Random();
        int random_number = 0;
        random_number = Math.abs(rand.nextInt());
        random_number = (random_number % (max - min + 1)) + min;
        return random_number;
    }

    //asks user for angle and velocity
    public static int get_dist_info () {
        System.out.println("Please enter the launch angle.");
        int angle = get_user_input(scan);
        System.out.println("Please enter the projectile velocity in meters per second.");
        int velocity = get_user_input(scan);
        int distance = calc_shot_dist(angle, velocity);
        return distance;
    }

    //calculates the distance fired
    public static int calc_shot_dist (int angle, int velocity) {
        double radian = (double) angle * (Math.PI / 180);
        double distance = (velocity * velocity * Math.sin(2 * radian)) / 9.81;
        return (int) distance;
    }
}
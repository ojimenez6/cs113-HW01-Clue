/*DetectiveJill.java - Checks if a theory about a murder is correct
  Author: Orlando Jimenez
  Project: Homework 1
  Problem statement: This program tries to solve a murder by generating a theory with a random person, location, and
                     weapon and checking with Assistant Jack until the theory generated is correct. There are 6
                     potential murderers, 6 weapons and 10 potential locations of where the murder took place.

  Algorithm / Plan:
  1. Use int variables that will store the murderer, weapon, location, the theory entered by the user, and
     the solution that is returned when we check the theory entered with Assistant Jack.
  2. Create three Integer ArrayList, one that will hold the amount of potential murderers(6), one that will hold the
     amount of weapons(6), and one that will hold the amount of locations(10). The reason for using ArrayList is
     because they will shrink as we eliminate potential murderers, locations, and weapons.
  3. Fill the ArrayLists with Integers starting from 1 and ending at the ArrayList's capacity.
  4. Ask the user to enter a theory(1-3) the third one being a randomly generated correct answer. Store the input
     and use it as the argument for creating an Assistant Jack object that we will check our theory with.
  5. Create a loop that will randomly generate a theory by getting a random Integer value from the
     appropriate ArrayLists. (Ex. The murder variable will store the an Integer from the murder ArrayList).
     The loop will run until the solution returned from Assistant Jack is theory, meaning the theory is correct.
  4. If the theory is not correct, then Assistant Jack will return an integer (1-3). It returns 3 if the murderer is
     wrong, 2 if location is wrong, and 1 if the weapon is wrong. Use if statements to eliminate the incorrect
     murderer, location, and weapons from their corresponding ArrayList.
  5. After the loop ends, use the correct murderer/weapon/location to create a theory object and print the
     result to the screen along with how many it checks it took with Assistant Jack to generate the correct theory.
  6. If took 20 checks or less to generate the correct theory, then print a congratulations message for the detective.
     It it took more than 20 tries, then print that the detective stinks.

  */

import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;

public class DetectiveJill
{

    public static void main(String[] args)
    {
        int answerSet, solution, murder, weapon, location;
        Scanner keyboard = new Scanner(System.in);
        Random random = new Random();
        Theory answer = null;
        AssistantJack jack;

        //These arrayList hold the number of people, weapons, and locations in this murder
        ArrayList<Integer> murderArray = new ArrayList<>(6);
        ArrayList<Integer> weaponArray = new ArrayList<>(6);
        ArrayList<Integer> locationArray = new ArrayList<>(10);

        //Will add the numbers 1-6 for both murder and weapon ArrayList
        for(int i = 0; i < 6; i++)
        {
            murderArray.add(i + 1);
            weaponArray.add(i + 1);
        }

        //Will add numbers 1-10 to the location ArrayList
        for(int i = 0; i < 10; i++)
        {
            locationArray.add(i + 1);
        }

        //Ask user to enter a theory
        System.out.println("Which theory would like you like to test? (1, 2, 3[random]): ");
        answerSet = keyboard.nextInt();
        keyboard.close();

        //Creates an Assistant jack that will have the correct answer for the theory entered
        jack = new AssistantJack(answerSet);

        //This do-while loop will run until the murderer, location, and weapon is correct
        do {

            //Will randomly generate a number based on the size of the array
            //Then it will get the Integer value at the index of the number that was randomly generated
            //Then it will store the value to the appropriate int variable
            murder = murderArray.get(random.nextInt(murderArray.size()));
            location = locationArray.get(random.nextInt(locationArray.size()));
            weapon = weaponArray.get(random.nextInt(weaponArray.size()));

           //Returns 3 if murderer is incorrect, returns 2 if location is incorrect, returns 1 if weapon is incorrect
            solution = jack.checkAnswer(weapon, location, murder);

            //If the solution returned is 3, 2 , or 1, then remove the incorrect murderer/location/weapon
            //from their corresponding ArrayList
            if(solution == 3)
            {
                int index = murderArray.indexOf(murder);
                murderArray.remove(index);
            }
            if(solution == 2)
            {
                int index = locationArray.indexOf(location);
                locationArray.remove(index);
            }
            if(solution == 1)
            {
                int index = weaponArray.indexOf(weapon);
                weaponArray.remove(index);
            }

        } while (solution != 0);

        //Holds the correct weapon/location/murderer of the murder
        answer = new Theory(weapon, location, murder);
        System.out.println("Total Checks = " + jack.getTimesAsked() + ", Solution = " + answer);

        if (jack.getTimesAsked() > 20)
        {
            System.out.println("FAILED!! You're a horrible Detective...");
        }
        else
            {
            System.out.println("WOW! You might as well be called Batman!");
        }

    }
}

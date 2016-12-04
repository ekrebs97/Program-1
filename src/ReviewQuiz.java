// Title:            ReviewQuiz
// Files:            ReviewQuiz.java and P1.jar
// Semester:         Fall 2016
//
// Author:           Ellen Krebs
// Email:            ekrebs@wisc.edu
// CS Login:         ekrebs
// Lecturer's Name:  Gary Dahl
// Lab Section:      345

/**
 * Class Header This is a description of the public class ReviewQuiz. It
 * contains a main method where all the code is written inside. The code allows
 * students to review questions that are asked from previous sections to review
 * for the quiz. This class (ReviewQuiz) is public, meaning that it can be
 * accessed by any class in the java program.
 * 
 * @author Ellen Krebs
 */

import java.util.Scanner;

public class ReviewQuiz {

	// This public method is where all the code in this program will
	// be written. The first print statement nested within the method
	// executes the welcome statement for the user to see.

	public static void main(String[] args) {

		// List of all my variables
		Scanner scnr = new Scanner(System.in);
		String emailAddress;
		int sectionNumber;
		String getQuestion;
		String userAnswer;
		char charUserAnswer;
		int numOfReviewProblems;
		int numOfCorrectAnswers;
		String multipleChoiceQuestion = "";
		String correctAnswer;
		char charCorrectAnswer;
		String confirmSubmission;
		char charConfirmSubmission;
		int difficultyRating;
		String mainMenuChoice;
		char mainMenuChoiceChar;
		int i = 0;
		int j = 0;
		String tempVariable;

		// This is the welcome statement
		System.out.println("Welcome to the CS302 Review Quiz!");
		System.out.println("=================================");

		System.out.println("");

		// This print statement asks that the user enter their email
		// address. The Scanner must be initialized before the user can
		// give an input. "input" is the name of the variable used by the
		// scanner. Once the Scanner has been called, then the user can
		// input their email address below.
		System.out.print("Please enter your email address: ");
		emailAddress = scnr.nextLine();
		System.out.println("");

		// This do statement prints out the main menu, which allows the user to
		// choose various options, such as a Random Sample vs. Proficiency Quiz,
		// or Submitting a Problem.
		do {
			System.out.println("MAIN MENU");
			System.out.println("    [R]andom Sample Quiz (fixed number of problems)");
			System.out.println("    [P]roficiency Quiz (fixed number of correct answers)");
			System.out.println("    [S]ubmit a Problem");
			System.out.println("    [Q]uit");
			System.out.print("Enter your choice: ");
			mainMenuChoice = scnr.nextLine();

			// This code runs if the user chooses "R"
			if (mainMenuChoice.equals("R") || mainMenuChoice.equals("r")) {

				// This prints out all of the sections for the user to see.
				// Then, the user is prompted to choose a section to answer a
				// problem
				// from. Finally, they input the section number
				System.out.println("");
				System.out.println(P1.getSections());
				System.out.print("Choose another section to answer problems from (or -1 to stop): ");
				sectionNumber = scnr.nextInt();
				scnr.nextLine();

				// This code brings up every section
				P1.toggleSection(sectionNumber);

				// What this code does is that if the user inputs a number > 0,
				// then the user will keep being prompted to
				// put in another section number until they input -1.
				// And, if there are questions available and the section number
				// is equivalent to -1, then the
				// loop breaks, since there are no questions available for
				// section -1.
				while (P1.questionsAvailable() == false || sectionNumber != -1) {
					System.out.println("");
					System.out.println(P1.getSections());
					System.out.print("Choose another section to answer problems from (or -1 to stop): ");
					sectionNumber = scnr.nextInt();
					scnr.nextLine();
					System.out.println("");
					if (sectionNumber > 0) {
						P1.toggleSection(sectionNumber);
					}
					if (sectionNumber == -1 && P1.questionsAvailable() == true) {
						break;
					}
				}
				
				// This prompts to user to enter how many problems they would like
				// to review.
				System.out.print("Enter the number of problems to review: ");
				numOfReviewProblems = scnr.nextInt();
				scnr.nextLine();
				System.out.println("");

				// This for loop allows for as many questions to appear as the
				// user would like.
				for (i = 0; i < numOfReviewProblems;) {
					System.out.println(j + 1 + ". " + P1.getQuestion());
					j++; // Increments each question as 1. 2. 3.
					System.out.print("Enter your answer: ");
					userAnswer = scnr.nextLine();
					++i;
					System.out.println("");

					// If the user enters the correct answer, then the printed
					// statement will say that it's correct. But if not, then
					// a statement will print saying the correct answer.

					charUserAnswer = userAnswer.charAt(0); // This turns the user's answer into a character.

					if (Character.toUpperCase(charUserAnswer) == P1.getAnswer()) {
						System.out.println("That's correct!!!");
						System.out.println("");
					} else {
						System.out.print("The correct answer is: ");
						System.out.println(P1.getAnswer());
						System.out.println("");
					} 

					// This statement prompts the user to rate the difficulty of
					// the problem and submit their answer. Then,
					// it prints out the average difficulty rating for the
					// question.

					System.out.print("Please rate the difficulty of this problem (1-easy to 5-hard, or 0-report): ");
					difficultyRating = scnr.nextInt();
					scnr.nextLine();
					P1.submitRating(difficultyRating);
					System.out.print("The average rating for this question has been: ");
					double difficultyRatingAverage = (double) difficultyRating;
					difficultyRatingAverage = P1.getRating();
					System.out.println(difficultyRatingAverage);
					System.out.println("");

					P1.gotoNextProblem();

				}
			}

			// This code runs if the user chooses "P"
			
			else if (mainMenuChoice.equals("P") || mainMenuChoice.equals("p")) {
				
				// This brings up each section and it will continue bringing up the sections
				// unless the user inputs -1 instead of a positive number.
				
				System.out.println("");
				System.out.println(P1.getSections());
				System.out.print("Choose another section to answer problems from (or -1 to stop): ");
				sectionNumber = scnr.nextInt();
				scnr.nextLine();

				P1.toggleSection(sectionNumber);

				// If there are no questions available in a section, or the section number is not -1,
				// then the code will bring up each section until the user inputs -1 for the loop to break.
				
				while (P1.questionsAvailable() == false || sectionNumber != -1) {
					System.out.println("");
					System.out.println(P1.getSections());
					System.out.print("Choose another section to answer problems from (or -1 to stop): ");
					sectionNumber = scnr.nextInt();
					scnr.nextLine();
					if (sectionNumber > 0) {
						P1.toggleSection(sectionNumber);
					}
					if (sectionNumber == -1 && P1.questionsAvailable() == true) {
						break;
					}
				}
				
				// This code allows the user to put the correct number of answers needed to
				// quit the loop.
				
				System.out.println("");
				System.out.print("Enter the number of correct answers needed to stop: ");
				numOfCorrectAnswers = scnr.nextInt();
				scnr.nextLine();
				System.out.println("");
				
				// This for loop gets a question and then asks the user to
				// input their answer.
				
				for (i = 0; i < numOfCorrectAnswers;) {
					System.out.println(j + 1 + ". " + P1.getQuestion());
					j++;
					System.out.print("Enter your answer: ");
					userAnswer = scnr.nextLine();
					System.out.println("");
					
					// Below, the if statement checks to see if the userAnswer's choice was correct.
					// If so, it will say that it is correct. If not, then the code will show the
					// user the correct answer.
					
					charUserAnswer = userAnswer.charAt(0); // takes userAnswer and turns it into a char.
					if (Character.toUpperCase(charUserAnswer) == P1.getAnswer()) {
						System.out.println("That's correct!!!");
						++i;
					} else {
						System.out.print("The correct answer is: ");
						System.out.println(P1.getAnswer());
					}
					System.out.println("");
					
					// This code asks the user to input the difficulty rating of the problem they just answered.
					
					System.out.print("Please rate the difficulty of this problem (1-easy to 5-hard, or 0-report): ");
					difficultyRating = scnr.nextInt();
					scnr.nextLine();
					P1.submitRating(difficultyRating);
					System.out.print("The average rating for this question has been: ");
					double difficultyRatingAverage = (double) difficultyRating;
					difficultyRatingAverage = P1.getRating();
					System.out.println(difficultyRatingAverage);

					System.out.println("");

					P1.gotoNextProblem(); // inputs a new problem if the user asked for more than 1 problem.
				}
			}
// This code runs if the user input "S"
			
			else if (mainMenuChoice.equals("S") || mainMenuChoice.equals("s")) {
				System.out.println("");

				// These methods ask the user to input their own multiple choice
				// question to the quiz, and to input the correct answer.
				
				System.out.println("Enter a multiple choice question (including the answer options): ");
				System.out.println("Type END on a line by itself to finish question.");

				// This do-while loop first makes a temporary variable to store
				// in the next line of code.
				// Then, an if statement follows to check if the tempVariable
				// does not equal "END"
				// If not, then it runs the multiple choice question and allows
				// the user to end it by
				// printing "END."
				
				do {
					tempVariable = scnr.nextLine();
					if (!tempVariable.equals("END")) {
						multipleChoiceQuestion = multipleChoiceQuestion + tempVariable + "\n";
					}
				} while (!tempVariable.equals("END"));

				// The user can answer the correct answer for the question they
				// submitted.
				
				System.out.print("Enter the correct answer for this question (one letter): ");
				correctAnswer = scnr.nextLine();
				
				// Takes the correctAnswer and makes it a char to be used in the
				// P1.submitProblem(); method later.
				charCorrectAnswer = correctAnswer.charAt(0);

				System.out.println(P1.getSections());
				System.out.print("Enter the section for this problem: ");
				sectionNumber = scnr.nextInt();
				scnr.nextLine();

				System.out.println("");

				// This code asks the user to proof-read their answer before submitting.
				
				System.out.print("Please proofread your problem and confirm you would like to submit it (Y/N): ");
				confirmSubmission = scnr.nextLine();

				// This code initializes the char for P1.submitProblem();
				charConfirmSubmission = confirmSubmission.charAt(0);

				// If a user inputs "Y," then the output registers that as a submitted problem.
				// If not, then the user is told that their problem was not submitted.
				
				if (charConfirmSubmission == 'Y' || charConfirmSubmission == 'y') {
					P1.submitProblem(multipleChoiceQuestion, Character.toUpperCase(charCorrectAnswer), sectionNumber,
							emailAddress);
					System.out.println("Thank you for submitting this new problem.");
				} else if (charConfirmSubmission == 'N' || charConfirmSubmission == 'n') {
					System.out.println("Problem was NOT submitted.");
				}
				System.out.println("");
				
			// This code runs if the user input "Q"	
			} else if (mainMenuChoice.equals("Q") || mainMenuChoice.equals("q")) {
				
				// This final print statement executes the closing statement.
				System.out.println("");
				System.out.println("==========================================");
				System.out.println("Thank you for using the CS302 Review Quiz!");

				P1.done();
				break;
				
// If a user does not give a valid menu choice, then it prints this statement.
			} else {
				System.out.println("");
				System.out.println("Sorry, but that menu choice was not recognized.");
				System.out.println("");

			}
		} while (mainMenuChoice != "Q" && mainMenuChoice != "q");

	}
}

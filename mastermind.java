import java.util.Scanner;

public class mastermind{
//dear god help me
  public static void main(String []args){
    gameLoop();

  }

  public static void gameLoop(){
    int attempts = 10;
    boolean won = false;
    int correctDigits = 0;
    char[] secretCode = generateSecretCode();
    Scanner reader = new Scanner(System.in);
    String userInput;

    System.out.println("Game Start!");
    System.out.println("===================");

    while(attempts > 0 && !won){
      System.out.println("Attempts Remaining: " + attempts);
      System.out.println("Enter a combination: ");
      userInput = reader.nextLine();
      correctDigits = 0;
      //check if userinput is valid before looping or make this section more robust. 

      for(int i = 0; i < 4; i++){
        if(userInput.charAt(i) == secretCode[i]){
          // System.out.println("nice");
          correctDigits+=1;
        }
      }
      if(correctDigits == 4){
        won = true;
      }
      else if(correctDigits > 0 && correctDigits < 4){
        System.out.println("Player guessed a correct number and position. Try again.");
      } 
      else {
        System.out.println("Player had no correct numbers. Try again.");
      } 

      attempts-=1;
    }
    reader.close();
    if(won){
      System.out.println("You won, Congradulations!");
    }
    else{
        System.out.println("Better luck next time");
    }

   }

  private static char[] generateSecretCode(){
    // call api to generate code, will have static code for time being
    char[] secretCode = {'1', '2', '3', '4'};
    
    return secretCode;
  }


  /*
    functions needed 
    a main loop the game logic
      - initialize the game parameters and tracking variables (attempts, guesses, the number as a question ) 
      run loop until attempts are out or player has won
        - check how many attempts are left
          if attempts remain then 
            display previous guesses and response data
            take player input
            check if all player input string matches stored value
              if all 4 characters match, state player is a winner and show stats (replay or exit)
          else 
            reveal actual code and tell player they have lost. 
            ask if they wish to replay

    function to do the api call to get the number,

  */
}
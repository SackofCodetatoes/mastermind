import java.util.Scanner;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;

public class mastermind{
  public static void main(String []args){
    gameLoop();

  }

  public static void gameLoop(){
    boolean won = false;
    int attempts = 10, correctDigits = 0, correctPositions = 0;
    char secretCode[] = generateSecretCode();
    boolean secretCodeNums[] = new boolean[8];

    for(int i = 0; i < 4; i++){
      secretCodeNums[Character.getNumericValue(secretCode[i])] = true;
    };

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
          correctPositions+=1;
        }
        // if(userInput.charAt(i))
      }
      if(correctPositions == 4){
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
    char secretCode[] = {'1', '2', '3', '4'};
    var client = HttpClient.newHttpClient();

    var request = HttpRequest.newBuilder(
      URI.create("https://www.random.org/integers/?num=4&min=0&max=7&col=1&base=10&format=plain&rnd=new"))
      .header("accept", "application/json")
      .build();
    
    // var response = client.send(request, new JsonBodyHandler<>(APOD.class));
    try{
      var response = client.send(request, HttpResponse.BodyHandlers.ofString());
      String responseString = response.body();
      //parse string
      responseString = responseString.replaceAll("[^0-9]", "");
      // System.out.println(responseString.length());
      responseString.getChars(0, responseString.length(), secretCode, 0);
    } catch (Exception e){
        e.printStackTrace();
    }
      // System.out.println(response.body().get().title);

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
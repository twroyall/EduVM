import java.util.Scanner;
import java.lang.*;


//Ops are going to be short ints.

class main{
  public static void main(String args[]){
    /*Argument structures:
        -eduvm filename.vmasm -[tags]             To run vmasm assembly.
        -eduvm filename.vmbc -[tags]              To run vmasm bytecode.
        -eduvm live                               To run the vmasm live console.
    */
    if(args.length == 0){
      System.out.println("Welcome to EduVM.");
      System.out.println("The syntax of this program is:");
      System.out.println("eduvm filename.eduvm -[tags]  | Use the eduvm interpreter.");
      System.out.println("eduvm live                    | Use the eduvm live console.");
    } else if (args.length > 0){
      if(args[0].equals("live")){ // Begin running the live console.
        CLI cmd = new CLI();
        boolean programState = true;
        Scanner input = new Scanner(System.in);
        String cmdToRun;

        System.out.println("Enter your commands here. To exit, type 'exit'. and for help type 'help'.");

        while(programState){
          cmdToRun = input.nextLine();
          cmd.interpret(cmdToRun);
        }
      } else {
        // I'll have to have it check for the file here, and if it exists, then run it.
      }
    }
  }
}

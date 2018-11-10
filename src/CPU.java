// register types:
//  -special purpose:
//   -program counter spc. This holds the location of the next instruction to be fetched from memory.
//   -accumulator sac. This holds the last performed result of an operation.
//   -instruction register sir. This holds the instruction currently being executed.
//  -general purpose:
//   -gpa, gpb, gpc, gpd, gpe, gpf, gpg, gph. These are general purpose registers a-h, you've got 8 of them.
//  -constant registers:
//   -zero register. crz.
//   -pi register. crp.
//   -one register. cro.
//   -negative one register. crn.

//Still to make: jump, move, call, jumpindirect, and making a block by going :whatever.

// jump address               If scb is 1 then you will set the sir to the address after this command.
// move address address               Just moves data from the first address to the second one.
// call                              This calls to a block of code expecting to be returned.
// jumpindirect                      This goes to the block without ever expecting to be returned.
// :nameofmyblock                    This makes a block of code.

public class CPU{
  public int spc = 0; // These are the special purpose registers.
  public int sac = 0; // This is the accumulator register.
  public int sir = 0; // This is the instruction register.
  public int scb = 0; // This is the conditional branch result register.
  public int gpa = 0; // These are the general purpose registers, from here on down.
  public int gpb = 0;
  public int gpc = 0;
  public int gpd = 0;
  public int gpe = 0;
  public int gpf = 0;
  public int gpg = 0;
  public int gph = 0;
  public int setItTo = 0; // Using this for juggling data.
  public boolean hasBeenInitialized = false; // For when running files.
  public int initLocation = 0; // Default starting location for the sir register.

  String[] commands; // This is for the later parsing of the commands.

  public int run(String command, boolean isLive, String[] memory){
    commands = command.split(" ");

    System.out.println("Running command " + commands[0]);

    if(commands[0].equals("add")){ // Add command.
      if(isInteger(commands[1]) && isInteger(commands[2])){ // If both arguments are integers.
        this.setRegister("sac", Integer.toString(Integer.parseInt(commands[1]) + Integer.parseInt(commands[2])));
      } else if(isInteger(commands[1]) == false && isInteger(commands[2]) == false){ // If neither argument is an integer.
        this.setRegister("sac", Integer.toString(this.getRegister(commands[1]) + this.getRegister(commands[2])));
      } else if(isInteger(commands[1]) == false && isInteger(commands[2]) == true ){ // If only the second argument is an integer.
        this.setRegister("sac", Integer.toString(this.getRegister(commands[1]) + Integer.parseInt(commands[2])));
      } else if(isInteger(commands[1]) == true && isInteger(commands[2]) == false){ // If only the first argument is an integer.
        this.setRegister("sac", Integer.toString(Integer.parseInt(commands[1]) + this.getRegister(commands[2])));
      }
      return 1;
    } else if(commands[0].equals("sub")){ // Substract command.
      if(isInteger(commands[1]) && isInteger(commands[2])){ // If both arguments are integers.
        this.setRegister("sac", Integer.toString(Integer.parseInt(commands[1]) - Integer.parseInt(commands[2])));
      } else if(isInteger(commands[1]) == false && isInteger(commands[2]) == false){ // If neither argument is an integer.
        this.setRegister("sac", Integer.toString(this.getRegister(commands[1]) - this.getRegister(commands[2])));
      } else if(isInteger(commands[1]) == false && isInteger(commands[2]) == true ){ // If only the second argument is an integer.
        this.setRegister("sac", Integer.toString(this.getRegister(commands[1]) - Integer.parseInt(commands[2])));
      } else if(isInteger(commands[1]) == true && isInteger(commands[2]) == false){ // If only the first argument is an integer.
        this.setRegister("sac", Integer.toString(Integer.parseInt(commands[1]) - this.getRegister(commands[2])));
      }
      return 1;
    } else if(commands[0].equals("mul")){ // Multiply command.
      if(isInteger(commands[1]) && isInteger(commands[2])){ // If both arguments are integers.
        this.setRegister("sac", Integer.toString(Integer.parseInt(commands[1]) * Integer.parseInt(commands[2])));
      } else if(isInteger(commands[1]) == false && isInteger(commands[2]) == false){ // If neither argument is an integer.
        this.setRegister("sac", Integer.toString(this.getRegister(commands[1]) * this.getRegister(commands[2])));
      } else if(isInteger(commands[1]) == false && isInteger(commands[2]) == true ){ // If only the second argument is an integer.
        this.setRegister("sac", Integer.toString(this.getRegister(commands[1]) * Integer.parseInt(commands[2])));
      } else if(isInteger(commands[1]) == true && isInteger(commands[2]) == false){ // If only the first argument is an integer.
        this.setRegister("sac", Integer.toString(Integer.parseInt(commands[1]) * this.getRegister(commands[2])));
      }
      return 1;
    } else if(commands[0].equals("div")){ // Divide command.
      if(isInteger(commands[1]) && isInteger(commands[2])){ // If both arguments are integers.
        this.setRegister("sac", Integer.toString(Integer.parseInt(commands[1]) / Integer.parseInt(commands[2])));
      } else if(isInteger(commands[1]) == false && isInteger(commands[2]) == false){ // If neither argument is an integer.
        this.setRegister("sac", Integer.toString(this.getRegister(commands[1]) / this.getRegister(commands[2])));
      } else if(isInteger(commands[1]) == false && isInteger(commands[2]) == true ){ // If only the second argument is an integer.
        this.setRegister("sac", Integer.toString(this.getRegister(commands[1]) / Integer.parseInt(commands[2])));
      } else if(isInteger(commands[1]) == true && isInteger(commands[2]) == false){ // If only the first argument is an integer.
        this.setRegister("sac", Integer.toString(Integer.parseInt(commands[1]) / this.getRegister(commands[2])));
      }
      return 1;
    } else if(commands[0].equals("mod")){ // Modulo command.
      if(isInteger(commands[1]) && isInteger(commands[2])){ // If both arguments are integers.
        this.setRegister("sac", Integer.toString(Integer.parseInt(commands[1]) % Integer.parseInt(commands[2])));
      } else if(isInteger(commands[1]) == false && isInteger(commands[2]) == false){ // If neither argument is an integer.
        this.setRegister("sac", Integer.toString(this.getRegister(commands[1]) % this.getRegister(commands[2])));
      } else if(isInteger(commands[1]) == false && isInteger(commands[2]) == true ){ // If only the second argument is an integer.
        this.setRegister("sac", Integer.toString(this.getRegister(commands[1]) % Integer.parseInt(commands[2])));
      } else if(isInteger(commands[1]) == true && isInteger(commands[2]) == false){ // If only the first argument is an integer.
        this.setRegister("sac", Integer.toString(Integer.parseInt(commands[1]) % this.getRegister(commands[2])));
      }
      return 1;
    } else if(commands[0].equals("set")){ // Set register to value.
        if(isInteger(commands[2])){ // If this register is being set to a numerical value.
          this.setRegister(commands[1], commands[2]);
        } else if(isInteger(commands[2]) == false){ // If this register is being set to the value of another register.
          setItTo = this.getRegister(commands[2]);
          this.setRegister(commands[1], Integer.toString(setItTo));
        }
        return 1;
    } else if(commands[0].equals("dump") && commands[1].equals("registers")){ // Dump every register.
      System.out.println("---------------------");
      System.out.println("Register spc: " + spc);
      System.out.println("Register sac: " + sac);
      System.out.println("Register sir: " + sir);
      System.out.println("Register scb: " + scb);
      System.out.println("Register gpa: " + gpa);
      System.out.println("Register gpb: " + gpb);
      System.out.println("Register gpc: " + gpc);
      System.out.println("Register gpd: " + gpd);
      System.out.println("Register gpe: " + gpe);
      System.out.println("Register gpf: " + gpf);
      System.out.println("Register gpg: " + gpg);
      System.out.println("Register gph: " + gph);
      System.out.println("---------------------");
      return 1;
    } else if(commands[0].equals("ifless")){
      if(isInteger(commands[1]) && isInteger(commands[2])){ // If both arguments are integers.
        if(Integer.parseInt(commands[1]) < Integer.parseInt(commands[2])){
          this.setRegister("scb", "1");
        } else{
          this.setRegister("scb", "0");
        }
      } else if(isInteger(commands[1]) == false && isInteger(commands[2]) == false){ // If neither argument is an integer.
        if(this.getRegister(commands[1]) < this.getRegister(commands[2])){
          this.setRegister("scb", "1");
        } else{
          this.setRegister("scb", "0");
        }
      } else if(isInteger(commands[1]) == false && isInteger(commands[2]) == true ){ // If only the second argument is an integer.
        if(this.getRegister(commands[1]) < Integer.parseInt(commands[2])){
          this.setRegister("scb", "1");
        } else{
          this.setRegister("scb", "0");
        }
      } else if(isInteger(commands[1]) == true && isInteger(commands[2]) == false){ // If only the first argument is an integer.
        if(Integer.parseInt(commands[1]) < this.getRegister(commands[2])){
          this.setRegister("scb", "1");
        } else{
          this.setRegister("scb", "0");
        }
      }
      return 1;
    } else if(commands[0].equals("ifequal")){
      if(isInteger(commands[1]) && isInteger(commands[2])){ // If both arguments are integers.
        if(Integer.parseInt(commands[1]) == Integer.parseInt(commands[2])){
          this.setRegister("scb", "1");
        } else{
          this.setRegister("scb", "0");
        }
      } else if(isInteger(commands[1]) == false && isInteger(commands[2]) == false){ // If neither argument is an integer.
        if(this.getRegister(commands[1]) == this.getRegister(commands[2])){
          this.setRegister("scb", "1");
        } else{
          this.setRegister("scb", "0");
        }
      } else if(isInteger(commands[1]) == false && isInteger(commands[2]) == true ){ // If only the second argument is an integer.
        if(this.getRegister(commands[1]) == Integer.parseInt(commands[2])){
          this.setRegister("scb", "1");
        } else{
          this.setRegister("scb", "0");
        }
      } else if(isInteger(commands[1]) == true && isInteger(commands[2]) == false){ // If only the first argument is an integer.
        if(Integer.parseInt(commands[1]) == this.getRegister(commands[2])){
          this.setRegister("scb", "1");
        } else{
          this.setRegister("scb", "0");
        }
      }
      return 1;
    } else if(commands[0].equals("ifgreater")){ // If both arguments are integers.
      if(isInteger(commands[1]) && isInteger(commands[2])){
        if(Integer.parseInt(commands[1]) > Integer.parseInt(commands[2])){
          this.setRegister("scb", "1");
        } else{
          this.setRegister("scb", "0");
        }
      } else if(isInteger(commands[1]) == false && isInteger(commands[2]) == false){ // If neither argument is an integer.
        if(this.getRegister(commands[1]) > this.getRegister(commands[2])){
          this.setRegister("scb", "1");
        } else{
          this.setRegister("scb", "0");
        }
      } else if(isInteger(commands[1]) == false && isInteger(commands[2]) == true ){ // If only the second argument is an integer.
        if(this.getRegister(commands[1]) > Integer.parseInt(commands[2])){
          this.setRegister("scb", "1");
        } else{
          this.setRegister("scb", "0");
        }
      } else if(isInteger(commands[1]) == true && isInteger(commands[2]) == false){ // If only the first argument is an integer.
        if(Integer.parseInt(commands[1]) > this.getRegister(commands[2])){
          this.setRegister("scb", "1");
        } else{
          this.setRegister("scb", "0");
        }
      }
      return 1;
    } else if(commands[0].equals("and")){ // And operation.
      if(isInteger(commands[1]) && isInteger(commands[2])){ // If both arguments are integers.
        this.setRegister("sac", Integer.toString((Integer.parseInt(commands[1]) & Integer.parseInt(commands[2]))));
      } else if(isInteger(commands[1]) == false && isInteger(commands[2]) == false){ // If neither argument is an integer.
        this.setRegister("sac", Integer.toString((this.getRegister(commands[1]) & this.getRegister(commands[2]))));
      } else if(isInteger(commands[1]) == false && isInteger(commands[2]) == true ){ // If only the second argument is an integer.
        this.setRegister("sac", Integer.toString((this.getRegister(commands[1]) & Integer.parseInt(commands[2]))));
      } else if(isInteger(commands[1]) == true && isInteger(commands[2]) == false){ // If only the first argument is an integer.
        this.setRegister("sac", Integer.toString((Integer.parseInt(commands[1]) & this.getRegister(commands[2]))));
      }
      return 1;
    } else if(commands[0].equals("or")){ // Or operation.
      if(isInteger(commands[1]) && isInteger(commands[2])){ // If both arguments are integers.
        this.setRegister("sac", Integer.toString((Integer.parseInt(commands[1]) | Integer.parseInt(commands[2]))));
      } else if(isInteger(commands[1]) == false && isInteger(commands[2]) == false){ // If neither argument is an integer.
        this.setRegister("sac", Integer.toString((this.getRegister(commands[1]) | this.getRegister(commands[2]))));
      } else if(isInteger(commands[1]) == false && isInteger(commands[2]) == true ){ // If only the second argument is an integer.
        this.setRegister("sac", Integer.toString((this.getRegister(commands[1]) | Integer.parseInt(commands[2]))));
      } else if(isInteger(commands[1]) == true && isInteger(commands[2]) == false){ // If only the first argument is an integer.
        this.setRegister("sac", Integer.toString((Integer.parseInt(commands[1]) | this.getRegister(commands[2]))));
      }
      return 1;
    } else if(commands[0].equals("not")){ // Inversion / not / complement operation.
      if(isInteger(commands[1])){ // If the arguments is an integer.
        this.setRegister("sac", Integer.toString((~Integer.parseInt(commands[1]))));
      } else{ // If the argument is not an integer.
        this.setRegister("sac", Integer.toString((~this.getRegister(commands[1]))));
      }
      return 1;
    } else if(commands[0].equals("xor")){ // Xor operation.
      if(isInteger(commands[1]) && isInteger(commands[2])){ // If both arguments are integers.
        this.setRegister("sac", Integer.toString((Integer.parseInt(commands[1]) ^ Integer.parseInt(commands[2]))));
      } else if(isInteger(commands[1]) == false && isInteger(commands[2]) == false){ // If neither argument is an integer.
        this.setRegister("sac", Integer.toString((this.getRegister(commands[1]) ^ this.getRegister(commands[2]))));
      } else if(isInteger(commands[1]) == false && isInteger(commands[2]) == true ){ // If only the second argument is an integer.
        this.setRegister("sac", Integer.toString((this.getRegister(commands[1]) ^ Integer.parseInt(commands[2]))));
      } else if(isInteger(commands[1]) == true && isInteger(commands[2]) == false){ // If only the first argument is an integer.
        this.setRegister("sac", Integer.toString((Integer.parseInt(commands[1]) ^ this.getRegister(commands[2]))));
      this.setRegister("sac", Integer.toString(~(this.getRegister(commands[1]) & this.getRegister(commands[2]))));
    }
      return 1;
    } else if(commands[0].equals("nand")){ // Nand operation (And + Not/Inversion/Complement).
      if(isInteger(commands[1]) && isInteger(commands[2])){ // If both arguments are integers.
        this.setRegister("sac", Integer.toString(~(Integer.parseInt(commands[1]) & Integer.parseInt(commands[2]))));
      } else if(isInteger(commands[1]) == false && isInteger(commands[2]) == false){ // If neither argument is an integer.
      } else if(isInteger(commands[1]) == false && isInteger(commands[2]) == true ){ // If only the second argument is an integer.
        this.setRegister("sac", Integer.toString(~(this.getRegister(commands[1]) & Integer.parseInt(commands[2]))));
      } else if(isInteger(commands[1]) == true && isInteger(commands[2]) == false){ // If only the first argument is an integer.
        this.setRegister("sac", Integer.toString(~(Integer.parseInt(commands[1]) & this.getRegister(commands[2]))));
      }
      return 1;
    } else if(commands[0].equals("nor")){ // Nor operation (Or + Not/Inversion/Complement).
      if(isInteger(commands[1]) && isInteger(commands[2])){ // If both arguments are integers.
        this.setRegister("sac", Integer.toString(~(Integer.parseInt(commands[1]) | Integer.parseInt(commands[2]))));
      } else if(isInteger(commands[1]) == false && isInteger(commands[2]) == false){ // If neither argument is an integer.
        this.setRegister("sac", Integer.toString(~(this.getRegister(commands[1]) | this.getRegister(commands[2]))));
      } else if(isInteger(commands[1]) == false && isInteger(commands[2]) == true ){ // If only the second argument is an integer.
        this.setRegister("sac", Integer.toString(~(this.getRegister(commands[1]) | Integer.parseInt(commands[2]))));
      } else if(isInteger(commands[1]) == true && isInteger(commands[2]) == false){ // If only the first argument is an integer.
        this.setRegister("sac", Integer.toString(~(Integer.parseInt(commands[1]) | this.getRegister(commands[2]))));
      }
      return 1;
    } else if(commands[0].equals("xnor")){ // Xnor operation (Xor + Not/Inversion/Complement).
      if(isInteger(commands[1]) && isInteger(commands[2])){ // If both arguments are integers.
        this.setRegister("sac", Integer.toString(~(Integer.parseInt(commands[1]) ^ Integer.parseInt(commands[2]))));
      } else if(isInteger(commands[1]) == false && isInteger(commands[2]) == false){ // If neither argument is an integer.
        this.setRegister("sac", Integer.toString(~(this.getRegister(commands[1]) ^ this.getRegister(commands[2]))));
      } else if(isInteger(commands[1]) == false && isInteger(commands[2]) == true ){ // If only the second argument is an integer.
        this.setRegister("sac", Integer.toString(~(this.getRegister(commands[1]) ^ Integer.parseInt(commands[2]))));
      } else if(isInteger(commands[1]) == true && isInteger(commands[2]) == false){ // If only the first argument is an integer.
        this.setRegister("sac", Integer.toString(~(Integer.parseInt(commands[1]) ^ this.getRegister(commands[2]))));
      }
      return 1;
    } else if(commands[0].equals("read")){ //Read operator, you "read data_from_one_address address_to_where_its_going".
       setRegister(commands[2], memory[Integer.parseInt(commands[1])]);
       return 1;
    } else if(commands[0].equals("write")){ // Write operator, you "write data address_getting_written_to".
      memory[Integer.parseInt(commands[2])] = commands[1];
      return 1;
    } else if(commands[0].equals("jump") && isLive == false){
      // TODO: Make this able to interpret the value of a register as well.
      if(isInteger(commands[1])){ // If it's jumping to a direct location in memory represented by a number.
        System.out.println("Jumping to an int.");
        setRegister("sir", commands[1]);
        return 0;
      } else{ // If it's jumping to a location stored in a register.
      System.out.println("Jumping to a register.");
        setRegister("sir", Integer.toString(getRegister(commands[1])));
        return 0;
      }
    } else if(commands[0].equals("shutdown")){
      return -1;
    } else if(commands[0].equals("move")){ // I need to make this work with numerical memory representations as well as register names.
       // setRegister(commands[2], commands[1]);
       // return 1;
       //
       System.out.println("isInteger result of first term: " + Boolean.toString(isInteger(commands[1])));
       System.out.println("isInteger result of second term: " + Boolean.toString(isInteger(commands[2])));
       if(isInteger(commands[1]) && isInteger(commands[2])){ // Move address address
        System.out.println("Moving register to register.");
        memory[Integer.parseInt(commands[1])] = memory[Integer.parseInt(commands[2])];
       } else if(isInteger(commands[1]) == false && isInteger(commands[2]) == true){ // Move register address
       //
     } else if(isInteger(commands[1]) == true && isInteger(commands[2]) == false){ // Move address register
       setRegister(commands[2], memory[Integer.parseInt(commands[1])]);
     } else if(isInteger(commands[1]) == false && isInteger(commands[2]) == false){ // Move register register
       //
     }
    } else{
      return 1; // Return 1 so that only 1 is added to sir and the next instruction gets read.
    }
    return 1;
  }

  public void initialize(int startingSir){
    //hasBeenInitialized = true;
    //initLocation = startingSir;
  }

  public void start(String[] memory){
    boolean programState = true;
    while(programState){
      int nextSir = this.run(memory[this.sir], false, memory);

      if(nextSir != -2){
        setRegister("sir", Integer.toString(this.sir + nextSir));
      } else{
        continue;
      }

      if(nextSir == -1){
        break;
      }
    }
  }

  public void setRegister(String register, String valueToConvert){
    int value = Integer.parseInt(valueToConvert);
    if(register.equals("spc")){
      this.spc = value;
    } else if(register.equals("sac")){
      this.sac = value;
    } else if(register.equals("sir")){
      this.sir = value;
    } else if(register.equals("gpa")){
      this.gpa = value;
    } else if(register.equals("gpb")){
      this.gpb = value;
    } else if(register.equals("gpc")){
      this.gpc = value;
    } else if(register.equals("gpd")){
      this.gpd = value;
    } else if(register.equals("gpe")){
      this.gpe = value;
    } else if(register.equals("gpf")){
      this.gpf = value;
    } else if(register.equals("gpg")){
      this.gpg = value;
    } else if(register.equals("gph")){
      this.gph = value;
    } else if (register.equals("scb")){
      this.scb = value;
    } else {
      throw new Error("Attempt to set register not detected by name of " + register + " to " + value);
    }
  }

  public int getRegister(String register){
    if(register.equals("spc")){
      return this.spc;
    } else if(register.equals("sac")){
      return this.sac;
    } else if(register.equals("sir")){
      return this.sir;
    } else if(register.equals("gpa")){
      return this.gpa;
    } else if(register.equals("gpb")){
      return this.gpb;
    } else if(register.equals("gpc")){
      return this.gpc;
    } else if(register.equals("gpd")){
      return this.gpd;
    } else if(register.equals("gpe")){
      return this.gpe;
    } else if(register.equals("gpf")){
      return this.gpf;
    } else if(register.equals("gpg")){
      return this.gpg;
    } else if(register.equals("gph")){
      return this.gph;
    } else {
      throw new Error("Attempt to get register not detected by name of " + register);
    }
  }

  public static boolean isInteger(String s){
      try{
          Integer.parseInt(s);
      } catch(NumberFormatException e){
          return false;
      } catch(NullPointerException e){
          return false;
      }

      return true; // Should only have gotten to this point if you've not returned false by now.
  }
}

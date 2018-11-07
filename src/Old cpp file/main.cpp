#include <iostream>
using namespace std;

class myClass{
  public:
  string name;

  myClass(){
    name = "Unnamed.";
  }

  myClass(string nameToAssign){
    name = nameToAssign;
  }

  void printName(){
    cout << "this class's name is " << name << endl;
  }
};

int main(){
  char str[100];
  myClass obj1("namedobject");

  cout << "This is going to be the basis of the VMASM." << endl;
  // cout << "What's your name?" << endl;
  // cin >> str;
  // cout << "Hi " << str << "!";
  obj1.printName();
}

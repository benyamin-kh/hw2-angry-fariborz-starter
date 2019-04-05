package ir.ac.kntu;

import java.util.Scanner;

public class AngryFariborz {
    public static int step = 0;
    
    public static double changeCharToOperator(double a,double b,char c){
        double ans=0.0;
        if (c=='+'){
            ans= a+b;
        }
        else if(c=='-'){
            ans= a-b;
        }
        else if(c=='*'){
            ans= a*b;
        }
        else if(c=='/'){
            ans= a/b;
        }

        return ans;
    }
    public static void printAllKLength(char[] set, int k,String[] fu) 
        { 
            int n = set.length;  
            printAllKLengthRec(set, fu,"", n, k); 
        } 

    public static void printAllKLengthRec(char[] set, String[] fu ,
                               String prefix,  
                               int n, int k) {

      
        if (k == 0){  
            fu[step]=prefix;
            step++;       
            return;
        } 
  
     
        for (int i = 0; i < n; i++){ 
    
  
            String newPrefix = prefix + set[i];  
          
        
            printAllKLengthRec(set, fu,newPrefix,  
                                n, k - 1);  
        } 
    } 

    
    public static boolean primeNumberChecker(long number){
        boolean checker=true;
        if(number==0 || number==1){
            checker=false;
        }
        for(int i=2;i<number;i++){
            if(number%i==0){
                checker=false;
            }
        }
        return checker;
    }
    public static String[] myString(String[] num,int k,int n,int p){
            String[] ro=new String[n-k+1];
            for(int i=0;i<n-k+1;i++){
                ro[i]="";
            }
            
            for(int j=p;j<=p+k-1;j++){
                ro[p]+=num[j];
                
            }
            for(int i=0;i<p;i++){
                ro[i]=num[i];
            }
            int y=0;
            for(int j=p+1;j<n-k+1;j++){
                ro[j]=num[p+k+y];
                y++;
            }
            return ro;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        
       
        char[] operator = new char[]{'+','-','*','/'};
        String[] numbers=new String[input.length()];
        for(int i=0;i<input.length();i++){
            numbers[i]=Character.toString(input.charAt(i));
        } 
        int numbersLength=numbers.length;
        if(numbers.length>1){
        for(int numberAttach=1;numberAttach<=numbersLength;numberAttach++){
            for(int attachPlace=0;attachPlace<=numbersLength-numberAttach;attachPlace++){
               String[] newString=myString(numbers, numberAttach, numbersLength, attachPlace);
               double testIt=Double.parseDouble(newString[0]);
               int freeSpace=newString.length-1;
               int operatorArraySize=1;
               for(int  i=1;i<=freeSpace;i++){
                   operatorArraySize=operatorArraySize*4;
               }
               String[] operatorArray=new String[operatorArraySize];
               for(int i=0;i<operatorArraySize;i++){
                   operatorArray[i]="";
               }
               printAllKLength(operator, freeSpace,operatorArray);
               for(int i=0;i<operatorArraySize;i++){
               for( int j=0;j<freeSpace;j++){
               testIt=changeCharToOperator(testIt, Double.parseDouble(newString[j+1]), operatorArray[i].charAt(j)) ;
                
                 
               } 
               long a=(long)testIt;
                              
            
               if(primeNumberChecker(a)){
                   System.out.println("YES");
                   System.out.print(newString[0]);
                   for(int j=0;j<freeSpace;j++){ 
                       System.out.print(operatorArray[i].charAt(j));
                       System.out.print(newString[j+1]);

                
                   }
                   System.out.print(" $ "+a+"\n");
               }
               
              
            
            
            
             
              
               }
            
            
            
            
            
             
            }//sheklhae adda
            
            
            
            
            
            
            
            
            
        }  
     }
    if(primeNumberChecker(Long.parseLong(input))){
        System.out.println("YES");
    }
    else{
        System.out.println("NO");
    }
        scanner.close();
    }
}

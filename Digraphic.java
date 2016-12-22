import java.util.*;
import java.io.*;
public class Digraphic {
    public static void main(String[] args) throws Exception {
       
        Scanner keyboard = new Scanner(System.in);
        
        
        
        //Read the plain text file for encrypt
        FileReader file = new FileReader("text.txt");
        BufferedReader read = new BufferedReader(file);
 
        String text = "";
        String line = read.readLine();
        
        while(line != null){
            text += line;
            
            line = read.readLine();
            
        }
        
        //Read the decrypt file
        Scanner file3 = new Scanner(new File("text2.txt"));
        StringBuilder sb = new StringBuilder();
        
        while(file3.hasNext()) {
             sb.append(file3.nextLine());
        }

        String[] code = sb.toString().split(" ");
        
        
        
        
        //Read the key file
        FileReader file2 = new FileReader("key.txt");
        BufferedReader read2 = new BufferedReader(file2);
        
        
        String key = "";
        String line2 = read2.readLine();
        
        while(line2 != null){
            if (line2.trim()=="") continue;
            key += line2;
            //key += "\n";
            
            line2 = read2.readLine();
            
        }
        
        
        
       //Get the user's input
       Scanner k = new Scanner(System.in);
       
       System.out.println("For encryption enter 1");
       System.out.println("For decryption enter 2");
       int user = k.nextInt();
       
       System.out.println();
       if (user == 1) {
           System.out.println("****   Encryption Method   ****");
           finalStepEncrypt(separateText(plainTextMethod(text)), Digraphic(key));
       }    
       else if (user == 2) {
           
            try {
                System.out.println("****   Decryption Method   ****");
                Decrypt(code,Digraphic(key));
            } catch (IOException e){
                System.out.println("Can not continue with Decrypt method");
            }
           
       }
       else 
           System.out.println("Wrong input, try again by re-running the program");
           
       
       
        
        
    }
    
    
    
    
    //compute the digraph and return it
    public static char[][] Digraphic(String key){
        
        char[][] digraph = new char[5][5];
        
        int x = 0;
        int i = 0;
        int j = 0;
        while(x < key.length()){
            if (i < 5 && j < 5){
                digraph[i][j] = key.charAt(x);
                               
                if (j < 5){
                  
                    j++;
                } 
                   
            }    
            else {
                   j = 0;
                    i++;
                    digraph[i][j] = key.charAt(x); 
       
                    j++;
            }    
            
            x++;
        }
        
        String word = "";

       
        for (int m = 0; m < 5; m ++){
            for(int n = 0; n < 5; n++){
               
                if (digraph[m][n] != 0){
                    word += digraph[m][n];
                }
            }
        }
       
        word = word.substring(0, word.length());
        String newWord = word;
            
       
       
        char [] alphabet = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        if ( word.length() < 25){
            char c1 = ' ';
            char c2 = ' ';

            int track = 0;
            int remain = word.length() -1;
          
            char [] charKeepTrack;

            for (int u = 0; u < 25; u++){
                for(int y = 0; y < word.length(); y++){
             
                      c1 = alphabet[u];
                      c2 = word.charAt(y);
                      if (c1 == c2){
                          track = 1;
             
                      }   
                }
                if (track == 0)      
                    newWord += alphabet[u];       
                else
                    track = 0;
                }
    
        }
      
        char [][] newDigraph = new char[5][5];
        int c = 0;
        for (int m = 0; m < 5; m++){
            for (int n = 0; n < 5; n++){
                newDigraph[m][n]= newWord.charAt(c);
                c++;
            }
        }
      
        System.out.println();
      
        for (int m = 0; m < 5; m++){
            for (int n = 0; n < 5; n++){
                System.out.print(" " +  newDigraph[m][n]);
                c++;
            }
            System.out.println();
        }
        System.out.println();

     return newDigraph;
     
    }
    
    //This method checks repeated letter and adds Z if needed at the end
    public static String plainTextMethod(String text){
          
        
        String newText = "";
        
        for (int n = 0; n < text.length(); n++){
            if (n < text.length() - 1  ){
                if( text.charAt(n) == text.charAt(n+1) ){
              
                    newText += text.charAt(n);
                    if (text.charAt(n) == 'X')
                        newText += 'Q';
                    else
                        newText += 'X';
                }
                else if (text.charAt(n) != ' ') {
                    newText += text.charAt(n);
                }
            }
        
            else {
                newText += text.charAt(n);  
            }
     
        }
        
        if (newText.length() % 2 != 0)
            newText += 'Z';
        
        
        
        return newText;
    }
    
    //this method seperates the text into pairs
    public static String[] separateText (String text){
        
        int len = text.length();
        int size = len/2;
        String[] arr = new String[size];
        
        int j = 0;
        for (int i = 0; i < arr.length; i++){
            
            arr[i] = text.substring(j, j+2);
            j += 2;
              
        }
        
        
       
        return arr;
    }
    
    //this method computs the final result and prints it to the screen and file
    public static void finalStepEncrypt(String[] plaintext, char[][] digraph ) throws IOException {
        int len = plaintext.length;
        String[] finalResult = new String[len];
        String temp = "";
        char c1 = ' ';
        char c2 = ' ';
        
        int c1numRow = -1;
        int c1numCol= -1;
        int c2numRow= -1;
        int c2numCol= -1;
        int num2= 0;
        
        //do the comparision
        for (int u = 0; u < plaintext.length ; u++){
            c1 = plaintext[u].charAt(0);
            c2 = plaintext[u].charAt(1);
            
            for (int i = 0; i < 5; i++){
                for (int j = 0; j < 5; j++){
                    if (digraph[i][j] == c1){
                        c1numRow = i;
                        c1numCol = j;
                       
                    } 
                    if (digraph[i][j] == c2){
                        c2numRow = i;
                        c2numCol = j;
                       
                    } 
                    
                        if ((c1numCol != -1 && c2numCol != -1) && c1numCol == c2numCol && (c1numRow != -1 && c2numRow != -1)){
                            if (c1numRow < 4){
                               
                                temp += digraph[c1numRow + 1][j];
                                
                            }    
                            else {
                               temp += digraph[0][j];
                            }   

                            if (c2numRow < 4){
                                
                                temp += digraph[c2numRow+1][j];
                                
                            }
                            else {
                                temp += digraph[0][j];
                                

                            }
                            finalResult[u] = temp;
                            
                                   
                            temp = " ";
                            c1numCol = -1;
                            c2numCol = -1;
                                   
                        }
                        
                        
                        
                        else  if ((c1numRow != -1 && c2numRow != -1) && c1numRow == c2numRow &&  (c1numCol != -1 && c2numCol != -1)){
                            if (c1numCol < 4){
                                temp += digraph[i][c1numCol + 1];
                            }    
                            else {
                               temp += digraph[i][0];
                            }   

                            if (c2numCol < 4){
                                temp += digraph[i][c2numCol+1];
                            }
                            else{
                                temp += digraph[i][0];

                            }
                            finalResult[u] = temp;
                            
                            temp = " ";
                            c1numRow = -1;
                            c2numRow = -1;
                        }
                        
                        else if ((c1numRow != -1 && c2numRow != -1) && (c1numCol != -1 && c2numCol != -1)&& (c1numRow != c2numRow) && (c1numCol != c2numCol) ){
                           
                            temp += digraph[c1numRow][c2numCol];
                            temp += digraph[c2numRow][c1numCol];
                            finalResult[u] = temp;
                            c1numRow = -1;
                            c2numRow = -1;
                            c1numCol = -1;
                            c2numCol = -1;
                            temp = " ";
                            
                        }    
                } 
      
            }      
                        
        }
        
        String finalText = "";
       
        System.out.print("Eecrypted text:  ");
        for (int g = 0; g < finalResult.length; g++){
            finalText = finalText.concat(finalResult[g]);
            System.out.print(finalResult[g]);
           
        }
        System.out.println("\n");

        
              
        File file = new File("ResultFileFromEncrypt.txt");
        file.createNewFile();
        
        FileWriter writer = new FileWriter(file);
        writer.write(finalText);
        writer.flush();
        writer.close();      
              
              
       
    }
    
    //this method does the decryption
    public static void Decrypt(String[] arr, char[][] digraph) throws IOException {
        
       
        int len = arr.length;
        String[] finalResult = new String[len];
        String temp = " ";
        char c1 = ' ';
        char c2 = ' ';
        
        int c1numRow = -1;
        int c1numCol= -1;
        int c2numRow= -1;
        int c2numCol= -1;
        int num2= 0;
        
        //do the comparision 
        for (int u = 0; u < arr.length ; u++){
            c1 = arr[u].charAt(0);
            c2 = arr[u].charAt(1);
            
            for (int i = 0; i < 5; i++){
                for (int j = 0; j < 5; j++){
                    if (digraph[i][j] == c1){
                        c1numRow = i;
                        c1numCol = j;
                       
                    } 
                    if (digraph[i][j] == c2){
                        c2numRow = i;
                        c2numCol = j;
                        
                      
                    } 
                    
                        if ((c1numCol != -1 && c2numCol != -1) && c1numCol == c2numCol && (c1numRow != -1 && c2numRow != -1)){
                           
                            if (c1numRow > 0){
                              
                                temp += digraph[c1numRow - 1][j];
                                

                            }    
                            else {
                               temp += digraph[4][j];
                            }
                            
                           
                            
                            if (c2numRow > 0){
                               
                                temp += digraph[c2numRow - 1][j];
                                
                            }
                            else {
                                
                                temp += digraph[4][j];
                                

                            }
                            finalResult[u] = temp;
                            
                                  
                            temp = "";
                            c1numCol = -1;
                            c2numCol = -1;
                        }
                        
                        
                        
                        else  if ((c1numRow != -1 && c2numRow != -1) && c1numRow == c2numRow && (c1numCol != -1 && c2numCol != -1)){
                            if (c1numCol > 0){
                                temp += digraph[i][c1numCol - 1];
                            }    
                            else {
                               temp += digraph[i][4];
                            }   

                            if (c2numCol > 0){
                                temp += digraph[i][c2numCol-1];
                            }
                            else{
                                temp += digraph[i][4];

                            }
                            finalResult[u] = temp;
                            
                            temp = "";
                            c1numRow = -1;
                            c2numRow = -1;
                        }
                        
                        else if ((c1numRow != -1 && c2numRow != -1) && (c1numCol != -1 && c2numCol != -1)&& (c1numRow != c2numRow) && (c1numCol != c2numCol) ){
                            
                            temp += digraph[c1numRow][c2numCol];
                            temp += digraph[c2numRow][c1numCol];
                            finalResult[u] = temp;
                            c1numRow = -1;
                            c2numRow = -1;
                            c1numCol = -1;
                            c2numCol = -1;
                            temp = "";
                            
                        }
                        
                } 
           
                
            } 
            
            
                
                        
        }
        
        
        
        
        
        
        String finalText = "";
       
        System.out.print("Decrypted text:  ");
        for (int g = 0; g < finalResult.length; g++){
            finalText = finalText.concat(finalResult[g]);
            System.out.print(finalResult[g] + " ");
        }
        System.out.println();

        
        System.out.println("Final decrypted text " + finalText);   
        System.out.println();
        String newText = "";
        for (int y = 0; y < finalText.length(); y++){
            if (finalText.charAt(y) == 'X' || finalText.charAt(y) == 'Q'){
                if ( (y > 0) && (finalText.charAt(y-1) != finalText.charAt(y+1)) )
                    newText += finalText.charAt(y);
            }
            else
                newText += finalText.charAt(y);
        }
        
        //checks for the added Z at the end
        if (newText.length() % 2 != 0 && (newText.substring(newText.length() -2, ( newText.length() -1)).equals('Z')))
            newText = newText.substring(0, ( newText.length() -1));
            
            
        
            
        //write it to file    
        File file = new File("ResultFileFrom Decrypt.txt");
        file.createNewFile();
        
        FileWriter writer = new FileWriter(file);
        writer.write(newText);
        writer.flush();
        writer.close();
        
       
    } 
}    
           
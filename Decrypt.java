import java.io.BufferedReader;
import java.io.FileReader;

class Decrypt
{
    public static void main(String args[])
    {
        char[] key = {'u', 'l', 'c', 'b', 'x', 's', 'o'};

        //Get file
        int cipherLen = 28119;
        char[] cipher = new char[cipherLen];
        try {
            BufferedReader reader = new BufferedReader(new FileReader("cipher.txt"));
            reader.read(cipher);
            reader.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        //Decrypt
        for (int index = 0; index < cipherLen; ++index) {
            int plain = cipher[index] - key[index % 7];
            if (plain < 0) { plain += 26; }
            cipher[index] = (char)(plain + 'a');
        }
        System.out.print(cipher); //print plain
    }
}
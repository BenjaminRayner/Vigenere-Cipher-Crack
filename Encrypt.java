import java.io.BufferedReader;
import java.io.FileReader;

class Encrypt
{
    public static void main(String args[])
    {
        char[] key = {'u', 'l', 'c', 'b', 'x', 's', 'o'};

        //Get file
        int plainLen = 28119;
        char[] plain = new char[plainLen];
        try {
            BufferedReader reader = new BufferedReader(new FileReader("plain.txt"));
            reader.read(plain);
            reader.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        //Encrypt
        for (int index = 0; index < plainLen; ++index) {
            int cipher = plain[index] + (key[index % 7] - 'a');
            if (cipher > 122) { cipher -= 26; }
            plain[index] = (char)cipher;
        }
        System.out.print(plain); //print cipher
    }
}
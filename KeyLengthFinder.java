import java.io.BufferedReader;
import java.io.FileReader;

class KeyLengthFinder
{
    public static void main(String args[])
    {
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

        //Frequency analysis for 128 keyLens.
        for(int keyLen = 1; keyLen <= 128; ++keyLen)
        {
            //For every character in the key...
            for(int keyOffset = 0; keyOffset < keyLen; ++keyOffset)
            {
                //Find every letter in cipher encrypted by the same letter in the key. Count occurances.
                int[] charFreq = new int[26];
                int numBlock = 0;
                for (int index = keyOffset; index < cipherLen; index += keyLen)
                {
                    char letter = cipher[index];
                    ++charFreq[letter - 97]; //-97 to map lowercase to start at 0 index
                    ++numBlock;
                }

                double maxLikely = 0;
                for (int letter = 0; letter < 26; ++letter) {
                    if (charFreq[letter] > maxLikely) { maxLikely = charFreq[letter]; }
                }
                maxLikely /= numBlock; //Percent used

                //Frequency analysis. Only check if most frequent char is at least 10% used ('e' usage ~13%)
                if (maxLikely > 0.10) {
                    System.out.println("For keyLen: " + keyLen + "; keyOffset: " + keyOffset + ";");
                    for (int letter = 0; letter < 26; ++letter) {
                        double percentage = (double) charFreq[letter] / numBlock;
                        System.out.println((char)(letter + 97) + ": " + percentage);
                    }
                }
            }
        }
    }
}
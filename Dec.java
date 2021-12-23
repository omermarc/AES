import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Dec {
    private byte[][] key1;
    private byte[][] key2;


    public Dec(ArrayList<byte[][]> keys) {
        key1 = keys.get(0);
        key2 = keys.get(1);
    }
    public ArrayList<byte[][]> Decription(ArrayList<byte[][]> Cypher) throws IOException {
        if (Cypher == null)
            return null;



            // First iter with key2:
            Enc.XorFunc( Cypher,key2);
            Cypher=Enc.SwapIndexes(Cypher);


            // Second Iteration key1

           Enc.XorFunc(Cypher, key1);
           return Enc.SwapIndexes(Cypher);

        }




    public static void DecToFile(ArrayList<byte[][]>dec, String path) {
        byte[]ToFile= new byte[dec.size()*16];
        int index=0;
        for(int i=0; i<dec.size();i++)
        {
            for(int j=0; j<4;j++)
            {
                for(int k=0; k<4; k++)
                {
                    ToFile[index]= dec.get(i)[j][k];
                    index++;

                }
            }
        }
        try (
                FileOutputStream fileOutputStream = new FileOutputStream(path)) {
            fileOutputStream.write(ToFile);
        } catch (Exception e) {
            System.out.println("there is an error");
        }
    }



}

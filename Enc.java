import com.sun.javafx.font.Metrics;
import com.sun.org.apache.bcel.internal.generic.ANEWARRAY;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Key;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



import java.nio.file.Files;


public class Enc {
    private byte[][] key1;
    private byte[][] key2;


// convert file of bytes to block.


    public Enc(ArrayList<byte[][]> keys) {
        key1 = keys.get(0);
        key2 = keys.get(1);
    }

    public static ArrayList<byte[][]> FileToBlock(File file) throws IOException {

        //empty array if not success..



            int j, k;


            Path path = Paths.get(file.getPath());

            byte[] data = Files.readAllBytes(path);
            int len = data.length ;

            ArrayList<byte[][]> Matrices = new ArrayList<byte[][]>();
            for (j = 0; j < len; j+=16) {
                byte[] subArray = Arrays.copyOfRange(data, j, j + 16);
                byte[][] ToInsert = new byte[4][4];
                Matrices.add(InsertToMatrix(ToInsert, subArray));

            }


            return Matrices;

        }




    //  16 couples to matrix
    public static byte[][] InsertToMatrix(byte[][] NewByteArray, byte[] OldArray) {
        int k = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                NewByteArray[i][j] = OldArray[k];
                k++;

            }

        }
        return NewByteArray;

    }

    // swap Indexes Function
    public static ArrayList<byte[][]> SwapIndexes(ArrayList<byte[][]> MatList) {





        byte FirstToSwap;
        byte SecToSwap;
        for (int k = 0; k < MatList.size(); k++) {
            byte[][] newMat= new byte[4][4];



            for (int i = 0; i < 4; i++) {

                for (int j = 0; j < 4; j++) {
                    newMat[i][j]=MatList.get(k)[j][i];


                }

            }
            MatList.set(k,newMat);
        }
        return MatList;



    }

    public static void XorFunc(ArrayList<byte[][]> Mats, byte[][] key) throws IOException {


        for (int k = 0; k < Mats.size(); k++) {

            for (int i = 0; i < Mats.get(k).length; i++) {
                for (int j = 0; j < Mats.get(k).length; j++) {
                    byte first = Mats.get(k)[i][j];
                     byte sec = key[i][j];
                     byte ToXor =( byte)( first ^ sec);
                    Mats.get(k)[i][j] = (byte) ToXor;

                }

            }

        }

    }

    public static ArrayList<byte[][]> Encription( File PlainText, File KeysFile) throws IOException {
        ArrayList<byte[][]> PlaintextBlocks = FileToBlock(PlainText);
        ArrayList<byte[][]> KeysBlocks = FileToBlock(KeysFile);


        // First iteration key1
        PlaintextBlocks=Enc.SwapIndexes(PlaintextBlocks);
         XorFunc(PlaintextBlocks,KeysBlocks.get(0));

        // Second Iteration key2
        PlaintextBlocks=Enc.SwapIndexes(PlaintextBlocks);
        XorFunc(PlaintextBlocks,KeysBlocks.get(1));
        return  PlaintextBlocks;



    }
}







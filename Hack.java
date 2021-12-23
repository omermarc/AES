import java.io.IOException;
import java.security.Key;
import java.util.ArrayList;


public class Hack {
    private String key1;


    public static ArrayList<byte[][]>HackKeys(ArrayList<byte[][]>Message, ArrayList<byte[][]>Cypher) throws IOException {
        String key1 = "00000000000000000000000000000000";
        byte[] bytes = key1.getBytes();
        byte[][] keyArray1 = new byte[4][4];
        keyArray1 = Enc.InsertToMatrix(keyArray1, bytes);
        byte[][] key2 = new byte[4][4];
        int i,j;
        byte[][] X= new byte[4][4];

        X=XORforBreak(Message.get(0),Cypher.get(0));
        key2= XORforBreak(X,keyArray1);


        ArrayList<byte[][]> keys= new ArrayList<byte[][]>();
        keys.add(keyArray1);
        keys.add(key2);
        return keys;







    }

    public static byte[][] XORforBreak(byte[][] msg, byte[][] cypher)
    {
        byte[][]result= new byte[4][4];
        for (int i = 0; i < msg.length; i++) {
            for (int j = 0; j < msg.length; j++) {
                byte first = msg[i][j];
                byte sec = cypher[i][j];
                byte ToXor =( byte)( first ^ sec);
                result[i][j] = (byte) ToXor;

            }

        }
        return result;
    }


    public static byte[][] SwapIndexes(byte[][] key) {
        byte[][] newMat= new byte[4][4];
        byte FirstToSwap;
        byte SecToSwap;
            for (int i = 0; i < 4; i++) {

                for (int j = 0; j < 4; j++) {
                    newMat[i][j]=key[j][i];

                }
            }
        return newMat;



    }


}


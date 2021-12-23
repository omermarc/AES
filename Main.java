import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;



public class Main {

    public static void main(String[] args) throws IOException {
        //Get argument from command


        if(args[0].equals("-b"))
        {
            String msgPath= args[2];
            String cypherPath=args[4];
            String OutputPath= args[6];

            File plainTextFile = new File(msgPath);
            File CypherTextFile= new File(cypherPath);

            ArrayList<byte[][]> msg= Enc.FileToBlock(plainTextFile);
            ArrayList<byte[][]> cpr= Enc.FileToBlock(CypherTextFile);
            ArrayList<byte[][]> keys= Hack.HackKeys(msg,cpr);

            Dec.DecToFile(keys,OutputPath);


        }
        if(args[0].equals("-d"))
        {
            String keyPath= args[2];
            String decPath=args[4];
            String OutputPath= args[6];

            File keyFile = new File(keyPath);
            File decFile= new File(decPath);

            ArrayList<byte[][]> cypher= Enc.FileToBlock(decFile);
            ArrayList<byte[][]> keys= Enc.FileToBlock(keyFile);
            Dec dec = new Dec(keys);


            //dec.Decription(cypher);
            Dec.DecToFile(dec.Decription(cypher),OutputPath);


        }

        if(args[0].equals("-e"))
        {
            String keyPath= args[2];
            String encPath=args[4];
            String OutputPath= args[6];

            File keyFile = new File(keyPath);
            File encFile= new File(encPath);

            ArrayList<byte[][]> keys= Enc.FileToBlock(keyFile);

            ArrayList<byte[][]> OutCome= Enc.Encription(encFile,keyFile);
            Dec dec = new Dec(keys);
            Dec.DecToFile(OutCome,OutputPath);


        }


















    }
}

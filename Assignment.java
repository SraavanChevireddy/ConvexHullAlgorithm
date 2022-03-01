import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.filechooser.FileSystemView;
import java.io.FileWriter;

public class Assignment extends JPanel {

    public static void main(String... args){
        Assignment tf = new Assignment();
        tf.showMe();
    }
    
    public void showMe(){
        JFileChooser jf = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        jf.showOpenDialog(Assignment.this);
        File f = jf.getSelectedFile();
        read_contents_and_createPoints(f);
    }
    
    private void read_contents_and_createPoints(File ofFile)
    {        
        try{
            Scanner textScanner = new Scanner(ofFile);
            textScanner.useDelimiter("\n");
            double[][] points  = new double[5][2];
          
            System.out.println("Printing all the input lines");

            int i = 0;
            while(textScanner.hasNextLine()){
                String str_points = textScanner.nextLine();
                String[] splited = str_points.split("\\s+");
                for(int j = 0; j<2;j++){
                    points[i][j] = Double.parseDouble(splited[j]);
                }
                i = i + 1;
            }
            int K = 2;
            System.out.println(points);
            pClosest(points, K);
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }

    }

    static void pClosest(double [][]pts, int k)
{
    int n = pts.length;
    double[] distance = new double[n];
    for(int i = 0; i < n; i++)
    {
        double x = pts[i][0], y = pts[i][1];
        distance[i] = (x * x) + (y * y);
    }
     
    // Find the k-th distance
    double distk = distance[k - 1];
 
    // Print all distances which are
    // smaller than k-th distance
    for(int i = 0; i < n; i++)
    {
        double x = pts[i][0], y = pts[i][1];
        double dist = (x * x) + (y * y);
         
        if (dist <= distk)
            System.out.println("[" + x + ", " + y + "]");
        File outputFile = new File("Output.txt");
        try{
            if(outputFile.createNewFile()){
                System.out.println("File created");
            }else{
                System.out.println("File Already Exists");
            }
            FileWriter writer = new FileWriter(outputFile);
            writer.write(x + ", " + y);
            writer.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
 




}



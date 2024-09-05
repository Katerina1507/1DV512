import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {

    public static void scanDisk(Disk disk, TypeScan typeScan, BufferedWriter writer, ArrayList<Integer>listNumber) throws IOException {
        writer.write(typeScan.name());
        writer.newLine();
        disk.scanning(typeScan,listNumber);
        writer.write(String.valueOf(disk.getTotalMovments()));
        writer.newLine();
        List <Integer> listResult = disk.getListNumber();
        for ( int i =0; i<listResult.size(); i++){
            writer.write(String.valueOf(listResult.get(i)));
            if (i< listResult.size()-1){
                writer.write(",");
            }
            else{
                writer.newLine();
            }
        }
    }

    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the name of your file");
        String result = scanner.next();
        String fileName;

        if (result.endsWith(".txt")) {
            fileName = result;
        } else {
            fileName = result + ".txt";
        }

        BufferedReader reader = new BufferedReader( new FileReader(fileName));
        int cylinder = Integer.parseInt(reader.readLine());
        int startNumber = Integer.parseInt(reader.readLine());
        int direction= Integer.parseInt(reader.readLine());
        TypeDirection typeDirection=TypeDirection.values()[direction];
        String s = reader.readLine();
        reader.close();
        String [] a = s.split(",");
        ArrayList<Integer> listNumber = new ArrayList<>();
        for (int i=0; i<a.length;i++){
            listNumber.add(Integer.parseInt(a[i]));
        }
        Disk disk = new Disk(cylinder, typeDirection, startNumber);
        String fileNameResult = fileName.substring(0, fileName.length() - 4) + ".out";
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileNameResult));
        scanDisk(disk, TypeScan.FCFS, writer, listNumber);
        scanDisk(disk, TypeScan.SCAN, writer, listNumber);
        scanDisk(disk,TypeScan.CSCAN, writer, listNumber );
        writer.close();

    }
}

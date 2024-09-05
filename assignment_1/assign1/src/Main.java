import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {

    static void simulation(ArrayList<String> listLines, BufferedWriter writer, int maxSize) throws IOException {

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

        BufferedReader reader = new BufferedReader(new FileReader(fileName));

        int maxSize = Integer.parseInt(reader.readLine());// max size of memory, 1000
        ArrayList<String> listLines = new ArrayList<>();
        String line1;
        while ((line1 = reader.readLine()) != null) {
            listLines.add(line1);
        }
        reader.close();

        String fileNameResult = fileName.substring(0, fileName.length() - 4) + ".out";
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileNameResult));
        Memory memory1 = new Memory(maxSize, AllocateStrategy.First);
        Memory memory2 = new Memory(maxSize, AllocateStrategy.Best);
        Memory memory3 = new Memory(maxSize, AllocateStrategy.Worst);

        ArrayList<Memory> list = new ArrayList<>();
        list.add(memory1);
        list.add(memory2);
        list.add(memory3);
        int counterOutFiles=1;

        int numberLine=0;
        for (String line : listLines) {
            numberLine++;
            String[] a = line.split(";");
            if(a[0].equals("O")){
                String outFileName=fileNameResult+counterOutFiles;
                BufferedWriter writer1=new BufferedWriter(new FileWriter(outFileName));
                for(Memory memory: list){
                    writer1.write(memory.collectInformation());
                }
                writer1.close();
                counterOutFiles++;
                continue;
            }

            for (Memory memory : list) {

                switch (a[0]) {
                    case "A": {
                        int id = Integer.parseInt(a[1]);
                        int size = Integer.parseInt(a[2]);
                        if (memory.allocate(id, size) == false) {
                            AllocateError allocateError = new AllocateError(numberLine, memory.findBigBlock(), id);
                            memory.addError(allocateError);


                        }
                        break;
                    }
                    case "D": {
                        int id = Integer.parseInt(a[1]);
                        if (memory.deallocate(id)==false){
                            int reason;
                            if(memory.checkAttemptAllocate(id)){
                                reason=1;
                            }else{
                                reason=0;
                            }
                            DeallocateError deallocateError = new DeallocateError(numberLine, reason);
                            memory.addError(deallocateError);

                        }


                        break;
                    }
                    case "C":
                        memory.compact();
                        break;
                    case "O":
                        break;
                }
            }
        }
        for(Memory memory: list){
            writer.write(memory.collectInformation());
        }
        writer.close();
    }
}

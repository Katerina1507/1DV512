import java.util.ArrayList;

import static java.lang.Math.round;

public class Memory {
    private ArrayList<Cell>cells = new ArrayList<>();
    private int maxSize;
    private AllocateStrategy strategy;
    private ArrayList<Error>errors=new ArrayList<>();


    public Memory(int maxSize, AllocateStrategy strategy) {
        this.maxSize = maxSize;
        Cell cell = new Cell(0, maxSize, -1);// free block
        cell.setFree(true);
        cells.add(cell);
        this.strategy=strategy;
    }


    public boolean allocate(int id, int sizeBlock){
        if(strategy==AllocateStrategy.First){
            return allocateFirst(id, sizeBlock);
        }
        else if (strategy==AllocateStrategy.Best){
            return allocateBest(id, sizeBlock);
        }
        else {
            return allocateWorst(id, sizeBlock);
        }
    }

    public boolean allocateFirst (int id, int sizeBlock){
        int start=0;// sum: adding address, the start of the block

        for (int i=0; i< cells.size(); i++){
            if(cells.get(i).isFree() && cells.get(i).getSize()>=sizeBlock){
                if (cells.get(i).getSize()== sizeBlock){
                    cells.get(i).setFree(false);// it is occupied
                    cells.get(i).setId(id);// setting id
                }
                else{
                    Cell cell = new Cell(start, sizeBlock, id);
                    cells.get(i).addSize(-sizeBlock); // reducing the size of the free cell
                    cells.add(i,cell);

                }
                // free cell, get start, get finish 899 + 1 = 900
                if(i< cells.size()-1){
                    cells.get(i+1).setStart(cells.get(i).getFinish()+1);
                }
                return true;
            }
            start+=cells.get(i).getSize();//next address where the next cell starts
        }
        return false;
    }



    public boolean allocateBest(int id, int sizeBlock){
        int numberOfCell=-1;// !!
        int minEnoughSize=Integer.MAX_VALUE;
        for (int i=0; i< cells.size(); i++) {
            if (cells.get(i).isFree() && cells.get(i).getSize() >= sizeBlock) {
                if (cells.get(i).getSize() < minEnoughSize) {
                    minEnoughSize = cells.get(i).getSize();// remember the size of the cell
                    numberOfCell = i;// remember the number of the cell
                }
            }
        }
        // cannot find free place
        if(numberOfCell==-1 ){
            return false;
        }
        else{
            if (cells.get(numberOfCell).getSize()== sizeBlock){
                cells.get(numberOfCell).setFree(false);// saying it is busy
                cells.get(numberOfCell).setId(id);// setting id
            }
            else{
                int start =0;
                if(numberOfCell>0){
                    start=cells.get(numberOfCell-1).getFinish()+1;// getting address of our cells via previous
                }
                Cell cell = new Cell(start, sizeBlock, id);
                cells.get(numberOfCell).addSize(-sizeBlock); // size reduction free cells
                cells.add(numberOfCell,cell);

            }
            // the free cell was set to start, get finish example 899 + 1 = 900
            if(numberOfCell< cells.size()-1){
                cells.get(numberOfCell+1).setStart(cells.get(numberOfCell).getFinish()+1);
            }
        }
        return true;
    }



    public boolean allocateWorst(int id, int sizeBlock) {
        int numberOfCell = -1;
        int maxSize = Integer.MIN_VALUE;
        for (int i = 0; i < cells.size(); i++) {
            if (cells.get(i).isFree() && cells.get(i).getSize() >= sizeBlock) {
                if(cells.get(i).getSize()>maxSize){
                    maxSize = cells.get(i).getSize();// remember size of the cell
                    numberOfCell = i;// remember number of the cell
                }
            }
        }
        if(numberOfCell==-1 ){
            return false;
        }
        else{
            if (cells.get(numberOfCell).getSize()== sizeBlock){
                cells.get(numberOfCell).setFree(false);// saying that it is busy
                cells.get(numberOfCell).setId(id);// setting id
            }
            else{
                int start =0;
                if(numberOfCell>0){
                    start=cells.get(numberOfCell-1).getFinish()+1;// getting address our cells via previous cell
                }
                Cell cell = new Cell(start, sizeBlock, id);
                cells.get(numberOfCell).addSize(-sizeBlock); // reducing the size of free cells
                cells.add(numberOfCell,cell);

            }
            // free cell: getting start, get finish, example 899 + 1 = 900
            if(numberOfCell< cells.size()-1){
                cells.get(numberOfCell+1).setStart(cells.get(numberOfCell).getFinish()+1);
            }
        }
        return true;

    }



    public boolean checkAttemptAllocate(int id){
        for (Error error :errors) {
            if(error instanceof AllocateError&& ((AllocateError) error).getId()==id){
                return true;
            }

        }
        return false;

    }


    public boolean deallocate (int id){
        for (int i=0; i< cells.size(); i++){
            if(cells.get(i).getId()==id){
                cells.get(i).setFree(true);
                cells.get(i).setId(-1);
                // check on the right, if free, take the size, destroy this cell
                if(i< cells.size()-1 && cells.get(i+1).isFree()){
                    cells.get(i).addSize(cells.get(i+1).getSize());
                    cells.remove(i+1);
                }
                if(i>0 && cells.get(i-1).isFree()){
                    cells.get(i-1).addSize(cells.get(i).getSize());
                    cells.remove(i);
                }
                return true;
            }
        }
        return false;
    }

    // adding error to the list

    public void addError (Error error){
        errors.add(error);
    }


     public int findBigBlock () {
         int largetsFreeMemory = 0;

         for (int i = 0; i < cells.size(); i++) {
             if (cells.get(i).isFree() == true) {
                 if (cells.get(i).getSize() > largetsFreeMemory) {
                     largetsFreeMemory = cells.get(i).getSize();
                 }
             }
         }
         return largetsFreeMemory;
     }



    public String collectInformation(){
        String result ="";
        if (strategy==AllocateStrategy.First){
            result="First fit\n";

        }
        else if (strategy==AllocateStrategy.Best){
            result="Best fit\n";
        }
        else {
            result="Worst fit\n";
        }

        String allocateResult="Allocated blocks\n";
        String freeResult = "Free blocks\n";
        int totalFreeMemory=0;
        int largetsFreeMemory=0;

        for (int i=0; i< cells.size(); i++){
            if(cells.get(i).isFree()==false){
                allocateResult+=cells.get(i).getId()+";" +cells.get(i).getStart()+";"+cells.get(i).getFinish()+"\n";
            }
            else{
                freeResult+=cells.get(i).getStart()+";"+cells.get(i).getFinish()+"\n";
                totalFreeMemory+=cells.get(i).getSize();
                if(cells.get(i).getSize()> largetsFreeMemory){
                    largetsFreeMemory=cells.get(i).getSize();
                }
            }

        }
        double fragmentation=1-(double)largetsFreeMemory/totalFreeMemory;


        result+=allocateResult+freeResult+"Fragmentation\n"+String.format("%.6f",fragmentation)+"\n" ;
        result+="Errors:\n ";
        if(errors.size()==0){
            result+="None \n ";
        }
        else{
            for (Error error:errors) {
                result+=error.toString()+"\n";

            }
        }
        return result;

    }


    public int getTotalMemory(){
        int totalFreeMemory=0;
        for (int i=0; i< cells.size(); i++){
            if(cells.get(i).isFree()){
                totalFreeMemory+=cells.get(i).getSize();

            }

        }
        return totalFreeMemory;

    }


    public void compact (){
        for (int i=0; i< cells.size()-1; i++){
            if(cells.get(i).isFree()){
                int size = cells.get(i).getSize();// get size
                Cell lastCell = cells.get(cells.size()-1);// get size of the last cell
                if(lastCell.isFree()){
                    lastCell.addSize(size);

                }
                else {
                    // create new cell and add to the end of the list
                    Cell cell = new Cell(lastCell.getStart() + lastCell.getSize(), size, -1);// start, size, id
                    cell.setFree(true);
                    cells.add(cell);// add to the list this cell
                }
                for (int g=i+1; g< cells.size(); g++){
                    cells.get(g).setStart(cells.get(g).getStart()-size);// shift to the left, that is  removed by 100, then shifted by 100 to the left
                }
                cells.remove(i);
                i--;
            }
        }
    }
}

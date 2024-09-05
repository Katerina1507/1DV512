public class Cell {

    private int start;
    private int size;
    private int id;
    private boolean free;



    public Cell(int start, int size,int id) {
        this.start = start;
        this.size = size;
        free = false;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public boolean isFree() {
        return free;
    }

    public void setFree(boolean free) {
        this.free = free;
    }

    public void addSize(int size){
        this.size += size; // increasing the size of free cells
    }

    public int getFinish (){
        return start+size-1;// starting at 100, finishing 199

    }
}

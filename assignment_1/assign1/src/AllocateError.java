public class AllocateError extends Error {

    private int maxBlock;
    private int id;

    public AllocateError(int numberofString, int maxBlock, int id) {
        super(numberofString);
        this.maxBlock=maxBlock;
        this.id = id;
    }

    @Override
    public String getInstruction() {
        return "A";
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return getInstruction()+ ";" + getNumberofString() + ";" + maxBlock;
    }
}

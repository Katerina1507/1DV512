public class DeallocateError extends Error{

    private int reasonError;

    public DeallocateError(int numberofString, int reasonError) {
        super(numberofString);
        this.reasonError=reasonError;
    }

    @Override
    public String getInstruction() {
        return "D";
    }

    public String toString() {
        return getInstruction()+ ";" + getNumberofString() + ";" + reasonError;
    }
}

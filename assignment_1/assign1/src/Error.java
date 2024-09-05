public abstract  class  Error {
    private int numberofString;

    public Error(int numberofString) {
        this.numberofString = numberofString;
    }

    public int getNumberofString() {
        return numberofString;
    }

    public abstract String getInstruction();



}

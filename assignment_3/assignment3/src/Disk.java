import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;

public class Disk {
    private int cylinders;
    private int totalMovments;
    private ArrayList <Integer> listNumber;
    private TypeDirection direction;
    private int startNumber;


    public Disk(int cylinders, TypeDirection direction,int startNumber ) {
        this.cylinders = cylinders;
        listNumber = new ArrayList<>();
        this.direction=direction;
        this.startNumber=startNumber;

    }


    public int getTotalMovments() {
        return totalMovments;
    }

    public ArrayList<Integer> getListNumber() {
        return listNumber;
    }

    public void scanning(TypeScan typeScan, ArrayList<Integer>enterListNumber){
        listNumber.clear();
        if(typeScan==TypeScan.FCFS){
            fsfc(enterListNumber);
        }
        else if (typeScan == TypeScan.SCAN)
        {
            scan(enterListNumber);
        }
        else {
            cscan(enterListNumber);
        }
        calcMovment();

    }


    private void calcMovment(){
        totalMovments = 0;
        for (int i=0; i<listNumber.size()-1; i++){
            totalMovments+=Math.abs(listNumber.get(i+1)-listNumber.get(i));
        }
    }

    private void scan (ArrayList<Integer>enterListNumber){

        Vector<Integer> left = new Vector<Integer>(),
                right = new Vector<Integer>();


        if (direction == TypeDirection.Left)
            left.add(0);
        else if (direction == TypeDirection.Right)
            right.add(cylinders - 1);
        listNumber.add(startNumber);
        for (int i = 0; i < enterListNumber.size(); i++)
        {
            if (enterListNumber.get(i) < startNumber)
                left.add(enterListNumber.get(i));
            if (enterListNumber.get(i) > startNumber)
                right.add(enterListNumber.get(i));
        }

        // sorting left and right vectors
        Collections.sort(left);
        Collections.sort(right);

        // run the while loop two times.
        // one by one scanning right
        // and left of the head
        for (int k =0; k<2; k ++)
        {
            if (direction == TypeDirection.Left)
            {
                for (int i = left.size() - 1; i >= 0; i--)
                {

                    listNumber.add(left.get(i));

                }
                direction = TypeDirection.Right;
            }
            else if (direction == TypeDirection.Right)
            {
                for (int i = 0; i < right.size(); i++)
                {
                    listNumber.add(right.get(i));
                }
                direction = TypeDirection.Left;
            }
        }

    }


    private void cscan (ArrayList<Integer>enterListNumber){
        listNumber.add(startNumber);
        Vector<Integer> left = new Vector<Integer>();
        Vector<Integer> right = new Vector<Integer>();


        left.add(0);
        right.add(cylinders - 1);

        for (int i = 0; i < enterListNumber.size(); i++) {
            if (enterListNumber.get(i) < startNumber)
                left.add(enterListNumber.get(i) );
            if (enterListNumber.get(i)  > startNumber)
                right.add(enterListNumber.get(i) );
        }

        Collections.sort(left);
        Collections.sort(right);

        // First service the requests
        // on the right side of the
        // head.
        for (int i = 0; i < right.size(); i++) {


            // Appending current track to seek sequence
            listNumber.add(right.get(i));


        }


        // Now service the requests again
        // which are left.
        for (int i = 0; i < left.size(); i++) {
          listNumber.add(left.get(i));
        }

    }
    private void fsfc(ArrayList<Integer>enterListNumber){
        listNumber.add(startNumber);
        for( int i=0; i< enterListNumber.size(); i++){
            listNumber.add(enterListNumber.get(i));

        }

    }
}

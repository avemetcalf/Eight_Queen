
public class EightQueen {

	 private int row;
	    private int column;

	    //Constructors
	    public EightQueen(int row, int column) {
	        this.row = row;
	        this.column = column;
	    }
	    

	    public void move () {
	        row++;
	    }
	    

	    public boolean ifConflict(EightQueen queen){
	        
	        if(row == queen.getRow() || column == queen.getColumn()){
	            return true;
	            }
	           
	        else if(Math.abs(column-queen.getColumn()) == Math.abs(row-queen.getRow())){
	            return true;
	            }
	        else{
	            return false;
	        }
	    }
	    

	    public int getRow() {
	        return row;
	    }


	    public int getColumn() {
	        return column;
	    }
	    
	
}

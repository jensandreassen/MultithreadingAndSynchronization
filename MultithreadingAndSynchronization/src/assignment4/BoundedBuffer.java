package assignment4;
/**
 * Synchronized buffer for writer-reader problem. 
 * @author Jens Andreassen
 *
 */
public class BoundedBuffer {
	private String[] strArr;
	private BufferStatus[] status;

	private int size;
	private int writePos;
	private int readPos;
	private int findPos;
	private String find;
	private String replace;

	/**
	 * Constructor
	 * @param size size of buffer
	 * @param find string to find and replace
	 * @param replace replacement String
	 */
	public BoundedBuffer(int size, String find, String replace) {//Constructor
		this.size = size;
		this.strArr = new String[size];
		this.status = new BufferStatus[size];
		this.find = find;
		this.replace = replace;
		for(int i=0; i<status.length; i++) {
			status[i] = BufferStatus.Empty;
		}
	}
	/**
	 * Modify-method, called by modifierThread. modifies string in buffer if it matches find-string
	 * @return true if end of line
	 */
	public synchronized Boolean modify() {
		while(status[findPos] != BufferStatus.New) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(find.length()>0) {
			int res = stringSearch(find, strArr[findPos]);
			if (res>=0) {
				StringBuilder string = new StringBuilder();
				string.append(strArr[findPos].substring(0, res) + 
						replace + 
						strArr[findPos].substring(res+find.length(), strArr[findPos].length()));
				strArr[findPos] = string.toString();
			}
		}
		status[findPos] = BufferStatus.Checked;
		boolean end = strArr[findPos].contains("*end*");
		findPos = (findPos + 1) % size;
		notifyAll();
		return end;
		
	}
	/**
	 * Bruteforce string serach algoritm
	 * @param pat to find
	 * @param txt text to search
	 * @return pos of pattern or -1
	 */
	private static int stringSearch(String pat, String txt) {
		int m = pat.length();
		int n = txt.length();
		for(int i =0; i<=n-m;i++) {
			int j;
			for(j=0; j<m;j++) {
				if(txt.charAt(i+j) != pat.charAt(j)) {
					break;
				}
			}
			if(j==m) { 
				return i;
			}
		}
		return -1; 
	}
	/**
	 * Reads next location in array with text
	 * @return
	 */
	public synchronized String readData() { //Anropas av readern
		while(status[readPos] != BufferStatus.Checked) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		String res = strArr[readPos];
		status[readPos] = BufferStatus.Empty;
		readPos = (readPos + 1) % size;
		notifyAll();
		return res;
	}
	/**
	 * puts string in parameter into buffer.
	 * @param str
	 */
	public synchronized void writeData(String str) {
		while(status[writePos] != BufferStatus.Empty) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		strArr[writePos] = str;
		status[writePos] = BufferStatus.New;
		writePos = (writePos + 1) % size;
		notifyAll();
	}
}

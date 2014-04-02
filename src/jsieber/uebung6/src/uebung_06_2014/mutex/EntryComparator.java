package uebung_06_2014.mutex;

import java.util.Comparator;

class EntryComparator implements Comparator<Entry> {
	//Returns a negative integer, zero, or a positive integer 
	// as this object is less than, equal to, or greater than the first object. 

	public int compare(Entry e1, Entry e2) {
		if (e1.getProcID()<e2.getProcID()) {
			return -1;
			}
		else if(e1.getProcID()>e2.getProcID()) {
					return +1;
				} 
		// == : check logical clock
		if(e1.getLogicalClock()<e2.getLogicalClock()){
			return -1;
		} else if(e1.getLogicalClock()>e2.getLogicalClock()){
			return 1;
		}
		return 0;
	}

}

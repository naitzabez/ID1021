import java.util.Random;

public class PathQueue {
	private Path[] array;
	private int k = 0;
	public PathQueue(int size) {
		array = new Path[size];
	}
	public int getK() {
		return k;
	}
	public boolean exists(City c) {
		for(int i = 0; i < k; i++) {
			if(array[i].city == c) {
				return true;
			}
		}return false;
	}
	public Integer findPath(int ind, Integer id) {
		if(array[ind].city.id == id) {
			return ind;
		}else {
			int li = leftInc(ind);
			if(li < k) {
				findPath(leftInc(ind),id);
			}
			int ri = rightInc(ind);
			if(ri < k) {
				findPath(rightInc(ind),id);
			}
			return ind;
		}
	}
	public void bubble(Path temp) {
		if(array[0] == null) {
			array[0] = temp;
			array[0].index = 0;
		}else {
			int index = k;
			//System.out.println(temp.city.name + " index: " + k);
			array[index] = temp;
			while(array[parentIndex(index)].dist > array[index].dist) {
				swap(array, index, parentIndex(index));
				index = parentIndex(index);
				if(index == 0) {
					break;
				}
			}
		}
		k +=1;
	}
	public Path getIndex(int index) {
		return array[index];
	}
	public Path sink() {
		if(k == 0 ) {
			return null;
		}
		Path ret = array[0];
		k -=1;
		array[0] = array[k];
		array[k] = null;
		int index = 0;
		int index2 = 0;
		while(k > leftInc(index) && k > rightInc(index)) {
			
			if(array[leftInc(index)] != null && array[rightInc(index)] != null) {
				if(array[index].dist > array[leftInc(index)].dist && array[index].dist > array[rightInc(index)].dist) {
					index2 = index;
					index = array[leftInc(index)].dist < array[rightInc(index)].dist ? leftInc(index) : rightInc(index);
					swap(array,index2,index);
					continue;
				}
			}
			if(array[leftInc(index)] != null ) {
				if(array[index].dist > array[leftInc(index)].dist) {
					index2 = index;
					index = leftInc(index);
					swap(array,index2,index);
					continue;
				}
			}
			if(array[rightInc(index)] != null) {
				if(array[index].dist > array[rightInc(index)].dist) {
					index2 = index;
					index = rightInc(index);
					swap(array,index2,index);
					continue;
				}
			}
			
			break;
		}
		array[0].index = 0;
		ret.index = null;
		return ret;
	}
	public Path sinkIndex(int n) {
		if(k == 0 ) {
			return null;
		}
		Path ret = array[n];
		k -=1;
		array[n] = array[k];
		array[k] = null;
		int index = 0;
		int index2 = 0;
		while(k > leftInc(index) && k > rightInc(index)) {
			
			if(array[leftInc(index)] != null && array[rightInc(index)] != null) {
				if(array[index].dist > array[leftInc(index)].dist && array[index].dist > array[rightInc(index)].dist) {
					index2 = index;
					index = array[leftInc(index)].dist < array[rightInc(index)].dist ? leftInc(index) : rightInc(index);
					swap(array,index2,index);
					continue;
				}
			}
			if(array[leftInc(index)] != null ) {
				if(array[index].dist > array[leftInc(index)].dist) {
					index2 = index;
					index = leftInc(index);
					swap(array,index2,index);
					continue;
				}
			}
			if(array[rightInc(index)] != null) {
				if(array[index].dist > array[rightInc(index)].dist) {
					index2 = index;
					index = rightInc(index);
					swap(array,index2,index);
					continue;
				}
			}
			
			break;
		}
		array[n].index = n;
		ret.index = null;
		return ret;
	}
	public void push(Integer inc) {
		if(k == 0 ) {
			return;
		}
		array[0].dist += inc;
		int index = 0;
		int index2 = 0;
		while(k > leftInc(index) && k > rightInc(index)) {
			
			if(array[leftInc(index)] != null && array[rightInc(index)] != null) {
				if(array[index].dist > array[leftInc(index)].dist && array[index].dist > array[rightInc(index)].dist) {
					index2 = index;
					index = array[leftInc(index)].dist < array[rightInc(index)].dist ? leftInc(index) : rightInc(index);
					swap(array,index2,index);
					continue;
				}
			}
			if(array[leftInc(index)] != null ) {
				if(array[index].dist > array[leftInc(index)].dist) {
					index2 = index;
					index = leftInc(index);
					swap(array,index2,index);
					continue;
				}
			}
			if(array[rightInc(index)] != null) {
				if(array[index].dist > array[rightInc(index)].dist) {
					index2 = index;
					index = rightInc(index);
					swap(array,index2,index);
					continue;
				}
			}
			
			break;
		}
	}
	public int leftInc(int index) {
		return (index * 2) + 1;
	}
	public int rightInc(int index) {
		return (index * 2) + 2;
	}
	public int parentIndex(int position) {
		if(position % 2 == 0) {
			return  (position - 2) / 2;
		}else {
			return  (position - 1) / 2;
		}
	}
	public void swap(Path[] array, int index1, int index2) {
		Path temp = array[index1];
		array[index1] = array[index2];
		array[index2] = temp;
		array[index1].index = index2;
		array[index2].index = index1;
	}
	
	public void print(int index) {
		System.out.println(index);
		if(array[0] == null || index > k-1) {
			System.out.println("array empty");
			return;
		}
		if(array[leftInc(index)] != null) {
			print(leftInc(index));
		}
		if(array[index].prev != null) {
		System.out.println("# Array @ index: "+ index + " with name: "+ array[index].city.name + " #"
				+ " with distance: " + array[index].dist + " with previous dest: " + array[index].prev.name);
		}else {
			System.out.println("# Array @ index: "+ index + " with name: "+ array[index].city.name + " #"
					+ " with distance: " + array[index].dist + " with previous dest: null"
					+ " with array index: " + array[index].index);
		}
		//System.out.println("# Array @ index: "+ index + " with value: "+ array[index].value + " with saturation: " + array[index].saturation + " #"); //bubble2
		if(array[rightInc(index)] != null) {
			print(rightInc(index));
		}
	}
	public void printTriangle(int index) {
		if(array[index] == null) {
			System.out.println("array empty (trig)");
			return;
		}
		System.out.println("root node: " + array[index].dist);
		if(array[leftInc(index)] != null) {
			System.out.println("left of root node: " + array[leftInc(index)].dist);
		}else {
			System.out.println("left of root node: null");
		}
		if(array[rightInc(index)] != null) {
			System.out.println("right of root node: " + array[rightInc(index)].dist);
		}else {
			System.out.println("right of root node: null");
		}
	}
}

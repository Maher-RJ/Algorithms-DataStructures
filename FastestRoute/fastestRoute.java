public class Test {

	public int fastestRoute(int from[], int to[], int locationA, int locationB) {
		//initializing the step = 1
		int count = 1;
		//checking from 'from' locations
		for(int i = 0; i < from.length; i++) {
			//if to[i] location is equal to starting location that is locationA
			if(to[i] == locationA) {
				//it will check to further locations after the i index location and will find the destination
				for(int j = i+1; j < from.length; j++) {
					//increment the step 
					count++;
					//if destination location is found
					if(to[j] == locationB) {
						count++;
						break;
					}
					//if another starting location is found it will be initialized to step because fastest route is main goal
					else if(to[j] == locationA) {
						count = 1;
						i = j;
						break;
					}
				}
			}
			//if steps are greater than 1 stop the program
			if(count > 1) {
				break;
			}
		}
		//if step = 1 or step = 0 means no route found the make step = -1
		if(count == 1 || count == 0) {
			count = -1;
		}
		return count;
	}

	public static void main(String[] args) {
		int from[] = {0,0,1};
		int to[] = {1,2,3};
		System.out.println(new Test().fastestRoute(from, to,  2, 7));
	}
}
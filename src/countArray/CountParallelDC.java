package countArray;

class SumThread extends java.lang.Thread {
    int lo; // fields for communicating inputs
    int hi;
    int[] arr;
    int ans = 0; // for communicating result

    SumThread(int[] a, int l, int h) {
        lo = l;
        hi = h;
        arr = a;
    }

    public void run() { // overriding, must have this type
        if(hi - lo == 1 ) {
        	if(arr[lo] == 100) {
        		ans = arr[lo];
        	}else {
        		ans = 0;
        	}
        	
        }else {
        	SumThread left = new SumThread(arr,lo,(hi+lo)/2);
        	SumThread right = new SumThread(arr,(hi+lo)/2,hi);
        	left.start();
        	right.start();
        	try {
				left.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	try {
				right.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	ans = left.ans + right.ans ;
        }
    }
}


class CountParallelDC {
    static int sum(int[] arr) throws InterruptedException {
        SumThread t = new SumThread(arr,0,arr.length);
        t.run();
        
        return t.ans/100;
    }
}

class C {
    public static void main(String[] args) {
        int[] data;
        int dsize = 1000;
        int n = (int)(Math.random()*120);
        boolean b = false;
        data = new int[dsize];
        for (int i = 0; i < dsize; i++) {
            data[i] = n;
            n = (int)(Math.random()*120);
        }
        try {
        	 System.out.println("Array have nummber 100 is " + CountParallelDC.sum(data));
        }catch ( Exception e) {
        	
        }
       
    }
}
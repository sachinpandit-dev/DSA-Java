class MedianFinder {

    PriorityQueue<Integer> maxheap;
    PriorityQueue<Integer> minheap;

    public MedianFinder() {

        minheap = new PriorityQueue<>();

        maxheap = new PriorityQueue<>(Collections.reverseOrder());


        
    }
    
    public void addNum(int num) {

        if(maxheap.isEmpty() || num < maxheap.peek()){
            maxheap.add(num);
        }else{
            minheap.add(num);
        }

        if(maxheap.size() > minheap.size() + 1){
            minheap.add(maxheap.poll());
        }else if(minheap.size() > maxheap.size()){
            maxheap.add(minheap.poll());
        }
        
    }
    
    public double findMedian() {

        if(maxheap.size() == minheap.size()){
            return (minheap.peek() + maxheap.peek())/2.0;
        }else{
            return maxheap.peek();
        }
        
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
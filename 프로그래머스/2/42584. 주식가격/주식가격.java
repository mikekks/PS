import java.util.*;

class Stock{
    int pos;
    int price;
    
    public Stock(int pos, int price){
        this.pos = pos;
        this.price = price;
    }
}

class Solution {
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        
        Stack<Stock> st = new Stack<>();
        
        for(int i=0; i<prices.length; i++){
            int curTime = i+1;
            if(!st.isEmpty()){
                while(!st.isEmpty() && st.peek().price > prices[i]){
                    Stock s = st.pop();
                    answer[s.pos] = curTime - (s.pos+1);
                }
            }
            
            st.push(new Stock(i, prices[i]));
        }
        
        while(!st.isEmpty()){
            Stock s = st.pop();
            answer[s.pos] = prices.length - (s.pos+1);
        }
        
        return answer;
    }
}
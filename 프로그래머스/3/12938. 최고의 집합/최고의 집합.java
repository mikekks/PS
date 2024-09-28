class Solution {
    public int[] solution(int n, int s) {
        int[] answer = new int[n];
        int[] tmp = new int[1];
        tmp[0] = -1;
        
        int add = s / n;
        int sub = s % n;
        int up = n - sub;
        
        if(add == 0){
            return tmp;
        }
        
        for(int i=0; i<n; i++){
            answer[i] += add;
            if(i >= up){
                answer[i] += 1;
            }
        }
        
        if(answer[0] == 0){
            return tmp;
        }

        
        return answer;
    }
}
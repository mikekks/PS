class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];
        
        
        // 가로가 더 길다.
        
        for(int i=1; i<=yellow; i++){
            if(yellow % i == 0){
                int w = yellow / i;
                int h = i;
                
                if(((w+2) * (h+2) - w*h) == brown){
                    answer[0] = w+2;
                    answer[1] = h+2;
                    break;
                }
            }
            
            
        }
        
        return answer;
    }
}
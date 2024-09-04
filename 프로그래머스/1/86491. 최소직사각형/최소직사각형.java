class Solution {
    public int solution(int[][] sizes) {
        int answer = 0;
        int m1 = 0, m2 = 0;
        // 가로세로 합쳐서 가장 큰거
        
        for(int i=0; i<sizes.length; i++){
            int tmp = sizes[i][0] > sizes[i][1] ? sizes[i][0] : sizes[i][1];
            m1 = tmp > m1 ? tmp : m1;
        }
        
        
        for(int i=0; i<sizes.length; i++){
            int tmp = sizes[i][0] < sizes[i][1] ? sizes[i][0] : sizes[i][1];
            
            m2 = tmp > m2 ? tmp : m2;
        }
        
        answer = m1 * m2;
        
        return answer;
    }
}
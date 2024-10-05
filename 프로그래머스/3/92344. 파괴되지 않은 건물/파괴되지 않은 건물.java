class Solution {
    public int solution(int[][] board, int[][] skill) {
        int answer = 0;
        int[] t = {-1,1};
        int[][] sum = new int[board.length+1][board[0].length+1];
        
        for(int k=0; k<skill.length; k++){
            int sy=skill[k][1], sx=skill[k][2];
            int ey=skill[k][3], ex=skill[k][4];
            int degree = skill[k][5] * t[skill[k][0]-1];
            
            sum[sy][sx] += degree;
            sum[sy][ex+1] += (-1 * degree);
            sum[ey+1][sx] += (-1 *degree);
            sum[ey+1][ex+1] += degree;
        }
        
        for(int j=0; j<board[0].length; j++){
            for(int i=0; i<board.length; i++){
                sum[i+1][j] += sum[i][j];
            }
        }
        
        for(int i=0; i<board.length; i++){
            for(int j=0; j<board[0].length; j++){
                sum[i][j+1] += sum[i][j];
            }
        }
        
        for(int i=0; i<board.length; i++){
            for(int j=0; j<board[0].length; j++){
                if(board[i][j] + sum[i][j] > 0) answer++;
            }
        }
        
        
        return answer;
    }
}
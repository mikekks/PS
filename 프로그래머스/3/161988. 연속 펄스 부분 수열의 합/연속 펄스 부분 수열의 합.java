class Solution {
    public long solution(int[] sequence) {
        long answer = 0;
        
        // 연속 펄스 부분 수열의 합 중 가장 큰 것을 return
        
        // 양수
        int[] a = new int[sequence.length];
        int[] b = new int[sequence.length];
        int[] op = {-1,1};
        
        for(int i=0; i<sequence.length; i++){
            a[i] = sequence[i] * op[(i+1)%2];  // i=0 -> 1, i=1 -> 0
            b[i] = sequence[i] * op[i%2];  // i=0 -> 0, i=1 -> 1
        }
        
        
        long cMax1 = b[0];
        long gMax1 = b[0];
        
        for(int i=1; i<sequence.length; i++){
            cMax1 = Math.max(b[i], cMax1 + b[i]);
            gMax1 = Math.max(cMax1, gMax1);
        }
        
        long cMax2 = a[0];
        long gMax2 = a[0];
        
        for(int i=1; i<sequence.length; i++){
            cMax2 = Math.max(a[i], cMax2 + a[i]);
            gMax2 = Math.max(cMax2, gMax2);
        }
        
        return Math.max(gMax1, gMax2);
    }
}
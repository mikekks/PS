class Solution {
    public String solution(String s) {
        String answer = null;
        
        String[] split = s.split(" ");
        
        Integer maxValue = - Integer.MAX_VALUE;
        Integer minValue = Integer.MAX_VALUE;
        
        for(String c : split){
            int cur = Integer.valueOf(c);
            maxValue = Math.max(cur, maxValue); 
            minValue = Math.min(cur, minValue); 
        }
        
        return minValue.toString() + " " + maxValue.toString();
    }
}
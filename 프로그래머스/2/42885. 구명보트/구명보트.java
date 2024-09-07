import java.util.*;

public class Solution {
    public int solution(int[] people, int limit) {
        int answer = 0;
        
        // 사람들의 몸무게를 오름차순으로 정렬
        Arrays.sort(people);
        
        int left = 0; // 가장 가벼운 사람을 가리키는 포인터
        int right = people.length - 1; // 가장 무거운 사람을 가리키는 포인터
        
        while (left <= right) {
            // 가장 무거운 사람과 가장 가벼운 사람을 태울 수 있으면 함께 태운다.
            if (people[left] + people[right] <= limit) {
                left++; // 가장 가벼운 사람도 태움
            }
            // 무거운 사람은 항상 보트에 태움
            right--; 
            // 보트 한 대 사용
            answer++;
        }
        
        return answer;
    }
}
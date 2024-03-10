#include <string>
#include <vector>
#include <algorithm>
#include <queue>
#include <cmath>
#include <iostream>

using namespace std;


long long solution(int n, vector<int> times) {
    long long answer = 0;
    
    sort(times.begin(), times.end());
    
    long long min = 1;
    long long max = (long long)times[times.size()-1] * n;
    
    while(min <= max){
        long long mid = (min+max) / 2;
        long long total = mid * n;
        
        long long cur = 0;
        
        for(int i=0; i<times.size(); i++){
            cur += mid / (long long)times[i];
        }
        
        if(cur >= n){
            max = mid - 1;
            answer = mid;
        }
        else{
            min = mid + 1;
        }
    }
    
    return answer;
}

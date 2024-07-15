#include <iostream>
#include <vector>
#include <algorithm>
#include <cmath>
#include <cstring>
#include <queue>

#define MAX 55

using namespace std;

// 19:30 시작 - 20:00 종료

long long N;

long long ans;
vector<long long> arr;
vector<long long> mArr;

bool check[MAX];

int main() {
    cin >> N;
    
    // 50, 1000
    
    // 1. 브루트포스 : 50*49*48
    // 2. 구현
        // -N : 0 또는 -M
        // N : M
        // 묶는게 무조건 이득? : 1인 경우에는 손해
        // 1 2 3 4
    
    for(int i=0; i<N; i++){
        int tmp;
        cin >> tmp;
        arr.push_back(tmp);
    }
   
    sort(arr.begin(), arr.end());
    
    for(int i=0; i<N-1; i++){
        long long cur = arr[i];
        if(cur >= 0) continue;
        if(check[i]) continue;
        
        if(arr[i+1] <= 0){
            mArr.push_back(arr[i] * arr[i+1]);
            check[i] = true;
            check[i+1] = true;
        }
    }
    
    for(int i=arr.size()-1; i>0; i--){
        long long cur = arr[i];
        if(cur <= 1) continue;
        if(check[i]) continue;
        
        if(arr[i-1] > 1){
            mArr.push_back(arr[i] * arr[i-1]);
            check[i] = true;
            check[i-1] = true;
        }
    }
    
    
    for(int i=0; i<N; i++){
        if(check[i]) continue;
        
        ans += arr[i];
    }
    
    for(int i=0; i<mArr.size(); i++){
        ans += mArr[i];
    }
    
    cout << ans << '\n';
    
}

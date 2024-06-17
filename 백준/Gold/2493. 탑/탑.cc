#include <iostream>
#include <vector>
#include <algorithm>
#include <cmath>

#define MAX 500005


using namespace std;

int N;
int dp[MAX];
vector<int> arr;

int main() {

    cin >> N;
    arr.push_back(0);
    
    for(int i=1; i<=N; i++){
        int tmp;
        cin >> tmp;
        arr.push_back(tmp);
    }
    
    
    dp[1] = 0;
    
    for(int i=2; i<=N; i++){
        
        // 이전과 동일한 경우 -> 바로 전 값 그대로
        if(arr[i-1] == arr[i]){
            dp[i] = dp[i-1];
        }
        else if(arr[i-1] > arr[i]){  // 이전보다 작아진 경우 -> 바로 전의 값
            dp[i] = i-1;
        }
        else{  // 이전보다 커진 경우 -> 이전 값이 가리키는 인덱스부터 찾기
            int prev = dp[i-1];
            while(arr[i] > arr[prev] && prev > 0){
                prev--;
            }
            
            if(prev == 0){
                dp[i] = 0;
            }
            else{
                dp[i] = prev;
            }
        }
                
    }
    
    for(int i=1; i<=N; i++){
        cout << dp[i] << " ";
    }
    
    cout << '\n';
}

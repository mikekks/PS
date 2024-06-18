#include <iostream>
#include <vector>
#include <algorithm>
#include <cmath>
#include <cstring>
#include <queue>

#define MAX 10005


using namespace std;

int n, k;
int dp[MAX];
vector<int> arr;

int main() {
    cin >> n >> k;
    
    for(int i=0; i<n; i++){
        int tmp;
        cin >> tmp;
        arr.push_back(tmp);
    }
    
    dp[0] = 0;
    
    for(int i=1; i<=k; i++){
        int v = 987654321;
        
        for(int k=0; k<arr.size(); k++){
            int cur = i-arr[k];
            
            if(cur>=0 && dp[cur] < v){
                v = dp[cur];
            }
        }
        
        dp[i] = v+1;
    }
    
    if(dp[k] == 987654322){
        dp[k] = -1;
    }
    
    cout << dp[k] << '\n';
}

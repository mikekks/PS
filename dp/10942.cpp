#include <cstring>
#include <vector>
#include <algorithm>
#include <queue>
#include <cmath>
#include <iostream>

#define MAX 2005

using namespace std;

int N, M;
int nums[MAX];
bool dp[MAX][MAX];

int main(){
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(NULL);

    cin >> N;
    
    for(int i=1; i<=N; i++){
        cin >> nums[i];
        dp[i][i] = true;
        if(nums[i] == nums[i-1]){
            dp[i-1][i] = true;
        }
    }
    
    for(int i=2; i<=N; i++){
        for(int j=1; j<=N-i; j++){
            if(dp[j+1][j+i-1] && (nums[j] == nums[j+i])){
                dp[j][j+i] = true;
            }
        }
    }
    
    cin >> M;
    
    for(int i=0; i<M; i++){
        int S, E;
        cin >> S >> E;
        
        if(dp[S][E]){
            cout << 1 << '\n';
        }
        else{
            cout << 0 << '\n';
        }
        
    }
    
        
}

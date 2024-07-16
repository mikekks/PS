#include <iostream>
#include <vector>
#include <algorithm>
#include <cmath>
#include <cstring>
#include <queue>

#define MAX 1005

using namespace std;

// 21:20 시작 - 21:55 종료

int N,M;
int ans = 0;
int dp[MAX][MAX][3];  // y, x, 정사각형이 됐는지 여부

int map[MAX][MAX];

int main() {
    cin >> N >> M;
    
    for(int i=1; i<=N; i++){
        string tmp;
        cin >> tmp;
        for(int j=0; j<tmp.size(); j++){
            map[i][j+1] = tmp[j] - '0';
        }
    }
    
    for(int i=1; i<=N; i++){
        for(int j=1; j<=M; j++){
            if(map[i][j] == 0){
                dp[i][j][0] = 0;
                dp[i][j][1] = 0;
            }
            else{
                dp[i][j][0] = dp[i-1][j][0] + map[i][j];
                dp[i][j][1] = dp[i][j-1][1] + map[i][j];
            }
            
            if(dp[i][j][0] != 0 && dp[i][j][1] != 0){
                int minValue = min(dp[i][j][0], dp[i][j][1]);
                int nextValue = dp[i-1][j-1][2] + 1;
                
                if(nextValue == 0){
                    dp[i][j][2] = 1;
                }
                else if(nextValue > minValue){
                    dp[i][j][2] = minValue;
                }
                else{
                    dp[i][j][2] = nextValue;
                }
            }
            
            ans = dp[i][j][2] > ans ? dp[i][j][2] : ans;
            
        }
    }
    

    cout << ans * ans << '\n';
    
}

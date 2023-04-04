// 1h

#include <iostream>
#include <vector>
#include <cstring>

#define MAX 100001

using namespace std;

int T, n;
int s[2][MAX];
int dp[2][MAX];


int check(){
    dp[0][0] = s[0][0];
    dp[1][0] = s[1][0];
    dp[0][1] = s[0][1] + dp[1][0];
    dp[1][1] = s[1][1] + dp[0][0];
     
    for (int i = 2; i < n; i++) {
            dp[0][i] = s[0][i] + max(dp[1][i - 1], dp[1][i - 2]);
            dp[1][i] = s[1][i] + max(dp[0][i - 1], dp[0][i - 2]);
        }
    
    return max(dp[0][n - 1], dp[1][n - 1]);
}

int main(){
    scanf("%d", &T);
    
    for(int i=0; i<T; i++){
        scanf("%d", &n);
        memset(dp, '\0', sizeof(dp));
        
        for(int j=0; j<2; j++){
            for(int k=0; k<n; k++){
                scanf("%d", &s[j][k]);
            }
        }
        int r = check();
        printf("%d\n", r);
    }
}

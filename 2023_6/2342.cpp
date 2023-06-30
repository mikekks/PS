#include <iostream>
#include <queue>
#include <algorithm>
#include <cstring>

#define MAX 100001

using namespace::std;

vector<int> v;

int n;
int dp[5][5][MAX];

int check(int start, int next){
    if(start == 0) return 2;
    if(start == next) return 1;
    if(abs(start - next) == 2) return 4;
    return 3;
}


int solve(int y, int x, int target){
    if(target == n) return 0;
    if(dp[y][x][target] != -1) return dp[y][x][target];

    int left = solve(v[target], x, target + 1) +check(y, v[target]);
    int right = solve(y, v[target], target + 1) + check(x, v[target]);
    return dp[y][x][target] = min(left,right);
}

int main(){
    while(true){
        int num;
        cin >> num;
        
        if(num == 0){
            break;
        }
        v.push_back(num);
    }
    
    memset(dp, -1, sizeof(dp));
    n = v.size();
    
    cout << solve(0, 0, 0) << '\n';
    return 0;
}

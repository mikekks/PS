#include <iostream>
#include <algorithm>
#include <math.h>
#include <queue>
#include <vector>
#include <cstring>

#define MOD 1000000007

using namespace std;

typedef long long ll;

ll N;

vector<vector<ll>> route = {
        {0, 1, 1, 0, 0, 0, 0, 0},
        {1, 0, 1, 1, 0, 0, 0, 0},
        {1, 1, 0, 1, 1, 0, 0, 0},
        {0, 1, 1, 0, 1, 1, 0, 0},
        {0, 0, 1, 1, 0, 1, 0, 1},
        {0, 0, 0, 1, 1, 0, 1, 0},
        {0, 0, 0, 0, 0, 1, 0, 1},
        {0, 0, 0, 0, 1, 0, 1, 0}
};


vector<vector<ll>> mul_matrix(vector<vector<ll>>& v1, vector<vector<ll>>& v2){
    vector<vector<ll>> ret(8, vector<ll>(8));
    
    for(int i=0; i<8; i++){
        for(int j=0; j<8; j++){
            ll tmp = 0;
            for(int k=0; k<8; k++){
                tmp += (v1[i][k] * v2[k][j]);
                tmp %= MOD;
            }
            ret[i][j] = tmp % MOD;
        }
    }
    return ret;
}

vector<vector<ll>> solve(){
    vector<vector<ll>> ans(8, vector<ll>(8));  // 단위 행렬
    for(int i=0; i<8; i++){
        ans[i][i] = 1;
    }
    
    vector<vector<ll>> factor = route;
    
    while(N > 0){
        if(N%2 == 1){
            ans = mul_matrix(ans, factor);
            N--;
        }
        factor = mul_matrix(factor, factor);
        N /= 2;
    }
    
    return ans;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);
    
    cin >> N;
    
    vector<vector<ll>> total = solve();
    
    cout << total[0][0] << '\n';
    
    return 0;
}

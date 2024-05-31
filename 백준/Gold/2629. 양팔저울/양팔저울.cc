#include <iostream>
#include <string>
#include <sstream>
#include <cmath>
#include <vector>
#include <algorithm>
#include <queue>
#include <cstring>

using namespace std;

#define MAX 70


bool ans[MAX];
int N, M;
vector<int> w;
int marble[MAX];
int dp[MAX][15005];

int main() {
    
    cin >> N;
    int total = 0;
    
    w.push_back(-987654321);
    for(int i=1; i<=N; i++){
        int tmp;
        cin >> tmp;
        w.push_back(tmp);
        w.push_back(-tmp);
        total += tmp;
    }
    sort(w.begin(), w.end());
    
    cin >> M;
    for(int i=1; i<=M; i++){
        cin >> marble[i];
    }
    

    
    // dp
    for(int i=1; i<=N*2; i++){
        for(int sum=1; sum<=total*2; sum++){
            int cur = sum-total;
            // 해당 추 하나로 성공하는 경우
            if(cur == w[i]){
                dp[i][sum] = true;
            }
            else if(dp[i-1][sum]){  // 이전 행에서 성공한 경우
                dp[i][sum] = true;
            }
            else if(dp[i-1][sum-w[i]]){  // 뺄셈해서 성공한 경우
                dp[i][sum] = true;
            }
            
        }
    }
    
    
    for(int i=1; i<=M; i++){
        if(dp[N*2][marble[i]+total]){
            cout << "Y";
        }
        else{
            cout << "N";
        }
        
        if(i != M){
            cout << " ";
        }
    }
    
    cout << '\n';
    
    return 0;
}



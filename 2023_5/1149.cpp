#include <iostream>
#include <algorithm>
#include <vector>
#define MAX 1001

using namespace::std;

int N;
int dp[MAX];
int rgb[MAX][3];
int where[MAX];

int result;

int Min(int j, int check1, int check2){
    int p = 10;
    int tmp = 1000000;
    for(int i=0; i<3; i++){
        if(check1 == i || check2 == i){
            continue;
        }
        if(rgb[j][i]<tmp){
            tmp = rgb[j][i];
            p = i;
        }
    }
    return p;
}

int Check(int a, int b, int i, int j, int cur){
    if(a<b){
        where[cur] = i;
        return a;
    }else{
        where[cur] = j;
        return b;
    }
}

int main(){
    scanf("%d", &N);
    
    
    for(int i=1; i<=N; i++){
        cin>>rgb[i][0]>>rgb[i][1]>>rgb[i][2];
        
        rgb[i][0] += min(rgb[i-1][1], rgb[i-1][2]);
        rgb[i][1] += min(rgb[i-1][0], rgb[i-1][2]);
        rgb[i][2] += min(rgb[i-1][0], rgb[i-1][1]);
    }
    
    
//    dp[0] = 0;
//    int tmp = Min(1, 10, 10);
//    dp[1] = rgb[1][tmp];
//    where[1] = tmp;
//
//    for(int i=2; i<=N; i++){
//        int t_index = Min(i, where[i-1], where[i-1]);
//        int c_index = Min(i, 10,10);
//
//        dp[i] = Check(dp[i-1] + rgb[i][t_index], rgb[i][c_index]+ dp[i-2] + rgb[i-1][Min(i-1, c_index, where[i-2])], t_index, c_index, i);
//
//    }
    printf("%d\n", min({rgb[N][0], rgb[N][1], rgb[N][2]}));
}

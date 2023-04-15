#include <iostream>
#include <cstring>
#include <algorithm>
#include <climits>


using namespace::std;

#define MAX 2001

string b[2] = {"BW", "WB"};
string w[2] = {"WB", "BW"};

int board[MAX][MAX];
int Bdp[MAX][MAX];
int Wdp[MAX][MAX];

int N, M, K;

int main(){
    scanf("%d %d %d", &N, &M, &K);
    
    for(int i=1; i<=N; i++){
        string tmp;
        cin >> tmp;
        for(int j=1; j<=M; j++){
            board[i][j] = tmp[j-1];
        }
    }
    
    for(int i=1; i<=N; i++){
        for(int j=1; j<=M; j++){
            if(board[i][j] != b[(i-1)%2][(j-1)%2]){
                Bdp[i][j] = Bdp[i-1][j] + Bdp[i][j-1] - Bdp[i-1][j-1] + 1;
            }else{
                Bdp[i][j] = Bdp[i-1][j] + Bdp[i][j-1] - Bdp[i-1][j-1];
            }
            
            if(board[i][j] != w[(i-1)%2][(j-1)%2]){
                Wdp[i][j] = Wdp[i-1][j] + Wdp[i][j-1] - Wdp[i-1][j-1] + 1;
            }else{
                Wdp[i][j] = Wdp[i-1][j] + Wdp[i][j-1] - Wdp[i-1][j-1];
            }
        }
    }
    
    int tb = 0;
    int tw = 0;
    int min = INT_MAX;
    
    for(int i=0; i<=N-K; i++){
       for(int j=0; j<=M-K; j++){
           tb = Bdp[K+i][K+j] - Bdp[i][K+j] - Bdp[K+i][j] + Bdp[i][j];
           tw = Wdp[K+i][K+j] - Wdp[i][K+j] - Wdp[K+i][j] + Wdp[i][j];
           int tmp;
           tb < tw ? tmp = tb : tmp = tw;
           tmp < min ? min = tmp : 1;
        }
    }
    
    printf("%d\n", min);
}

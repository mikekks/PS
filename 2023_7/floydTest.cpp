#include <iostream>
#include <algorithm>
#include <cstring>

#define MAX 401
#define INF 987654321

using namespace std;

int T;
int N, M;
int R[MAX][MAX];

void floyd(){
    for(int k=1; k<=N; k++){
        for(int i=1; i<=N; i++){
            for(int j=1; j<=N; j++){
                R[i][j] = min(R[i][j], R[i][k] + R[k][j]);
            }
        }
    }
}

int main() {
    
    scanf("%d", &T);
    
    for(int k=1; k<=T; k++){
        scanf("%d %d", &N, &M);
        
        for(int i=1; i<=N; i++){
            for(int j=1; j<=N; j++){
                R[i][j] = INF;
            }
        }
        
        for(int i=0; i<M; i++){
            int X,Y,C;
            scanf("%d %d %d", &X, &Y, &C);
            R[X][Y] = C;
        }
        
        floyd();
        
        int result = INF;
        
        for(int i=1; i<=N; i++){
            for(int j=1; j<=N; j++){
                int tmp = R[i][j] + R[j][i];
                if(tmp < result)
                    result = tmp;
            }
        }
        
        printf("%d %d\n", k, result);
    }
     
   
    
    return 0;
}

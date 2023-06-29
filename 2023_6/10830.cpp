#include <iostream>
#include <queue>

#define MAX 100001
#define INF 987654321
using namespace::std;

long long N, B;
int Matrix[6][6];
int cur[6][6];
int total[6][6];

void oper2(){
    int tmp[6][6];
    
    for(int i=1; i<=N; i++){
        for(int j=1; j<=N; j++){
            tmp[i][j] = 0;
            for(int k=1; k<=N; k++){
                tmp[i][j] += cur[i][k]*cur[k][j]%1000;
            }
        }
    }
    
    for(int i=1; i<=N; i++){
        for(int j=1; j<=N; j++){
            cur[i][j] = tmp[i][j]%1000;
        }
    }
    
}

void oper1(){
    int tmp[6][6];
    
    for(int i=1; i<=N; i++){
        for(int j=1; j<=N; j++){
            tmp[i][j] = 0;
            for(int k=1; k<=N; k++){
                tmp[i][j] += total[i][k]*cur[k][j]%1000;
            }
        }
    }
    
    for(int i=1; i<=N; i++){
        for(int j=1; j<=N; j++){
            total[i][j] = tmp[i][j]%1000;
        }
    }
}

int main(){
    scanf("%lld %lld", &N, &B);
    
    for(int i=1; i<=N; i++){
        for(int j=1; j<=N; j++){
            scanf("%d", &Matrix[i][j]);
            cur[i][j] = Matrix[i][j];
            if(i == j){
                total[i][j] = 1;
            }
            
        }
    }
    
    
    while(B>0){
        if(B%2 == 1){
            oper1();
        }
        oper2();
        B /= 2;
    }
    
    for(int i=1; i<=N; i++){
        for(int j=1; j<=N; j++){
            printf("%d ", total[i][j]);
        }
        printf("\n");
    }
}

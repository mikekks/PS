#include <iostream>
#define MAX 1002

using namespace::std;

int b[MAX];
int Udp[MAX];
int Ddp[MAX];

int N;

int Max(int a, int b){
    return a > b ? a : b;
}

int main(){
    scanf("%d", &N);
    
    for(int i=1; i<=N; i++){
        cin>>b[i];
    }
    
    for(int i=1; i<=N; i++){
        Udp[i] = 1;
        for(int j=i-1; j>0; j--){
            if(b[i]>b[j])
                Udp[i] = Max(Udp[i], Udp[j]+1);
        }
    }
    
    for(int i=N; i>0; i--){
        Ddp[i] = 1;
        for(int j=i+1; j<=N; j++){
            if(b[i]>b[j])
                Ddp[i] = Max(Ddp[i], Ddp[j]+1);
        }
    }
    
    int result = 0;
    for(int i=1; i<=N; i++){
        if(Udp[i] + Ddp[i] > result){
            result = Udp[i] + Ddp[i];
        }
    }
    printf("%d\n", result-1);
    
}

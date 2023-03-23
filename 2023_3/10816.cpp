#include <iostream>
#include <algorithm>
#include <vector>

#define MAX 500001

using namespace::std;

int N, M;
int card[MAX];
int result[MAX];

int s[MAX];
int t1,t2;

void check2(int idx, int start, int end){
    int m = (end+start)/2;
    
    if(start >= end){
        if(s[idx] == card[end]){
            t2 = start+1;
            return;
        }else{
            t2 = start;
            return;
        }
    }
    
    
    if(s[idx] < card[m]){
        check2(idx, start, m);
    }else{
        check2(idx, m+1, end);
    }
    
}

void check1(int idx, int start, int end){
    int m = (end+start)/2;
    
    if(start >= end){
        t1 = start;
        return;
    }
    
    if(s[idx] <= card[m]){
        check1(idx, start, m);
    }else{
        check1(idx, m+1, end);
    }
    
}

int main(){
    cin >> N;
    
    for(int i=0; i<N; i++){
        cin >> card[i];
    }
    
    cin >> M;
    
    for(int i=0; i<M; i++){
        cin >> s[i];
    }
    
    sort(card, card+N);
    
    
    for(int i=0; i<M; i++){
        check1(i, 0, N-1);
        check2(i, 0, N-1);
        printf("%d ", t2-t1);
        t1 = 0;
        t2 = 0;
    }
    
    printf("\n");
}

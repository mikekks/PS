#include <iostream>
#include <algorithm>
#include <vector>

using namespace std;

// 1 10 12 32 47 90 96 99 123 140 402 444 5555


int N, K;

int m[51];

void p(int low, int high){
    
    if(low >= high)
        return ;
    
    int tm[51];
    
    memcpy(tm+low, m+low, 4*(low+high));
    sort(tm+low, tm+high);
    
    int mid = tm[(low+high)/2];
    
    int pivot = mid;
    
    int i = low;
    int j = high;
    
    while(i<j){
        
        while(pivot > m[i]){
            i++;
        }
        while(pivot < m[j]){
            j--;
        }
        
        if(i <= j){
            swap(m[i], m[j]);
          
        }
    }
    p(low, i- 1);
    p(i + 1, high);
}



int main(){
    scanf("%d", &N);
    
    for(int i=0; i<N; i++){
        scanf("%d", &m[i]);
    }
    
    p(0, N-1);
    
    for(int i=0; i<N; i++){
        printf("%d ", m[i]);
    }
}

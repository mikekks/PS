#include <iostream>
#include <algorithm>
#include <vector>

using namespace std;

int N, K;

int m[5000001];

void p(int low, int high){
    
    if(low >= high)
        return ;
    
    int mid = (low+high) / 2;
    int pivot = m[mid];
    
    int i = low;
    int j = high;
    
    
    while(i<=j){
        
        while(pivot > m[i]){
            i++;
        }
        while(pivot < m[j]){
            j--;
        }
        
        if(i <= j){  // exit condition
            swap(m[i], m[j]);
            i++;
            j--;
        }
    }
    p(low, j);
    p(i, high);
}



int main(){
    scanf("%d %d", &N, &K);
    
    for(int i=0; i<N; i++){
        scanf("%d", &m[i]);
    }
    
    p(0, N-1);
    printf("%d\n", m[K-1]);
    
}

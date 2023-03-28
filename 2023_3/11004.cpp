#include <iostream>
#include <algorithm>
#include <vector>

using namespace std;

int N, K;

int m[51];

void p(int low, int high){
    
    if(low >= high)
        return ;
    
    int mid = (low+high) / 2;
    int pivot = m[mid];
    
    int i = low;
    int j = high;
    
    
    while(i<=j){  // exit condition : 피벗 위치 선정 완료
        
        while(pivot > m[i]){
            i++;
        }
        while(pivot < m[j]){
            j--;
        }
        
        if(i <= j){  // 피벗 위치 도달 -> 피벗 위치 조정
            swap(m[i], m[j]);
            i++;
            j--;
        }
    }
    p(low, j);  // 피벗기준 왼쪽, j인 이유는 i-j 역전 때문에
    p(i, high);  // 피벗기준 오른쪽, i인 이유는 i-j 역전 때문에
}



int main(){
    scanf("%d %d", &N, &K);
    
    for(int i=0; i<N; i++){
        scanf("%d", &m[i]);
    }
    
    p(0, N-1);
    printf("%d\n", m[K-1]);
    
}

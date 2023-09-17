#include <iostream>
#include <queue>

using namespace::std;
int N;

int main(){
    scanf("%d", &N);
    priority_queue<int> pq;
    
    for(int i=0; i<N; i++){
        int tmp;
        scanf("%d", &tmp);
        
        if(tmp == 0){
            if(pq.empty()){
                printf("0\n");
            }else{
                printf("%d\n", pq.top());
                pq.pop();
            }
        }else{
            pq.push(tmp);
        }
        
        
    }
    
    
}

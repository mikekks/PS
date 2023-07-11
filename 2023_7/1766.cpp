#include <iostream>
#include <queue>

#define MAX 32001

using namespace std;

int N, M;
int ingree[MAX];
vector<int> Order[MAX];
vector<int> sol;

int main(){
    
    scanf("%d %d", &N, &M);
    
    // 오름차순 큐
    priority_queue<pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>>> q;
    
    for(int i=0; i<M; i++){
        int t1,t2;
        scanf("%d %d", &t1, &t2);
        Order[t1].push_back(t2);
        ingree[t2]++;
    }
    
    // ingree == 0 && outgree 있는 경우 -> q.push
    for(int i=1; i<=N; i++){
        if(ingree[i] == 0){
            q.push({0, i});  // 0은 먼저 푸는 것이 좋은 문제라는 의미
        }
    }
    
    
    
    while(!q.empty()){  // q
        int cur = q.top().second;
        //int depend = q.top().first;
        q.pop();
        
        // solve : 현재 문제와 매핑된 문제들 ingree 감소 -> ingree == 0 검사
        //
        sol.push_back(cur);
        
        for(int i=0; i<Order[cur].size(); i++){
            int next = Order[cur][i];
            ingree[next]--;
            
            if(ingree[next] == 0){  // 먼저 풀 문제를 다 푼 경우
                q.push({0, next});
            }
        }
        
    }
    
    for(int i=0; i<sol.size(); i++){
        printf("%d ", sol[i]);
    }
    printf("\n");
    return 0;
}

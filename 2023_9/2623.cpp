#include <iostream>
#include <vector>
#include <queue>

using namespace::std;

int N, M;

vector<int> child[1001];

int check[1001];
bool suc = true;

vector<int> ans;

void sol(){
    queue<int> q;
    
    for(int i=1; i<=N; i++){
        if(check[i] == 0) q.push(i);
    }
    
    while(!q.empty()){
        int cur = q.front();
        q.pop();
        ans.push_back(cur);
        for(int i=0; i<child[cur].size(); i++){
            int c = child[cur][i];
            check[c]--;
            if(check[c] == 0){
                q.push(c);
            }
        }
    }
    
    if(ans.size() != N) suc = false;
}

int main(){
    scanf("%d %d", &N, &M);
    for(int i=0; i<M; i++){
        int tmp;
        scanf("%d", &tmp);
        vector<int> order;
        for(int j=0; j<tmp; j++){
            int cur;
            scanf("%d", &cur);
            order.push_back(cur);
        }
        for(int j=0; j<tmp; j++){
            for(int k=j+1; k<tmp; k++){
                child[order[j]].push_back(order[k]);
                check[order[k]]++;
            }
        }
    }
    
    sol();
    
    if(!suc){
        printf("0\n");
        return 0;
    }
    
    for(int i=0; i<ans.size(); i++)
        printf("%d\n", ans[i]);
    
    
}

#include <iostream>
#include <queue>
#include <algorithm>
#include <cstring>

#define MAX 100001
#define INF 987654321

using namespace::std;
int N, M;
int Parent[MAX];
vector<pair<int, pair<int, int>>> E;
vector<int> V;
int result;

// 17:20


int find(int n){
    if(n == Parent[n]) return n;
    else return Parent[n] = find(Parent[n]);
}

void Merge(int x, int y){
    x = find(x);
    y = find(y);
    
    if(x==y) return;
    Parent[y] = x;
    
}

bool P_check(int x, int y){
    x = find(x);
    y = find(y);
    
    if(x == y) return false;
    return true;
}

int main(){
    
    scanf("%d %d", &N, &M);
    
    for(int i=0; i<M; i++){
        int t1,t2,w;
        scanf("%d %d %d", &t1, &t2, &w);
        E.push_back({w, {t1,t2}});
    }
    
    for(int i=1; i<=N; i++) Parent[i] = i;
    
    // sorting
    // split
    sort(E.begin(), E.end());
    
    for(int i=0; i<E.size(); i++){
        if(P_check(E[i].second.first, E[i].second.second)){
            Merge(E[i].second.first, E[i].second.second);
            V.push_back(E[i].first);
        }
    }
    
    for(int i=0; i<V.size() - 1; i++){
        result += V[i];
    }
    
    printf("%d\n", result);
    return 0;
}

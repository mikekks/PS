#include <iostream>
#include <string>
#include <sstream>
#include <cmath>
#include <vector>
#include <algorithm>
#include <queue>
#include <cstring>
#include <climits>

using namespace std;

#define MAX 1005
int N, M;
pair<int, int> node[MAX];
double ans = 0;
vector<pair<pair<int, int>, double>> edges;
int p[MAX];

bool cmp(pair<pair<int, int>, double> p1, pair<pair<int, int>, double> p2){
    return p1.second < p2.second;
}

int findParent(int x){
    if(x == p[x]){
        return x;
    }
    return p[x] = findParent(p[x]);
}

void mergeNode(int v1, int v2){
    v1 = findParent(v1);
    v2 = findParent(v2);
    
    if(v1 > v2){
        p[v2] = v1;
    }else{
        p[v1] = v2;
    }
}

int main() {
    cin.tie(0);
    cout.tie(0);
    ios_base::sync_with_stdio(0);
    
    cin >> N >> M;
    
    for(int i=1; i<=N; i++){
        int a,b;
        cin >> a >> b;
        node[i] = {a,b};
    }
    
    for(int i=1; i<=M; i++){
        int a,b;
        cin >> a >> b;
        edges.push_back({{a, b}, 0});
    }
    
    // 엣지 모든 경우의 수 만들기
    for(int i=1; i<=N; i++){
        for(int j=i+1; j<=N; j++){
            if(i == j) continue;
            
            double ydiff = pow((node[i].first - node[j].first), 2);
            double xdiff = pow((node[i].second - node[j].second), 2);
            double cost = sqrt(ydiff + xdiff);
            edges.push_back({{i, j}, cost});
        }
    }
    
    // sort
    sort(edges.begin(), edges.end(), cmp);
    
    for(int i=1; i<=N; i++) p[i] = i;
    
    for(int i=0; i<edges.size(); i++){
        int v1 = edges[i].first.first;
        int v2 = edges[i].first.second;
        double cost = edges[i].second;
        
        if(findParent(v1) != findParent(v2)){
            mergeNode(v1,v2);
            ans += cost;
        }
    }
    
    cout << fixed;
    cout.precision(2);
    cout << ans << '\n';
    
    return 0;
}


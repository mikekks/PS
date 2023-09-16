#include <iostream>
#include <algorithm>
#include <math.h>
#include <queue>
#include <vector>
#include <cstring>

using namespace std;

typedef long long ll;

int N;
vector<pair<double, double>> Map;  // value, key(별 번호)
vector<pair<double, pair<int, int>>> Edge;

int p[101];

double ans;

struct node{
    double diff;
    int v1;
    int v2;
};

struct cmp{
    bool operator()(const node a, const node b){
           return a.diff > b.diff;
       }
};


int find_P(int x){
    if(x == p[x])
        return x;
    return p[x] = find_P(p[x]);
}

void union_p(int a, int b){
    a = find_P(a);
    b = find_P(b);
    
    if(a>b)
        p[b] = a;
    else
        p[a] = b;
}


void solve(){
   
    for(int i=0; i<Map.size(); i++){
        double cx = Map[i].first;
        double cy = Map[i].second;
        
        for(int j=i+1; j<Map.size(); j++){
            double tx = Map[j].first;
            double ty = Map[j].second;
            
            double tmp1 = (cx - tx) * (cx - tx);
            double tmp2 = (cy - ty) * (cy - ty);
            double diff = sqrt(tmp1 + tmp2);
            Edge.push_back({diff, {i, j}});
        }
    }
    
    
    for(int i=0; i<N; i++) p[i] = i;
    
    sort(Edge.begin(), Edge.end());
    
    for(int i=0; i<Edge.size(); i++){
        int v1 = Edge[i].second.first;
        int v2 = Edge[i].second.second;
        double cost = Edge[i].first;
        
        if(find_P(v1) == find_P(v2)) continue;
        
        union_p(v1, v2);
        ans += cost;
    }
    
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);
    
    cin >> N;
    
    for(int i=0; i<N; i++){
        double tx, ty;
        cin >> tx >> ty;
        Map.push_back({tx, ty});
    }
    
    solve();
    
    cout << fixed;
    cout.precision(2);
    cout << ans << '\n';
    
    return 0;
}

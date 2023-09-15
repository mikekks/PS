#include <iostream>
#include <algorithm>
#include <math.h>
#include <queue>
#include <vector>
#include <cstring>

using namespace std;

typedef long long ll;

int N;
vector<pair<int, int>> in_x;  // value, key(행성 번호)
vector<pair<int, int>> in_y;  // value, key(행성 번호)
vector<pair<int, int>> in_z;  // value, key(행성 번호)

int check[100001];
int p[100001];

ll ans;


struct node{
    int diff;
    int v1;  // 행성1
    int v2;  // 행성2
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
    priority_queue<node, vector<node>, cmp> q;
    
    for(int i=1; i<=N; i++) p[i] = i;
    
    for(int i=0; i<N-1; i++){
        int tmp_diff = abs(in_x[i].first - in_x[i+1].first);
        node temp;
        temp.diff = tmp_diff;
        temp.v1 = in_x[i].second;
        temp.v2 = in_x[i+1].second;
        q.push(temp);
    }
    
    for(int i=0; i<N-1; i++){
        int tmp_diff = abs(in_y[i].first - in_y[i+1].first);
        node temp;
        temp.diff = tmp_diff;
        temp.v1 = in_y[i].second;
        temp.v2 = in_y[i+1].second;
        q.push(temp);
    }
    
    for(int i=0; i<N-1; i++){
        int tmp_diff = abs(in_z[i].first - in_z[i+1].first);
        node temp;
        temp.diff = tmp_diff;
        temp.v1 = in_z[i].second;
        temp.v2 = in_z[i+1].second;
        q.push(temp);
    }
    
    
    while(!q.empty()){
        int cur_v1 = q.top().v1;
        int cur_v2 = q.top().v2;
        int cur_diff = q.top().diff;
        q.pop();
        
        if(find_P(cur_v1) == find_P(cur_v2)) continue;
                    
        union_p(cur_v1, cur_v2);
        ans += cur_diff;
    }
    
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);
    
    cin >> N;
    
    for(int i=1; i<=N; i++){
        int tx, ty, tz;
        cin >> tx >> ty >> tz;
        in_x.push_back({tx, i});
        in_y.push_back({ty, i});
        in_z.push_back({tz, i});
    }
  
    sort(in_x.begin(), in_x.end());
    sort(in_y.begin(), in_y.end());
    sort(in_z.begin(), in_z.end());
    
    solve();
    
    
    cout << ans << '\n';
    
    return 0;
}

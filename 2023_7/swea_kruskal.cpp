#include <iostream>
#include <vector>
#include <algorithm>

#define MAX 50001

using namespace std;

int N, M;
int p[MAX];

vector<pair<pair<int, int>, int>> edges;

bool cmp(pair<pair<int, int>, int> p1, pair<pair<int, int>, int> p2){
    return p1.second < p2.second;
}

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

int main(int argc, char** argv)
{
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);
    
    int test_case;
    int T;
    
    //freopen("input.txt", "r", stdin);
    cin>>T;
    /*
       여러 개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
    */
    for(test_case = 1; test_case <= T; ++test_case)
    {
        int total = 0;
        
        edges.clear();
        
        cin >> N;
        cin >> M;
        
        for(int i=0; i<M; i++){
            int s,e,c;
            cin >> s >> e >> c;
            edges.push_back({{s,e}, c});
        }
        
        sort(edges.begin(), edges.end(), cmp);
        
        for(int i=1; i<=N; i++) p[i] = i;
        
        
        for(int i=0; i<edges.size(); i++){
            int v1 = edges[i].first.first;
            int v2 = edges[i].first.second;
            int cost = edges[i].second;
            
            if(find_P(v1) == find_P(v2))
                continue;
            
            union_p(v1, v2);
            total += cost;
        }

        cout << "#" << test_case << " " << total << '\n';
    }
    return 0;//정상종료시 반드시 0을 리턴해야합니다.
}

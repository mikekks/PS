#include <iostream>
#include <algorithm>
#include <cstring>

#define INF INT_MAX
using namespace std;

vector<pair<int,int>> v[501];
int N,M,W;
int dist[501];


bool bellman(){
    memset(dist, 0x7f,sizeof(dist));
    dist[1] = 0;
    bool changed = false;
    
    for(int i = 0; i<N; i++)
    {
        
        for(int j = 1 ; j<= N; j++)
        {
            
            for(int k = 0; k<v[j].size(); k++)
            {
                int n = v[j][k].first;
                int w = v[j][k].second;
               
                if(dist[n] > dist[j]+w)
                {
                    dist[n] = dist[j]+w;
                    if(i == N-1) changed = true;
                }
            }
        }
    }
    return changed;
}
int main(void)
{
    ios::sync_with_stdio(false);
    cin.tie(NULL);
    
    int T;
    cin>> T;
    
    for(int i = 0 ; i< T; i++){
        cin >> N>>M>>W;
        
        for(int a = 0; a<M; a++)
        {
            int s,e,w;
            cin >> s>>e>>w;
            v[s].push_back({e,w});
            v[e].push_back({s,w});
        }
        
        
        for(int b = 0; b<W; b++)
        {
            int s,e,w;
            cin >> s>>e>>w;
            v[s].push_back({e,-w});
        }
        
        
        if(bellman())
        {
            cout << "YES"<<endl;
        }else{
            cout << "NO"<<endl;
        }
        
        
        for(int a = 0; a<=N; a++)
        {
            v[a].clear();
        }
    }
}

#include <string>
#include <vector>
#include <iostream>
#include <queue>

#define MAX 1000005

using namespace std;

int N;
int total;
int ans;

int Early[MAX][2];
bool isVisited[MAX];

vector<int> Map[MAX];


void sol(int v){
    isVisited[v] = true;
    Early[v][1] = 1;
    
    for(int i=0; i<Map[v].size(); i++){
        int child = Map[v][i];
        if(isVisited[Map[v][i]]) continue;
        
        sol(Map[v][i]);
        Early[v][0] += Early[child][1];
        Early[v][1] += min(Early[child][0], Early[child][1]);
    }
}

int main(){
    cin >> N;
    
    for(int i=1; i<N; i++){
        int u,v;
        cin >> u >> v;
        Map[u].push_back(v);
        Map[v].push_back(u);
    }
    
    sol(1);
    
    cout << min(Early[1][0], Early[1][1]) << '\n';
}

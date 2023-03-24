#include <iostream>
#include <queue>
#include <vector>
using namespace::std;

#define MAX 101

vector<int> m[MAX];
int v[MAX];

int N;
int cnt;

int total;


void bfs(){
    queue<int> q;
    
    q.push(1);
    v[1] = 1;
    
    while(!q.empty()){
        int tmp = q.front();
        q.pop();
        
        for(int i=0; i<m[tmp].size(); i++){
            if(v[m[tmp][i]] == 0){
                v[m[tmp][i]] = 1;
                q.push(m[tmp][i]);
                total++;
            }
        }
    }
}

int main(){
    cin >> N;
    
    cin >> cnt;
    
    for(int i=0; i<cnt; i++){
        int t1,t2;
        cin >> t1 >> t2;
        m[t1].push_back(t2);
        m[t2].push_back(t1);
    }
    
    bfs();
    cout << total << '\n';
    
}

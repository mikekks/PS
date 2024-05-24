#include <string>
#include <vector>
#include <iostream>
#include <queue>
#include <algorithm>
#include <cstring>
#include <list>
#include <sstream>
#include <cmath>
#include <map>

using namespace std;

#define MAX 200005

int N, T;

unordered_map<string, int> network_map;
int root[MAX];
int network_count[MAX];

int find(int cur){
    if(root[cur] == cur){
        return cur;
    }
    return root[cur] = find(root[cur]);
}


void merge(int f1, int f2){
    int r1 = find(f1);
    int r2 = find(f2);
    
    if(r1 != r2){
        root[r2] = r1;
        network_count[r1] += network_count[r2];
    }
}

int count(int f1, int f2){
    int r1 = find(f1);
    
    return network_count[r1];
}

int main(){
    ios_base::sync_with_stdio(0);
    cin.tie(NULL);
    cout.tie(NULL);
    cin >> T;
    
    
    for(int t=0; t<T; t++){
        cin >> N;
        int idx = 1;
        network_map.clear();
        for(int i=0; i<MAX; i++){
            root[i] = i;
            network_count[i] = 1;
        }
        
        for(int i=0; i<N; i++){
            string f1, f2;
            cin >> f1 >> f2;
            
            if(network_map[f1] == 0){
                network_map[f1] = idx++;
            }
            
            if(network_map[f2] == 0){
                network_map[f2] = idx++;
            }
            
            merge(network_map[f1], network_map[f2]);
            
            int ans = count(network_map[f1], network_map[f2]);
            cout << ans << '\n';
        }
        
    }
    
}

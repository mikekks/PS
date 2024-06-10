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

#define MAX 2005

int S;
bool isVisit[MAX][MAX];
int ans = 987654321;

struct img{
    int cost;
    int pos;
    int copy;
};

void bfs(){
    queue<img> q;
    q.push({0, 1, 0});
    isVisit[1][0] = true;
    
    while (!q.empty()) {
        int cost = q.front().cost;
        int pos = q.front().pos;
        int copy = q.front().copy;
        q.pop();
        
        if(pos <= 0 || pos > MAX) continue;
        
        if(pos == S){
            ans = cost < ans ? cost : ans;
            return;;
        }
        
        if(!isVisit[pos][pos]){
            isVisit[pos][pos] = true;
            q.push({cost+1, pos, pos});
        }
        
        if(!isVisit[pos-1][copy]){
            isVisit[pos-1][copy] = true;
            q.push({cost+1, pos-1, copy});
        }
        
        if(copy > 0 && pos+copy <MAX){
            if(!isVisit[pos+copy][copy]){
                isVisit[pos+copy][copy] = true;
                q.push({cost+1, pos+copy, copy});
            }
        }
        
    }
}

int main() {
    cin.tie(0);
    cout.tie(0);
    ios_base::sync_with_stdio(0);
    
    cin >> S;
    
    bfs();
    
    cout << ans << '\n';
    
    return 0;
}

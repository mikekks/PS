#include <iostream>
#include <cstdio>
#include <cmath>
#include <string>
#include <vector>
#include <algorithm>
#include <cstring>
#include <queue>

#define MAX 105

using namespace std;

long long ans = 0;
int N, T;
int sy, sx;
int ey, ex;
vector<pair<int, int>> store;
bool isVisit[MAX];  // 해당 편의점을 들리고는 절대 도착할 수 없음을 의미

bool dfs(int y, int x){
    
    if(abs(ey-y) + abs(ex-x) <= 1000){
        cout << "happy" << '\n';
        return true;
    }
    
    bool ret = false;
    
    for(int i=0; i<store.size(); i++){
        if(isVisit[i] == false){
                int d = abs(store[i].first - y) + abs(store[i].second - x);
                if(d <= 1000){
                    isVisit[i] = true;
                    ret = dfs(store[i].first, store[i].second);
                    isVisit[i] = false;
                    if(ret == false){
                        isVisit[i] = true;
                    }
                    else{
                        return true;
                    }
                }
        }
    }
    
    return ret;
}

int main(void)
{
    cin >> T;
    
    while (T--) {
        store.clear();
        memset(isVisit, '\0', sizeof(isVisit));
        
        cin >> N;
        cin >> sy >> sx;
        
        for(int i=0; i<N; i++){
            int ty, tx;
            cin >> ty >> tx;
            store.push_back({ty, tx});
        }
        
        cin >> ey >> ex;
        
        bool ret = dfs(sy, sx);
        
        if(ret == false){
            cout << "sad" << '\n';
        }
        
    }

    return 0;
}

#ifndef _CRT_SECURE_NO_WARNINGS
#define _CRT_SECURE_NO_WARNINGS
#endif

#include <stdio.h>

extern int init(int N, int sCity[], int eCity[], int mCost[]);
extern void add(int sCity, int eCity, int mCost);
extern int cost(int mHub);

/////////////////////////////////////////////////////////////////////////

#define MAX_N 1400
#define CMD_INIT 1
#define CMD_ADD 2
#define CMD_COST 3

static bool run() {
    int q;
    scanf("%d", &q);

    int n;
    int sCityArr[MAX_N], eCityArr[MAX_N], mCostArr[MAX_N];
    int sCity, eCity, mCost, mHub;
    int cmd, ans, ret = 0;
    bool okay = false;

    for (int i = 0; i < q; ++i) {
        scanf("%d", &cmd);
        switch (cmd) {
            case CMD_INIT:
                okay = true;
                scanf("%d", &n);
                for (int j = 0; j < n; ++j) {
                    scanf("%d %d %d", &sCityArr[j], &eCityArr[j], &mCostArr[j]);
                }
                scanf("%d", &ans);
                ret = init(n, sCityArr, eCityArr, mCostArr);
                if (ans != ret)
                    okay = false;
                break;
            case CMD_ADD:
                scanf("%d %d %d", &sCity, &eCity, &mCost);
                add(sCity, eCity, mCost);
                break;
            case CMD_COST:
                scanf("%d %d", &mHub, &ans);
                ret = cost(mHub);
                if (ans != ret)
                    okay = false;
                break;
            default:
                okay = false;
                break;
        }
    }
    return okay;
}

int main() {
    setbuf(stdout, NULL);
    freopen("/Users/songmingyu/sample_input (2).txt", "r", stdin);

    int T, MARK;
    scanf("%d %d", &T, &MARK);

    for (int tc = 1; tc <= T; tc++) {
        int score = run() ? MARK : 0;
        printf("#%d %d\n", tc, score);
    }

    return 0;
}












#include <queue>
#include <vector>
#include <map>
#include <unordered_map>

using namespace std;

multimap<int, pair<int, int>> Map;
multimap<int, pair<int, int>> re_Map;
vector<int> sol;
unordered_map<int, bool> check;


int init(int N, int sCity[], int eCity[], int mCost[]) {
    
    Map.clear();
    sol.clear();
    check.clear();

    int ans = 0;
    
    for(int i=0; i<N; i++){
        if(check.count(sCity[i]) == 0){
            check[sCity[i]] = true;
            ans++;
            sol.push_back(sCity[i]);
        }
        if(check.count(eCity[i]) == 0){
            check[eCity[i]] = true;
            ans++;
            sol.push_back(eCity[i]);
        }
        
        
        Map.insert({sCity[i], {eCity[i], mCost[i]}});
        re_Map.insert({eCity[i], {sCity[i], mCost[i]}});
    }
    
    return ans;
}

void add(int sCity, int eCity, int mCost) {
    Map.insert({sCity, {eCity, mCost}});
    re_Map.insert({eCity, {sCity, mCost}});
    
    if(check.count(sCity) == 0){
        check[sCity] = true;
        sol.push_back(sCity);
    }
    if(check.count(eCity) == 0){
        check[eCity] = true;
        sol.push_back(eCity);
    }
    
    return;
}

int dijk2(int start){
    
    int ans = 0;
    
    priority_queue<pair<int, int>, vector<pair<int, int>>, greater<>> q;   // cost, cur
    
    unordered_map<int, int> D;
    
    q.push({0, start});
    D[start] = 0;
    
    while(!q.empty()){
        int cur = q.top().second;
        int cost = q.top().first;
        q.pop();
        
        if(D[cur] < cost) continue;
        
        multimap<int, pair<int, int>>::iterator iter;
        for(iter = re_Map.equal_range(cur).first; iter != re_Map.equal_range(cur).second; iter++){
            int next = iter->second.first;
            int next_cost = iter->second.second + cost;
            
            if(D.count(next) == 0){  // 없는 경우
                D[next] = next_cost;
                q.push({next_cost, next});
            }
            else{
                if(D[next] > next_cost){
                    D[next] = next_cost;
                    q.push({next_cost, next});
                }
            }
            
        }
        
    }
    
    for(int i=0; i<sol.size(); i++){
        ans += D[sol[i]];
    }
    
    return ans;
    
}

int dijk(int start){

    int ans = 0;
    
    priority_queue<pair<int, int>, vector<pair<int, int>>, greater<>> q;  // cost, cur
    
    unordered_map<int, int> D;
    
    q.push({0, start});
    D[start] = 0;
    
    while(!q.empty()){
        int cur = q.top().second;
        int cost = q.top().first;
        q.pop();
        
        if(D[cur] < cost) continue;
        
        
        multimap<int, pair<int, int>>::iterator iter;
        for(iter = Map.equal_range(cur).first; iter != Map.equal_range(cur).second; iter++){
            int next = iter->second.first;
            int next_cost = iter->second.second + cost;
            
            if(D.count(next) == 0){  // 없는 경우
                D[next] = next_cost;
                q.push({next_cost, next});
            }
            else{
                if(D[next] > next_cost){
                    D[next] = next_cost;
                    q.push({next_cost, next});
                }
            }
            
        }
        
    }
    
    for(int i=0; i<sol.size(); i++){
        ans += D[sol[i]];
    }
    
    return ans;
}




int cost(int mHub) {
    
    int ans = 0;
    ans += dijk(mHub);
    ans += dijk2(mHub);
    
    
    return ans;
}


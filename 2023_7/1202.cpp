#include <iostream>
#include <algorithm>
#include <cstring>
#include <vector>
#include <queue>
#include <set>

#define MAX 300001

using namespace std;

int N, K;
long long total = 0;
vector<pair<int, int>> value;

multiset<int> bag;

int main()
{
    scanf("%d %d", &N, &K);
    
    for(int i=1; i<=N; i++){  // 보석
        int m,v;
        scanf("%d %d", &m, &v);
        value.push_back({-v, m});
    }
    
    for(int i=1; i<=K; i++){  // 가방
        int b;
        scanf("%d", &b);
        bag.insert(b);
    }
    
    sort(value.begin(), value.end());
    
    for(auto i = value.begin(); i != value.end(); i++){
        if(bag.size() == 0) break;
        
        int cur_value = -((*i).first);
        int cur_w = (*i).second;
        
        auto iter = bag.lower_bound(cur_w);
        
        if(iter != bag.end()){
            total += cur_value;
            bag.erase(iter);
        }
        
    }
    
    cout << total << '\n';
    
    return 0;
}
